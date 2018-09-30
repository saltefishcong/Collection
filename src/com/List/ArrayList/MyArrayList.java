package com.List.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements java.io.Serializable, Iterable<E> {

	private static final int DEFAULT_NUM = 10;

	private static final Object[] DEFAULT_ELEMENTDATA = {};

	transient private Object[] element;

	private int size;

	public MyArrayList() {
		element = DEFAULT_ELEMENTDATA;
	}

	public MyArrayList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		element = new Object[capacity];
	}

	public E get(int index) {
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException("element out" + index);
		}
		return (E) element[index];
	}

	public int size() {
		return size;
	}

	public E set(int index, E newVal) {
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException("element out" + index);
		}
		E oldVal = (E) element[index];
		element[index] = newVal;
		return oldVal;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		size = 0;
		ensureCapacity(DEFAULT_NUM);
	}

	public void ensureCapacity(int newCapacity) {
		if (newCapacity < size) {
			return;
		}
		E[] oldElement = (E[]) element;
		element = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++)
			element[i] = oldElement[i];
	}

	public boolean add(E newVal) {
		if (element.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		element[size] = newVal;
		size++;
		return true;
	}

	public void add(E newVal, int index) {
		if (element.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		for (int i = size; i > index; i--) {
			element[i] = element[i - 1];
		}
		element[index] = newVal;
		size++;
	}

	public E remove(int index) {
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException("element out" + index);
		}
		E oldVal = (E) element[index];
		for (int i = index; i < size - 1; i++) {
			element[i] = element[i + 1];
		}
		element[--size] = null;
		return oldVal;
	}

	public void sort() {
		ensureCapacity(size);
		Arrays.sort(element);
	};

	public void toStrings() {
		System.out.println(Arrays.toString(element));
	}

	public Iterator<E> iterator() {
		return new MyArraylistIterator();
	}

	private class MyArraylistIterator implements Iterator<E> {

		private int current;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < size();
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			return (E) element[current++];
		}

		public void remove() {
			MyArrayList.this.remove(--current);
		}
	}
}