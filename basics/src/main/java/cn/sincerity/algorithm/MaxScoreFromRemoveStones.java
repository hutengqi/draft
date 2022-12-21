package cn.sincerity.algorithm;

import java.util.Arrays;


/**
 * MaxScoreFromRemoveStones: 移除石子的最大得分
 * <p>
 * 你正在玩一个单人游戏，面前放置着大小分别为 a、b 和 c 的 三堆 石子。
 * <br/>
 * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。
 * <br/>
 * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/21
 */
public class MaxScoreFromRemoveStones {

    static int sincerity(int a, int b, int c) {
        int[] array = {a, b, c};
        Arrays.sort(array);
        if (array[0] + array[1] <= array[2])
            return array[0] + array[1];
        else
            return (array[0] + array[1] + array[2]) / 2;
    }

    /**
     * LeetCode 思路：贪心算法
     * <a href="https://leetcode.cn/problems/maximum-score-from-removing-stones/solutions/2026649/yi-chu-shi-zi-de-zui-da-de-fen-by-leetco-e5wm/"> 题解</a>
     */
    static int leetCode(int a, int b, int c) {
        int sum = a + b + c;
        int maxVal = Math.max(Math.max(a, b), c);
        return Math.min(sum - maxVal, sum / 2);
    }
}
