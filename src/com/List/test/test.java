package com.List.test;

import java.io.IOException;

import com.List.Heap.MaxHeap;

public class test {

	static volatile boolean flag = false;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MaxHeap<Integer> max = new MaxHeap<Integer>(11);
		max.add(3);
		max.add(6);
		max.add(31);
		max.add(18);
		max.add(99);
		max.add(81);
		max.add(23);
		max.add(15);
		max.add(63);
		max.add(2);
		max.add(67);
		max.add(62);
		System.out.println(max.length() + "   " + max.size());
		max.print();
		max.remove();
		System.out.println(max.length() + "   " + max.size());
		max.print();
		max.remove();
		System.out.println(max.length() + "   " + max.size());
		max.print();
	}

	class B {
		private String name = "B";

		public void b() {
			System.out.println("B  " + this + "  " + test.this);
		}
	}

	static class A {
		private static String name = "A";

		public void a() {
			System.out.println("B  " + this);
		}
	}

	public static double[] bubble(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					double temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
		return arr;
	}
}
