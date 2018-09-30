package com.List.Srack;

import java.util.Iterator;

public class ListStack<T> implements Iterable<T> {
	// 链表实现的栈重点是让栈顶永远是头节点或者尾节点,就是当插入一个新节点的时候,让这个新节点成为首节点

	private int size;

	private Node<T> prev;

	public ListStack() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void setFirst(Node newFirst) {
		prev = newFirst;
	}

	public Node<T> getFirst() {
		return prev;
	}

	public void init() {
		size = 0;
		prev = new Node<T>(null, null, null);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void push(T data) {
		if (size() == 0) {
			prev.data = data;
			size++;
			return;
		}
		Node<T> oldFirst = getFirst();
		Node<T> newFirst = new Node<T>(data, null, oldFirst);
		oldFirst.prev = newFirst;
		setFirst(newFirst);
		size++;
	}

	public T pop() {
		if (size == 1) {
			T data = prev.data;
			prev.data = null;
			size--;
			return data;
		}
		Node<T> oldFirst = getFirst();
		Node<T> newFirst = oldFirst.next;
		newFirst.prev = null;
		oldFirst.next = null;
		setFirst(newFirst);
		size--;
		return oldFirst.data;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListStackIterator();
	}

	private class Node<T> {

		private T data;

		private Node<T> prev;

		private Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	private class ListStackIterator implements Iterator<T> {

		private int current = 0;

		private Node<T> node = ListStack.this.prev;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < size;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if (current == 0) {
				current++;
				return node.data;
			}
			node = node.next;
			current++;
			return node.data;
		}

	}
}
