// 14889. 스타트와 링크

import java.util.Scanner;

public class Main {
	static int N;
	static int ans = Integer.MAX_VALUE;
	static int[][] arr;
	static boolean[] visited;
	
	public static void solve(int idx, int cnt) {
		if(cnt == N / 2 + 1) {
			divideTeam();
		// 사람에게 어디 팀에 배정할지 boolean값 배정
		}else {
			for(int i = idx + 1; i <= N; i++) {
				if(!visited[i]) {
					visited[i] = true;
					solve(i, cnt + 1);
				}
			}
		}
		// 백트래킹
		visited[idx] = false;
	}
	
	public static void divideTeam() {
		int[] start = new int[N / 2 + 1];
		int[] link = new int[N / 2 + 1];
		int startX = 1;
		int linkX = 1;
		// visited가 true 일 때 스타트팀에 false면 link팀에 저장
		for(int i = 1; i <= N; i++) {
			if(visited[i]) {
				start[startX++] = i;
			}else {
				link[linkX++] = i;
			}
		}
		difference(start, link);
	}
	
	public static void difference(int[] start, int[] link) {
		int startStat = getStat(start);
		int linkStat = getStat(link);
		int diff = Math.abs(startStat - linkStat);
		if(diff < ans) {
			ans = diff;
		}
	}
	
	public static int getStat(int[] team) {
		int sum = 0;
		for(int i = 1; i <= N / 2; i++) {
			for(int j = i + 1; j <= N / 2; j++) { // 자기자신 중복 제외
				sum += arr[team[i]][team[j]];
				sum += arr[team[j]][team[i]];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		solve(1, 1); // idx, cnt
		System.out.println(ans);
	}
}
