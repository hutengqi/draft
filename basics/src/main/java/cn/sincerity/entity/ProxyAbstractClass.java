package cn.sincerity.entity;

/**
 * ProxyAbstractClass: 代理抽象类
 *
 * @author Ht7_Sincerity
 * @date 2023/5/30
 */
public class ProxyAbstractClass implements ProxyInterface{

    void abstractMethod() {
        System.out.println("abstractClass");
    }

    @Override
    public void proxyMethod() {

    }
}
