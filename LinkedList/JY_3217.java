import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main{
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();
	
	int n, variableSize;
	String query, variable;
	String[] split;
	
	HashMap<String, Integer> varToStart = new HashMap<>();
	int[] next = new int[100002];
	int[] prev = new int[100002];
	int[] size = new int[100002];
	
	void malloc(String variable, int variableSize) {
		int cur = 0;
		int nextCur = next[cur];
		int m = cur + size[cur];
		int possibleSize = nextCur - m;
		
		while (nextCur != 0) {
			// 할당 가능하면
			if (variableSize <= possibleSize) {
				next[cur] = m;
				prev[nextCur] = m;
				
				next[m] = nextCur;
				prev[m] = cur;
				size[m] = variableSize;
				
				varToStart.put(variable, m);
				return;
			}
			
			cur = next[cur];
			nextCur = next[cur];
			m = cur + size[cur];
			possibleSize = nextCur - m;
		}
		// 할당 불가능하면
		varToStart.put(variable, 0);
	}
	
	void free(String variable) {
		if (varToStart.containsKey(variable)) {			
			int start = varToStart.get(variable);
			
			if (start == 0) {
				return;
			}
			
			int before = prev[start];
			int after = next[start];
			
			next[before] = after;
			prev[after] = before;
			
			varToStart.put(variable, 0);
		}
	}
	
	void print(String variable) {
		if (varToStart.containsKey(variable)) {			
			sb.append(varToStart.get(variable)).append("\n");
		} else {
			sb.append(0).append("\n");
		}
	}
	
	void solution() throws Exception {
		
		varToStart = new HashMap<>();
		
		size[0] = 1;
		size[100001] = 1;
		
		next[0] = 100001;
		prev[0] = 100001;
		next[100001] = 0;
		prev[100001] = 0;
		
		
		n = Integer.parseInt(br.readLine());
		for (int q = 0; q < n; q++) {
			query = br.readLine();
			if (query.charAt(4) == '=') {
				split = query.split("=");
				variable = split[0];
				variableSize = Integer.parseInt(split[1].substring(7, split[1].length() - 2));
				malloc(variable, variableSize);
			} else if (query.startsWith("free(")) {
				variable = query.substring(5, 9);
				free(variable);
			} else {
				variable = query.substring(6, 10);
				print(variable);
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}
