package chth.playground.front.annotation;

import chth.playground.front.exception.UserDetailsIsNotRootException;
import com.github.yingzhuo.carnival.restful.security.annotation.AuthenticationComponent;
import com.github.yingzhuo.carnival.restful.security.annotation.Requires;
import com.github.yingzhuo.carnival.restful.security.exception.RestfulSecurityException;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Requires(RequireRoot.AuthCompent.class)
public @interface RequireRoot {
    String errorMessage() default "<NO MESSAGE>";

    @Component
    public static class AuthCompent implements AuthenticationComponent<RequireRoot> {

        public AuthCompent() {

        }

        public void authenticate(UserDetails var1, RequireRoot var2) throws RestfulSecurityException {
            if (var1 == null || var1.isNotRoot()) {
                throw new UserDetailsIsNotRootException(this.getMessage(var2));
            }
        }

        private String getMessage(RequireRoot var1) {
            String var2 = var1.errorMessage();
            if ("<NO MESSAGE>".equals(var2)) {
                var2 = null;
            }

            return var2;
        }
    }


}
