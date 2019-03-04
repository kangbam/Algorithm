// 3190. 뱀

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class snakeInfo{
	int x;
	int y;
	snakeInfo(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main_3190 {
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static int changeDir(int dir, char instruct) {
		int nextDir = 0;
		if(instruct == 'L') {
			nextDir = (dir + 3) % 4;
		}else if(instruct == 'D') {
			nextDir = (dir + 1) % 4;
		}
		return nextDir;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];
		// 맵 외곽에 벽으로 막기
		for(int i = 0; i < N + 2; i++) {
			map[i][0] = map[N + 1][i] = map[0][i] = map[i][N + 1] = 3;
		}
		// 사과위치 입력
		for(int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = 1; // 사과
		}
		// 시간에 따른 지시방향
		char[] move = new char[100001];
		int L = sc.nextInt();
		for(int i = 0; i < L; i++) {
			int X = sc.nextInt();
			char C = sc.next().charAt(0);
			move[X] = C;
		}
		List<snakeInfo> snake = new ArrayList<>(); // 뱀의 정보를 담기위한 리스트
		snakeInfo p = new snakeInfo(1, 1); // 초기값 저장
		snake.add(p);
		map[p.x][p.y] = 2; // 뱀의 위치 2로 저장
		int dir = 1; // 초기방향 (오른쪽 시작)
		int time = 0;
		while(true) {
			++time;
			snakeInfo head = snake.get(snake.size() - 1);
			int nx = head.x + dx[dir];
			int ny = head.y + dy[dir];
			// 다음 칸이 벽이거나 뱀이면 종료
			if(map[nx][ny] == 2 || map[nx][ny] == 3) {
				break;
			// 다음 칸이 빈칸이라면 리스트에 저장하며 원래 있던 자리 (꼬리부분) 삭제
			}else if(map[nx][ny] == 0) {
				map[nx][ny] = 2;
				snake.add(new snakeInfo(nx, ny));
				snakeInfo tail = snake.remove(0);
				map[tail.x][tail.y] = 0;
			// 다음 칸이 사과라면 리스트에 저장 길이 증가
			}else if(map[nx][ny] == 1) {
				map[nx][ny] = 2;
				snake.add(new snakeInfo(nx, ny));
			}
			// 지시된 방향으로 전환
			if(move[time] == 'L' || move[time] == 'D') {
				dir = changeDir(dir, move[time]);
			}
		}
		System.out.println(time);
    sc.close();
	}
}
