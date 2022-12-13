package cn.sincerity.algorithm;

/**
 * CheckIfPangram: 判断句子是否为全字母句
 *
 * <p>全字母句 指包含英语字母表中每个字母至少一次的句子。</p>
 *
 * <p>给你一个仅由小写英文字母组成的字符串 sentence ，请你判断 sentence 是否为 全字母句 。</p>
 *
 * <p>如果是，返回 true ；否则，返回 false 。</p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/13
 */
public class CheckIfPangram {
    public boolean sincerity(String sentence) {
        int[] alphabet = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            alphabet[sentence.charAt(i) - 'a'] = 1;
        }

        for (int letter : alphabet) {
            if (letter != 1)
                return false;
        }
        return true;
    }

    /**
     * LeetCode 思路：二进制表示集合
     *
     * <p>使用数组记录每种字符是否出现仍然需要 O(C) 的空间复杂度。</p>
     *
     * <p>由于字符集仅有 26 个，我们可以使用一个长度为 26 的二进制数字来表示字符集合，这个二进制数字使用 32 位带符号整型变量即可。</p>
     */
    public boolean leetCode(String sentence) {
        // 采用二进制表示，低 26 位从低到高表示字母表
        int state = 0;
        for (int i = 0; i < sentence.length(); i++) {
            // 位移运算使字母对应的位上为 1，或运算保证与其余位上原本的存在不受影响。
            state |= 1 << (sentence.charAt(i) - 'a');
        }
        // 若 state 的 0～25 位上不是都为 1, 那就证明句子不是全字母句
        return state == (1 << 26) - 1;
    }
}
