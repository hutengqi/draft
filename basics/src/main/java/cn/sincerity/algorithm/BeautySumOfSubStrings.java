package cn.sincerity.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BeautySumOfSubStrings: 所有子字符串美丽值之和
 *
 * <p>一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。</p>
 *
 * <p>比方说，"abaacc" 的美丽值为 3 - 1 = 2 。</p>
 *
 * <p>给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。</p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/12
 */
public class BeautySumOfSubStrings {
    public static void main(String[] args) {
        System.out.println(beautySumByLeetCode("aabcbaa"));
    }

    static int beautySum(String s) {
        int beautySum = 0;
        int length = s.length();
        char[] chars = s.toCharArray();
        Map<Character, Integer> charCount;
        for (int i = 0; i < length; i++) {
            charCount = new HashMap<>();
            charCount.put(chars[i], 1);
            for (int j = i + 1; j < length; j++) {
                char key = chars[j];
                Integer count = charCount.get(key);
                if (count == null) {
                    charCount.put(key, 1);
                } else {
                    charCount.put(key, ++count);
                }
                if (charCount.size() > 1) {
                    List<Integer> list = charCount.values().stream().sorted().collect(Collectors.toList());
                    beautySum += list.get(charCount.size() - 1) - list.get(0);
                }
            }
        }
        return beautySum;
    }

    static int beautySumByLeetCode(String s) {
        int beautySum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            // 记录每轮子字符串中每个字母的出现次数。
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < length; j++) {
                int count = ++cnt[s.charAt(j) - 'a'];
                // 本轮子字符串中的最大次数
                maxFreq = Math.max(maxFreq, count);
                int minFreq = s.length();
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        // 本轮子字符串中的最小次数
                        minFreq = Math.min(minFreq, cnt[k]);
                    }
                }

                beautySum += maxFreq - minFreq;
            }
        }
        return beautySum;
    }
}
