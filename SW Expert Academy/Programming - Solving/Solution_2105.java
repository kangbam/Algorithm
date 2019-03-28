// 2105. 디저트 카페 
import java.util.Scanner;

public class Solution {
	static int N, startX, startY, ans;
	static int[][] map;
	static boolean[] visited;
	static final int[] dx = {1, 1, -1, -1}; // 0↘(남서) 1↙(남서) 2↖(북서) 3↗(북동)
	static final int[] dy = {1, -1, -1, 1};
	
	public static void solve(int x, int y, int dir, int cnt) {
		// 방향 경계 조건
		if(dir == 4) {
			return;
		}
		visited[map[x][y]] = true;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(0 <= nx && nx < N && 0 <= ny && ny < N) {
			//시작지점으로 되돌아 왔을 때 길이 비교 후 반환
			if(startX == nx && startY == ny) {
				if(cnt > ans) {
					ans = cnt;
					return;
				}
			}
			if(!visited[map[nx][ny]]) {
				visited[map[nx][ny]] = true;
				solve(nx, ny, dir, cnt + 1);
				solve(nx, ny, dir + 1, cnt + 1); 
				visited[map[nx][ny]] = false; //백트래킹
			}			
		}
		visited[map[x][y]] = false; //백트래킹
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			visited = new boolean[101];
			ans = -1;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 시작 좌표 받고 탐색
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					startX = i;
					startY = j;
					solve(i, j, 0, 1);
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
}
