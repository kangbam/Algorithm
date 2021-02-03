import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int h, w, ans;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void solve(int x, int y) {
		if (visited[x][y]) {
			return;
		}
		
		visited[x][y] = true;
		
		for (int k = 0; k < 8; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if (0 <= nx && nx < h && 0 <= ny && ny < w) {
				if (!visited[nx][ny] && map[nx][ny] == 1) {
					solve(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			visited = new boolean[h][w];
			ans = 0;
			
			if (h == 0 && w == 0) {
				return;
			}
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						++ans;
						solve(i, j);
					}					
				}
			}
			System.out.println(ans);
		}
	}
}
