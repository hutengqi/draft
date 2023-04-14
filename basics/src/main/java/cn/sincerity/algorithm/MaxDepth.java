package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;

/**
 * MaxDepth:  二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <br/>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/4/14
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        return dps(root, 0);
    }

    public int dps(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        int lDepth = dps(root.left, depth);
        int rDepth = dps(root.right, depth);
        return Math.max(lDepth, rDepth);
    }
}
