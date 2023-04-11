package cn.sincerity.algorithm;

import cn.sincerity.algorithm.model.ListNode;

/**
 * DeleteDuplicates:
 *
 * @author Ht7_Sincerity
 * @date 2023/4/3
 */
public class DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode current = head;
        while (current.next != null) {
            while (current.next != null && current.val == current.next.val) {
                current.next = current.next.next;
            }
            if (current.next != null)
                current = current.next;
        }
        return head;
    }
}
