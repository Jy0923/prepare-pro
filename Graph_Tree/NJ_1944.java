import java.beans.ConstructorProperties;
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
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;

	static class Pos {
		int r,c,dist;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Route implements Comparable<Route>{
		int from,to,dist;

		public Route(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		

		@Override
		public int compareTo(Route o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.dist, o.dist);
		}
		
	}
	
	static int n;
	static char[][] map;
	static boolean[] found;
	static Pos[] positions;
	static PriorityQueue<Route> routes = new PriorityQueue<>();
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	static void makeRoute(Pos curr, int num) {
		
		boolean[][] visited = new boolean[n][n];
		Queue<Pos> q = new LinkedList<>();
		q.add(curr);
		visited[curr.r][curr.c] = true;
		
		while(!q.isEmpty()) {
			curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if (map[nr][nc] == '1' || visited[nr][nc] || map[nr][nc] == 'S') continue;
				visited[nr][nc] = true;
				Pos next = new Pos(nr,nc);
				next.dist = curr.dist + 1;
				if (map[nr][nc] == 'K') {
					for (int i = 0; i < positions.length; i++) {
						if (positions[i].r == nr && positions[i].c == nc) {
							routes.add(new Route(0,i,next.dist));
							break;
						}
					}
				} else {
					q.add(next);
				}
			}
		}

	}
	
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		map = new char[n][n];
		found = new boolean[m+1];
		positions = new Pos[m+1];
		
		int idx = 1;
		for (int r = 0; r < n; r++) {
			String row = br.readLine();
			map[r] = row.toCharArray();
			for (int c = 0; c < n; c++) {
				if (map[r][c] == 'S') positions[0] = new Pos(r, c);
				if (map[r][c] == 'K') positions[idx++] = new Pos(r, c);
			}
		}
		
		makeRoute(positions[0],0);
		
		int ans = 0;
		
		while (!routes.isEmpty()) {
			Route r = routes.poll();
			if (found[r.to]) continue;
			found[r.to] = true;
			ans += r.dist;
			makeRoute(positions[r.to], r.to);
		}
		
		for (int i = 1; i <= m; i++) {
			if (!found[i]) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(ans);
	}

}
