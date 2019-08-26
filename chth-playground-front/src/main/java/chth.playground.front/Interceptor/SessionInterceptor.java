package chth.playground.front.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author: 程泰恒
 * @date: 2019/6/25 9:46
 */

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    private HashMap<String, String> uriPatterns = new HashMap();
    private HashMap<String, String> excludeURIPatterns = new HashMap();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.debug("path : {}", requestURI);
        log.debug("method:{}", request.getMethod());

        if (uriPatterns.containsKey("/security") && uriPatterns.containsValue("GET")) {
            System.out.println("有人访问安全区域");
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("执行到postHandle方法");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("执行到afterCompletion方法");
    }

    public SessionInterceptor addUriPatterns(String uri, String method) {
        this.uriPatterns.put(uri, method);
        return this;
    }

    public SessionInterceptor excludeURIPatterns(String uri, String method) {
        this.excludeURIPatterns.put(uri, method);
        return this;
    }


}
