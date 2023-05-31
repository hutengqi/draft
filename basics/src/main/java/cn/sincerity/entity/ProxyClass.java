package cn.sincerity.entity;

/**
 * ProxyClass
 *
 * @author Ht7_Sincerity
 * @date 2023/5/30
 */
public class ProxyClass extends ProxyAbstractClass implements ProxyInterface {

    @Override
    void abstractMethod() {
        super.abstractMethod();
    }

    @Override
    public void proxyMethod() {
        System.out.println("proxyClass");
    }
}
