package cn.sincerity.webservice.document.handlers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Rick
 * @Create 2023-07-17 11:28
 * @email 123456789@qq.com
 */
public class BasicTypeHandler extends PHandler {
    Map<Class, Object> basics = new HashMap<>();

    static {
        //init @basics
    }

    @Override
    protected String doHandle(Class<?> clz, Type genericType) {
        // 优化判断和取值 合并成一次调用
        return "\"" + basics.get(clz) + "\"";
    }

    @Override
    protected boolean judge(Class<?> clz) {
        return basics.containsKey(clz);
    }
}
