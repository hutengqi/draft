package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.TreeNode;
import lombok.val;

/**
 * SameTree: 相同的树
 * <p>
 * 你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <br/>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * </p>
 *
 * @author Ht7_Sincerity
 * @date 2023/4/12
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        if (p.val != q.val)
            return false;

        return isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
    }

}
