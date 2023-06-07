import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class YG_3217 {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static LinkedList list;
	static Map<String, Node> map;
	static StringBuilder ansMaker;

	static void input() {
		n = sc.nextInt();
		map = new HashMap<>();
		list = new LinkedList();
		ansMaker = new StringBuilder();
	}

	static void doMalloc() {
		for (int i = 0; i < n; i++) {
			String order = sc.next();
			StringBuilder sb = new StringBuilder();
			if (order.contains("free(")) {
				for (int j = 5; j < order.length(); j++) {
					if (order.charAt(j) != ')') {
						sb.append(order.charAt(j));
					} else {
						break;
					}
				}
				if(!map.containsKey(sb.toString())) {
					continue;
				}
				Node node = map.get(sb.toString());
				if(node.start == 0) {
					continue;
				}
				if (list.head.equals(list.tail)) {
					list.head = null;
					list.tail = null;
				} else if (node.prev == null) {
					list.head = node.next;
					list.head.prev = null;
				} else if (node.next == null) {
					list.tail = node.prev;
					list.tail.next = null;
				} else {
					Node prev = node.prev;
					Node next = node.next;
					prev.next = next;
					next.prev = prev;
				}
				node.start = 0;
			} else if (order.contains("print(")) {
				for (int j = 6; j < order.length(); j++) {
					if (order.charAt(j) != ')') {
						sb.append(order.charAt(j));
					} else {
						break;
					}
				}
				if(!map.containsKey(sb.toString())) {
					ansMaker.append(0).append("\n");
					continue;
				}
				Node node = map.get(sb.toString());
				ansMaker.append(node.start).append("\n");
			} else {
				String[] sp = order.split("=");
				String name = sp[0];
				for (int j = 7; j < sp[1].length(); j++) {
					if (sp[1].charAt(j) != ')') {
						sb.append(sp[1].charAt(j));
					} else {
						break;
					}
				}
				map.put(name, list.add(Integer.parseInt(sb.toString())));
			}
		}
	}

	public static void main(String[] args) {
		input();
		doMalloc();
		System.out.println(ansMaker);
	}

	static class LinkedList {
		Node head;
		Node tail;

		public LinkedList() {
			this.head = null;
			this.tail = null;
		}

		Node add(int size) {
			Node node = new Node(1, size);
			if (head == null) {
				head = node;
				tail = node;
				return node;
			} else {
				if (head.start > size) {
					node.next = head;
					head.prev = node;
					head = node;
					return node;
				}
				Node cur = head;
				while (cur != null) {
					Node next = cur.next;
					if (next == null) {
						if (100000 - (cur.start + cur.size - 1) < size) {
							node.start = 0;
							return node;
						}
						node.start = cur.start + cur.size;
						cur.next = node;
						node.prev = cur;
						tail = node;
						return node;
					} else {
						if (next.start - (cur.start + cur.size) >= size) {
							node.start = cur.start + cur.size;
							cur.next = node;
							node.prev = cur;
							node.next = next;
							next.prev = node;
							return node;
						}
					}
					cur = cur.next;
				}
			}
			return node;
		}
	}

	static class Node {
		Node next;
		Node prev;
		int start;
		int size;

		public Node(int start, int size) {
			this.next = null;
			this.prev = null;
			this.start = start;
			this.size = size;
		}

	}
}