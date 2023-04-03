package cn.sincerity.algorithm;

/**
 * ListNode: 链表
 *
 * @author Ht7_Sincerity
 * @date 2023/4/3
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
