// 13460. 구슬 탈출 2

import java.awt.Point;
import java.util.Scanner;

public class Main {
	static int N, M, ans;
	static Point R, B;
	static char[][] map;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve(int dir, char[][] prev, boolean red, boolean blue, Point R, Point B, int cnt) {
		// 탐색 횟수가 10 이상이면 return
		if(cnt > 10) {
			return;
		}
		char[][] temp = new char[N][M]; // 구슬 이동경로를 저장하기 위한 맵
		arrayCopy(temp, prev); // 맵 복사
		int first = firstMove(dir, temp, R, B); // 0 이면 빨간구슬 먼저, 1 이면 파란구슬 먼저
		// 빨간 구슬 -> 파란 구슬
		if(first == 0) {
			while(true) {
				int nx = R.x + dx[dir];
				int ny = R.y + dy[dir];
				// 다음 칸이 벽일 때
				if(temp[nx][ny] == '#') {
					break;
				}
				// 구슬이 홀에 빠졌을 때
				if(temp[nx][ny] == 'O') {
					temp[R.x][R.y] = '.';
					red = true;
					R = null;
					break;
				}
				// 구슬 좌표 갱신
				temp[R.x][R.y] = '.';
				temp[nx][ny] = 'R';
				R = new Point(nx, ny);
			}
			while(true) {
				int nx = B.x + dx[dir];
				int ny = B.y + dy[dir];
				// 다음 칸이 벽이거나, 다른 구슬을 만났을 경우
				if(temp[nx][ny] == '#' || temp[nx][ny] == 'R') {
					break;
				}
				// 구슬이 홀에 빠졌을 때
				if(temp[nx][ny] == 'O') {
					temp[B.x][B.y] = '.';
					blue = true;
					B = null;
					break;
				}
				// 구슬 좌표 갱신
				temp[B.x][B.y] = '.';
				temp[nx][ny] = 'B';
				B = new Point(nx, ny);
			}
		}
		// 파란 구슬 -> 빨간 구슬
		if(first == 1) {
			while(true) {
				int nx = B.x + dx[dir];
				int ny = B.y + dy[dir];
				if(temp[nx][ny] == '#') {
					break;
				}
				if(temp[nx][ny] == 'O') {
					temp[B.x][B.y] = '.';
					blue = true;
					B = null;
					break;
				}
				temp[B.x][B.y] = '.';
				temp[nx][ny] = 'B';
				B = new Point(nx, ny);
			}
			while(true) {
				int nx = R.x + dx[dir];
				int ny = R.y + dy[dir];
				if(temp[nx][ny] == '#' || temp[nx][ny] == 'B') {
					break;
				}
				if(temp[nx][ny] == 'O') {
					temp[R.x][R.y] = '.';
					red = true;
					R = null;
					break;
				}
				temp[R.x][R.y] = '.';
				temp[nx][ny] = 'R';
				R = new Point(nx, ny);
			}
		}
		// 구슬이 모두 빠졌을 경우 실패
		if(red && blue) {
			return;
		}
		// 파란 구슬이 빠졌을 경우 실패
		if(!red && blue) {
			return;
		}
		// 빨간 구슬만 빠졌을 경우 성공
		if(red && !blue) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 4방향을 탐색(* 현재와 같은 방향과 반대방향은 제외 - 무의미한 이동)
		for(int k = 0; k < 4; k++) {
			if(k == dir || k == (dir + 2) % 4) {
				continue;
			}
			solve(k, temp, red, blue, R, B, cnt + 1);
		}
	}
	
	public static void arrayCopy(char[][] arr1, char[][] arr2) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	public static int firstMove(int dir, char[][] temp, Point R, Point B) {
		int color = 0;
		switch(dir) {
			case 0:
				if(R.x > B.x) color = 1;
				break;
			case 1:
				if(R.y < B.y) color = 1;
				break;
			case 2:
				if(R.x < B.x) color = 1;
				break;
			case 3:
				if(R.y > B.y) color = 1;
				break;
		}
		return color;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		Point R = null;
		Point B = null;
		ans = Integer.MAX_VALUE;
		// 맵 입력, 빨간구슬과 파란구슬좌표 Point에 저장
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					R = new Point(i, j);
				}else if(map[i][j] == 'B') {
					B = new Point(i, j);
				}
			}
		}
		// 구슬 통과 여부
		boolean red = false;
		boolean blue = false;
		// 4방향 탐색
		for(int k = 0; k < 4; k++) {
			solve(k, map, red, blue, R, B, 1);
		}
		if(ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		System.out.println(ans);
	}
}
