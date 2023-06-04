import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	class LinkedList {

		int head;
		int[] prev;
		int[] next;

		LinkedList() {
			head = 0;
			prev = new int[1000001];
			next = new int[1000001];
		}

		void addLast(int data) {
			if (head == 0) {
				head = data;
				prev[head] = head;
				next[head] = head;
			} else {
				int last = prev[head];
				prev[data] = last;
				next[data] = head;

				next[last] = data;
				prev[head] = data;
			}
		}

		void bn(int i, int j) throws Exception {
			int data = next[i];
			
			prev[j] = i;
			next[j] = data;
			
			next[i] = j;
			prev[data] = j;
			
			bw.write(data + "\n");
		}

		void bp(int i, int j) throws Exception {
			int data = prev[i];

			prev[j] = data;
			next[j] = i;

			next[data] = j;
			prev[i] = j;
			
			bw.write(data + "\n");
		}

		void cn(int i) throws Exception {
			int data = next[i];
			
			int after = next[data];
			next[i] = after;
			prev[after] = i;
			
			bw.write(data + "\n");
		}

		void cp(int i) throws Exception {
			int data = prev[i];
			
			int before = prev[data];
			next[before] = i;
			prev[i] = before;
			
			bw.write(data + "\n");
		}
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	int n, m, i, j;
	LinkedList ll;
	String[] input;
	String cmd;

	void solution() throws Exception {
		
		ll = new LinkedList();
		input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		input = br.readLine().split(" ");
		for (int idx = 0; idx < n; idx++) {
			ll.addLast(Integer.parseInt(input[idx]));
		}
		for (int idx = 0; idx < m; idx++) {
			input = br.readLine().split(" ");
			cmd = input[0];
			if (cmd.equals("BN")) {
				i = Integer.parseInt(input[1]);
				j = Integer.parseInt(input[2]);
				ll.bn(i, j);
			} else if (cmd.equals("BP")) {
				i = Integer.parseInt(input[1]);
				j = Integer.parseInt(input[2]);
				ll.bp(i, j);
			} else if (cmd.equals("CN")) {
				i = Integer.parseInt(input[1]);
				ll.cn(i);
			} else if (cmd.equals("CP")) {
				i = Integer.parseInt(input[1]);
				ll.cp(i);
			}
		}
		bw.flush();
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
