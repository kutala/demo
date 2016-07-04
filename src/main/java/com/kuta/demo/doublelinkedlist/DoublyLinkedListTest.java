package com.kuta.demo.doublelinkedlist;

import org.junit.Test;

public class DoublyLinkedListTest {

    @Test
    public void test() {

        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 6; i++) {
            Node<Integer> node = new Node<Integer>();
            node.data = i;
            doublyLinkedList.addToHead(node);
        }
        doublyLinkedList.traversalFromHead();
        doublyLinkedList.traversalFromTail();
        System.out.println("---------------------------");
        for (int i = 10; i < 16; i++) {
            Node<Integer> node = new Node<Integer>();
            node.data = i;
            doublyLinkedList.addToHead(node);
        }
        Node<Integer> node = new Node<Integer>();
        node.data = 88;
        doublyLinkedList.insertAfter(node, 11);
        doublyLinkedList.traversalFromHead();
        doublyLinkedList.traversalFromTail();
        
        Node<Integer> node1 = new Node<Integer>();
        node1.data = 99;
        doublyLinkedList.insertBefore(node1, 14);
        doublyLinkedList.traversalFromHead();
        doublyLinkedList.traversalFromTail();
    }
}