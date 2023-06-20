package cn.sincerity.type;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * Sub
 *
 * @author Ht7_Sincerity
 * @date 2022/11/21
 */
public class Sub extends Super{

    @CallerSensitive
    public void caller() {
        Class<?> callerClass = Reflection.getCallerClass();
        System.out.println(callerClass.getName());
    }
}
