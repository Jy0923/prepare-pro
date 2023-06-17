import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
	
	int n, m, u, v, sparseTableCol;
	int maxDepth = Integer.MIN_VALUE;
	String[] input;
	ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	int[] parent;
	int[] depth;
	int[][] sparseTable;
	
	public void dfs(int cur, int par, int cnt) {
		ArrayList<Integer> tmp = adjList.get(cur);
		if (cur != 1 && tmp.size() == 1) {
			parent[cur] = par;
			depth[cur] = cnt;
			if (cnt > maxDepth) {
				maxDepth = cnt;
			}
			return;
		}
		
		for (int nxt : tmp) {
			if (nxt == par) {
				continue;
			}
			parent[cur] = par;
			depth[cur] = cnt;
			dfs(nxt, cur, cnt+1);
		}
	}
	
	public void fillSparseTable() {
		for (int i = 1; i <= n; i++) {
			sparseTable[i][0] = parent[i];
		}
		
		for (int j = 1; j < 18; j++) {
			for (int i = 1; i <= n; i++) {
				if (depth[i] < (1<<j)) {
					continue;
				}
				sparseTable[i][j] = sparseTable[sparseTable[i][j-1]][j-1];
			}
		}
	}
	
	public int getLCA(int u, int v) {// u가 더 얕음
		if (u == v) {
			return u;
		}
		
		int tmp;	
		int diff = depth[v] - depth[u];
		if (diff != 0) {
			for (int i = sparseTableCol-1; i >= 0; i--) {
				tmp = 1 << i;
				if ((diff & tmp) == tmp) {
					v = sparseTable[v][i];
				}
			}
		}
		
		if (u == v) {
			return u;
		}
		
		
		for (int i = sparseTableCol-1; i >= 0; i--) {
			if (sparseTable[u][i] == 0 || sparseTable[v][i] == 0) {
				continue;
			}
			if (sparseTable[u][i] == sparseTable[v][i]) {
				continue;
			}
			u = sparseTable[u][i];
			v = sparseTable[v][i];
		}
		
		return parent[u];
		
	}
	
	public void solution() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i <= n; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < n-1; i++) {
			input = br.readLine().split(" ");
			u = Integer.parseInt(input[0]);
			v = Integer.parseInt(input[1]);
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		
		parent = new int[n+1];
		depth = new int[n+1];
		dfs(1, -1, 0); 
		

		sparseTableCol = 0;
		for (int i = 18; i >= 0; i--) {
			int tmp = 1 << i;
			if ((maxDepth & tmp) == tmp) {
				sparseTableCol = i+1;
				break;
			}
		}

		sparseTable = new int[n+1][sparseTableCol];
		
		fillSparseTable();
		
		m = Integer.parseInt(br.readLine());
		int swap;
		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			u = Integer.parseInt(input[0]); // u가 더 얕음
			v = Integer.parseInt(input[1]);
			if (depth[v] < depth[u]) {
				swap = u;
				u = v;
				v = swap;
			}
			
			bw.write(getLCA(u, v) + "\n");
		}
		bw.flush();
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
