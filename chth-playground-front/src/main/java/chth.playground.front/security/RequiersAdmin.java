package chth.playground.front.security;

import com.github.yingzhuo.carnival.restful.security.annotation.AuthenticationComponent;
import com.github.yingzhuo.carnival.restful.security.annotation.Requires;
import com.github.yingzhuo.carnival.restful.security.exception.AuthenticationException;
import com.github.yingzhuo.carnival.restful.security.exception.RestfulSecurityException;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 程泰恒
 * @date: 2019/8/22 17:24
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Requires(RequiersAdmin.AuthComponent.class)
public @interface RequiersAdmin {

    public static class AuthComponent implements AuthenticationComponent<RequiersAdmin> {

        @Override
        public void authenticate(UserDetails userDetails, RequiersAdmin requiersAdmin) throws RestfulSecurityException {
            if (!userDetails.isRoot()) {
                throw new AuthenticationException();
            }
        }
    }
}
