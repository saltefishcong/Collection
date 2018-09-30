package com.List.Srack;

import java.util.Iterator;

public class ArrayStack<T> implements Iterable<T> {

	private static final int DEFAULT_NUM = 10;

	private T[] stack;

	private int size;

	public ArrayStack() {
		stack = (T[]) new Object[DEFAULT_NUM];
		size = 0;
	}

	public ArrayStack(int num) {
		stack = (T[]) new Object[num];
		size = 0;
	}

	public void push(T data) {
		if (size == stack.length)
			ensureCapacity(size());
		stack[size++] = data;
	}

	public T pop() {
		return stack[--size];
	}

	public int size() {
		return size;
	}

	public void ensureCapacity(int newCapacity) {
		if (newCapacity < stack.length) {
			return;
		}
		T[] oldstack = stack;
		newCapacity = newCapacity + (newCapacity >> 1);
		T[] newstack = (T[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newstack[i] = oldstack[i];
		}
		stack = newstack;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new myStackIterator();
	}

	private class myStackIterator implements Iterator<T> {

		private int current;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < size;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return ArrayStack.this.stack[current++];
		}

	}
}
