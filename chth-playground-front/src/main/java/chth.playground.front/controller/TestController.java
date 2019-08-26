package chth.playground.front.controller;

import com.github.yingzhuo.carnival.json.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 程泰恒
 * @date: 2019/7/1 16:54
 */

@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Json testController() {
        return Json.newInstance();
    }
}
