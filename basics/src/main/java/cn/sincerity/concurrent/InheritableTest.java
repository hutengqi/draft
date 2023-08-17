package cn.sincerity.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableTest
 *
 * @author Ht7_Sincerity
 * @date 2023/8/3
 */
public class InheritableTest {

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread((() -> {
                String name = "Thread-" + finalI;
                inheritableThreadLocal.set(name);
                System.out.println("ThreadLocal:" + inheritableThreadLocal.get());
                new Thread(() -> {
                    System.out.println(name + "：子线程的 inheritable ThreadLocal:" + inheritableThreadLocal.get());
                }).start();
            })).start();
        }
    }
}
