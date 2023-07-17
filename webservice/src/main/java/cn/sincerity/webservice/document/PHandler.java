package cn.sincerity.webservice.document;

import java.lang.reflect.Type;

/**
 * @Description
 * @Author Rick
 * @Create 2023-07-17 10:43
 * @email 123456789@qq.com
 */
public abstract class PHandler {

    private PHandler next;

    public Object handle(Class<?> clz, Type genericType){
        if (judge(clz)) {
            return doHandle(clz, genericType);
        }
        if (next != null) {
            return next.handle(clz, genericType);
        }
        return null;
    }

    protected abstract Object doHandle(Class<?> clz, Type genericType);

    protected abstract boolean judge(Class<?> clz);
}
