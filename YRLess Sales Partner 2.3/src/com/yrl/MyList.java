package com.yrl;

import java.util.Comparator;

/**
 * Authors: Jaden Miller Immanuel Soh Emails: jmiller144@huskers.unl.edu
 * isoh2@huskers.unl.edu
 * 
 * Date: 2024/05/03
 * 
 * Description: This class is the implementation of a custom sorted linked list ADT.
 */

public class MyList<T> {

	private Node<T> head;
	private int size;
	private Comparator<T> comparator;

	/**
	 * Creates an initially empty linked list.
	 */
	public MyList() {
		this.head = null;
		this.size = 0;
	}

	/**
	 * Creates an initially empty linked list that is sorted by a comparator c.
	 * @param c is used to pass in a comparator to sort items in this linked list as they are added.
	 */
	public MyList(Comparator<T> c) {
		this.head = null;
		this.size = 0;
		this.comparator = c;
	}

	/**
	 * Handles the logic of adding new nodes to the end of a linked list.
	 * @param x is used to pass in a value for a new node added to the end of a linked list.
	 */
	public void add(T x) {
		Node<T> newNode = new Node<>(x);
		if (this.isEmpty()) {
			this.head = newNode;
		} else {
			int index = 0;
			while (index < size && this.comparator.compare(x, get(index)) > 0) {
				index++;
			}
			add(index, x);
		}
		this.size++;
	}

	/**
	 * Adds the given element x to this list at the given index
	 */
	private void add(int index, T x) {
		if (index < 0) {
			throw new IllegalArgumentException("cannot have negative indices");
		} else if (index > this.size) {
			throw new IllegalArgumentException("indices cannot exceed the size");
		}

		Node<T> newNode = new Node<>(x);

		if (index == 0) {
			newNode.setNext(this.head);
			this.head = newNode;
		} else {
			Node<T> previous = this.getNode(index - 1);
			Node<T> indexNode = previous.getNext();
			previous.setNext(newNode);
			newNode.setNext(indexNode);
		}
		return;
	}

	/**
	 * Retrieves and returns the element at the given index
	 */
	public T get(int index) {
		return this.getNode(index).getElement();
	}

	/**
	 * Replaces the element at the given index with the given element x.
	 */
	private void replace(int index, T x) {
		if (!boundCheck(index)) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		Node<T> node = getNode(index);
		node.setElement(x);
	}

	/**
	 * Removes and returns the element at the given index
	 */
	private T remove(int index) {
		if (!boundCheck(index)) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		if (index == 0) {
			T data = head.getElement();
			head = head.getNext();
			return data;
		}
		Node<T> prevNode = getNode(index - 1);
		Node<T> currentNode = prevNode.getNext();
		prevNode.setNext(currentNode.getNext());
		return currentNode.getElement();
	}

	/**
	 * Returns true if this list is empty (no elements), false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> currentNode = this.head;
		while (currentNode.getNext() != null) {
			sb.append(currentNode.getElement());
			sb.append(", ");
			currentNode = currentNode.getNext();
		}
		sb.append(currentNode.getElement());
		sb.append(" ]");
		return sb.toString();
	}

	private Node<T> getNode(int index) {

		Node<T> currentNode = this.head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	public int getSize() {
		return this.size;
	}

	private boolean boundCheck(int index) {
		return index >= 0 && index < this.getSize();
	}
}