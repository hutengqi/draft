package cn.sincerity.algorithm;

/**
 * CountTime: 有效时间的数目
 * <p>
 * 给你一个长度为 5 的字符串 time ，表示一个电子时钟当前的时间，格式为 "hh:mm" 。最早 可能的时间是 "00:00" ，最晚 可能的时间是 "23:59" 。
 * <br/>
 * 在字符串 time 中，被字符 ? 替换掉的数位是 未知的 ，被替换的数字可能是 0 到 9 中的任何一个。
 * <br/>
 * 请你返回一个整数 answer ，将每一个 ? 都用 0 到 9 中一个数字替换后，可以得到的有效时间的数目。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/5/9
 */
public class CountTime {

    public int countTime(String time) {
        int answer;
        char questionMark = '?';
        char hh = time.charAt(0);
        char hl = time.charAt(1);
        char sh = time.charAt(3);
        char sl = time.charAt(4);

        return 0;
    }
}
