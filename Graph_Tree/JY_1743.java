import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int r, c;
	
	Pos(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class Main{
	
	int[] dr = {1, -1, 0, 0};
	int[] dc = {0, 0, 1, -1};
	
	int row, col, k;
	String[] input;
	boolean[][] map;
	
	int bfs(){
		boolean[][] visited = new boolean[row+1][col+1];
		int maxSize = 0;
		
		int curSize;
		Queue<Pos> q;
		Pos cur;
		int nr, nc;
		
		for (int r = 1; r <= row; r++) {
			for (int c = 1; c <= col; c++) {
				if (!map[r][c] || visited[r][c]) {
					continue;
				}
				q = new LinkedList<>();
				q.offer(new Pos(r, c));
				visited[r][c] = true;
				curSize = 1;
				while (!q.isEmpty()) {
					cur = q.poll();
					for (int dir = 0; dir < 4; dir++) {
						nr = cur.r + dr[dir];
						nc = cur.c + dc[dir];
						if (nr < 1 || nr > row || nc < 1 || nc > col){
							continue;
						}
						if (!map[nr][nc] || visited[nr][nc]) {
							continue;
						}
						q.offer(new Pos(nr, nc));
						visited[nr][nc] = true;
						curSize++;
					}
				}
				if (curSize > maxSize) {
					maxSize = curSize;
				}
			}
		}
		
		return maxSize;
	}
	
	void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		row = Integer.parseInt(input[0]);
		col = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		
		map = new boolean[row+1][col+1];
		for (int i = 0; i < k; i++) {
			input = br.readLine().split(" ");
			map[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = true;
		}
		
		System.out.println(bfs());
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
