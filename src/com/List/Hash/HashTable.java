package com.List.Hash;

public class HashTable<K extends Comparable<K>, V> {

	private Node<K, V>[] node;

	private static final int DEFAULT_NUM = 11;

	private int size;

	private static final double load = 0.75;

	private static int threshold;

	public HashTable(int capacity) {
		node = new Node[capacity];
		size = 0;
		threshold = (int) Math.min(capacity * load, Integer.MAX_VALUE - 1);
	}

	public HashTable() {
		this(DEFAULT_NUM);
	}

	public int length() {
		return node.length;
	}

	public void rehash() {
		int old_capacity = node.length;
		int new_capacity = (old_capacity << 1) + 1;
		Node<K, V>[] new_node = new Node[new_capacity];
		threshold = (int) Math.min(new_capacity * load, Integer.MAX_VALUE - 1);
		for (int i = 0; i < old_capacity; i++) {
			for (Node<K, V> no = node[i]; no != null;) {
				Node<K, V> old = no;
				no = no.next;
				int index = (old.hash & 0x7FFFFFFF) % new_node.length;
				old.next = new_node[index];
				new_node[index] = old;
			}
		}
		node = new_node;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object v) {
		if (v == null) {
			throw new RuntimeException("没有包含空值");
		}
		for (int i = 0; i < node.length; i++) {
			for (Node<K, V> no = node[i]; no != null; no = no.next) {
				if (no.value.equals(v)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean containKey(Object k) {
		int hash = k.hashCode();
		int index = hash % node.length;
		for (Node<K, V> no = node[index]; no != null; no = no.next) {
			if ((no.hash == hash) && no.key.equals(k)) {
				return true;
			}
		}
		return false;
	}

	private void add(int hash, K k, V v, int index) {
		if (size >= threshold) {
			rehash();
			index = (k.hashCode() & 0x7FFFFFFF) % node.length;
		}
		node[index] = new Node(k.hashCode(), k, v, node[index]);
		size++;
	}

	public V put(K k, V v) {
		if (v == null && k == null) {
			throw new RuntimeException("不能为空");
		}
		int hash = k.hashCode();
		int index = (hash & 0x7FFFFFFF) % node.length;
		for (Node<K, V> old = node[index]; old != null; old = old.next) {
			if (old.hash == hash && (old.key.equals(k))) {
				V oldvalue = old.value;
				old.value = v;
				return oldvalue;
			}
		}
		add(hash, k, v, index);
		return v;
	}

	public V remove(K k) {
		if (k == null) {
			throw new RuntimeException("不能为空");
		}
		int hash = k.hashCode();
		int index = hash % node.length;
		Node<K, V> no = node[index];
		for (Node<K, V> prev = null; no != null; prev = no, no = no.next) {
			if (no.hash == hash && no.key.equals(k)) {
				if (prev == null) {
					node[index] = no.next;
				} else {
					prev.next = no.next;
				}
				size--;
				return no.value;
			}
		}
		return null;
	}

	public V get(K k) {
		if (k == null) {
			throw new RuntimeException("不能为空");
		}
		int hash = k.hashCode();
		int index = hash % node.length;
		for (Node<K, V> no = node[index]; no != null; no = no.next) {
			if (no.hash == hash && no.key.equals(k)) {
				return no.value;
			}
		}
		return null;
	}

	public void clear() {
		node = new Node[DEFAULT_NUM];
		size = 0;
		threshold = (int) Math.min(DEFAULT_NUM * load, Integer.MAX_VALUE);
	}

	private class Node<K, V> {

		private int hash;

		private K key;

		private V value;

		private Node<K, V> next;

		public Node(int hash, K key, V value, Node<K, V> next) {
			// TODO Auto-generated constructor stub
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
}
