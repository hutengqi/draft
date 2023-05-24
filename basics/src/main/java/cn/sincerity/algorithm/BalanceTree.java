package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;

/**
 * BalanceTree: 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <br/>
 * 本题中，一棵高度平衡二叉树定义为：
 * <br/>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/4/18
 */
public class BalanceTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        if (Math.abs(height(root.left) - height(root.right)) > 1)
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode node) {
        if (node == null)
            return 0;

        int lHeight = height(node.left);
        int rHeight = height(node.right);
        return Math.max(lHeight, rHeight) + 1;
    }
}
