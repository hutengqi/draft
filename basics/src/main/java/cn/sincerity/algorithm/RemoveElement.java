package cn.sincerity.algorithm;

/**
 * Description：<h1>移除元素</h1>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * Create by Ht7_Sincerity on 2021/10/18
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }

    public static int removeElement(int[] nums, int val) {
        int len = nums.length, i = 0, j;
        while (i < len) {
            if (nums[i] == val) {
                j = i;
                while (j < len - 1) {
                    nums[j] = nums[j + 1];
                    j ++;
                }
                len--;
            } else {
                i ++;
            }
        }
        return len;
    }
}
