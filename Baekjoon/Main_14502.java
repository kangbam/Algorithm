// 14502. 연구소

import java.util.Scanner;

public class Main {
	static int N, M, ans;
	static int[][] map, temp, virus;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve(int v, int wallCnt) {
		// 벽을 3개 세웠을 경우 바이러스 퍼뜨리며 탐색
		if(wallCnt == 3) {
			virus = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					virus[i][j] = temp[i][j];
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					// 바이러스가 있는 칸 퍼뜨리기
					if(virus[i][j] == 2) {
						spread(i, j);
					}
				}
			}
			// 안전한 칸 구하기
			areaCheck();
		}else {
			for(int i = v + 1; i < N * M; i++) {
				int nr = i / M;
				int nc = i % M;
				if(temp[nr][nc] == 0) {
					temp[nr][nc] = 1;
					solve(i, wallCnt + 1);
					temp[nr][nc] = 0;
				}
			}
		}
	}
	
	public static void spread(int x, int y) {
		for(int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(0 <= nx && nx < N && 0 <= ny && ny < M) {
				if(virus[nx][ny] == 0) {
					virus[nx][ny] = 3;
					spread(nx, ny);
				}
			}
		}
		
	}
	
	public static void areaCheck() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(virus[i][j] == 0) {
					cnt++;
				}
			}
		}
		ans = Math.max(cnt, ans);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		temp = new int[N][M];
		ans = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = map[i][j] = sc.nextInt();
			}
		}
		// 벽을 1을 세우고 2,3 을 세우는 탐색시작
		for(int i = 0; i < N * M; i++) {
			int r = i / M;
			int c = i % M;
			if(temp[r][c] == 0) {
				temp[r][c] = 1;
				solve(i, 1);
				temp[r][c] = 0;
			}
		}
		System.out.println(ans);
		sc.close();
	}
}
