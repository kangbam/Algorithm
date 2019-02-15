package solution;

import java.util.Scanner;

public class Solution_2112 {
	static int D, W, K, ans;
	static int[][] map;
	
	public static void solve(int idx, int cnt) {
		if(cnt >= ans) {
			return;
		}
		if(idx >= D) {
			if(check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		solve(idx + 1, cnt);
		int[] temp = new int[W];
		for(int i = 0; i < W; i++) {
			temp[i] = map[idx][i];
		}
		
		for(int i = 0; i < W; i++) {
			map[idx][i] = 0;
		}
		solve(idx + 1, cnt + 1);
		
		for(int i = 0; i < W; i++) {
			map[idx][i] = 1;
		}
		solve(idx + 1, cnt + 1);
		
		for(int i = 0; i < W; i++) {
			map[idx][i] = temp[i];
		}
	}
	
	public static boolean check() {
		for(int i = 0; i < W; i++) {
			int temp = map[0][i];
			int cnt = 0;
			boolean flag = false;
			for(int j = 0; j < D; j++) {
				if(temp == map[j][i]) {
					cnt++;
				}else {
					temp = map[j][i];
					cnt = 1;
				}
				if(cnt == K) {
					flag = true; 
					break;
				}
			}
			if(!flag) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			map = new int[D][W];
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			ans = Integer.MAX_VALUE;
			solve(0, 0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
