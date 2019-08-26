package chth.playground.front.dao.impl;

import chth.playground.common.DrugModel;
import chth.playground.front.dao.DrugDao;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author: 程泰恒
 * @date: 2019/5/31 9:23
 */
@Repository
public class DrugDaoImpl implements DrugDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void saveDrugList(String pid, List<DrugModel> list) {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("pid", pid);
        map.put("drugList", list);

        sqlSession.insert("Drug.save", map);

    }

    @Override
    public List<DrugModel> findByPrescriptionId(String pid) {
        return sqlSession.selectList("Drug.findByPrescriptionId", pid);
    }
}
