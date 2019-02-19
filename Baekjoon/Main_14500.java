// 14500. 테트로미노

import java.util.Scanner;

public class Main {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve(int x, int y, int cnt, int sum) {
		visited[x][y] = true;
		// 4칸 까지의 탐색결과가 모양의 범위
		if(cnt == 4) {
			ans = Math.max(ans, sum);
			return;
		}
		// 4방향 탐색
		for(int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(0 <= nx && nx < N && 0 <= ny && ny < M) {
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					solve(nx, ny, cnt + 1, sum + map[nx][ny]);
					// 백트래킹
					visited[nx][ny] = false;
				}
			}
		}
		// 백트래킹
		visited[x][y] = false;
	}
	
	public static void solveExcept(int x, int y) {
		// 4 방향을 탐색한다 ㅗ ㅏ ㅓ ㅜ 모양 
		for(int i = 0; i < 4; i++) {
			int sum = map[x][y];
			boolean flag = true;			
			for(int j = 0; j < 3; j++) {
				// dir이 012 123 230 301 로 나오도록
				int nx = x + dx[(i + j) % 4];
				int ny = y + dy[(i + j) % 4];
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					sum += map[nx][ny];
				// 날개가 범위를 벗어나면 종료
				}else {
					flag = false;
					break;
				}
			}
			if(flag) {
				ans = Math.max(ans, sum);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		
		ans = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();				
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				solve(i, j, 1, map[i][j]); // 나머지 모양 x좌표, y좌표, 길이, 값
				solveExcept(i, j); // 예외적인 ㅗ 모양
			}
		}
		System.out.println(ans);
	}
}
