package com.List.Hash;

import java.util.LinkedList;
import java.util.List;

public class LinkHashTable<T> {

	private List<T>[] list; // hash表的链表

	private static final int DEFUALT_NUM = 11;

	private int size;

	public LinkHashTable() {
		// TODO Auto-generated constructor stub
		this(DEFUALT_NUM);
	}

	public LinkHashTable(int size) {
		list = new LinkedList[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = new LinkedList<T>();
		}
		size = 0;
	}

	public int size() { // 获取hash表的总元素
		return size;
	}

	public void makeEmpty() {
		for (int i = 0; i < list.length; i++)
			list[i].clear();
		size = 0;
	}

	public void insert(T data) { // 插入
		int index = myhash(data);
		if (!list[index].contains(data)) {
			list[index].add(data);
			size++;
		}
	}

	public void remove(T data) { // 删除
		int index = myhash(data);
		if (list[index].contains(data)) {
			list[index].remove(data);
			size--;
		}
	}

	public boolean contains(T data) { // 包含元素
		int index = myhash(data);
		return list[index].contains(data);
	}

	public void rehash() { // 重新hash
	}

	private int myhash(T data) { // 获取hash
		int hash = data.hashCode();
		hash %= list.length;
		if (hash < 0)
			hash += list.length;
		return hash;
	}
}
