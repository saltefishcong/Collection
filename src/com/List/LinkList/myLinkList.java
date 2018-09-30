package com.List.LinkList;

import java.util.Iterator;

public class myLinkList<T> implements Iterable<T> {

	private Node<T> first; // 首节点

	private Node<T> last; // 尾节点

	private int mcount; // 迭代器计时器

	private int size; // 链表长度

	public myLinkList() {
		init(); // 初始化
	}

	private void init() {
		size = 0; // 长度设置为0
		first = new Node<T>(null, null, null); // 初始化首节点
		last = new Node<T>(null, first, null); // 初始化尾节点
		first.next = last; // 将首节点的下索引指向尾节点
		mcount++;
	}

	public int size() {
		return size; // 返回链表长度
	}

	public boolean isEmpty() { // 判断链表是否为空
		return size == 0;
	}

	public void clear() { // 清除链表
		init();
	}

	private Node<T> getFirst() { // 获取首节点
		return first;
	}

	private Node<T> getLast() { // 获取尾节点
		return last;
	}

	private void setFirstData(T newdata) { // 设置首节点的值
		first.data = newdata;
		size++; // 说明首节点要被使用了,所以size++
	}

	private void setLastData(T newdata) { // 设置尾节点的值
		last.data = newdata;
		size++; // 说明尾节点被使用了,所以size++
	}

	private void setFirst(Node<T> newfirst) { // 设置首节点
		this.first = newfirst;
	}

	private void setLast(Node<T> newlast) { // 设置尾节点
		this.last = newlast;
	}

	private Node<T> getNode(int index) { // 根据index返回指定节点
		checkIndex(index); // 检查Index是否越界
		Node<T> node = getFirst(); // 获取首节点
		for (int i = 0; i < index; i++) { // 使用首节点遍历
			node = node.next;
		}
		return node; // 返回的节点为Index位置上的节点
	}

	private Node<T> getNode(Object data) {
		// 根据指定的对象获取节点,使用Object而不是用泛型T的原因是,泛型T == 对比返回为假,而用Object.equals()为true
		Node<T> node = getFirst(); // 获取首节点
		if (data.equals(node.data)) { // 判断首节点的值是否为传进对象的值
			return node;
		}
		for (int i = 1; i < size; i++) { // 从1开始循环,因为首节点的值已经判断过了
			node = node.next;
			if (node != null && data.equals(node.data)) { // 判断是否有相同的对象
				return getNode(i);
			}
		}
		return null; // 没有返回null
	}

	private int indexOf(Object data) { // 返回对象在链表的位置
		Node<T> node = getFirst(); // 获取首节点
		if ((data.equals(node.data))) { // 判断首节点的值是否为传进对象的值
			return 0;
		}
		for (int i = 1; i < size; i++) { // 从1开始循环,因为首节点的值已经判断过了
			node = node.next;
			if (node != null && data.equals(node.data)) { // 判断是否有相同的对象
				return i;
			}
		}
		return -1; // 没有包含对象就返回-1
	}

	public boolean contains(T data) { // 判断是否包含对象
		return indexOf(data) >= 0 ? true : false;
	}

	public T set(int index, T newdata) { // 设置节点的值
		Node<T> node = getNode(index); // 获取节点
		T oldData = node.data; // 存储节点的旧值
		node.data = newdata; // 设置节点的新值
		return oldData; // 返回旧值
	}

	public T get(int index) { // 获取节点的值
		Node<T> node = getNode(index); // 获取节点
		return node.data; // 返回节点值
	}

	public void checkIndex(int index) { // 检查数值是否越界
		if (index > size || index < 0) {
			throw new RuntimeException("输入下表无效");
		}
	}

	private void addLast(T newdata) { // 在链表末尾添加节点
		Node<T> oldlast = getLast(); // 获取尾节点
		Node newLast = new Node<T>(newdata, oldlast, null); // 创建新的尾节点
		oldlast.next = newLast; // 把旧的尾节点下索引指向新的为尾节点
		setLast(newLast); // 把新的尾节点设置成当前尾节点
		size++; // 因为有新的节点加入,所以size++
	}

	private void addFirst(T newdata) { // 在链表头部添加节点
		Node<T> first = getFirst(); // 获取首节点
		Node<T> newnfirst = new Node<T>(newdata, null, null); // 创建新的首节点
		first.prev = newnfirst; // 把旧的首节点上索引设置新的首节点
		newnfirst.next = first; // 新的首节点下索引指向旧的首节点
		setFirst(newnfirst); // 把新的首节点设置成当前首节点
		size++; // 因为有新的节点加入,所以size++
	}

	public void add(T newdata) { // 添加新的节点到链表末尾
		if (size == 0) { // 判断是否为第一次添加
			setFirstData(newdata); // 直接设置首节点的值
			return;
		}
		if (size == 1) { // 判断是否为第二次添加
			setLastData(newdata); // 直接设置尾节点的值
			return;
		}
		addLast(newdata); // 直接添加新的节点到链表末尾
	}

