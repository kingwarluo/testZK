package com.leetcode;

public class SumInt {

    /**
     * 输入：
     * [2,4,3]
     * [5,6,4]
     * 预期：
     * [7,0,8]
     * @param args
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode dummy = new ListNode(-1), pre = dummy;
        int total = 0;
        while (l1 != null || l2 != null || total != 0) {
            if(l1 != null) {
                total += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                total += l2.val;
                l2 = l2.next;
            }
            pre.next = new ListNode(total % 10);
            pre = pre.next;
            total /= 10;
        }

        System.out.println(dummy.next);

    }

    static class ListNode {
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

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

}
