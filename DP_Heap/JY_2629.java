import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
	
	int n, m; // 추의 갯수, 구슬의 갯수
	int[] chu, gu;
	boolean[][] dp;
	String[] input;
	
	void print(int k) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (dp[i][j]) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}
	
	void fillDp() {
		int curChu;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= 40000; j++) {
				curChu = chu[i];
				
				// 무게가 curChu와 같다면
				if (j == curChu) {
					dp[i][j] = true;
				}
				
				// 이전 조합만으로 만들 수 있으면
				if (dp[i-1][j]) {
					dp[i][j] = true; 
					dp[i][j+curChu] = true;
					if (j - curChu >= 1) {
						dp[i][j-curChu] = true;
					}
					if (curChu - j >= 1) {
						dp[i][curChu-j] = true;
					}
				}
			}
		}
	}
	
	void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		chu = new int[n+1];
		input = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			chu[i+1] = Integer.parseInt(input[i]);
		}
		m = Integer.parseInt(br.readLine());
		gu = new int[m+1];
		input = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			gu[i+1] = Integer.parseInt(input[i]);
		}
		dp = new boolean[n+1][400001];
		fillDp();
		
		for (int i = 1; i <= m; i++) {
			if (dp[n][gu[i]]) {
				sb.append("Y ");
			} else {
				sb.append("N ");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) throws Exception{
		new Main().solution();
	}
}
