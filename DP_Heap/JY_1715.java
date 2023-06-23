import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main{
	
	int n;
	PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	int getSum() {
		int sum;
		int total = 0; 
		while (pq.size() >= 2) {
			sum = pq.poll() + pq.poll();
			total += sum;
			pq.offer(sum);
		}
		return total;
	}
	
	void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		System.out.println(getSum());
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}
}
