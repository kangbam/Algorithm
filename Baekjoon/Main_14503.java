// 14503. 로봇 청소기

import java.util.Scanner;

public class Main {
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int dir = sc.nextInt();
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		while(true) {
			// 청소
			if(map[x][y] == 0) {
				map[x][y] = 2;
			}
			// 4방향이 빈칸이 아닐 때
			if(map[x - 1][y] != 0 && map[x + 1][y] != 0 && map[x][y - 1] != 0 && map[x][y + 1] != 0) {
				// 후진할 칸이 벽이면 종료, 아니라면 후진
				if(map[x - dx[dir]][y - dy[dir]] == 1) {
					break;
				}else {
					x -= dx[dir];
					y -= dy[dir];
				}
			}else {
				// 왼쪽 칸으로 돌며 탐색
				dir = (dir + 3) % 4;
				if(map[x + dx[dir]][y + dy[dir]] == 0) {
					x += dx[dir];
					y += dy[dir];
				}
			}
		}
		// 청소된 칸 카운트 증가
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
