// 14501. 퇴사 (완전 탐색)

import java.util.Scanner;

public class Main {
	static int N;
	static int[] T, P;
	static int ans = Integer.MIN_VALUE;
	
	public static void solve(int day, int total) {
		// 탐색 종료조건
		if(day == N + 1) {
			ans = Math.max(ans, total);
			return;
		}
		// 상담을 하지않고 하루 지나갈 경우
		solve(day + 1, total);
		// 퇴사 날짜를 넘기지 않아 상담 가능할 경우
		if(day + T[day] <= N + 1) {
			// 상담에 걸리는 시간과 수익만큼 증가
			solve(day + T[day], total + P[day]);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		T = new int[N + 1]; // 상담에 소모되는 기간
		P = new int[N + 1]; // 상담으로 얻는 수익
		
		for(int i = 1; i <= N; i++) {
			int t = sc.nextInt();
			int p = sc.nextInt();
			T[i] = t;
			P[i] = p;
		}
		solve(1, 0);
		System.out.println(ans);
	}
}
