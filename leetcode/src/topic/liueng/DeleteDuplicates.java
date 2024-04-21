package topic.liueng;

import java.util.HashSet;

public class DeleteDuplicates {
    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        System.out.println(listNode.insert(1));
        System.out.println(listNode.insert(1));
        System.out.println(listNode.insert(1));
        System.out.println(listNode.insert(2));
        System.out.println(listNode.insert(2));
        System.out.println(listNode.insert(3));
        System.out.println(listNode.insert(3));
        System.out.println(listNode.insert(4));
        System.out.println(listNode.insert(4));

        System.out.println(deleteDuplicates(listNode.head));

        Node head = listNode.head;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static Node deleteDuplicates(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    public static class Node {
        private int val;
        private Node next;

        public Node(int value) {
            this.val = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static class ListNode {
        private Node head;

        public ListNode() {
            this.head = null;
        }

        public Node insert(int value) {
            Node newNode = new Node(value);
            if (this.head == null) {
                this.head = newNode;
            } else {
                Node node = this.head;
                while (node.next != null) {
                    node = node.next;
                }
                node.next = newNode;
            }
            return newNode;
        }
    }
}
