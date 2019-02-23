// 14888. 연산자 끼워넣기
import java.util.Scanner;

public class Main {
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] nums, func;
	
	public static void solve(int idx, int plus, int sub, int multi, int divi,int sum) {
		// 탐색 종료 조건
		if(idx == N) {
			max = Integer.max(max, sum);
			min = Integer.min(min, sum);
			return;
		}
		// 완전 탐색
		if(plus < func[0]) {
			solve(idx + 1, plus + 1, sub, multi, divi, sum + nums[idx]);
		}
		if(sub < func[1]) {
			solve(idx + 1, plus, sub + 1, multi, divi, sum - nums[idx]);		
		}
		if(multi < func[2]) {
			solve(idx + 1, plus, sub, multi + 1, divi, sum * nums[idx]);
		}
		if(divi < func[3]) {
			solve(idx + 1, plus, sub, multi, divi + 1, sum / nums[idx]);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		func = new int[4];
		// 수
		for(int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		// 연산자
		for(int i = 0; i < 4; i++) {
			func[i] = sc.nextInt();
		}
		
		solve(1, 0, 0, 0, 0, nums[0]); // 탐색 횟수, +, -, *, /, 결과값
		System.out.println(max);
		System.out.println(min);
	}
}
