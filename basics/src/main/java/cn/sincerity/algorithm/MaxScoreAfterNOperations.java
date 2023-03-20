package cn.sincerity.algorithm;

/**
 * MaxScoreAfterNOperations: N 次操作后的最大分数和
 * <p>
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
 * <br/>
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 * <li>选择两个元素 x 和 y 。</li>
 * <li>获得分数 i * gcd(x, y) 。</li>
 * <li>将 x 和 y 从 nums 中删除。</li>
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 * <br/>
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/22
 */
public class MaxScoreAfterNOperations {

    static int sincerity(int[] nums) {
        int m = nums.length;
        int[] dp = new int[1 << m];
        int[][] gcdTmp = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                gcdTmp[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int all = 1 << m;
        for (int s = 1; s < all; ++s) {
            int t = Integer.bitCount(s);
            if ((t & 1) != 0) {
                continue;
            }
            for (int i = 0; i < m; ++i) {
                if (((s >> i) & 1) != 0) {
                    for (int j = i + 1; j < m; ++j) {
                        if (((s >> j) & 1) != 0) {
                            dp[s] = Math.max(dp[s], dp[s ^ (1 << i) ^ (1 << j)] + t / 2 * gcdTmp[i][j]);
                        }
                    }
                }
            }
        }
        return dp[all - 1];
    }


    /**
     * 计算两个整数的最大公约数
     *
     * @param num1 整数一
     * @param num2 整数二
     * @return 最大公约数
     */
    static int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }
}
