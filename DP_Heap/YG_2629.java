import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int wCnt, bCnt;
	static int[] bead, weight;
	static boolean[][] visit = new boolean[31][30001];

	static void input() {
		wCnt = sc.nextInt();
		weight = new int[wCnt];
		for (int i = 0; i < wCnt; i++) {
			weight[i] = sc.nextInt();
//			visit[weight[i] + 15000] = true;
		}
		bCnt = sc.nextInt();
		bead = new int[bCnt];
		for (int i = 0; i < bCnt; i++) {
			bead[i] = sc.nextInt();
		}
	}

	static void checkWeight(int idx, int sum) {
		if (visit[idx][sum]) {
			return;
		}
//		System.out.println("sum: " + (sum - 15000));
		visit[idx][sum] = true;
		if (idx == wCnt) {
			return;
		}
		checkWeight(idx + 1, sum);
		checkWeight(idx + 1, sum + weight[idx]);
		checkWeight(idx + 1, sum - weight[idx]);
	}

	static void canWeight() {
		for (int i = 0; i < bCnt; i++) {
			if (bead[i] > 15000 || !visit[wCnt][bead[i] + 15000]) {
				System.out.print("N ");
			} else {
				System.out.print("Y ");
			}
		}
	}

	public static void main(String[] args) {
		input();
		checkWeight(0, 15000);
		canWeight();
	}
}