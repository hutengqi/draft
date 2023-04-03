package cn.sincerity.algorithm;

/**
 * SumBinary: 二进制求和
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 * @author Ht7_Sincerity
 * @date 2023/3/28
 */
public class SumBinary {

    public static void main(String[] args) {
        String a = "1010", b = "1011";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        if (b.length() > a.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int carry = 0;
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        int aIndex = a.length() - 1;

        for (int i = bArray.length - 1; i >= 0; i--) {
            int sum = Integer.parseInt(String.valueOf(bArray[i])) + carry + Integer.parseInt(String.valueOf(aArray[aIndex--]));
            carry = sum / 2;
            sb.append(sum % 2);
        }
        while (aIndex >= 0) {
            int sum = Integer.parseInt(String.valueOf(aArray[aIndex--])) + carry;
            carry = sum / 2;
            sb.append(sum % 2);
        }

        if (carry != 0)
            sb.append(carry);

        return sb.reverse().toString();
    }
}
