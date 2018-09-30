package com.List.Heap;

import java.util.Arrays;

public class MinHeap<T extends Comparable<T>> { // 最小堆

	private T[] arr;

	private int size;

	public MinHeap(int capacity) {
		arr = (T[]) new Comparable[capacity];
		size = 0;
	}

	public MinHeap() {
		this(8);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public int length() {
		return arr.length;
	}

	private void rehash() {
		int old_capacity = arr.length;
		int new_capacity = (old_capacity << 1) + 1;
		T[] new_arr = (T[]) new Comparable[new_capacity];
		for (int i = 1; i < old_capacity; i++) {
			new_arr[i] = arr[i];
		}
		arr = new_arr;
	}

	public void add(T data) {
		// 将要插入的元素放在数组的第一个不为null的位置上,然后循环和父节点对比,直到父节点比插入节点小
		if (isEmpty()) {
			arr[++size] = data;
			return;
		}
		if (size >= arr.length - 1) { // 判断数组是否要扩容
			rehash();
		}
		arr[++size] = data; // 将元素添加到数组的第一个不为null的元素
		int index = size;
		int old_index = 0;
		while (index > 1) {
			old_index = index;
			index = (int) (index >> 1);
			if (data.compareTo(arr[index]) < 0) { // 判断父节点是否比要插入的节点小,是就交换
				arr[old_index] = arr[index];
				arr[index] = data;
			} else // 不是就退出
				break;
		}
	}

	public T remove() { // 将最后一个节点放到第一个节点,然后和左右节点对比,直到左右节点比最后节点小为止
		T data = arr[1];
		arr[1] = arr[size];
		int index = 1;
		int new_index = 0;
		while (index < size) {
			new_index = index;
			index = (index << 1);
			T d = null;
			if (index > size) // 再次判断是有可能出现index在乘于2之后出现比size的情况
				break;
			if (index != size && arr[index + 1].compareTo(arr[index]) < 0)
				// 判断左右节点那个小,找到小节点的位置
				index++;
			if (arr[index].compareTo(arr[new_index]) < 0) { // 判断左右节点是否比父节点小,小就交换
				d = arr[new_index];
				arr[new_index] = arr[index];
				arr[index] = d;
			} else {
				// 不比父节点小就退出,因为这是个最小堆,某个节点如果不比父节点小,那么其左右节点都不可能比父节点小,正常情况下
				break;
			}
		}
		arr[size] = null;
		size--;
		return data;
	}

	public T findMin() { // 返回最小的元素
		return arr[1];
	}

	public void print() {
		System.out.println(Arrays.toString(arr));
	}
}
