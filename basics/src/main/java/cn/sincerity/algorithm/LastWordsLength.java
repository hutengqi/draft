package cn.sincerity.algorithm;

/**
 * LastWordsLength：最后一个单词的长度
 *
 * @author Ht7_Sincerity
 * @date 2023/3/24
 */
public class LastWordsLength {

    public static void main(String[] args) {
        String s ="   fly me   to   the moon ";
        char blank = ' ';
        System.out.println(blank);
        System.out.println(lengthOfLastWord(s));
    }

    public static int lengthOfLastWord(String s) {
        int length = 0;
        char blank = ' ';
        boolean word = false;
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != blank) {
                word = true;
                length++;
            }

            if (word && chars[i] == blank)
                break;
        }
        return length;
    }
}
