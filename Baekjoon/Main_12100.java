// 12100. 2048(Easy)

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, ans;
	static int[][] map;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void solve(int dir, int[][] prev, int cnt) {
    // cnt 5이상일 때 반환
		if(cnt == 5) {
			int result = getResult(prev);
			ans = Math.max(ans, result);
			return;
		}
		int[][] temp = new int[N][N];
		arrayCopy(temp, prev); // 맵이 바뀌므로 맵을 복사하여 이동경로 저장
		switch(dir) {
		case 0:
			upMove(temp);
			break;
		case 1:
			rightMove(temp);
			break;
		case 2:
			downMove(temp);
			break;
		case 3:
			leftMove(temp);
			break;
		}
		// 4방향 완전 탐색
		for(int k = 0; k < 4; k++) {
			solve(k, temp, cnt + 1);
		}
	}
	
	public static void arrayCopy(int[][] arr1, int[][] arr2) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	
	public static int[][] upMove(int[][] temp){
		// 한줄 씩 큐에 저장한 후, 조건에 맞게 재배열
		for(int y = 0; y < N; y++) {
			Queue<Integer> q = new LinkedList<>();
			for(int x = 0; x < N; x++) {
				if(temp[x][y] != 0) {
					q.add(temp[x][y]);
				}
				temp[x][y] = 0;
			}
			int idx = 0;
			int num = 0; // 큐에 저장된 데이터
			while(!q.isEmpty()) {
				num = q.poll();
				// 현재 칸이 0 이면 데이터 바로 배치
				if(temp[idx][y] == 0) {
					temp[idx][y] = num;
				// 현재 칸이 큐에 저장된 데이터와 같을 경우 연산 후 배치
				}else if(num == temp[idx][y]) {
					temp[idx][y] *= 2;
					idx++;
				// 이외의 조건일 경우 다음칸에 배치
				}else {
					temp[++idx][y] = num;
				}
			}
		}
		return temp;
	}
	
	public static int[][] rightMove(int[][] temp){
		for(int x = 0; x < N; x++) {
			Queue<Integer> q = new LinkedList<>();
			for(int y = N - 1; y >= 0; y--) {
				if(temp[x][y] != 0) {
					q.add(temp[x][y]);
				}
				temp[x][y] = 0;
			}
			int idx = N - 1;
			int num = 0;
			while(!q.isEmpty()) {
				num = q.poll();
				if(temp[x][idx] == 0) {
					temp[x][idx] = num;
				}else if(num == temp[x][idx]) {
					temp[x][idx] *= 2;
					idx--;
				}else {
					temp[x][--idx] = num;
				}
			}
		}
		return temp;
	}

	public static int[][] downMove(int[][] temp){
		for(int y = 0; y < N; y++) {
			Queue<Integer> q = new LinkedList<>();
			for(int x = N -1; x >= 0; x--) {
				if(temp[x][y] != 0) {
					q.add(temp[x][y]);
				}
				temp[x][y] = 0;
			}
			int idx = N - 1;
			int num = 0;
			while(!q.isEmpty()) {
				num = q.poll();
				if(temp[idx][y] == 0) {
					temp[idx][y] = num;
				}else if(num == temp[idx][y]) {
					temp[idx][y] *= 2;
					idx--;
				}else {
					temp[--idx][y] = num;
				}
			}
		}
		return temp;
	}
	
	public static int[][] leftMove(int[][] temp){
		for(int x = 0; x < N; x++) {
			Queue<Integer> q = new LinkedList<>();
			for(int y = 0; y < N; y++) {
				if(temp[x][y] != 0) {
					q.add(temp[x][y]);
				}
				temp[x][y] = 0;
			}
			int idx = 0;
			int num = 0;
			while(!q.isEmpty()) {
				num = q.poll();
				if(temp[x][idx] == 0) {
					temp[x][idx] = num;
				}else if(num == temp[x][idx]) {
					temp[x][idx] *= 2;
					idx++;
				}else {
					temp[x][++idx] = num;
				}
			}
		}
		return temp;
	}
	
	public static int getResult(int[][] resultMap) {
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(resultMap[i][j] > result) {
					result = resultMap[i][j];
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		ans = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 4방향 검사 
		for(int k = 0; k < 4; k++) {
			solve(k, map, 0);
		}
		
		System.out.println(ans);
	}
}
