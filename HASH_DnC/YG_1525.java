import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static final int[] dy = { 1, -1, 0, 0 };
	static final int[] dx = { 0, 0, 1, -1 };
	static final String finish = "123456780";

	static Set<String> set;
	static String start;
	static StringBuilder sb = new StringBuilder();

	static void input() {
		for(int i = 0; i < 9; i++) {
			int tmp = sc.nextInt();
			sb.append(tmp);
		}
		start = sb.toString();
		sb.setLength(0);
		set = new HashSet<>();
	}

	static void playPuzzle() {
		Queue<Puzzle> q = new LinkedList<>();
		q.add(new Puzzle(start, 0));
		while (!q.isEmpty()) {
			Puzzle now = q.remove();
			set.add(now.puzzle);
			if (finish.equals(now.puzzle)) {
				System.out.println(now.cnt);
				return;
			}
			String puzzle = now.puzzle;
			int zeroY = 0;
			int zeroX = 0;
			int[][] arr = new int[3][3];
			for(int i = 0; i < 9; i++) {
				arr[i / 3][i % 3] = puzzle.charAt(i) - '0';
				if(puzzle.charAt(i) == '0') {
					zeroY = i / 3;
					zeroX = i % 3;
				}
			}
			for (int i = 0; i < 4; i++) {
				int ny = zeroY + dy[i];
				int nx = zeroX + dx[i];
				if (ny < 0 || nx < 0 || ny >= 3 || nx >= 3) {
					continue;
				}
				arr[zeroY][zeroX] = arr[ny][nx];
				arr[ny][nx] = 0;
				for(int y = 0; y < 3; y++) {
					for(int x = 0; x < 3; x++) {
						sb.append(arr[y][x]);
					}
				}
				if(!set.contains(sb.toString())) {
					set.add(sb.toString());
					q.add(new Puzzle(sb.toString(), now.cnt + 1));
				}
				sb.setLength(0);
				arr[ny][nx] = arr[zeroY][zeroX];
				arr[zeroY][zeroX] = 0;
			}
		}
		System.out.println(-1);
	}

	public static void main(String[] args) {
		input();
		playPuzzle();
	}

	static class Puzzle {
		String puzzle;
		int cnt;

		public Puzzle(String puzzle, int cnt) {
			this.puzzle = puzzle;
			this.cnt = cnt;
		}

	}
}