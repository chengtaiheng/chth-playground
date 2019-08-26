package password.encrypt;

import chth.playground.front.FrontApplication;
import com.github.yingzhuo.carnival.password.util.PasswordEncrypterUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: 程泰恒
 * @date: 2019/5/20 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrontApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"defaul"})
public class PasswordEncrypt {

    @Test
    public void test1(){
        System.out.println(PasswordEncrypterUtils.encrypt("大程子fighting"));
    }
}
