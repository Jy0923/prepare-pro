import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	
	static class Jewel implements Comparable<Jewel> {
		
		int weight,value;
		
		public Jewel (int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Jewel o) {
			// TODO Auto-generated method stub
			return o.value - this.value;
		}

		
		
	}
	
	static int n,k;
	static PriorityQueue<Jewel> pq = new PriorityQueue<>();
	static Jewel[] jewels;
	static int[] bags;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		jewels = new Jewel[n];
		bags = new int[k];
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			jewels[i] = new Jewel(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		}
		for (int i = 0; i < k; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(jewels, new Comparator<Jewel>() {

			@Override
			public int compare(Jewel o1, Jewel o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});
		Arrays.sort(bags);
		
		long max = 0;
		
		for (int i = 0, j = 0; i < k; i++) {
			while (j < n && jewels[j].weight <= bags[i]) {
				pq.add(jewels[j++]);
			}
			if (!pq.isEmpty()) max += pq.poll().value;
		}
		System.out.println(max);
	}

}
