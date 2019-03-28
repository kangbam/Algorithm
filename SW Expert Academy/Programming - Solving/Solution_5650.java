// 5650. 핀볼 게임 

import java.awt.Point;
import java.util.Scanner;

public class Solution {
	static int N, ans;
	static int[][] map;
	static Point[][] worm;
	static final int[] dx = {-1, 0, 1, 0}; // 0상 1우 2하 3좌
	static final int[] dy = {0, 1, 0, -1}; // 시계 방향
	
	public static void solve(int startX, int startY, int dir) {
		int cnt = 0;
		int x = startX;
		int y = startY;
		
		while(true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			// 블랙홀에 빠지거나, 출발점에 되돌아 왔을 경우
			if(map[nx][ny] == -1 || (nx == startX && ny == startY)) {
				break;
			}
			// 웜홀에 빠진 경우
			if(map[nx][ny] >= 6) {
				Point p = warf(nx, ny, map[nx][ny] - 6);
				x = p.x;
				y = p.y;
				continue;
			// 블록에 맞은경우 방향 전환
			}else if(map[nx][ny] >= 1) {
				dir = dirChange(dir, map[nx][ny]);
				cnt++;
			}
			x = nx;
			y = ny;
		}
		ans = Math.max(cnt, ans);
	}
	
	public static Point warf(int x, int y, int wormhole) {
		Point A = worm[0][wormhole];
		Point B = worm[1][wormhole];
		// 대응하는 웜홀 반환
		if(A.x == x && A.y == y) {
			return B;
		}else {
			return A;
		}		
	}
	
	public static int dirChange(int dir, int block) {
		int nextDir = 0;
		switch(block) {
		case 1:
			switch(dir) {
			case 0:
				nextDir = 2;
				break;
			case 1:
				nextDir = 3;
				break;
			case 2:
				nextDir = 1;
				break;
			case 3:
				nextDir = 0;
				break;
			}
			break;
		case 2:
			switch(dir) {
			case 0:
				nextDir = 1;
				break;
			case 1:
				nextDir = 3;
				break;
			case 2:
				nextDir = 0;
				break;
			case 3:
				nextDir = 2;
				break;
			}
			break;
		case 3:
			switch(dir) {
			case 0:
				nextDir = 3;
				break;
			case 1:
				nextDir = 2;
				break;
			case 2:
				nextDir = 0;
				break;
			case 3:
				nextDir = 1;
				break;
			}
			break;
		case 4:
			switch(dir) {
			case 0:
				nextDir = 2;
				break;
			case 1:
				nextDir = 0;
				break;
			case 2:
				nextDir = 3;
				break;
			case 3:
				nextDir = 1;
				break;
			}
			break;
		case 5:
			switch(dir) {
			case 0:
				nextDir = 2;
				break;
			case 1:
				nextDir = 3;
				break;
			case 2:
				nextDir = 0;
				break;
			case 3:
				nextDir = 1;
				break;
			}
			break;			
		}
		return nextDir;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N + 2][N + 2]; // 외곽 블록을 만들기 위해 2칸 추가
			worm = new Point[2][5];
			
			// 외곽에 블록 5 삽입
			for(int i = 0; i < N + 2; i++) {
				map[0][i] = map[i][0] = map[i][N + 1] = map[N + 1][i] = 5;
			}
			// map 입력
			for(int i = 1; i < N + 1; i++) {
				for(int j = 1; j < N + 1; j++) {
					map[i][j] = sc.nextInt();
					// worm 입력
					if(map[i][j] >= 6) {
						if(worm[0][map[i][j] - 6] == null) {
							worm[0][map[i][j] - 6] = new Point(i, j);
						}else {
							worm[1][map[i][j] - 6] = new Point(i, j);
						}
					}
				}
			}
			ans = 0;
			// 탐색
			for(int i = 1; i < N + 1; i++) {
				for(int j = 1; j < N + 1; j++) {
					if(map[i][j] == 0) {
						for(int k = 0; k < 4; k++) {
							solve(i, j, k);
						}	
					}									
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
