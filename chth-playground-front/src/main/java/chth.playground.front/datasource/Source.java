package chth.playground.front.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 程泰恒
 * @date: 2019/5/7 17:14
 */
public class Source {

    public static final Map<String, String> SOURCE_MAP;

    static {
        Map<String, String> sourceMap = new HashMap<String, String>();
        sourceMap.put("张三", "123456");
        sourceMap.put("李四", "123456www");
        SOURCE_MAP = Collections.unmodifiableMap(sourceMap);
    }
}
