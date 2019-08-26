package chth.playground.front.controller;

import chth.playground.common.User;
import chth.playground.front.dao.UserDao;
import chth.playground.front.form.LoginForm;
import com.github.yingzhuo.carnival.exception.business.BusinessException;
import com.github.yingzhuo.carnival.json.Json;
import com.github.yingzhuo.carnival.jsr349.ValidationException;
import com.github.yingzhuo.carnival.jwt.factory.JwtTokenFactory;
import com.github.yingzhuo.carnival.jwt.factory.JwtTokenInfo;
import com.github.yingzhuo.carnival.password.util.PasswordEncrypterUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/security")
@Validated
@Slf4j
public class SecurityController {

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Autowired
    private UserDao userDao;

    @PostMapping
    public Json login(@Validated LoginForm form, BindingResult bindingResult) {

        ValidationException.raiseIfNecessary(bindingResult);

        val user = userDao.findByUserName(form.getUserName());

        if (user == null) {
            log.debug("查无此人");
            throw BusinessException.of("000001.01");
        }

        if (!user.getPassword().equals(PasswordEncrypterUtils.encrypt(form.getPassword()))) {
            throw BusinessException.of("000001.01");
        }

        if (Objects.isNull(jwtTokenFactory)) {
            System.out.println("是空的");
        }

        System.out.println("jwtTokenFactory = " + jwtTokenFactory);
        log.debug("jwtTokenFactory = {}", jwtTokenFactory);

        val token = jwtTokenFactory.create(JwtTokenInfo.builder()
                .putPrivateClaim("id", user.getId())
                .putPrivateClaim("username", user.getUserName())
                .putPrivateClaim("admin", user.getAdmin())
                .expiresAtFuture(30, TimeUnit.MINUTES)
                .build()
        );

        return Json.newInstance()
                .payload("name", form.getUserName())
                .payload("token", token);
    }

    @PostMapping("/find")
    public Json test(@RequestParam("userName") String userName) {

        User user = userDao.findByUserName(userName);
        user.setPassword("****");

        return Json.newInstance()
                .payload("user", user);
    }

    @GetMapping("/forbidden")
    public Json noRight() {
        return Json.newInstance()
                .code("002.001")
                .errorMessage("没有权限");
    }

    @GetMapping("/secret-visit")
    public Json testVisit() {
        log.debug("进入秘密访问");
        return Json.newInstance()
                .payload("test", "successful");
    }

}
