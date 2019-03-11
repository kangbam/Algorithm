// 5644. 무선 충전

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class AP{
	int x;
	int y;
	int c;
	int p;
	AP(int x, int y, int c, int p){
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

public class Solution {
	static int M, A, ans;
	static int[] moveA, moveB; // 사람 A, B의 이동정보
	static List<AP> list; // AP 정보
	static int[][][] chargeMap;
	static final int[] dx = {0, -1, 0, 1, 0};
	static final int[] dy = {0, 0, 1, 0, -1};
	
	public static void chargeZone() {
		Queue<AP> q = new LinkedList<>();
		boolean[][][] visited = new boolean[A][11][11];
		for(int i = 0; i < A; i++) {			
			q.clear();
			AP apInfo = list.get(i); // 인덱스 별로 차례대로 AP초기정보를 받아와 큐에 저장 후 탐색
			int x = apInfo.x;
			int y = apInfo.y;
			int c = apInfo.c;
			int p = apInfo.p;
			q.add(new AP(x, y, 0, p)); // 충전범위 0 부터
			chargeMap[i][x][y] = p;
			visited[i][x][y] = true;
			while(!q.isEmpty()) {
				AP ap = q.remove();
				if(ap.c == c) break; // 탐색범위에 다다르면 종료
				for(int k = 1; k <= 4; k++) {
					int nx = ap.x + dx[k];
					int ny = ap.y + dy[k];
					if(0 < nx && nx < 11 && 0 < ny && ny < 11) {
						if(!visited[i][nx][ny]) {
							q.add(new AP(nx, ny, ap.c + 1, ap.p)); // 탐색 범위 1씩 증가하며 큐에 추가
							chargeMap[i][nx][ny] = ap.p;
							visited[i][nx][ny] = true;
						}
					}
				}
			}			
		}
	}
	
	public static void solve(int time, int x1, int y1, int x2, int y2, int sum) {
		// 초기 위치 탐색 시간 0 초
		if(time == M + 1) {
			ans = sum;
			return;
		}
		// 0부터 시작하므로 초기검사도 시행
		int nx1 = x1 + dx[moveA[time]];	//이동 정보에 따른 다음 좌표
		int ny1 = y1 + dy[moveA[time]];
		int nx2 = x2 + dx[moveB[time]];
		int ny2 = y2 + dy[moveB[time]];
		int cnt1 = distance(nx1, ny1);	//사람A가 몇 개의 충전범위에 있는지 카운트
		int cnt2 = distance(nx2, ny2);	//사람B가 몇 개의 충전범위에 있는지 카운트
		int result = 0; // 충전 값
		// 완전 탐색
		if(cnt1 >= 1 && cnt2 >= 1) { // 두사람 모두 충전 범위 안에 있을 때
			for(int i = 0; i < A; i++) {
				for(int j = 0; j < A; j++) {
					// 같은 충전 범위일 때
					if(i == j && chargeMap[i][nx1][ny1] > 0 && chargeMap[j][nx2][ny2] > 0) {
						result = Math.max(result, chargeMap[i][nx1][ny1]);
					// 서로 다른 충전 범위 일 때
					}else if(i != j && chargeMap[i][nx1][ny1] > 0 && chargeMap[j][nx2][ny2] > 0) {
						result = Math.max(result, chargeMap[i][nx1][ny1] + chargeMap[j][nx2][ny2]);
					}
				}
			}
		}else if((cnt1 >= 1 && cnt2 == 0) || (cnt1 == 0 && cnt2 >= 1)) { // 각자 한명만 충전범위에 있는 경우
			int max1 = max(nx1, ny1);	//현재 좌표 중 가장 큰 충전량 체크
			int max2 = max(nx2, ny2);
			result = max1 + max2;
		}
		solve(time + 1, nx1, ny1, nx2, ny2, sum + result);	//time증가 시키며 다음 좌표 탐색
	}
	
	public static int distance(int x, int y){
		int cnt = 0;
		for(int i = 0; i < A; i++) {
			if(chargeMap[i][x][y] > 0) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public static int max(int x, int y)	{
		int max = 0;
		for(int i = 0; i < A; i++) {
			if(chargeMap[i][x][y] > 0) {
				max = Math.max(max, chargeMap[i][x][y]);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			M = sc.nextInt(); // 이동 횟수
			A = sc.nextInt(); // AP 갯수
			moveA = new int[M + 1];
			moveB = new int[M + 1];
			list = new ArrayList<>(); // AP 정보 담을 리스트
			chargeMap = new int[A][11][11];
			// 이동정보 입력
			for(int i = 1; i <= M; i++) {
				moveA[i] = sc.nextInt();
			}
			for(int i = 1; i <= M; i++) {
				moveB[i] = sc.nextInt();
			}
			//AP 정보 입력
			for(int i = 0; i < A; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				int c = sc.nextInt();
				int p = sc.nextInt();
				list.add(new AP(x, y, c, p));
			}
			chargeZone(); // ap정보를 이용해 충전 범위 맵에 펼침
			solve(0, 1, 1, 10, 10, 0); // time, 사람A좌표, 사람B좌표, 충전된 값
			System.out.println("#" + tc + " "+ ans);
		}
		sc.close();
	}
}
