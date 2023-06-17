import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Pos{
	int r, c;
	Pos(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class Main{
	
	int[] dr = {-1, 1, 0, 0};
	int[] dc = {0, 0, -1, 1};
	
	int[][] map;
	int n, m;
	Pos[] nodes;
	int[][] dist;
	
	void bfs() {
		Queue<Pos> q;
		int[][] visited;
		int nr, nc;
		
		for (int idx = 0; idx <= m; idx++) {
			q = new LinkedList<>();
			visited = new int[n][n];
			q.offer(nodes[idx]);
			visited[nodes[idx].r][nodes[idx].c] = 1;
			int startNodeNumber = map[nodes[idx].r][nodes[idx].c];
			while (!q.isEmpty()) {
				Pos cur = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					nr = cur.r + dr[dir];
					nc = cur.c + dc[dir];
					
					if (visited[nr][nc] > 0 || map[nr][nc] == -1) {
						continue;
					}
					
					visited[nr][nc] = visited[cur.r][cur.c] + 1;
					q.offer(new Pos(nr, nc));
					
					if (map[nr][nc] >= 1) {
						int curNodeNumber = map[nr][nc];
						dist[startNodeNumber][curNodeNumber] = visited[nr][nc] - 1;
					}
				}
			}
		}
	}
	
	int prim() {
		boolean[] visited = new boolean[m+2];
		int sum = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[2], e2[2]));
		for (int idx = 2; idx <= m+1; idx++) {
			if (dist[1][idx] != 0) {				
				pq.offer(new int[] {1, idx, dist[1][idx]});
			}
		}
		visited[1] = true;
		int vn = 1;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visited[cur[1]]) {
				continue;
			}
			sum += cur[2];
			visited[cur[1]] = true;
			vn++;
			if (vn == m+1) {
				return sum;
			}
			for (int idx = 1; idx <= m+1; idx++) {
				if (visited[idx]) {
					continue;
				}
				if (dist[cur[1]][idx] != 0) {					
					pq.offer(new int[] {cur[1], idx, dist[cur[1]][idx]});
				}
			}
		}
		return -1;
	}
	
	void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		n = Integer.parseInt(inputs[0]);
		m = Integer.parseInt(inputs[1]);
		map = new int[n][n];
		nodes = new Pos[m+1];
		dist = new int[m+2][m+2];
				
		for (int r = 0, idx = 1; r < n; r++) {
			String input = br.readLine();
			for (int c = 0; c < n; c++) {
				char cur = input.charAt(c);
				if (cur == 'S') {
					nodes[0] = new Pos(r, c);
					map[r][c] = 1;
				} else if (cur == 'K') {
					nodes[idx] = new Pos(r, c);
					map[r][c] = idx+1;
					idx++;
				} else if (cur == '1') {
					map[r][c] = -1;
				} else {
					map[r][c] = 0;
				}
			}
		}
		bfs();
		System.out.println(prim());
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
