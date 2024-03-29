package com.yz.work.common.app.algorithms.insertionSortList;

import java.util.Arrays;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-22 14:49
 */
public class Solution {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(3, null);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(4, node2);
        Solution solution = new Solution();
        solution.insertionSortList(node1);

        // array
        int [] source = new int[]{4,2,1,3};
        System.out.println(Arrays.toString(source));
        int[] sort = solution.insertSort(source);
        System.out.println(Arrays.toString(sort));
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode curr = head.next;
        ListNode currBefore = head;
        while (curr != null) {
            if (curr.val > currBefore.val) {
                currBefore = curr;
                curr = curr.next;
            } else {
                ListNode ergodic = dummy;
                while (ergodic.next != null && ergodic.next.val < curr.val) {
                    ergodic = ergodic.next;
                }
                ListNode temp = ergodic.next;
                temp.next = curr.next;
                curr.next = ergodic.next;
                ergodic.next = curr;
                curr = temp.next;
            }
        }

        return dummy.next;
    }

    public ListNode insertionSortList1(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        if (head == null || head.next == null) return head;
        ListNode curr = head.next;
        ListNode last = head;
        while (curr != null) {
            if (curr.val >= last.val) {
                last = curr;
                curr = curr.next;
            } else {
                ListNode prev = dummy;
                while (prev.next != null && prev.next.val < curr.val) {
                    prev = prev.next;
                }
                last.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
                curr = last.next;
            }
        }
        return dummy.next;
    }

    public int[] insertSort(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length <= 1) {
            return sourceArray;
        }

        for (int i = 1; i < sourceArray.length; i++) {
            int temp = sourceArray[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temp < sourceArray[j]) {
                    sourceArray[j + 1] = sourceArray[j];
                    sourceArray[j] = temp;
                }
            }
        }
        return sourceArray;
    }
}
