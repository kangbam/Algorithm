// 5653. 줄기세포 배양 

import java.util.Scanner;

public class Solution {
	static int N, M, K;
	static int[][] map, stat, life;
	
	public static void solve() {
		for(int time = 0; time < K; time++) {
			for(int i = 0; i < N + K; i++) {
				for(int j = 0; j < M + K; j++) {
					// stat가 1 비활성 상태일 때, life 수치 증가
					if(stat[i][j] == 1) {
						life[i][j]++;
						// life가 map수치와 같아지면 활성화 상태로 전환
						if(life[i][j] == map[i][j]) {
							stat[i][j] = 2;
						}
					// stat가 2 활성 상태일 때, life 수치 감소 
					}else if(stat[i][j] == 2) {
						// life가 map수치와 같을 때, 번식
						if(life[i][j] == map[i][j]) {
							spread(i, j, map[i][j]);
						}
						
						life[i][j]--;
						
						if(life[i][j] == 0) {
							stat[i][j] = 3;
						}
					}
				}
			}
			// 번식한 세포들 비활성화 상태로 전환
			for(int i = 0; i < N + K; i++) {
				for(int j = 0; j < M + K; j++) {
					if(stat[i][j] == 0 && map[i][j] != 0) {
						stat[i][j] = 1;
					}
				}
			}
		}
	}
	
	public static void spread(int x, int y, int value) {
		int val = 0;
		// 같은 한칸에 번식한 세포 중 가장 큰 수치의 세포 번식
		if(x > 0 && stat[x - 1][y] == 0) {
			val = Math.max(value, map[x - 1][y]);
			map[x - 1][y] = val;
		}
		if(x < N + K - 1 && stat[x + 1][y] == 0) {
			val = Math.max(value, map[x + 1][y]);
			map[x + 1][y] = val;
		}
		if(y > 0 && stat[x][y - 1] == 0) {
			val = Math.max(value, map[x][y - 1]);
			map[x][y - 1] = val;
		}
		if(y < M + K - 1 && stat[x][y + 1] == 0) {
			val = Math.max(value, map[x][y + 1]);
			map[x][y + 1] = val;
		}
	}
	
	public static int calc() {
		int ans = 0;
		for(int i = 0; i < N + K; i++) {
			for(int j = 0; j < M + K; j++) {
				if(stat[i][j] == 1 || stat[i][j] == 2) {
					ans++;
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			map = new int[N + K][M + K]; //  비활성 상태에 걸리는 시간을 포함하여 K시간 만큼 뻗어갈 수 있는 최대 크기
			stat = new int[N + K][M + K]; // stat가 1 비활성화, 2 활성화, 3 죽음
			life = new int[N + K][M + K];
			// map 입력
			for(int i = K / 2; i < N + K / 2; i++) {
				for(int j = K / 2; j < M + K / 2; j++) {
					int val = sc.nextInt();
					map[i][j] = val;
					if(val != 0) {
						stat[i][j] = 1;
					}
				}
			}
			solve();
			System.out.println("#" + tc + " " + calc());
		}
		sc.close();
	}
}
