// 2117. 홈 방범 서비스 (DFS )

import java.util.ArrayList;
import java.util.Scanner;

class Pair{
	int x;
	int y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	static int N, M, ans;
	static ArrayList<Pair> house;
	static int[][] map;
	
	public static void solve(int x, int y, int K) {
		// 문제에 명시된 서비스 영역에 따른 비용
		int cost = K * K + (K - 1) * (K - 1);
		int houseCount = 0;
		// 비용이 최대 수익보다 클 경우 종료
		if(cost > house.size() * M) {
			return;
		}
		// 범위에 포함되어 있는지 house전부 비교
		for(int i = 0; i < house.size(); i++) {
			int houseX = house.get(i).x;
			int houseY = house.get(i).y;
			int dist = Math.abs(houseX - x) + Math.abs(houseY - y);
			
			if(dist < K) {
				houseCount += 1; // 집 카운트 증가
			}
		}
		// 수익이 비용보다 크다면
		if(houseCount * M >= cost) {
			ans = Math.max(ans, houseCount);
		}
		// 서비스 영역을 증가시키며 다시 탐색
		solve(x, y, K + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			house = new ArrayList<>();
			ans = Integer.MIN_VALUE;
			// map 입력
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] == 1) {
						house.add(new Pair(i, j));
					}
				}
			}
			// DFS 탐색
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					solve(i, j, 1); // x좌표, y좌표, 서비스 영역
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
}
