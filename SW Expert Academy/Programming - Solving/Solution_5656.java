// 5656. 벽돌 깨기

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int N, W, H, ans;
	static int[][] map;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	
	public static void solve(int cnt) {
		if(cnt == N + 1) {
			int result = getResult();
			ans = Math.min(ans, result);
			return;
		}
		for(int c = 0; c < W; c++) {
			int r = getRow(c);	// 가장 윗줄에 있는 블록의 x좌표 반환
			if(r != -1) {
				int[][] copy = new int[H][W];
				
				arrayCopy(copy, map);	// map복사
				explosion(r, c);	// 폭발
				reArrange();	// 폭발하고 남은 블록들 map재정렬
				solve(cnt + 1);	// 벽돌을 N번째 까지 터뜨림
				arrayCopy(map, copy);	// 백트래킹
			}			
		}
	}
	
	public static int getRow(int col) {
		for(int i = 0; i < H; i++) {
			if(map[i][col] != 0) {
				return i;
			}
		}
		return -1;
	}
	
	public static void arrayCopy(int[][] arr1, int[][] arr2) {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	public static void explosion(int x, int y) {
		int val = map[x][y]; // val 폭발 반경
		map[x][y] = 0; // 폭발
		
		for(int v = 1; v < val; v++) {
			for(int k = 0; k < 4; k++) {
				int nx = x + dx[k] * v;
				int ny = y + dy[k] * v;
				if(0 <= nx && nx < H && 0 <= ny && ny < W) {
					if(map[nx][ny] > 1) {
						explosion(nx, ny); // 연쇄 폭발
					}else {
						map[nx][ny] = 0;
					}
				}
			}
		}
	}
	
	public static void reArrange() {
		for(int y = 0; y < W; y++) {
			Queue<Integer> q = new LinkedList<Integer>();
			// col 기준, 밑에서 부터 블록인 경우를 큐에 순서대로 저장
			for(int x = H - 1; x >= 0; x--) {
				if(map[x][y] != 0) {
					q.add(map[x][y]);
				}
			}
			// 큐가 빌 때 까지 밑에서 부터 블록을 차례대로 내보내며 블록을 쌓음
			for(int x = H - 1; x >= 0; x--) {
				if(!q.isEmpty()) {
					map[x][y] = q.poll();
				}else {
					map[x][y] = 0;
				}
			}
		}
	}
	
	public static int getResult() {
		int result = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] != 0) {
					result++;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			map = new int[H][W];
			ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			solve(1); // 벽돌을 부수는 횟수
			if(ans == Integer.MAX_VALUE) {
				ans = 0;
			}
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
}
