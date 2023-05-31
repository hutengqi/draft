package cn.sincerity;

import cn.sincerity.exception.CustomException;
import cn.sincerity.reflect.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

/**
 * RunAnything reflect
 *
 * @author Ht7_Sincerity
 * @date 2022/10/16
 */
public class RunAnything {
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        String id = "810000199708081515";
        System.out.println(id.length());
        System.out.println(id.substring(6, 14));
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
