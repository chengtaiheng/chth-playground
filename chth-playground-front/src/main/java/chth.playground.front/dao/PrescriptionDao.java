package chth.playground.front.dao;

import chth.playground.common.ComplexModel;
import chth.playground.common.Prescription;
import chth.playground.common.form.PrescriptionForm;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author: 程泰恒
 * @date: 2019/5/23 11:46
 */

@Slf4j
@Component
public class PrescriptionDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SqlSession sqlSession;

    /**
     * 创建对象
     */
    public void savePrescription(PrescriptionForm prescription) {
        log.debug("prescription: {}", prescription);
        mongoTemplate.save(prescription);
    }

    /**
     * 通过ID查询
     */
    public Prescription findById(String id) {
        Query query = new Query(Criteria.where("body._id").is(id));
        Prescription one = mongoTemplate.findOne(query, Prescription.class);

        return one;
    }

    /**
     * 查询所有
     */
    public List<Prescription> findList() {

        List<Prescription> list = mongoTemplate.findAll(Prescription.class);

        return list;
    }

    /**
     * 添加复杂模型
     */
    public void saveComplexModel(ComplexModel complexModel) {
        mongoTemplate.save(complexModel);
    }

    public void savePrescriptionBySql(Prescription prescription) {
        sqlSession.insert("Prescription.save", prescription);
    }

    public void saveIcdCodes(String pid, List<String> icdCodes) {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("pid", pid);
        map.put("icdCodes", icdCodes);

        sqlSession.insert("Prescription.saveIcdCodes", map);
    }

    public List<String> findIcdCodesByPid(String pid) {
        return sqlSession.selectList("Prescription.findIcdCodesByPid", pid);
    }

    public Prescription findByPid(String pid) {
        return sqlSession.selectOne("prescription.findById", pid);
    }
}
