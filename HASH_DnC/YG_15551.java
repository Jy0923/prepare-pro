import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	
	static void input() {
		n = sc.nextInt();
	}

	static void makeStr() {
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < n - 2; i++) {
			a.append("\1");
			b.append("\1");
		}
		a.append("\2]");
		b.append("\3>");
		System.out.println(a.toString());
		System.out.println(b.toString());
	}
	
	public static void main(String[] args) {
		input();
		makeStr();
	}
}