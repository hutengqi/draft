package cn.sincerity.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * CheckPowersOfThree: 判断一个数字是否可以表示成三的幂的和
 *
 * <p>给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。</p>
 *
 * <p>对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。</p>
 *
 * <p>eg: 91 = 3^0 + 3^2 + 3^4</p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/9
 */
public class CheckPowersOfThree {

    public static boolean checkPowersOfThree(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            // 每轮找到小于 n 的最大的 3 的幂。
            // 从 3 的 0 次方开始
            int max = 1;
            int powerLevel = 0;

            // 循环寻找并记录
            while (max <= n / 3) {
                max *= 3;
                powerLevel++;
            }

            // 计算差值
            int diff = n - max;

            // 若已经存在该 幂 则 n 不符合题目
            if (!set.add(powerLevel))
                return false;

            // 若差值已经等于零，则 n 已经符合题目
            if (diff == 0)
                return true;

            // 将差值赋值给 n，进行下一轮寻找。
            n = diff;
        }
    }

    /**
     * LeetCode 思路：进制转换
     *
     * <p> 我们可以将 n 转换成 3 进制。如果 n 的 3 进制表示中每一位均不为 2，那么答案为 True，否则为 False。</p>
     *
     * <p>例如 n = 12 时， 12 的三进制表示为 110，满足要求；当 n = 21 时，21 的三进制表示为 210，不满足要求。</p>
     */
    public boolean checkPowersOfThreeByLeetCode(int n) {
        while (n != 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
