import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Station {
		int seq;
		Station prev;
		Station next;
	}

	static Station[] stations = new Station[1000001];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());

		Station first = new Station();
		Station curr = first;
		Station last = new Station();

		stk = new StringTokenizer(br.readLine());
		first.seq = Integer.parseInt(stk.nextToken());
		stations[first.seq] = first;

		Station newStation;

		for (int i = 1; i < n; i++) {
			int newSeq = Integer.parseInt(stk.nextToken());
			newStation = new Station();
			curr.next = newStation;
			newStation.prev = curr;
			newStation.seq = newSeq;
			curr = newStation;
			stations[newSeq] = newStation;
		}

		last = curr;
		last.next = first;
		first.prev = last;


		StringBuilder answer = new StringBuilder();
		
		Station next,prev;
		
		
		for (int i = 0; i < m; i++) {

			stk = new StringTokenizer(br.readLine());
			String com = stk.nextToken();
			curr = stations[Integer.parseInt(stk.nextToken())];

			if (com.equals("BN")) {
				next = curr.next;
				answer.append(next.seq);
				newStation = new Station();
				newStation.prev = curr;
				newStation.next = next;
				curr.next = newStation;
				next.prev = newStation;
				newStation.seq = Integer.parseInt(stk.nextToken());
				stations[newStation.seq] = newStation;
			
			} else if (com.equals("BP")) {
				prev = curr.prev;
				answer.append(prev.seq);
				newStation = new Station();
				newStation.next = curr;
				newStation.prev = prev;
				curr.prev = newStation;
				prev.next = newStation;
				newStation.seq = Integer.parseInt(stk.nextToken());
				stations[newStation.seq] = newStation;
			
			} else if (com.equals("CN")) {
				next = curr.next;
				answer.append(next.seq);
				curr.next = next.next;
				next.next.prev = curr;
				stations[next.seq] = null;
				
			} else if (com.equals("CP")) {
				prev = curr.prev;
				answer.append(prev.seq);
				prev.prev.next = curr;
				curr.prev = prev.prev;
				stations[prev.seq] = null;
				
			}
			

			answer.append('\n');
		}
		
		System.out.println(answer);
	}

}
