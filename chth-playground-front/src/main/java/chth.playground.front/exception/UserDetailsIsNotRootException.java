package chth.playground.front.exception;

import com.github.yingzhuo.carnival.restful.security.exception.AuthorizationException;

/**
 * @author: 程泰恒
 * @date: 2019/4/26 15:01
 */
public class UserDetailsIsNotRootException extends AuthorizationException {

    public UserDetailsIsNotRootException() {
    }

    public UserDetailsIsNotRootException(String var1) {
        super(var1);
    }

    public UserDetailsIsNotRootException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public UserDetailsIsNotRootException(Throwable var1) {
        super(var1);
    }
}
