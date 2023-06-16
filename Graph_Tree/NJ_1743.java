import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stk;
	
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		
		boolean[][] map = new boolean[n+1][m+1];
		boolean[][] visited = new boolean[n+1][m+1];
		
		for (int i = 0; i < k; i++) {
			stk = new StringTokenizer(br.readLine());
			map[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = true;
		}
		
		int max = Integer.MIN_VALUE;
		
		int[] dr = {0,0,-1,1};
		int[] dc = {1,-1,0,0};
		
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= m; c++) {
				if (map[r][c] && !visited[r][c]) {
					
					int cnt = 1;
					
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {r,c});
					visited[r][c] = true;
					
					while (!q.isEmpty()) {
						int[] curr = q.poll();
						
						for (int i = 0; i < 4; i++) {
							int nr = curr[0] + dr[i];
							int nc = curr[1] + dc[i];
							if (nr < 1 || nc < 1 || nr > n || nc > m || !map[nr][nc] || visited[nr][nc]) continue;
							if (map[nr][nc]) {
								q.add(new int[] {nr,nc});
								visited[nr][nc] = true;
								cnt++;
							}
						}
					}
					
					max = Math.max(max, cnt);
				}
			}
		}
		
		System.out.println(max);
	}

}
