package chth.playground.front;

import chth.playground.front.security.RequiersAdmin;
import com.github.yingzhuo.carnival.jwt.EnableJwtTokenFactory;
import com.github.yingzhuo.carnival.jwt.factory.DefaultJwtTokenFactory;
import com.github.yingzhuo.carnival.jwt.factory.JwtTokenFactory;
import com.github.yingzhuo.carnival.jwt.factory.JwtTokenInfo;
import com.github.yingzhuo.carnival.restful.security.AuthenticationStrategy;
import com.github.yingzhuo.carnival.restful.security.EnableRestfulSecurity;
import com.github.yingzhuo.carnival.restful.security.parser.BearerTokenParser;
import com.github.yingzhuo.carnival.restful.security.parser.TokenParser;
import com.github.yingzhuo.carnival.restful.security.userdetails.UserDetails;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJwtTokenFactory
@EnableRestfulSecurity(authenticationStrategy = AuthenticationStrategy.ALL)
public class ApplicationCnfSecurity implements WebMvcConfigurer {
    @Bean
    @Primary
    public TokenParser tokenParser() {
        return new BearerTokenParser();
    }

    @Bean
    public RequiersAdmin.AuthComponent mallAdminAuthComponent(){
        return  new RequiersAdmin.AuthComponent();
    }

}
