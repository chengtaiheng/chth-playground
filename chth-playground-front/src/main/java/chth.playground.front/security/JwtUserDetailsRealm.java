package chth.playground.front.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.yingzhuo.carnival.jwt.realm.AbstractJwtUserDetailsRealm;
import com.github.yingzhuo.carnival.restful.security.token.Token;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * @author: 程泰恒
 * @date: 2019/8/22 13:45
 */

@Component
public class JwtUserDetailsRealm extends AbstractJwtUserDetailsRealm {

    @Override
    protected UserDetails getUserDetails(Token token, DecodedJWT decodedJWT) {

        val userDetails = UserDetails.builder()
                .id(decodedJWT.getClaim("id").asString())
                .username(decodedJWT.getClaim("username").asString())
                .root(decodedJWT.getClaim("admin").asBoolean())
                .build();
        return userDetails;
    }

}
