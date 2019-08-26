package chth.playground.front.dao;

import chth.playground.common.User;

import java.util.List;
import java.util.Map;

/**
 * @author: 程泰恒
 * @date: 2019/5/20 16:44
 */
public interface UserDao {

    public User findByUserName(String userName);

    public List<Map<String, Object>> findAll();
}
