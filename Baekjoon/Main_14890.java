// 14890. 경사로

import java.util.Scanner;

public class Main {
	static int N, L;
	static int[][] map;
	static int[] line;
	
	public static boolean solve() {
		// 받아온 라인 중 높이차가 2 이상인경우 false반환
		for(int i = 0; i < N - 1; i++) {
			if(Math.abs(line[i] - line[i + 1]) >= 2) {
				return false;
			}
		}
		boolean[] upSlope = new boolean[N];
		boolean[] downSlope = new boolean[N];
		
		for(int i = 0; i < N - 1; i++) {
			// 다음 칸이 현재 칸보다 높을 경우
			if(line[i] < line[i + 1]) {
				int start = i - L + 1;
				int end = i;
				// 경사로 설치 가능한지 확인
				if(available(start, end, upSlope)) {
					for(int j = start; j <= end; j++) {
						upSlope[j] = true;
					}
				}else {
					return false;
				}
			}
			// 다음 칸이 현재 칸보다 낮을 경우
			if(line[i] > line[i + 1]) {
				int start = i + 1;
				int end = i + L;
				// 경사로 설치 가능한지 확인
				if(available(start, end, downSlope)) {
					for(int j = start; j <= end; j++) {
						downSlope[j] = true;
					}
				}else {
					return false;
				}
			}
		}
		// upSlope와 downSlope가 중복될 경우 false반환
		for(int i = 0; i < N; i++) {
			if(upSlope[i] && downSlope[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean available(int start, int end, boolean[] slope) {
		// 경사로를 설치할 구간이 범위를 벗어나지 않는지 확인
		if(start < 0 || end >= N) {
			return false;
		}
		// 경사로를 설치할 구간이 평평한지 확인
		for(int i = start; i < end; i++) {
			if(line[i] != line[i + 1]) {
				return false;
			}
		}
		// 경사로가 이미 설치 되어있는지
		for(int i = start; i <= end; i++) {
			if(slope[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		map = new int[N][N];
		line = new int[N];
		// 맵 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int ans = 0;
		// 한 줄씩 받아 row검사
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				line[j] = map[i][j];
			}
			if(solve()) {
				ans++;
			}
		}
		// 한 줄씩 받아 col검사
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				line[j] = map[j][i];
			}
			if(solve()) {
				ans++;
			}
		}
		System.out.println(ans);
		sc.close();
	}
}
