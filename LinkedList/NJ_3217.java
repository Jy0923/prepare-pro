import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer stk;
	
	static class Space {
		int start;
		int size;
		int last;
		String var;
		Space prev;
		Space next;
	}
	
	static Space startPoint;
	static Space endPoint;
	
	static {
		startPoint = new Space();
		startPoint.start = 0;
		startPoint.last = 0;
		startPoint.size = 0;
		endPoint = new Space();
		endPoint.start = 100001;
		endPoint.last = 100001;
		endPoint.size = 0;
		startPoint.next = endPoint;
		endPoint.prev = startPoint;
	}
	
	static Map<String, Space> map = new HashMap<String, Main.Space>();
	
	static void malloc(String var, int size) {
		Space curr = startPoint;
		while (!curr.equals(endPoint) && ((curr.next.start - curr.last - 1) < size)) {
			curr = curr.next;
		}
		if (curr.equals(endPoint)) {
			Space cant = new Space();
			cant.start = 0;
			map.put(var, cant);
			return;
		}
		Space newbie = new Space();
		newbie.start = curr.last+1;
		newbie.size = size;
		newbie.last = newbie.start + size - 1;
		newbie.prev = curr;
		newbie.next = curr.next;
		curr.next.prev = newbie;
		curr.next = newbie;
		map.put(var, newbie);
	}
	
	static void free(String var) {
		Space fs = map.get(var);
		if (fs == null || fs.start == 0) return;
		fs.prev.next = fs.next;
		fs.next.prev = fs.prev;
		map.remove(var);
	}
	
	static void print(String var) {
		Space ps = map.get(var);
		if (ps == null) {
			System.out.println(0);
			return;
		}
		System.out.println(ps.start);
	}
	
	public static void main(String[] args) throws IOException {
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String com = br.readLine();
			if (com.substring(0,4).equals("free")) {
				free(com.substring(5, 9));
			} else if (com.substring(0, 5).equals("print")) {
				print(com.substring(6, 10));
			} else {
				String var = com.substring(0,4);
				int size = Integer.parseInt(com.substring(12, com.length()-2));
				malloc(var, size);
			}
		}
		
	}

}
