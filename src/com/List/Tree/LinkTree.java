package com.List.Tree;

import java.util.Iterator;

public class LinkTree<T extends Comparable<T>> implements Iterable<T> {

	protected Node<T> root;

	public LinkTree() {
		root = new Node<T>(null, null, null, null);
	}

	public boolean isEmpty() {
		return root.data == null;
	}

	private void makeEmpty() {
		root = null;
	}

	private Node<T> findMin(Node<T> node) {
		if (isEmpty()) {
			throw new RuntimeException("树为空");
		}
		if (node == null)
			return null;
		return (node != null && node.left == null) ? node : findMin(node.left);
	}

	private Node<T> findMax(Node<T> node) {
		if (isEmpty()) {
			throw new RuntimeException("树为空");
		}
		if (node == null)
			return null;
		return (node != null && node.right == null) ? node : findMin(node.right);
	}

	public boolean contains(T data) {
		return contain(data, root);
	}

	public void add(T data) {
		adds(data, root);
	}

	public Node<T> remove(T data) {
		return removes(data, root);
	}

	public void Traverse() {
		PreorderTraverse(root);
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

	private void adds(T data, Node<T> node) {
		if (node.data == null) {
			node.data = data;
			return;
		} else if (data.compareTo(node.data) == 0) {
			System.out.println("元素已经在集合了!");
			return;
		} else if (data.compareTo(node.data) > 0) {
			if (node.right == null) {
				Node<T> newnode = new Node<T>(data, null, null, node);
				node.right = newnode;
				return;
			}
			adds(data, node.right);
			return;
		} else if (data.compareTo(node.data) < 0) {
			if (node.left == null) {
				Node<T> newnode = new Node<T>(data, null, null, node);
				node.left = newnode;
				return;
			}
			adds(data, node.left);
		}
	}

	private Node<T> removes(T data, Node<T> node) {
		if (node == null || isEmpty()) {
			return null;
		}
		if (data.compareTo(node.data) > 0) {
			return removes(data, node.right);
		} else if (data.compareTo(node.data) < 0) {
			return removes(data, node.left);
		} else if (node.left != null && node.right != null) {
			Node<T> min = findMin(node.right);
			node.data = min.data;
			node.right = removes(node.data, node.right);
		} else if (node.left != null || node.right != null) {
			node = (node.left != null) ? node.left : node.right;
		} else {
			Node<T> parent = node.parent;
			if (parent.data.compareTo(node.data) > 0) {
				parent.left = null;
				return null;
			} else {
				parent.right = null;
				return null;
			}
		}
		return node;
	}

	private void MediumOrderTraverse(Node<T> node) {
		if (node != null) {
			MediumOrderTraverse(node.left);
			System.out.print(node.data + "  ");
			MediumOrderTraverse(node.right);
		}
	}

	public void PreorderTraverse(Node<T> node) {
		if (node != null && node.data != null) {
			System.out.print(node.data + "  ");
			PreorderTraverse(node.left);
			PreorderTraverse(node.right);
		}
	}

	@Override
	public java.util.Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new TreeIterator();
	}

	private class Node<T> {

		private T data;

		private Node<T> left;

		private Node<T> right;

		private Node<T> parent;

		public Node(T data, Node<T> left, Node<T> right, Node<T> parent) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}

	private class TreeIterator<T> implements Iterator<T> {

		private int current = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < 0;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
