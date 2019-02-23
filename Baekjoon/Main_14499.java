// 14499. 주사위 굴리기

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] dice, moveK, result;
	static final int[] dx = {0, 0, 0, -1, 1};
	static final int[] dy = {0, 1, -1, 0, 0};
	
	public static void solve(int i, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(0 <= nx && nx < N && 0 <= ny && ny < M) {
			rotate(dir); // 주사위 굴림
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice[6];
			}else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}
			x = nx;
			y = ny;
			result[i] = dice[1];
		}
	}
	
	public static void rotate(int dir) {
		int[] temp = dice.clone();
		switch(dir) {
			case 1:
				dice[1] = temp[4];
				dice[3] = temp[1];
				dice[4] = temp[6];
				dice[6] = temp[3];
				break;
			case 2:
				dice[1] = temp[3];
				dice[3] = temp[6];
				dice[4] = temp[1];
				dice[6] = temp[4];
				break;
			case 3:
				dice[1] = temp[5];
				dice[2] = temp[1];
				dice[5] = temp[6];
				dice[6] = temp[2];
				break;
			case 4:
				dice[1] = temp[2];
				dice[2] = temp[6];
				dice[5] = temp[1];
				dice[6] = temp[5];
				break;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		dice = new int[7];
		moveK = new int[K];
		result = new int[K];
		Arrays.fill(result, -1);
		// 맵 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 방향정보 입력
		for(int i = 0; i < K; i++) {
			moveK[i] = sc.nextInt();
		}
		// 방향정보에 따라 이동
		for(int i = 0; i < K; i++) {
			solve(i, moveK[i]); // 횟수, 방향
		}
		// 맵을 벗어나는 경우 출력하면 안되므로
		for(int i = 0; i < K; i++) {
			if(result[i] != -1) {
				System.out.println(result[i]);
			}
		}
	}
}
