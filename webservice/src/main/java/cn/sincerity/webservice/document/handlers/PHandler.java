package cn.sincerity.webservice.document.handlers;

import java.lang.reflect.Type;

/**
 * @Description
 * @Author Rick
 * @Create 2023-07-17 10:43
 * @email 123456789@qq.com
 */
public abstract class PHandler {

    private PHandler next;


    // 当前逻辑出来的就是body 再补下属性名的字符串
    // list  => [1,2,3] 再补下 "filedA" :
    public String handle(Class<?> clz, Type genericType){
        if (judge(clz)) {
            return doHandle(clz, genericType);
        }
        if (next != null) {
            return next.handle(clz, genericType);
        }
        return null;
    }

    protected abstract String doHandle(Class<?> clz, Type genericType);

    protected abstract boolean judge(Class<?> clz);
}
