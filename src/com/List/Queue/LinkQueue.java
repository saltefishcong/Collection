package com.List.Queue;

import java.util.Iterator;

public class LinkQueue<T> implements Iterable<T> {

	private Node<T> prev;

	private Node<T> next;

	private int size;

	public LinkQueue() {
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		size = 0;
		prev = new Node<T>(null, null, null);
		next = new Node<T>(null, null, null);
		prev.next = next;
		next.prev = prev;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void setFirst(Node<T> newnode) {
		prev = newnode;
	}

	private Node<T> getFirst() {
		return prev;
	}

	private void setLast(Node<T> newnode) {
		next = newnode;
	}

	private Node<T> getLast() {
		return next;
	}

	public void add(T data) {
		if (size() == 0) {
			getFirst().data = data;
			size++;
			return;
		}
		if (size() == 1) {
			next.data = data;
			size++;
			return;
		}
		Node oldnext = getLast();
		Node newnext = new Node(data, oldnext, null);
		oldnext.next = newnext;
		next = newnext;
		size++;
		return;
	}

	public T remove() {
		if (size == 0) {
			return null;
		}
		Node oldfirst = getFirst();
		Node newfirst = oldfirst.next;
		oldfirst.next = null;
		newfirst.prev = null;
		setFirst(newfirst);
		size--;
		return (T) oldfirst.data;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkQueueIterator();
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

	private class LinkQueueIterator<T> implements Iterator<T> {

		private int current = 0;

		private Node node = LinkQueue.this.prev;

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
				return (T) node.data;
			}
			node = node.next;
			current++;
			return (T) node.data;
		}

	}

}
