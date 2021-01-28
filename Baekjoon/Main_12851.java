import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K , minTime, cnt;
	static int[] visited;
	static int[] dx = {-1, 1, 0};

	public static void solve (int n) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		visited[n] = 1;
		
		while(!q.isEmpty()) {
			int current = q.remove();
			
			if (visited[current] > minTime) {
				return;
			}
			
			for (int k = 0; k < 3; k++) {
				int next;
				if (dx[k] != 0){
					next = current + dx[k];
				} else {
					next = current * 2;
				}
				
				if (0 <= next && next <= 100000) {
					if (next == K) {
						minTime = visited[current];
						cnt++;
					}
					
					if ((visited[next] == 0) || (visited[next] == visited[current] + 1)) {
						q.add(next);
						visited[next] = visited[current] + 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[100001];
		minTime = Integer.MAX_VALUE;
		cnt = 0;
		
		solve(N);
		
		System.out.print(minTime + "\n" + cnt);
	}
}
