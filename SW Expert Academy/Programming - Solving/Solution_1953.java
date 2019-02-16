//1953. 탈주범 검거
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static int N, M, R, C, L;
	static int[][] map;
	static int[][] dist;
	static final int[] dx = {-1, 0, 1, 0}; // 0상, 1우, 2하, 3좌
	static final int[] dy = {0, 1, 0, -1}; // 위 부터 시계방향
	static int[][] pipe = {
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{1, 0, 1, 0},
			{0, 1, 0, 1},
			{1, 1, 0, 0},
			{0, 1, 1, 0},
			{0, 0, 1, 1},
			{1, 0, 0, 1}
	};
	
	public static void solve(Queue<Pair> q) {
		while(!q.isEmpty()) {
			Pair p = q.remove();
			for(int dir = 0; dir < 4; dir++) {
				int nx = p.x + dx[dir];
				int ny = p.y + dy[dir];
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					if(dist[nx][ny] == 0 && map[nx][ny] != 0) {
						// 현재 pipe상태와 비교할 다음 pipe방향 찾기
						int nextDir = findNextDir(dir);
						if(pipe[map[p.x][p.y]][dir] == 1 && pipe[map[nx][ny]][nextDir] == 1) {
							q.add(new Pair(nx, ny));
							dist[nx][ny] = dist[p.x][p.y] + 1; 
						}
					}					
				}				
			}
		}
	}
	// 비교할 다음 pipe의 방향 찾기
	public static int findNextDir(int curDir) {
		int nextDir = 0;
		switch(curDir) {
			case 0 :
				nextDir = 2;
				break;
			case 1 :
				nextDir = 3;
				break;
			case 2 :
				nextDir = 0;
				break;
			case 3 :
				nextDir = 1;
				break;
		}
		return nextDir;
	}
	// 모두 탐색한 후 조건에 맞는 dist만 찾아 ans증가
	public static int calc() {
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(1 <= dist[i][j] && dist[i][j] <= L) {
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
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			map = new int[N][M];
			dist = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			Queue<Pair> q = new LinkedList<>();
			q.add(new Pair(R, C));
			dist[R][C] = 1;
			solve(q);
			System.out.println("#" + tc + " " + calc());
		}
		sc.close();
	}
}
