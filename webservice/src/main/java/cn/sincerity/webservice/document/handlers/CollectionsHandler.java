package cn.sincerity.webservice.document.handlers;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * @Description
 * @Author Rick
 * @Create 2023-07-17 11:31
 * @email 123456789@qq.com
 */
public class CollectionsHandler extends PHandler {

    @Override
    protected String doHandle(Class<?> clz, Type genericType) {
        return getPrefix(clz) + getBody(clz, genericType) + getSuffix(clz);
    }

    private String getPrefix(Class<?> clz) {
        if (Map.class.isAssignableFrom(clz)) return "{";
        return "[";
    }
    private String getSuffix(Class<?> clz) {
        if (Map.class.isAssignableFrom(clz)) return "}";
        return "]";
    }

    private String getBody(Class<?> clz, Type genericType) {
        return HandlerHolder.HEAD.doHandle(clz, genericType);
    }

    @Override
    protected boolean judge(Class<?> clz) {
        return Collection.class.isAssignableFrom(clz) || Map.class.isAssignableFrom(clz);
    }
}
