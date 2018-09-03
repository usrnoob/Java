package com.algorithm;

import lombok.Data;

/**
 *	定义链表Node
 */
@Data
public class Node {
	private final int value;
	private Node next;

	public Node(int value) {
		this.value = value;
		this.next = null;
	}

	/**
	 * 打印
	 */
	public static void printLinkedList(Node head) {
		while (head != null) {
			System.out.print(head.getValue());
			System.out.print(" ");
			head = head.getNext();
		}
		System.out.println();
	}
}
