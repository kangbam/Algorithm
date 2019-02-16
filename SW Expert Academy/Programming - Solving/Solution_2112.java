// 2112. 보호 필름
import java.util.Scanner;

public class Solution {
	static int D, W, K, ans;
	static int[][] map;
	
	public static void solve(int idx, int cnt) {
		// 이미 구한 값이 더 작은 경우. 불필요한 연산 X
		if(cnt >= ans) {
			return;
		}
		if(idx >= D) {
			if(check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		solve(idx + 1, cnt); // 약품을 넣지 않고 탐색(초기상태)
		
		int[] temp = new int[W];
		for(int i = 0; i < W; i++) {
			temp[i] = map[idx][i];
		}
		// idx번 째 약품A 처리
		for(int i = 0; i < W; i++) {
			map[idx][i] = 0;
		}
		solve(idx + 1, cnt + 1); // A처리 후 탐색
		
		// idx번 째 약품B 처리
		for(int i = 0; i < W; i++) {
			map[idx][i] = 1;
		}
		solve(idx + 1, cnt + 1); // B처리 후 탐색
		
		// 백트래킹
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
			ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			solve(0, 0); // index, cnt
			System.out.println("#" + tc + " " + ans);
		}
	}
}
