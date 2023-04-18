package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;

/**
 * SortedArrayToBST: 有序数组转换为平衡二叉树
 *
 * @author Ht7_Sincerity
 * @date 2023/4/17
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return child(nums, 0, nums.length - 1);
    }

    public TreeNode child(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = child(nums, left, mid - 1);
        root.right = child(nums, mid + 1, right);
        return root;
    }
}
