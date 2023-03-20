package cn.sincerity.algorithm;

/**
 * TwoNumberAdd
 *
 * @author Ht7_Sincerity
 * @date 2023/3/20
 */
public class TwoNumberAdd {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum, nextDigit = 0, currentDigit;
        ListNode node = new ListNode(0);
        ListNode head = node;
        while (l1 != null && l2!= null) {
            sum = l1.val + l2.val + nextDigit;
            currentDigit = sum % 10;
            nextDigit = sum / 10;
            node.next = new ListNode(currentDigit);
            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            sum = l1.val + nextDigit;
            currentDigit = sum % 10;
            nextDigit = sum / 10;
            node.next = new ListNode(currentDigit);
            node = node.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            sum = l2.val + nextDigit;
            currentDigit = sum % 10;
            nextDigit = sum / 10;
            node.next = new ListNode(currentDigit);
            node = node.next;
            l2 = l2.next;
        }
        if (nextDigit != 0) {
            node.next = new ListNode(nextDigit);
        }
        return head.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
