package com.List.Queue;

import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T> {
	/*
	 * queue_head记录着队列的头部,queue_end记录着队列的尾部,添加一个元素就让队尾+1 size++, 删除元素就让队头+1
	 * size--, 最重要的是要%数组的长度找出队头队尾的存储元素位置
	 */

	private static final int DEFUALT_NUM = 5;

	private T[] queue;

	private int queue_head = 0;

	private int queue_end = 0;

	private int size;

	public ArrayQueue() {
		// TODO Auto-generated constructor stub
		queue = (T[]) new Object[DEFUALT_NUM];
		size = 0;
	}

	public ArrayQueue(int capacity) {
		queue = (T[]) new Object[capacity];
		size = 0;
	}

	public void ensureCapacity(int newcapacity) {
		if (newcapacity < queue.length) {
			return;
		}
		newcapacity = queue.length + (queue.length >> 1);
		T[] oldqueue = queue;
		T[] newqueue = (T[]) new Object[newcapacity];
		for (int i = 0; i < size; i++) {
			newqueue[i] = oldqueue[i];
		}
		queue = newqueue;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void add(T data) {
		int end_index = queue_end % queue.length; // 用queue_end数组长度找出元素在数组存储的位置
		// 当计算出的存储位置上有元素和队尾的长度减去队头的长度等于数组的长度二者同时满足的时候就证明队列的数组已经满了
		// size等于queue.length同时也可以证明数组已经满了
		if ((queue[end_index] != null && queue_end - queue_head == queue.length) || size == queue.length)
			throw new RuntimeException("队列已经满了");
		queue[end_index] = data; // 设置值
		queue_end++; // 队尾+1
		size++; // size+1
	}

	public T remove() {
		if (size == 0) { // 判断队列有没有元素
			throw new RuntimeException("队列没有元素");
		}
		int head_index = queue_head % queue.length; // 用queue_head%数组长度得到要出队元素的存储位置
		// 当计算出来的存储位置上没有元素和队尾长度等于队头元素就证明队列没有元素了
		// size等于0也证明队列没有元素
		if ((queue[head_index] == null && queue_end == queue_head) || size == 0)
			throw new RuntimeException("队列没有元素");
		T data = queue[head_index]; // 获取出队的元素
		queue[head_index] = null; // 设置存储位置为空
		queue_head++; // 队头+1
		size--; // size --
		return data; // 返回出队的元素
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayQueueIterator();
	}

	private class ArrayQueueIterator implements Iterator<T> {

		private int current = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < queue.length;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return ArrayQueue.this.queue[current++];
		}

	}

}
