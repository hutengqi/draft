package cn.sincerity.algorithm;


import java.util.HashMap;
import java.util.Map;

/**
 * Description：罗马数字转阿拉伯数字
 * 罗马数字包含以下七种字符: I(1)， V(5)，X(10)，L(50)，C(100)，D(500) 和 M(1000)。
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * Create by Ht7_Sincerity on 2021/10/9
 */
public class RomeToArab {

    /**
     * 罗马数字中的字符
     */
    public final static char[] ROME_DIGEST = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    public static void main(String[] args) {
        System.out.println(romeToArab("MCMXCIV"));
    }

    public static int romeToArab(String rome) {
        if (rome == null || rome.trim().length() == 0) {
            return 0;
        }
        char[] romeDigests = rome.toCharArray();
        int[] arabValues = new int[romeDigests.length];
        for (int i = 0; i < romeDigests.length; i++) {
            arabValues[i] = getValByRomeDigest(romeDigests[i]);
        }
        int index = 1, result = 0, begin = 0;
        while (index <= arabValues.length) {
            if (index == arabValues.length || arabValues[index - 1] >= arabValues[index]) {
                if (begin == index - 1) {
                    result += arabValues[index - 1];
                } else {
                    result += (arabValues[index - 1] - arabValues[begin]);
                }
                begin = index;
            }
            index++;
        }
        return result;
    }

    public static int getValByRomeDigest(char c) {
        int i;
        for (i = 0; i < ROME_DIGEST.length; i++) {
            if (c == ROME_DIGEST[i]) {
                break;
            }
        }
        if (i % 2 == 0) {
            return (int) Math.pow(10, (double) i / 2);
        } else {
            return (int) (5 * Math.pow(10, (double) (i - 1) / 2));
        }
    }

    /**
     * LeetCode 版本
     */
    public static Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {
        private static final long serialVersionUID = -7052690153505153913L;

        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public static int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
}
