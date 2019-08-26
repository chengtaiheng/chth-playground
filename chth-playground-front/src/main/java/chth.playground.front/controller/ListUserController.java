package chth.playground.front.controller;

import chth.playground.front.dao.UserDao;
import com.github.yingzhuo.carnival.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: 程泰恒
 * @date: 2019/7/9 14:43
 */
@Slf4j
@RestController
@RequestMapping("/list-user")
public class ListUserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/find-all")
    public Json findAll() {
        List<Map<String, Object>> all = userDao.findAll();
        return Json.newInstance()
                .payload("result", all);
    }
}
