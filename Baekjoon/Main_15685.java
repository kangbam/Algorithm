// 15685. 드래곤 커브

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static boolean[][] map = new boolean[101][101];
	static final int[] dx = {0, -1, 0, 1};
	static final int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i = 0; i < N; i++) {
			int y = sc.nextInt(); // y좌표
			int x = sc.nextInt(); // x좌표
			int d = sc.nextInt(); // 시작 방향
			int g = sc.nextInt(); // 총 세대
			// 방향을 담을 리스트
			List<Integer> list = new ArrayList<>();
			list.add(d);
			// 세대를 증가하며 이전 세대의 방향 정보를 뒤집고 각 방향을 1식증가하여 저장(각 방향 왼쪽으로 바뀜)
			for(int j = 0; j < g; j++) {
				for(int k = list.size() - 1; k >= 0; k--) {
					int nextDir = (list.get(k) + 1) % 4 ;
					list.add(nextDir);
				}
			}
			map[x][y] = true; // 처음 좌표 찍고
			// 리스트안의 방향대로 이동하며 좌표 찍음
			for(int dir : list) {
				x += dx[dir];
				y += dy[dir];
				map[x][y] = true;
			}
			
		}
		int ans = 0;
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
    sc.close();
	}
}
