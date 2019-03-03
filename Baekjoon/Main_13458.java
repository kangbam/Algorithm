// 13458. 시험 감독 

import java.util.Scanner;

public class Main {
	static int N, B, C;
	static int[] room;
	static long ans;
	
	public static void solve() {
		for(int i = 0; i < N; i++) {
			// 총 감독관이 1명 들어가야 하므로
			room[i] -= B;
			ans += 1;
			// 총 감독관이 감시 가능한 수를 넘은 경우 부 감독관 투입
			if(room[i] > 0) {
				ans += Math.ceil(1.0 * room[i] / C); // 올림
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 방의 수
		room = new int[N];
		ans = 0;
		// 방에 있는 사람 수 입력받음
		for(int i = 0; i < N; i++) {
			room[i] = sc.nextInt();
		}
		B = sc.nextInt(); // 총 감독관이 감시 가능한 수
		C = sc.nextInt(); // 부 감독관이 감시 가능한 수
		solve();
		System.out.println(ans);
		sc.close();
	}
}
