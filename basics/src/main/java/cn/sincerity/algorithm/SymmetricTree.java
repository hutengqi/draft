package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;

/**
 * SymmetricTree: 对称二叉树
 *
 * @author Ht7_Sincerity
 * @date 2023/4/13
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isSame(root.left, root.right);
    }

    public boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        if (node1.val != node2.val)
            return false;
        return isSame(node1.left, node2.right) && isSame(node1.right, node2.left);
    }
}
