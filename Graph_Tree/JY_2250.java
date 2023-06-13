import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
	
	int n, maxLevel, maxWidth, root;
	int[] left, right, par, size, depth, col;
	String[] input;
	
	void findRoot() {
		root = 1;
		while (par[root] != 0) {
			root = par[root];
		}
	}
	
	void dfs(int cur) {
		int l = left[cur], r = right[cur];
		int ls = 0, rs = 0;
		
		if (l != 0) {
			depth[l] = depth[cur] + 1;
			dfs(l);
			ls = size[l];
		}
		if (r != 0) {
			depth[r] = depth[cur] + 1;
			dfs(r);
			rs = size[r];
		}
		size[cur] = ls + rs + 1;
	}
	
	void getCol(int cur, int leftBound, int rightBound) {
		int l = left[cur], r = right[cur];
		int ls = size[l];
		col[cur] = leftBound + ls;
		if (l != 0) {
			getCol(l, leftBound, col[cur]-1);
		}
		if (r != 0) {
			getCol(r, col[cur]+1, rightBound);
		}	
	}
	
	void getMaxWidth() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(root);
		maxLevel = 1;
		maxWidth = 1;
		int d = 1;
		int l = n, r = 1;
		
		while (!q.isEmpty()) {
			int dn = q.size();
			for (int i = 0; i < dn; i++) {
				int cur = q.poll();
				if (col[cur] < l) {
					l = col[cur];
				}
				if (col[cur] > r) {
					r = col[cur];
				}
				if (right[cur] != 0) {
					q.offer(right[cur]);
				}
				if (left[cur] != 0) {
					q.offer(left[cur]);
				}
			}
			int width = r - l + 1;
			if (width > maxWidth) {
				maxWidth = width;
				maxLevel = d;
			}
			l = n;
			r = 1;
			d++;
		}
	}
	
	void solution() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		left = new int[n+1];
		right = new int[n+1];
		par = new int[n+1];
		size = new int[n+1];
		depth = new int[n+1];
		col = new int[n+1];
		
		for (int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			int p = Integer.parseInt(input[0]);
			int l = Integer.parseInt(input[1]);
			int r = Integer.parseInt(input[2]);
			if (l != -1) {
				left[p] = l;
				par[l] = p;
			}
			if (r != -1) {
				right[p] = r;
				par[r] = p;
			}
		}
		
		findRoot();
		depth[root] = 1;
		dfs(root);
		
		getCol(root, 1, size[1]);
		
		getMaxWidth();
		System.out.println(maxLevel + " " + maxWidth);
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
