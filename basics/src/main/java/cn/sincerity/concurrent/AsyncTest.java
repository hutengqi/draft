package cn.sincerity.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * AsyncTest
 *
 * @author Ht7_Sincerity
 * @date 2023/6/30
 */
public class AsyncTest {

    public static void main(String[] args) {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> print("task1"));
        CompletableFuture<Void> task2 = task1.thenRun(() -> print("task2"));
        CompletableFuture<Void> task3 = task1.thenRun(() -> print("task3"));
        CompletableFuture<Void> task4 = task1.thenRun(() -> print("task4"));

        CompletableFuture<Void> task5 = task2.thenRun(() -> print("task5"));
        CompletableFuture<Void> task6 = task2.thenRun(() -> print("task6"));
        CompletableFuture<Void> task7 = task2.thenRun(() -> print("task7"));

        CompletableFuture<Void> task8 = task3.thenRun(() -> print("task8"));
        CompletableFuture<Void> task9 = task3.thenRun(() -> print("task9"));
        CompletableFuture<Void> task10 = task3.thenRun(() -> print("task10"));

        CompletableFuture<Void> task11 = task3.thenRun(() -> print("task11"));
        CompletableFuture<Void> task12 = task3.thenRun(() -> print("task12"));

        CompletableFuture.allOf(task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, task11, task12).join();
    }

    @SuppressWarnings({"unused"})
    private static void print(String printStr) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(printStr);
    }
}
