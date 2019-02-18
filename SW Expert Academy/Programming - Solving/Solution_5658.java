// 5658. 보물상자 비밀번호

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
	static int N;
	static HashSet<Integer> nums;
	
	public static void toDecimal(String str) {
		int result = 0;
		// 뒷 자리 부터 16진수를 10진수로
		for(int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			if('A' <= ch && ch <= 'F'){
				ch -= 55;
			}else {
				ch -= '0';
			}
			result += Math.pow(16, str.length() - i - 1) * ch;
		}
		// 10진수로 변환된 수를 HashSet에 추가
		nums.add(result);
	}
	
	public static String rotate(String str) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(str.charAt(str.length() - 1));
		sb.append(str.substring(0, str.length() - 1));
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			int K = sc.nextInt();
			String input = sc.next();

			nums = new HashSet<>();
			int cut = N / 4;
			
			// cut 만큼 회전 
			for(int i = 0; i < cut; i++) {
				for(int j = 0; j < N; j += cut) {
					// 한 변만큼 문자열을 자르고 10진수로 변환
					String cutStr = input.substring(j, j + cut);
					toDecimal(cutStr);
				}
				// 문자열을 한칸식 옮겨준다
				input = rotate(input);
			}
			
			ArrayList<Integer> list = new ArrayList<>(nums);
			Collections.sort(list);
			Collections.reverse(list);
			
			int ans = list.get(K - 1);
			System.out.println("#" + tc + " " + ans);
		}
	}
}
