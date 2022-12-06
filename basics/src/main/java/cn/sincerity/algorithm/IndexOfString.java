package cn.sincerity.algorithm;


/**
 * Description：实现 indexOf()
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 * Create by Ht7_Sincerity on 2021/10/19
 */
public class IndexOfString {

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
    }

    public static int strStr(String haystack, String needle) {
        char[] check = haystack.toCharArray();
        if (needle == null || needle.trim().length() == 0){
            return 0;
        }
        char[] target = needle.toCharArray();
        int index = 0,right= 0;
        while (index <= haystack.length() - needle.length()){
            while (right - index < needle.length()){
                if (check[right] != target[right]){
                    break;
                } else {
                    right ++;
                }
            }
            if (right - index == needle.length() - 1){
                return index;
            }
            index ++;
        }
        return 0;
    }
}
