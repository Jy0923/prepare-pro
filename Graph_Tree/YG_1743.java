import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static final int[] dy = { 1, -1, 0, 0 };
	static final int[] dx = { 0, 0, 1, -1 };
	static int n, m, k;
	static boolean[][] board;
	static boolean[][] visit;
	static int sum;
	static int ans;
	
	static void input() {
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		board = new boolean[n + 1][m + 1];
		visit = new boolean[n + 1][m + 1];
		for (int i = 0; i < k; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			board[y][x] = true;
		}
	}

	static void dfs(int y, int x) {
		sum++;
		visit[y][x] = true;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 1 || nx < 1 || ny > n || nx > m || visit[ny][nx] || !board[ny][nx]) {
				continue;
			}
			dfs(ny, nx);
		}
	}

	static void checkAll() {
		for (int y = 1; y <= n; y++) {
			for (int x = 1; x <= m; x++) {
				if (board[y][x] && !visit[y][x]) {
					sum = 0;
					dfs(y, x);
					ans = Math.max(ans, sum);
				}
			}
		}
	}

	public static void main(String[] args) {
		input();
		checkAll();
		System.out.println(ans);
	}
}