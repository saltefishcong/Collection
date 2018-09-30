package com.List.Tree;

import java.util.Iterator;

public class stretchTree<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> root;

	public stretchTree() {
		// TODO Auto-generated constructor stub
	}

	public boolean isEpmty() {
		return root == null;
	}

	public void makeEpmty() {
		root = null;
	}

	public void Traverse() {
		PreorderTraverse(root);
	}

	public boolean contains(T data) {
		return contain(data, root);
	}

	public void add(T data) {
		root = adds(data, root);
	}

	public void remove(T data) {
		root = removes(data, root);
	}

	public void select(T data) {
		root = search(data, root);
	}

	private boolean contain(T data, Node<T> node) {
		if (node == null)
			return false;
		else if (data.compareTo(root.data) == 0 || data.compareTo(node.data) == 0)
			return true;
		else if (data.compareTo(node.data) > 0)
			return contain(data, node.right);
		else if (data.compareTo(node.data) < 0)
			return contain(data, node.left);
		return false;
	}

	private Node<T> adds(T data, Node<T> node) {
		if (node == null)
			return new Node<T>(data, null, null);
		else if (data.compareTo(node.data) > 0)
			node.right = adds(data, node.right);
		else if (data.compareTo(node.data) < 0)
			node.left = adds(data, node.left);
		else
			return null;
		return node;
	}

	private Node<T> removes(T data, Node<T> node) {
		if (node == null)
			return null;
		else if (data.compareTo(node.data) > 0)
			node.right = removes(data, node.right);
		else if (data.compareTo(node.data) < 0)
			node.left = removes(data, node.left);
		else if (node.left != null && node.right != null) {
			node.data = findMin(node.right).data;
			node.right = removes(node.data, node.right);
		} else
			node = (node.left != null) ? node.left : node.right;
		return node;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node<T> left_left_Rotate(Node<T> node) {
		Node<T> nw = node.left;
		node.left = nw.right;
		nw.right = node;
		return nw;
	}

	private Node<T> right_right_Rotate(Node<T> node) {
		Node<T> nw = node.right;
		node.right = nw.left;
		nw.left = node;
		return nw;
	}

	private Node<T> search(T data, Node<T> node) {
		if (node == null)
			return node;
		Node<T> N = new Node<T>(null, null, null);
		Node<T> l = N;
		Node<T> r = N;
		Node<T> c = null;
		for (;;) {
			int cmp = data.compareTo(node.data);
			if (cmp < 0) {
				if (data.compareTo(node.left.data) < 0) {
					node = left_left_Rotate(node); /* rotate right */
					if (node.left == null)
						break;
				}
				r.left = node; /* link right */
				r = node;
				node = node.left;
			} else if (cmp > 0) {
				if (node.right == null)
					break;
				if (data.compareTo(node.right.data) > 0) {
					node = right_right_Rotate(node); /* rotate left */
					if (node.right == null)
						break;
				}

				l.right = node; /* link left */
				l = node;
				node = node.right;
			} else {
				break;
			}
		}
		l.right = node.left; /* assemble */
		r.left = node.right;
		node.left = N.right;
		node.right = N.left;
		return node;
	}

	private Node<T> findMin(Node<T> node) {
		if (isEpmty()) {
			throw new RuntimeException("树为空");
		}
		if (node == null)
			return null;
		return (node != null && node.left == null) ? node : findMin(node.left);
	}

	private Node<T> findMax(Node<T> node) {
		if (isEpmty()) {
			throw new RuntimeException("树为空");
		}
		if (node == null)
			return null;
		return (node != null && node.right == null) ? node : findMin(node.right);
	}

	public void PreorderTraverse(Node<T> node) {
		if (node != null && node.data != null) {
			System.out.print(node.data + "  ");
			PreorderTraverse(node.left);
			PreorderTraverse(node.right);
		}
	}

	private class Node<T> {

		private T data;

		private Node<T> left;

		private Node<T> right;

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

}
