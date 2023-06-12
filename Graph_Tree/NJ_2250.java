import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stk;

	static final int MAXNUM = 10001;

	static int[] parent = new int[MAXNUM];
	static int[] left = new int[MAXNUM];
	static int[] right = new int[MAXNUM];
	static int[] level = new int[MAXNUM];
	static int[] col = new int[MAXNUM];
	static int[] width = new int[MAXNUM];
	static int maxDepth = 0;
	static ArrayList<Integer>[] colsOfDepth = new ArrayList[MAXNUM];

	static void getInfo(int curr, int depth, int stack) {
		level[curr] = depth;
		width[curr] += 1;
		col[curr] += 1 + stack;
		
		if (colsOfDepth[depth] == null) {
			colsOfDepth[depth] = new ArrayList<>();
		}
		
		if (left[curr] == -1 && right[curr] == -1) {
			maxDepth = Math.max(maxDepth, depth);
			return;
		}
		
		if (left[curr] != -1) {
			if (width[left[curr]] == 0) {
				getInfo(left[curr], depth + 1, stack);
			}
			width[curr] += width[left[curr]];
			col[curr] += width[left[curr]];
		}

		if (right[curr] != -1) {
			if (width[right[curr]] == 0) {
				getInfo(right[curr], depth + 1, col[curr]);
			}
			width[curr] += width[right[curr]];
		}
	}

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(stk.nextToken());
			int l = Integer.parseInt(stk.nextToken());
			int r = Integer.parseInt(stk.nextToken());
			left[p] = l;
			right[p] = r;
			if (l != -1) {
				parent[l] = p;
			}
			if (r != -1) {
				parent[r] = p;
			}
		}

		int root = 1;
		while (parent[root] != 0) {
			root = parent[root];
		}

		getInfo(root, 1, 0);
		
		for (int i = 1; i <= n; i++) {
			colsOfDepth[level[i]].add(col[i]);
		}
		
		int ansLevel = 0;
		int ansWidth = 0;
		
		for (int i = 1; i <= maxDepth; i++) {
			Collections.sort(colsOfDepth[i]);
			int curWidth = colsOfDepth[i].get(colsOfDepth[i].size()-1) - colsOfDepth[i].get(0) + 1;
			if (curWidth > ansWidth) {
				ansLevel = i;
				ansWidth = curWidth;
			}
		}
		
		System.out.println(ansLevel + " " + ansWidth);
	}

}
