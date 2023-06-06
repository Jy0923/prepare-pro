import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class YG_23309 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, m;
	static LinkedList list;
	static StringBuilder ansMaker;
	static StringTokenizer st;
	static final int ARR_MAX = 1000001;
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new LinkedList();
		ansMaker = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	static void construct() throws IOException {
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch (order) {
			case "BN":
				list.addNext(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case "BP":
				list.addPrev(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case "CN":
				list.removeNext(Integer.parseInt(st.nextToken()));
				break;
			default:
				list.removePrev(Integer.parseInt(st.nextToken()));
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		construct();
		System.out.println(ansMaker);
	}
	
	public static class LinkedList {
		int head;
		int[] prevArr;
		int[] nextArr;
		
		public LinkedList() {
			head = 0;
			prevArr = new int[ARR_MAX];
			nextArr = new int[ARR_MAX];
		}
		
		void add(int data) {
			if(head == 0) {
				head = data;
				prevArr[data] = data;
				nextArr[data] = data;
			}
			else {
				int tail = prevArr[head];
				nextArr[tail] = data;
				prevArr[data] = tail;
				nextArr[data] = head;
				prevArr[head] = data;
			}
		}
		
		void addNext(int id, int data) {
			int next = nextArr[id];
			nextArr[id] = data;
			nextArr[data] = next;
			prevArr[data] = id;
			prevArr[next] = data;
			ansMaker.append(next).append("\n");
		}
		
		void addPrev(int id, int data) {
			int prev = prevArr[id];
			nextArr[prev] = data;
			nextArr[data] = id;
			prevArr[id] = data;
			prevArr[data] = prev;
			ansMaker.append(prev).append("\n");
		}
		
		void removeNext(int id) {
			int next = nextArr[id];
			int next2 = nextArr[next];
			if(next == head) {
				head = next2;
			}
			nextArr[id] = next2;
			prevArr[next2] = id;
			ansMaker.append(next).append("\n");
		}
		
		void removePrev(int id) {
			int prev = prevArr[id];
			int prev2 = prevArr[prev];
			if(prev == head) {
				head = prev2;
			}
			nextArr[prev2] = id;
			prevArr[id] = prev2;
			ansMaker.append(prev).append("\n");
		}
	}
}