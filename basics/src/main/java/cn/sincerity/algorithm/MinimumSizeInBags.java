package cn.sincerity.algorithm;

import java.util.*;
import java.util.stream.Collector;

/**
 * MinimumSizeInBags: 袋子里最少数目的球
 *
 * <p>给你一个整数数组 nums ，其中 nums[i] 表示第 i 个袋子里球的数目。同时给你一个整数 maxOperations 。</p>
 * <p>你可以进行如下操作至多 maxOperations 次：</p>
 * <p>
 * 选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有 正整数 个球。
 * <li/>比方说，一个袋子里有 5 个球，你可以把它们分到两个新袋子里，分别有 1 个和 4 个球，或者分别有 2 个和 3 个球。
 * </p>
 * <p>你的开销是单个袋子里球数目的 最大值 ，你想要 最小化 开销。</p>
 * <p>请你返回进行上述操作后的最小开销。</p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/20
 */
public class MinimumSizeInBags {

    public static void main(String[] args) {
        int[] nums = {9};
        int maxOperations = 2;
        System.out.println(leetCode(nums, maxOperations));
    }

    @Deprecated
    static int sincerity(int[] nums, int maxOperations) {
        List<Integer> then = new ArrayList<>(nums.length + maxOperations);
        for (int num : nums) {
            then.add(num);
        }
        while (maxOperations > 0) {
            Integer max = then.stream().max(Integer::compareTo).get();
            int maxIndex = then.indexOf(max);
            int half = (max + 1) / 2;
            then.set(maxIndex, half);
            then.add(max - half);
            maxOperations--;
            then.forEach(System.out::print);
            System.out.println("---------------------");
        }

        return then.stream().max(Integer::compareTo).get();
    }

    /**
     * LeetCode 思路：二分查找
     * <p>
     * 我们可以将题目中的要求转换成判定问题，即：
     * <br/><b>给定 maxOperations 次操作次数，能否可以使得单个袋子里球数目的最大值不超过 y</b>
     * </p>
     *
     * <a href="https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/solutions/2025611/dai-zi-li-zui-shao-shu-mu-de-qiu-by-leet-boay/">力扣官方题解</a>
     */
    static int leetCode(int[] nums, int maxOperations) {
        // 二分查找的边界 1 ～ nums.max
        int left = 1, right = Arrays.stream(nums).max().getAsInt();
        // 最小的开销
        int ans = 0;
        while (left <= right) {
            // y 为本轮预设的开销
            int y = (left + right) / 2;
            // 本轮总的所需操作次数
            long ops = 0;
            for (int x : nums) {
                //（每个袋子中的球数）x - 1 / y（当前的预设） 的下取整操作
                // 即每个袋子中的球数所需的操作次数
                ops += (x - 1) / y;
            }
            // 检验预设开销
            if (ops <= maxOperations) {
                // 若本轮预设成立，则记录开销并将其预设减小继续查找。
                ans = y;
                right = y - 1;
            } else {
                // 若本轮预设成立，则将开销预设增大继续查找。
                left = y + 1;
            }
        }
        // 返回最后找到的最小开销
        return ans;
    }
}
