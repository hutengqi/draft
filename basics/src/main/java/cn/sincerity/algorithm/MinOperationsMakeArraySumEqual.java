package cn.sincerity.algorithm;

import cn.sincerity.structures.Array;

import java.util.Arrays;

/**
 * MinOperationsMakeArraySumEqual：通过最少操作次数使数组的和相等
 *
 * <p>给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。</p>
 *
 * <p>每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。</p>
 *
 * <p>请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。</p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/7
 */
public class MinOperationsMakeArraySumEqual {

    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 4, 5, 6};
        int[] num2 = {1, 1, 2, 2, 2, 2};
        System.out.println(minOperations(num1, num2));
    }

    public static int minOperations(int[] nums1, int[] nums2) {
        // 若极限变化量仍不满足数组和相等，则无法完成
        if (6 * nums1.length < nums2.length || 6 * nums2.length < nums1.length)
            return -1;

        // 计算两个数组的差值
        int d = 0;
        for (int num : nums1)
            d += num;

        for (int num : nums2)
            d -= num;

        // 使 num1 为数组和大的引用，便于后续操作
        if (d < 0) {
            d = -d;
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // 操作数哈希表：统计每个变化量上的需操作次数
        int[] cnt = new int[6];
        for (int num : nums1)
            ++cnt[num - 1];

        for (int num : nums2)
            ++cnt[6 - num];

        //按照变化量从大到小遍历上面的操作数哈希表，缩小差值直到 0，并记录操作数。
        int operations = 0;
        for (int i = 5; ; --i) {
            // 若此轮的 变化量 * 操作数 已经大于剩余差值，则只需计算出 d/i 的上取整
            // 上取整是因为 即使剩余差值无法整除当前变化量，但也仅需多操作一次比当前变化量小的变化量。
            if (i * cnt[i] >= d)
                return operations + (d + i - 1) / i;

            operations += cnt[i];
            d -= i * cnt[i];
        }
    }
}
