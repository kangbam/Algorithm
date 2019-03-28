// 2382. 미생물 격리 

import java.util.ArrayList;
import java.util.Scanner;

class bacInfo{
	int x;
	int y;
	int num;
	int dir;
	bacInfo(int x, int y, int num, int dir){
		this.x = x;
		this.y = y;
		this.num = num;
		this.dir = dir;
	}
}

public class Solution {
	static int N, M, K;
	static int ans = -1;
	static int[][] map;
	static int[][] visited;
	static ArrayList<bacInfo> list;
	static final int[] dx = {0, -1, 1, 0, 0}; // 1상 2하 3좌 4우
	static final int[] dy = {0, 0, 0, -1, 1};
	
	public static void solve(int time) {
		if(time == M + 1) {
			ans = getResult(); // 살아남은 미생물 수 구하기
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			bacInfo bI = list.get(i);
			visited[bI.x][bI.y]--;
			int nx = bI.x + dx[bI.dir];
			int ny = bI.y + dy[bI.dir];
			int num = bI.num;
			int dir = bI.dir;
			// 맵 양 끝 약품이 있는 곳에 닿았을 때 - 미생물 수 반감, 방향 반대로 전환
			if(nx == 0 || nx == N - 1) {
				num = num / 2;
				switch(dir) {
					case 1:
						dir = 2;
						break;
					case 2:
						dir = 1;
						break;				
				}
			}
			if(ny == 0 || ny == N - 1) {
				num = num / 2;
				switch(dir) {
					case 3:
						dir = 4;
						break;
					case 4:
						dir = 3;
						break;				
				}
			}
			// 다음 칸으로 전진하며 생긴 정보들 저장
			bI.x = nx;
			bI.y = ny;
			bI.num = num;
			bI.dir = dir;
			visited[nx][ny]++;
		}
		collision(); // 미생물 군집 끼리 충돌하는 경우
		solve(time + 1); // 격리시간 증가시키며 재귀
	}
	
	public static void collision() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int totalNum = 0;
				int maxNum = 0;
				int nextDir = 0;
				// 방문배열에 미생물 군집이 두 개 이상 한 경우
				if(visited[i][j] > 1) {
					for(int k = list.size() - 1; k >= 0 ; k--) {
						bacInfo bI = list.get(k);
						int curX = bI.x;
						int curY = bI.y;
						int curNum = bI.num;
						int curDir = bI.dir;
						if(curX == i && curY == j) {
							list.remove(k);
							totalNum += curNum;
							// 가장 큰 미생물 군집의 방향을 따르므로 
							if(maxNum < curNum) {
								nextDir = curDir;
								maxNum = curNum;
							}
						}
					}
					// 합쳐진 미생물 정보 갱신
					list.add(new bacInfo(i, j, totalNum, nextDir));
					visited[i][j] = 1;
				}
			}
		}
	}
	
	public static int getResult() {
		int result = 0;
		for(int i = 0; i < list.size(); i++) {
			result += list.get(i).num;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			visited = new int[N][N];
			list = new ArrayList<bacInfo>();
			
			for(int i = 0; i < K; i++) {
				int r = sc.nextInt(); // x좌표
				int c = sc.nextInt(); // y좌표
				int num = sc.nextInt(); // 미생물 수
				int dir = sc.nextInt(); // 방향
				list.add(new bacInfo(r, c, num, dir));
				visited[r][c] = 1;
			}
			solve(1); // 격리 시간
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
}
