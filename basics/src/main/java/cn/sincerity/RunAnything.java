package cn.sincerity;

import cn.sincerity.exception.CustomException;
import cn.sincerity.reflect.*;
import cn.sincerity.type.Sub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * RunAnything reflect
 *
 * @author Ht7_Sincerity
 * @date 2022/10/16
 */
public class RunAnything {


    static Map<Integer, Integer> map = new HashMap<>();

    static Logger logger = LoggerFactory.getLogger(RunAnything.class);

    public static void main(String[] args) {
        // do anything.
        logger.info("run anything.");
        String name = RunAnything.class.getName();
        System.out.println(name);
    }


    public static void switchCase(String insurePeriodFlag) {
        String unit;
        switch (insurePeriodFlag) {
            case "Y":
                unit = "YEAR";
                break;
            case "M":
                unit = "MONTH";
                break;
            case "D":
                unit = "DAY";
                break;
            default:
                unit = "AGE";
                break;
        }
        System.out.println(unit);
    }

    public static void change(Boolean flag) {
        flag = false;
    }

    static class Caller {
        void run(Sub sub) {
            if (sub == null) {
                throw new IllegalArgumentException("callee can not be null");
            }
            sub.caller();
        }
    }

    public static void sum(String name, Integer... nums) {
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        System.out.println(name + "的的得分：" + sum);
    }

    public static void updateNum(Integer num) {
        num = 7;
    }

    public static void customException() {
        try {
            throw new CustomException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void incr(TestInstance one) {
        one.setCount(one.getCount() + 1);
    }


    private static Boolean even(int num) {
        if (num % 2 == 0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    static void generic() {
        GenericContainer<DefaultGenericParameter> container = new DefaultGenericContainer();
        Class<? extends GenericContainer> containerClass = container.getClass();
        Type[] genericInterfaces = containerClass.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            System.out.println(genericInterface.getTypeName());
        }
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument.getTypeName());
        }
    }

    static void sort() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }
}
