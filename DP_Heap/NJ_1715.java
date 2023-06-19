import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;

	static int n;
	static PriorityQueue<Integer> cards = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			cards.add(Integer.parseInt(br.readLine()));
		}
		int max = 0;
		while(cards.size() > 1) {
			int curr = cards.poll() + cards.poll();
			max += (curr);
			cards.add(curr);
		}
		System.out.println(max);
	}

}
