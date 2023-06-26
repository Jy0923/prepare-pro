import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static int n;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int ans;
	
	static void input() {
		n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			pq.add(sc.nextInt());
		}
	}
	
	static void sortCard() {
		for(int i = 0; i < n - 1; i++) {
			int num = pq.remove();
			int num2 = pq.remove();
			ans += num + num2;
			pq.add(num + num2);
		}
	}
	
	public static void main(String[] args) {
		input();
		sortCard();
		System.out.println(ans);
	}
}