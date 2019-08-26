package chth.playground.front.service;

import chth.playground.common.User;

/**
 * @author: 程泰恒
 * @date: 2019/5/20 16:38
 */

public interface UserService {
    public User findByUserName(String userName);
}
