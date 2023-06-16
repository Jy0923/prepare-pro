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

	static ArrayList<Integer>[] adj;
	static int[][] parent;
	static int[] depth;
	
	static void makeTree(int curr, int dep) {
		depth[curr] = dep;
		
		for (int child : adj[curr]) {
			if (depth[child] != 0) continue;
			
			parent[child][0] = curr;
			int copy = curr;
			int idx = 0;
			while (copy != 0) {
				int anc = parent[copy][idx];
				parent[child][++idx] = anc;
				copy = anc;
			}
			
			makeTree(child,dep+1);
		}
	}
	
	static void getLCA(int n1, int n2) {
		
		if (depth[n1] > depth[n2]) {
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		
		while (depth[n2] != depth[n1]) {
			int diff = depth[n2] - depth[n1];
			int jump = (int) (Math.log(diff)/Math.log(2));
			n2 = parent[n2][jump];
		}
		
		if (n1 == n2) {
			sb.append(n1).append('\n');
			return;
		}
		
		int lca = findLCA(n1,n2);
		sb.append(lca).append('\n');
		
	}
	
	static int findLCA(int n1, int n2) {
		int idx = -1;
		while (idx < 18 && parent[n1][++idx] != parent[n2][idx]) {}
		if (idx == 0) return parent[n1][idx];
		return findLCA(parent[n1][--idx],parent[n2][idx]);
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		parent = new int[n+1][18];
		depth = new int[n+1];
		
		for (int i = 1; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(stk.nextToken());
			int n2 = Integer.parseInt(stk.nextToken());
			adj[n1].add(n2);
			adj[n2].add(n1);
		}
		
		makeTree(1,1);
		
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(stk.nextToken());
			int n2 = Integer.parseInt(stk.nextToken());
			getLCA(n1,n2);
		}
		
		System.out.println(sb);
		
	}

}
