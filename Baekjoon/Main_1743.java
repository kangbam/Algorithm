import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Main_1743 {
	static int N, M, K;
	static int[][] map;
	static int[][] trash;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve(Queue<Pair> q) {		
		int cnt = 1;
		
		while(!q.isEmpty()){
			Pair p = q.remove();
			for (int k = 0; k < 4; k++) {				
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (map[nx][ny] == 1 && trash[nx][ny] == 0) {
						trash[nx][ny] = ++cnt;
						q.add(new Pair(nx, ny));
					}
				}
			}
		}	
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		trash = new int[N][M];		
		Queue<Pair> q = new LinkedList<>();
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && trash[i][j] == 0) {
					trash[i][j] = 1;
					q.add(new Pair(i, j));
					solve(q);
				}
			}
		}
		
		int ans = Integer.MIN_VALUE;		
		
		for(int i = 0; i < N; i++	){
			for(int j = 0; j < M; j++){				
				ans = Math.max(ans, trash[i][j]);
			}	
		}		
		
		System.out.println(ans);
    }
}

