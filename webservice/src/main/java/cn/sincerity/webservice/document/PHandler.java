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

    public void handle(Class<?> clz, Type genericType){
        if (judge(clz)) {
            doHandle(clz, genericType);
        } else if (next != null) {
            handle(clz, genericType);
        }
    }

    protected abstract void doHandle(Class<?> clz, Type genericType);

    protected abstract boolean judge(Class<?> clz);
}
