import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// 4013. 특이한 자석

class magInfo{
	int num;
	int dir;
	magInfo(int num, int dir){
		this.num = num;
		this.dir = dir;
	}
}

public class Solution {
	static int K;
	static int[][] mag;
	
	public static void solve(int num, int dir) {
		Queue<magInfo> q = new LinkedList<>();
		q.add(new magInfo(num, dir));
		
		boolean[] visited = new boolean[4];
		visited[num] = true;
		
		while(!q.isEmpty()) {
			magInfo mI = q.remove();
			int curNum = mI.num;
			int curDir = mI.dir;
			int left = curNum - 1;
			int right = curNum + 1;

			if(left >= 0) {
				if(!visited[left]) {
					if(mag[left][2] != mag[curNum][6]) {
						visited[left] = true;
						q.add(new magInfo(left, curDir * -1));
					}
				}
			}
			if(right < 4) {
				if(!visited[right]) {
					if(mag[curNum][2] != mag[right][6]) {
						visited[right] = true;
						q.add(new magInfo(right, curDir * -1));
					}
				}
			}
			rotate(curNum, curDir);
		}
	}
	
	public static void rotate(int num, int dir) {
		int temp = 0;
		
		switch(dir) {
			case 1:
				temp = mag[num][7];
				for(int i = 7; i > 0; i--) {
					mag[num][i] = mag[num][i - 1];
				}
				mag[num][0] = temp;
				break;
			case -1:
				temp = mag[num][0];
				for(int i = 0; i < 7; i++) {
					mag[num][i] = mag[num][i + 1];
				}
				mag[num][7] = temp;
				break;
		}
	}
	
	public static int calc() {
		int ans = 0;
		int[] score = {1, 2, 4, 8};
		for(int i = 0; i < 4; i++) {
			if(mag[i][0] == 1) {
				ans += score[i];
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			K = sc.nextInt();
			mag = new int[4][8];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 8; j++) {
					mag[i][j] = sc.nextInt();
				}
			}
			while(K-- > 0) {
				int num = sc.nextInt();
				int dir = sc.nextInt();
				solve(num - 1, dir);
			}
			System.out.println("#" + tc + " " + calc());
		}
		sc.close();
	}
}
