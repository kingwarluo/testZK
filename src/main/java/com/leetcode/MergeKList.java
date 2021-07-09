package com.leetcode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 *
 * @author jianhua.luo
 * @date 2021/7/9
 */
public class MergeKList {

    public static void main(String[] args) {
        String arg = args[0].substring(2, args[0].length() - 2);
        String[] arr = arg.split("],\\[");
        ListNode[] lists = new ListNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            String[] nodes = s.split(",");
            ListNode first = new ListNode(0);
            ListNode temp = first;
            for (String node : nodes) {
                ListNode listNode = new ListNode(Integer.parseInt(node));
                temp.next = listNode;
                temp = temp.next;
            }
            lists[i] = first.next;
        }
        ListNode list = mergeKLists(lists);
        System.out.println(list);
    }

    // 顺序合并，多个链表，转换成2个链表的合并
    public static ListNode mergeKLists(ListNode[] lists) {
//        return merge(lists, 0, lists.length - 1);
        return priorityQueue(lists);
    }

    // 分治思想
    public static ListNode merge(ListNode[] lists, int l, int r) {
        if(l == r) {
            return lists[l];
        }
        if(l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        return mergeTwo(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 合并两个有序链表
     * @param a
     * @param b
     * @return
     */
    public static ListNode mergeTwo(ListNode a, ListNode b) {
        if(a == null || b == null) {
            return a == null ? b : a;
        }
        ListNode head = new ListNode(0);
        ListNode aPtr = a, bPtr = b, tail = head;
        while(aPtr != null && bPtr != null) {
            if(aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        // 补充a或b的后续
        tail.next = aPtr == null ? bPtr : aPtr;
        return head.next;
    }

    // java自带优先队列
    public static ListNode priorityQueue(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((a, b) -> a.val - b.val);
        for (ListNode listNode : lists) {
            if(listNode != null) {
                pq.add(listNode);
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if(node.next != null) {
                pq.add(node.next);
            }
        }
        return head.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(){}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

}
