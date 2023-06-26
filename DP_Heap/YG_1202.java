import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, k;
	static long ans;
	static PriorityQueue<int[]> jewels;
	static PriorityQueue<Integer> bags;

	static void input() {
		n = sc.nextInt();
		k = sc.nextInt();
		jewels = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		bags = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			jewels.add(new int[] { sc.nextInt(), sc.nextInt() });
		}
		for (int i = 0; i < k; i++) {
			bags.add(sc.nextInt());
		}
	}

	static void steal() {
		PriorityQueue<Integer> tmp = new PriorityQueue<>(Comparator.reverseOrder());
		while (!bags.isEmpty()) {
			int now = bags.remove();
			while(!jewels.isEmpty() && jewels.peek()[0] <= now) {
				tmp.add(jewels.remove()[1]);
			}
			if(!tmp.isEmpty()) {
				ans += tmp.remove();
			}
		}
	}

	public static void main(String[] args) {
		input();
		steal();
		System.out.println(ans);
	}
}