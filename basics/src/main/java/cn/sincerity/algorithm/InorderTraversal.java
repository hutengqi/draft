package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * InorderTraversal: 中序遍历树节点
 * <p>给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。</p>
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        return list;
    }

    public void traversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);
    }
}
