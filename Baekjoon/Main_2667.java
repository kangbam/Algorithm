import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	static int N;
	static int[][] map;
	static int[][] village;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve (int x, int y, int cnt) {		
		village[x][y] = cnt;
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (village[nx][ny] == 0 && map[nx][ny] == 1) {
					solve(nx, ny, cnt);
				}
			}
		}		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		village = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int cnt = 0;		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && village[i][j] == 0) {
					solve(i, j, ++cnt);
				}
			}
		}
		
		int[] ans = new int[cnt];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				if (village[i][j] != 0) {
					ans[village[i][j] - 1]++;
				}
			}
		}
		
		Arrays.sort(ans);
		System.out.println(cnt);
		for(int i = 0; i < cnt; i++){
			System.out.println(ans[i]);
		}
	}
}