	public void add(T newdata, int index) { // 在指定位置添加新节点
		if (size == 0) { // 判断是否为第一次添加
			setFirstData(newdata); // 直接设置首节点的值
			return;
		}
		if (size == 1) { // 判断是否为第二次添加
			setLastData(newdata); // 直接设置尾节点的值
			return;
		}
		if (index == size) { // 判断指定的位置是否为链表的大小
			addLast(newdata); // 直接在链表的末尾添加新的节点
			return;
		}
		Node<T> Node = getNode(index); // 获取指定节点
		Node<T> index_prev = Node.prev; // 获取指定节点的上一个节点
		if (index_prev == null) { // 判断上一个节点是否为空
			addFirst(newdata); // 为空在链表的头部添加新的节点
			return;
		}
		Node<T> newnode = new Node<T>(newdata, null, null); // 创建新的节点
		Node.prev = newnode; // 将指定位置的节点上索引指向新节点
		newnode.next = Node; // 将新节点的下索引指向指定位置的节点
		newnode.prev = index_prev; // 将新节点的上索引指向指定节点的上一个节点
		index_prev.next = newnode; // 指定节点的上一个节点的下索引指向新节点
		size++; // 因为有新的节点加入,所以size++
	}

	private Node<T> removeLast() { // 删除当前尾节点
		Node<T> oldlast = getLast(); // 获取尾节点
		Node<T> newLast = oldlast.prev; // 获取尾节点的上一个节点
		newLast.next = null; // 将尾节点的上一个节点的下索引设置为空
		oldlast.prev = null; // 将尾节点的上索引设置为空
		setLast(newLast); // 设置新的尾节点
		size--; // 删除了节点,所以size--
		return oldlast; // 返回删除的尾节点
	}

	private Node<T> removeFirst() { // 删除当前首节点
		Node<T> oldfirst = getFirst(); // 获取首节点
		Node<T> newFirst = oldfirst.next; // 获取首节点的下一个节点
		newFirst.prev = null; // 设置首节点的下一个节点的上索引为空
		oldfirst.next = null; // 设置首节点的下索引为空
		setFirst(newFirst); // 设置新的首节点
		size--; // 删除了节点,size--
		return oldfirst; // 返回删除的首节点
	}

	public T remove(T data) { // 根据指定对象删除节点
		if (size <= 0) { // 判断链表是否为空
			throw new RuntimeException("集合没有元素");
		}
		Node<T> node = getNode(data); // 获取指定对象的节点
		if (node == null) { // 判断链表是否包含节点
			throw new RuntimeException("没有这个元素");
		}
		if (node.prev == null) { // 判断节点的上索引是否为空
			return removeFirst().data; // 删除首节点并返回首节点的值
		}
		if (node.next == null) { // 判断节点的下索引是否为空
			return removeLast().data; // 删除尾节点并返回尾节点的值
		}
		Node<T> node_next = node.next; // 获取节点的下索引节点
		Node<T> node_prev = node.prev; // 获取节点的上索引节点
		node_prev.next = node_next; // 将上索引节点的下索引设置为下索引节点
		node_next.prev = node_prev; // 将下索引节点的上索引设置为上索引节点
		node.next = null; // 节点的下索引设置为空
		node.prev = null; // 节点的上索引设置为空
		size--; // 删除了节点,size--
		return node.data; // 返回删除节点的值
	}

	public T remove(int index) { // 根据指定的位置删除节点
		if (size <= 0) { // 判断链表是否为空
			throw new RuntimeException("集合没有元素");
		}
		if (index == 0) { // 判断是否删除首节点
			return removeFirst().data;
		}
		if (index == size - 1) { // 是否删除尾节点
			return removeLast().data;
		}
		Node<T> indexnode = getNode(index); // 获取指定的节点
		if (indexnode == null) { // 判断指定的节点是否为空
			throw new RuntimeException("请检查index");
		}
		Node<T> index_prev = indexnode.prev; // 获取指定节点的上索引节点
		Node<T> index_next = indexnode.next; // 获取指定节点的下索引节点
		index_prev.next = index_next; // 设置上索引节点的下索引为下索引节点
		index_next.prev = index_prev; // 设置下索引节点的上索引为上索引节点
		indexnode.next = null; // 将指定的节点下索引设置为空
		indexnode.prev = null; // 将指定的节点上索引设置为空
		size--;
		return indexnode.data; // 返回删除节点的数据
	}

	private class Node<T> {

		private T data;

		private Node<T> prev;

		private Node<T> next;

		public Node(T data, Node<T> prev, Node<T> next) {
			// TODO Auto-generated constructor stub
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	@Override
	public java.util.Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new myLinkIterator();
	}

	private class myLinkIterator<T> implements Iterator<T> {

		private int current;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < size;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			Node<T> node = (Node<T>) getNode(current);
			current++;
			return (T) node.data;
		}

	}

}
