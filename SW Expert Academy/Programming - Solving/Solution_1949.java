// 1949. 등산로 조성 
import java.util.Scanner;

public class Solution {
	static int N, K, maxH, ans;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve(int x, int y, int maxH, int dist, boolean available) {
		if(visited[x][y]) {
			if(ans < dist) {
				ans = dist;
			}
		}
		visited[x][y] = true;
		for(int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(0 <= nx && nx < N && 0 <= ny && ny < N) {
				// 방문하지 않았고
				if(!visited[nx][ny]) {
					// 공사 필요 없을 때
					if(maxH > map[nx][ny]) {
						visited[nx][ny] = true;
						solve(nx, ny, map[nx][ny], dist + 1, available);
						visited[nx][ny] = false;
					// 공사가 필요 할 때
					}else {
						//공사여부 판단
						if(available == true) {
							for(int i = 1; i <= K; i++) {
								if(maxH > map[nx][ny] - i) {
									visited[nx][ny] = true;
									available = false;
									solve(nx, ny, map[nx][ny] - i, dist + 1, available);
									visited[nx][ny] = false;
									available = true;
								}else {
									continue;
								}
							}
						}							
					}
				}
			}
		}	
		visited[x][y] = false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			visited = new boolean[N][N];
			maxH = 0;
			ans = -1;
			// 맵을 입력 받으며 가장 큰 높이 값 저장
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] > maxH) {
						maxH = map[i][j];
					}
				}
			}
			// 큰 높이 지점에서 탐색
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == maxH) {
						// x, y , 최대높이, 거리, 공사여부
						solve(i, j, maxH, 1, true);
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
