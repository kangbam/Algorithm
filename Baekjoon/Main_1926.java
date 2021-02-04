import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1926 {
	static int n, m, paintCnt, paintSize;
	static int[][] map;
	static int[][] visited;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve (int x, int y) {
		if (visited[x][y] != 0) {
			return;
		}
		
		visited[x][y] = paintCnt;
		
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (visited[nx][ny] == 0 && map[nx][ny] == 1) {
					solve(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		paintCnt = 0;
		paintSize = 0;
				
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++){
				if (visited[i][j] == 0 && map[i][j] == 1) {
					++paintCnt;
					solve(i, j);
				}
			}
		}
		
		// 최대 그림 넓이
		int[] paintCntArr = new int[paintCnt + 1];			
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++){
				if (visited[i][j] != 0) {
					paintCntArr[visited[i][j]]++;
				}
			}
		}
		
		for (int i = 1; i <= paintCnt; i++) {
			paintSize = Math.max(paintSize, paintCntArr[i]);
		}		
		
		System.out.println(paintCnt + "\n" + paintSize);
	}
}
