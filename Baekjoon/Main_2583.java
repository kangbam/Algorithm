import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2583 {
	static int N, M, K, groupCnt;
	static int[][] map;
	static int[][] visited;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
		
	public static void solve (int x, int y) {
		if (visited[x][y] != 0) {
			return;
		}
		
		visited[x][y] = groupCnt;
		
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				if (map[nx][ny] == 0 && visited[nx][ny] == 0) {					
					solve(nx, ny);
				}
			}		
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());		
		map = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sX = Integer.parseInt(st.nextToken());
			int sY = Integer.parseInt(st.nextToken());
			int eX = Integer.parseInt(st.nextToken());
			int eY = Integer.parseInt(st.nextToken());
			
			for (int x = sX; x < eX; x++) {
				for (int y = sY; y < eY; y++) {					
					map[x][y] = 1;
				}
			}
		}
		groupCnt = 0;		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 && visited[i][j] == 0){
					++groupCnt;
					solve(i, j);
				}
			}
		}
		
		int[] ans = new int[groupCnt];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] != 0) {
					ans[visited[i][j] - 1]++;
				}			
			}
		}
		
		Arrays.sort(ans);
		
		System.out.println(groupCnt);
		for (int i = 0; i < groupCnt; i++) {
			System.out.print(ans[i] + " ");
		}
	}
}
