import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	
	static int n;
	static int[][] cost;
	static int[][] dp;
	static int clear;
	static final int INF = Integer.MAX_VALUE;
	static final int IMPOSSIBLE = 100000000;
	
	static int tsp(int curr, int mask) {
		if (mask == clear) {
			if (cost[curr][0] == 0) return IMPOSSIBLE;
			return cost[curr][0];
		}
		if (dp[curr][mask] != -1) return dp[curr][mask];
		
		int min = INF;
		for (int next = 0; next < n; next++) {
			if (cost[curr][next] == 0) continue;
			if ((mask & (1 << next)) == 0) {
				int con = cost[curr][next] + tsp(next, mask | (1 << next));
				min = Math.min(min, con);
			}
		}
		if (min == INF) min = IMPOSSIBLE;
		dp[curr][mask] = min;
		return min;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		clear = (1 << n) - 1;
		cost = new int[n][n];
		dp = new int[n][1 << n];
		for (int st = 0; st < n; st++) {
			stk = new StringTokenizer(br.readLine());
			for (int ed = 0; ed < n; ed++) {
				cost[st][ed] = Integer.parseInt(stk.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i],-1);
		}
		System.out.println(tsp(0, 1));
	}

}
