package cn.sincerity.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * FindIntegerNumInString
 *
 * @author Ht7_Sincerity
 * @date 2022/12/6
 */
public class FindIntegerNumInString {

    public static void main(String[] args) {
        String str = "035985750011523523129774573439111590559325a1554234973";
        int count = numDifferentIntegers(str);
        System.out.println(count);
    }

    /*
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/number-of-different-integers-in-a-string/solutions/2006870/zi-fu-chuan-zhong-bu-tong-zheng-shu-de-s-vxly/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        //定义变量 p1: 序列起始索引 p2: 序列终止索引
        int len = word.length(), p1 = 0, p2;
        //循环寻找数字序列
        while (true) {
            // 找到此轮数字字符序列的起始索引
            while (p1 < len && !Character.isDigit(word.charAt(p1)))
                p1++;

            // 说明此次剩余的字符都不是数字或已经检查完毕
            if (p1 == len)
                break;

            // 找到当前数字序列的终止索引 p2
            p2 = p1;
            while (p2 < len && Character.isDigit(word.charAt(p2)))
                p2++;

            // 舍弃数字中的前导0
            while (p2 - p1 > 1 && word.charAt(p1) == '0')
                p1++;

            // 将处理后的子序列加入 set
            set.add(word.substring(p1, p2));
            // 重置起始索引 p1
            p1 = p2;
        }
        return set.size();
    }

    private static int getCountOfInteger(String word) {
        if (word == null) {
            return 0;
        }

        int count = 0, length = word.length();
        char[] chars = word.toCharArray();
        String[] integers = new String[length];

        int tempIdx = 0;
        char[] temp = null;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                if (temp == null)
                    temp = new char[length];

                if (tempIdx == 0 && c == '0')
                    continue;

                temp[tempIdx++] = c;

            } else {
                if (temp != null) {
                    String s = new String(temp);
                    if (single(integers, s))
                        integers[count++] = s;

                    temp = null;
                    tempIdx = 0;
                }
            }
        }

        if (temp != null && single(integers, new String(temp)))
            count++;

        return count;
    }

    static boolean single(String[] array, String temp) {
        if (array == null)
            return true;

        for (String num : array) {
            if (num == null)
                continue;

            if (num.equals(temp))
                return false;

        }
        return true;
    }
}
