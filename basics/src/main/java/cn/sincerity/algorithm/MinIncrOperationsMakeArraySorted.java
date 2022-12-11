package cn.sincerity.algorithm;

/**
 * MinIncrOperationsMakeArraySorted: 最少操作使数组递增
 *
 * <p>给你一个整数数组 nums （下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加 1 。</p>
 *
 * <p>比方说，如果 nums = [1,2,3] ，你可以选择增加 nums[1] 得到 nums = [1,3,3] 。</p>
 *
 * <p>
 * 请你返回使 nums 严格递增 的 最少 操作次数。
 * 我们称数组 nums 是 严格递增的 ，当它满足对于所有的 0 <= i < nums.length - 1 都有 nums[i] < nums[i+1] 。一个长度为 1 的数组是严格递增的一种特殊情况。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/11
 */
public class MinIncrOperationsMakeArraySorted {

    public static int minOperations(int[] nums) {
        int operations = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                int increment = nums[i - 1] + 1 - nums[i];
                operations += increment;
                nums[i] += increment;
            }
        }
        return operations;
    }

    public static int minOperationsByLeetCode(int[] nums) {
        // pre + 1 每轮需要达到的最小的值，pre 代表了前一位的值，res 记录操作次数。
        int pre = nums[0] - 1, res = 0;
        for (int num : nums) {
            // pre + 1 与 本轮的实际数值 比较取出最大值，记录需要自增的操作数。
            pre = Math.max(pre + 1, num);
            res += pre - num;
        }
        return res;
    }
}
