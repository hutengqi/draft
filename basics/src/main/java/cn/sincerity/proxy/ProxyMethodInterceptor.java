package cn.sincerity.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ProxyMethodInterceptor
 *
 * @author Ht7_Sincerity
 * @date 2023/5/30
 */
public class ProxyMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String name = method.getName();
        if (name.equals("proxyMethod")) {
            System.out.println("before proxyMethod");
            proxy.invoke(obj, args);
            System.out.println("after proxyMethod");
        }
        return obj;
    }
}
