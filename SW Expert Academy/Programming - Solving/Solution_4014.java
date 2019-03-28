// 4014. 활주로 건설

import java.util.Scanner;

public class Solution {
	static int N, X;
	static int[][] map;
	static int[] line;
	
	public static boolean solve() {
		// line의 높이 차 중 2이상이 있는 경우
		for(int i = 0; i < N - 1; i++) {
			if(Math.abs(line[i] - line[i + 1]) >= 2) {
				return false;
			}
		}
		// 경사로 체크 생성
		boolean[] upSlope = new boolean[N];
		boolean[] downSlope = new boolean[N];
		// 경사로 설치
		for(int i = 0; i < N - 1; i++) {
			if(line[i] < line[i + 1]) {	// 오르막 경사로
				int start = i - X + 1;
				int end = i;
				if(isFlat(start, end)) {
					for(int j = start; j <= end; j++) {
						upSlope[j] = true;
					}
				}else {
					return false;
				}
			}else if(line[i] > line[i + 1]) {	// 내리막 경사로
				int start = i + 1;
				int end = i + X;
				if(isFlat(start, end)) {
					for(int j = start; j <= end; j++) {
						downSlope[j] = true;
					}
				}else {
					return false;
				}
			}
		}
		// 경사로가 중복 설치 되었는지 확인
		for(int i = 0; i < N; i++) {
			if(upSlope[i] && downSlope[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isFlat(int start, int end) {
		// 경계값 조건
		if(start < 0 || end >= N) {
			return false;
		}
		// 경사로 놓을 구간이 평평 한지 체크
		for(int i = start; i < end; i++) {
			if(line[i] != line[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			X = sc.nextInt();
			map = new int[N][N];
			line = new int[N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			//	행 검사
			int rowCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					line[j] = map[i][j];
				}
				if(solve()) {
					rowCnt++;
				}
			}
			// 열 검사
			int colCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					line[j] = map[j][i];					
				}
				if(solve()) {
					colCnt++;
				}
			}
			System.out.println("#" + tc + " " + (rowCnt + colCnt));
		}
		sc.close();
	}
}
