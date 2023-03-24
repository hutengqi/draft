package cn.sincerity.algorithm;

/**
 * SearchInsert: 搜索插入位置
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <br/>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/3/23
 */
public class SearchInsert {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 0;
        System.out.println(searchInsert(nums, target));
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0)
            return 0;

        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;

            if (target < nums[mid])
                right = mid + 1;
            else
                left = mid - 1;
        }
        return left;
    }
}
