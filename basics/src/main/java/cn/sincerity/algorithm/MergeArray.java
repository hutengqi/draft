package cn.sincerity.algorithm;

import java.util.Arrays;

/**
 * MergeArray: 合并两个有序数组
 * <p>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <br/>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <br/>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/4/4
 */
public class MergeArray {

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] num2 = new int[]{2, 5, 6};
        merge(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;
        m--;
        n--;
        while (n >= 0) {
            while (m >= 0 && nums1[m] > nums2[n]) {
                int temp = nums1[m];
                nums1[m] = nums1[index];
                nums1[index] = temp;
                m--;
                index--;
            }
            int temp = nums2[n];
            nums2[n] = nums1[index];
            nums1[index] = temp;
            n--;
            index--;
        }
    }
}
