import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2468 {
	static int N, ans;
	static int[][] map;
	static boolean[][] visited;	
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve (int x, int y, int rain) {
		if (visited[x][y]){
			return;
		}
		visited[x][y] = true;
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (!visited[nx][ny] && map[nx][ny] > rain) {
					solve(nx, ny, rain);
				}				
			}
		}
	}	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		ans = 1;
		int highest = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				highest = Math.max(map[i][j], highest);
			}
		}
		
		for (int rain = 0; rain <= highest; rain++) {
			int cnt = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) continue;
					if (map[i][j] > rain) {
						++cnt;
						solve(i, j, rain);
					}
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
}
