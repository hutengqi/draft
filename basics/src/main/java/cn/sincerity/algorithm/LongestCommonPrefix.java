package cn.sincerity.algorithm;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * Description：最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 输入：strings = ["flower","flow","flight"]
 * 输出："fl"
 * Create by Ht7_Sincerity on 2021/10/11
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strings = {"ab","a"};
        System.out.println(getLongestCommonPrefix(strings));
    }

    public static String getLongestCommonPrefix(String[] strings) {
        if (strings.length < 1 || strings.length > 200) {
            return "";
        }
        StringBuilder prefix = new StringBuilder();
        int len = strings[0].length(), count = strings.length;
        for (String string : strings) {
            if (string.length() < 1 || string.length() > 200) {
                return "";
            }
            if (len > string.length()) {
                len = string.length();
            }
        }
        boolean flag;
        char c;
        for (int i = 0; i < len; i++) {
            flag = true;
            c = strings[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (c != strings[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                prefix.append(c);
            } else {
                break;
            }
        }
        return prefix.toString();
    }
}
