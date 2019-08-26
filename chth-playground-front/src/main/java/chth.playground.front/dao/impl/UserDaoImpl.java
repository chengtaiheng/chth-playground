package chth.playground.front.dao.impl;

import chth.playground.common.User;
import chth.playground.front.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: 程泰恒
 * @date: 2019/5/20 16:48
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public User findByUserName(String userName) {
        return sqlSession.selectOne("User.findByUserName", userName);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return sqlSession.selectList("ListUser.findAll");
    }
}
