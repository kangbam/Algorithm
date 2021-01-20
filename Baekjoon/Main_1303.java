import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, M, allies, enemy;
	static char[][] map;
	static boolean[][] visited;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	public static int solve(Queue<Pair> q, char type) {
		int cnt = 0;
		
		while(!q.isEmpty()){
			Pair p = q.remove();
			for (int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (type == 'W' && (map[nx][ny] == 'W' && !visited[nx][ny])) {
						visited[nx][ny] = true;
						cnt++;
						q.add(new Pair(nx, ny));						
					}else if (type == 'B' && (map[nx][ny] == 'B' && !visited[nx][ny])) {
						visited[nx][ny] = true;
						cnt++;
						q.add(new Pair(nx, ny));						
					}
				}
			}
		}
		if (cnt == 0) {
			return 1;
		} else { 
			return (cnt * cnt);
		}
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		Queue<Pair> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++){
				map[i][j] = input.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < M; j++){
				if (map[i][j] == 'W' && !visited[i][j]) {
					q.add(new Pair(i, j));
					allies += solve(q, map[i][j]);
				} else if (map[i][j] == 'B' && !visited[i][j]){
					q.add(new Pair(i, j));
					enemy += solve(q, map[i][j]);
				}
			}			
		}
		System.out.println(allies + " " + enemy);
	}	
}
