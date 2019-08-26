package chth.playground.front.advice;

import chth.playground.front.exception.UserDetailsIsNotRootException;
import com.github.yingzhuo.carnival.exception.business.BusinessException;
import com.github.yingzhuo.carnival.json.Json;
import com.github.yingzhuo.carnival.jsr349.ValidationException;
import com.github.yingzhuo.carnival.restful.security.exception.RestfulSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Optional;


/**
 * @author: 程泰恒
 * @date: 2019/4/26 17:52
 */

@Slf4j
@RestControllerAdvice(basePackages = "chth.playground.front")
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserDetailsIsNotRootException.class)
    @ResponseStatus(HttpStatus.OK)
    public Json handleUserDetailsIsNotRootException() {
        return Json.newInstance()
                .code("401")
                .errorMessage("非法访问");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Json handlerConstraintViolationException() {
        return Json.newInstance()
                .code("400")
                .errorMessage("参数不能为blank");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Json ValidationException(ValidationException ex) {
        Optional.ofNullable(
                ex.getErrors()).ifPresent(errors -> errors.getFieldErrors().forEach(fe -> log.debug("\t字段:{} -> {}", fe.getField(), fe.getCode()))
        );

        return Json.newInstance()
                .code("400")
                .errorMessage("上行数据错误");

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Json handlerBusinessException(BusinessException ex) {
        return Json.newInstance()
                .code(ex.getCode())
                .errorMessage(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Json handlerIncorrectCredentialsException(IncorrectCredentialsException e) {
        return Json.newInstance()
                .code("001.002")
                .errorMessage("密码错误");
    }

    @ExceptionHandler(RestfulSecurityException.class)
    public Json handleRestfulSecurityException() {
        return Json.newInstance()
                .code("401")
                .errorMessage("非法访问");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Json handerAuthenticationException(AuthenticationException exception) {
        return Json.newInstance()
                .code("001.001")
                .errorMessage(exception.getMessage());
    }
}
