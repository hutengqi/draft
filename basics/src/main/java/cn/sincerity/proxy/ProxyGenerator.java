package cn.sincerity.proxy;

import cn.sincerity.entity.ProxyClass;
import net.sf.cglib.proxy.Enhancer;

/**
 * ProxyGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/5/30
 */
public class ProxyGenerator {

    public static void main(String[] args) {
        ProxyClass o = (ProxyClass)Enhancer.create(ProxyClass.class, new ProxyMethodInterceptor());
        o.proxyMethod();
    }
}
