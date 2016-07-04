package com.kuta.demo.doublelinkedlist;

public class DoublyLinkedList<T> {

    // 双向链表的头指针
    private Node<T> head;

    // 双向链表的尾指针
    private Node<T> tail;

    public DoublyLinkedList() {
        head = new Node<T>();
        tail = new Node<T>();
    }

    // 添加节点到链表的头部
    public void addToHead(Node<T> node) {
        if (null == head.next) {
            head.next = node;
            tail.prev = node;
        } else {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
    }

    // 添加节点到链表的尾部
    public void addToTail(Node<T> node) {
        if (null == tail.prev) {
            tail.prev = node;
            head.next = node;
        } else {
            tail.prev.next = node;
            node.prev = tail.prev;
            tail.prev = node;
        }
    }

    // 链表的遍历(从head顺序遍历)
    public void traversalFromHead() {
        if (isEmpty()) {
            System.out.println("The Doubly Linked List is empry");
        } else {
            Node<T> node = head;
            while (null != node.next) {
                System.out.print(node.next + "-->");
                node = node.next;
            }
            System.out.println();
        }
    }

    // 链表的遍历(从tail倒序遍历)
    public void traversalFromTail() {
        if (isEmpty()) {
            System.out.println("The Doubly Linked List is empty");
        } else {
            Node<T> node = tail;
            while (null != node.prev) {
                System.out.print(node.prev+ "-->");
                node = node.prev;
            }
            System.out.println();
        }
    }

    // 添加某个值到指定的数值的节点后面
    public void insertAfter(Node<T> node, T key) {
        if (null == head.next || null == tail.prev) {
            System.out.println("The Doubly Linked List is empty");
        } else {
            Node<T> theNode = head;
            while (null != theNode.next) {
                if (theNode.next.data.equals(key)) {
                    node.next = theNode.next.next;
                    theNode.next.next.prev = node;
                    theNode.next.next = node;
                    node.prev = theNode.next;
                    break;
                }
                theNode = theNode.next;
            }
        }
    }

    // 添加某个值到指定的数值的节点前面
    public void insertBefore(Node<T> node, T key) {
        if (null == head.next || null == tail.prev) {
            System.out.println("The Doubly Linked List is empty");
        } else {
            Node<T> theNode = head;
            while (null != theNode.next) {
                if (theNode.next.data.equals(key)) {
                    node.next = theNode.next;
                    theNode.next.prev = node;
                    theNode.next = node;
                    node.prev = theNode;
                    break;
                }
                theNode = theNode.next;
            }
        }
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        if (null == head.next || null == tail.prev) {
            return true;
        }
        return false;
    }

}
