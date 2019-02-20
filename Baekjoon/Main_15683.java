// 15683. 감시

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class cctvInfo{
	int x;
	int y;
	int idx;
	cctvInfo(int x, int y, int idx){
		this.x = x;
		this.y = y;
		this.idx = idx;
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static ArrayList<cctvInfo> cctv;
	static int ans = Integer.MAX_VALUE;
	
	public static void solve(int cctvIdx, int[][] prev) {
		int[][] visited = new int[N][M];
		// 종료 조건
		if(cctvIdx == cctv.size()) {
			int temp = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(prev[i][j] == 0) {
						temp++;
					}
				}
			}
			if(temp < ans) {
				ans = temp;
			}
		}else {
			cctvInfo cI = cctv.get(cctvIdx);
			int x = cI.x;
			int y = cI.y;
			int idx = cI.idx;
			// 몇 번째 cctv 인지 찾은 후 가능한 방향 모두 탐색
			switch(idx) {
				case 1:
					for(int k = 0; k < 4; k++) {
						for(int i = 0; i < N; i++) {
							visited[i] = Arrays.copyOf(prev[i], M);
						}
						watch(visited, x, y, k);
						solve(cctvIdx + 1, visited);
					}
					break;
				case 2:
					for(int k = 0; k < 2; k++) {
						for(int i = 0; i < N; i++) {
							visited[i] = Arrays.copyOf(prev[i], M);
						}
						watch(visited, x, y, k);
						watch(visited, x, y, k + 2);
						solve(cctvIdx + 1, visited);
					}
					break;
				case 3:
					for(int k = 0; k < 4; k++) {
						for(int i = 0; i < N; i++) {
							visited[i] = Arrays.copyOf(prev[i], M);
						}
						watch(visited, x, y, k);
						watch(visited, x, y, (k + 1) % 4);
						solve(cctvIdx + 1, visited);
					}
					break;
				case 4:
					for(int k = 0; k < 4; k++) {
						for(int i = 0; i < N; i++) {
							visited[i] = Arrays.copyOf(prev[i], M);
						}
						watch(visited, x, y, k);
						watch(visited, x, y, (k + 1) % 4);
						watch(visited, x, y, (k + 2) % 4);
						solve(cctvIdx + 1, visited);
					}
					break;
				case 5:
					for(int i = 0; i < N; i++) {
						visited[i] = Arrays.copyOf(prev[i], M);
					}
					watch(visited, x, y, 0);
					watch(visited, x, y, 1);
					watch(visited, x, y, 2);
					watch(visited, x, y, 3);
					solve(cctvIdx + 1, visited);
					break;
			}
		}
	}
	
	public static void watch(int[][] visited, int x, int y, int dir) {
		switch(dir) {
			case 0:
				for(int k = x; k >= 0; k--) {
					if(map[k][y] == 6) {
						break;
					}
					visited[k][y] = 7;
				}
				break;
			case 1:
				for(int k = y; k < M; k++) {
					if(map[x][k] == 6) {
						break;
					}
					visited[x][k] = 7;
				}
				break;
			case 2:
				for(int k = x; k < N; k++) {
					if(map[k][y] == 6) {
						break;
					}
					visited[k][y] = 7;
				}
				break;
			case 3:
				for(int k = y; k >= 0; k--) {
					if(map[x][k] == 6) {
						break;
					}
					visited[x][k] = 7;
				}
				break;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		cctv = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(1 <= map[i][j] && map[i][j] <= 5) {
					cctv.add(new cctvInfo(i, j, map[i][j]));
				}
			}
		}
		solve(0, map); // cctv 인덱스, 초기 배열
		System.out.println(ans);
		sc.close();		
	}
}
