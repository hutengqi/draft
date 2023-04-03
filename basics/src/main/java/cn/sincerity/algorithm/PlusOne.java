package cn.sincerity.algorithm;

import java.util.Arrays;

/**
 * PlusOne: 加一
 * <p>
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <br/>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <br/>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/3/27
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 9};
        System.out.println(Arrays.toString(plusOne(array)));
    }

    public static int[] plusOne(int[] digits) {
        int current, sum, carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum = digits[i] + carry;
            if (sum < 10) {
                digits[i] = sum;
                carry = 0;
                break;
            } else {
                carry = sum / 10;
                current = sum % 10;
                digits[i] = current;
            }
        }

        if (carry == 0)
            return digits;

        int[] array = new int[digits.length + 1];
        System.arraycopy(digits, 0, array, 1, digits.length);
        array[0] = carry;
        return array;
    }
}
