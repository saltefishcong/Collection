package com.List.Tree;

import java.util.Iterator;

public class balanceTree<T extends Comparable<T>> implements Iterable<T> {

	protected Node<T> root;

	public balanceTree() {
	}

	public boolean isEmpty() {
		return root == null;
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

	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	private int Maxheight(int left, int right) {
		return left > right ? left : right;
	}

	public boolean contains(T data) {
		return contain(data, root);
	}

	public void add(T data) {
		root = adds(data, root);
	}

	public void Traverse() {
		PreorderTraverse(root);
	}

	public void remove(T data) {
		removes(data, root);
	}

	private Node<T> adds(T data, Node<T> node) {
		if (isEmpty()) {
			root = new Node<T>(data, null, null, 1);
			return root;
		}
		if (node == null) {
			node = new Node<T>(data, null, null, 1);
		} else if (data.compareTo(node.data) > 0) {
			node.right = adds(data, node.right);
			if (height(node.right) - height(node.left) > 1) {
				if (height(node.right.right) >= height(node.right.left)) {
					node = right_right_Rotate(node);
				} else {
					node = right_left_Rotate(node);
				}
			}
		} else if (data.compareTo(node.data) < 0) {
			node.left = adds(data, node.left);
			if (height(node.left) - height(node.right) > 1) {
				if (height(node.left.left) >= height(node.left.right)) {
					node = left_left_Rotate(node);
				} else {
					node = left_right_Rotate(node);
				}
			}
		} else {
			return null;
		}
		node.height = Maxheight(height(node.left), height(node.right)) + 1;
		return node;
	}

	private Node<T> removes(T data, Node<T> node) {
		if (isEmpty()) {
			return null;
		}
		if (data.compareTo(node.data) > 0) {
			node.right = removes(data, node.right);
			if (height(node.left) - height(node.right) > 1) {
				if (height(node.left.left) >= height(node.left.right)) {
					node = left_left_Rotate(node);
				} else {
					node = left_right_Rotate(node);
				}
			}
		} else if (data.compareTo(node.data) < 0) {
			node.left = removes(data, node.left);
			if (height(node.right) - height(node.left) > 1) {
				if (height(node.right.right) >= height(node.right.left)) {
					node = right_right_Rotate(node);
				} else {
					node = right_left_Rotate(node);
				}
			}
		} else if (node.left != null && node.right != null) {
			node.data = findMin(node.right).data;
			node.right = removes(node.data, node.right);
		} else {
			node = (node.left != null) ? node.left : node.right;
		}
		return node;
	}

	private Node<T> left_left_Rotate(Node<T> node) {
		Node<T> nw = node.left;
		node.left = nw.right;
		nw.right = node;
		node.height = Maxheight(height(node.left), height(node.right)) + 1;
		nw.height = Maxheight(height(nw.left), node.height) + 1;
		return nw;
	}

	private Node<T> right_right_Rotate(Node<T> node) {
		Node<T> nw = node.right;
		node.right = nw.left;
		nw.left = node;
		node.height = Maxheight(height(node.left), height(node.right)) + 1;
		nw.height = Maxheight(height(nw.right), node.height) + 1;
		return nw;
	}

	private Node<T> left_right_Rotate(Node<T> node) {
		node.left = right_right_Rotate(node.left);
		return left_left_Rotate(node);
	}

	private Node<T> right_left_Rotate(Node<T> node) {
		node.right = left_left_Rotate(node.right);
		return right_right_Rotate(node);
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

	private class Node<T> {

		private T data;

		private Node<T> left;

		private Node<T> right;

		private int height;

		public Node(T data, Node<T> left, Node<T> right, int height) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.height = height;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
