package cn.sincerity.algorithm;

import java.util.Stack;

/**
 * Description：整数反转
 * <p>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * Create by Ht7_Sincerity on 2021/9/29
 */
public class IntegerReverse {

    public static void main(String[] args) {
        System.out.println(reverse(-123));
        System.out.println(reverseViaLeetCode(-123));

    }

    public static int reverse(int num) {
        String s = String.valueOf(num);
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        Stack<Character> stack = new Stack<>();
        boolean isMinus = chars[0] == 45;
        for (int i = isMinus ? 1 : 0; i < chars.length; i++) {
            stack.push(chars[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (isMinus) {
            stringBuilder.append('-');
        }
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        int result;
        try {
            result = Integer.parseInt(stringBuilder.toString());
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }

    public static int reverseViaLeetCode(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digest = x % 10;
            x /= 10;
            rev = rev * 10 +digest;
        }
        return rev;
    }
}
