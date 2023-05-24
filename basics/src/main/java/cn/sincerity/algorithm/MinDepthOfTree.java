package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;

/**
 * MinDepthOfTree: 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <br/>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <br/>
 * 说明：叶子节点是指没有子节点的节点。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/4/19
 */
public class MinDepthOfTree {

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right != null)
            return 1 + minDepth(root.right);

        if (root.left != null && root.right == null)
            return 1 + minDepth(root.left);

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
