package com.yrl;

/**
 * Authors: Jaden Miller Immanuel Soh Emails: jmiller144@huskers.unl.edu
 * isoh2@huskers.unl.edu
 * 
 * Date: 2024/05/03
 * 
 * Description: This class represents a node for the implementation of a custom sorted linked list ADT.
 */

public class Node<T> {

	private T element;
	private Node<T> next;

	public Node(T element) {
		this.element = element;
		this.next = null;
	}

	public T getElement() {
		return this.element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return this.next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}