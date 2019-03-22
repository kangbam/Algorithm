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

public class Main {
	static int N, L, R;
	static int groupCnt;
	static int[][] map;
	static int[][] visited;
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	public static boolean solve(int x, int y) {
		groupCnt++;	// 총 그룹 번호 +1
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		visited[x][y] = groupCnt; // 방문배열에 현재 그룹번호 저장
		int groupPeople = map[x][y]; // 총 그룹의 인구 수
		int groupNum = 1; // 그룹에 속한 칸의 갯수
		
		while(!q.isEmpty()) {
			Pair p = q.remove();
			for(int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				if(0 <= nx && nx < N && 0 <= ny && ny < N) {
					if(visited[nx][ny] == 0) {
						int diff = Math.abs(map[nx][ny] - map[p.x][p.y]); // 다음 칸과 현재 칸의 차이
						if(L <= diff && diff <= R) {	// 차이가 L과 R사이라면 인구이동 가능
							visited[nx][ny] = groupCnt;
							groupNum++;
							groupPeople += map[nx][ny];
							q.add(new Pair(nx, ny));
						}
					}
				}
			}
		}
		
		// 현재 그룹 번호에 소속된 칸에 총 인원 수를 나누어 map에 저장
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] == groupCnt) {
					map[i][j] = groupPeople / groupNum;
				}
			}
		}
		
		
		if(groupNum > 1) return true; // 연합이 존재 할 경우
		else return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 맵의 크기
		L = sc.nextInt();	// L명 이상
		R = sc.nextInt();	// R명 이하
		map = new int[N][N];
		visited = new int[N][N];
		
		// map 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int ans = 0;
		while(true) {	// 인구이동이 없을 때 까지 반복
			boolean flag = false; // 인구이동을 했는지 체크
			groupCnt = 0;
			// 모두 칸 인구이동 확인
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == 0) {
						if(solve(i, j)) {
							flag = true;
						}
					}
				}
			}
			
			if(flag) { //인구이동이 있다면 답 증가
				ans++;
			}else { // 인구이동이 없다면 종료
				break;
			}
			
			// 방문 배열 초기화
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visited[i][j] = 0;
				}
			}
		}
		System.out.println(ans);
		sc.close();
	}
}
