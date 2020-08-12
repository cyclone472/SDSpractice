package P1339;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static String[] num;
	static boolean[] containAlpha;
	static int numAlpha;
	static int[] alphaToNum;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P1339\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		num = new String[N];
		containAlpha = new boolean[26];
		alphaToNum = new int[26];
		
		for (int i = 0; i < N; i++) {
			num[i] = sc.next();
			for (int j = 0; j < num[i].length(); j++) {
				int idx = num[i].charAt(j) - 'A';
				containAlpha[idx] = true;
			}
		}
		
		numAlpha = 0;
		for (int i = 0; i < 26; i++) {
			alphaToNum[i] = -1;
			if (containAlpha[i])
				numAlpha++;
		}
		
		
		System.out.println(dfs(0));
		//System.out.println("numAlpha : " + numAlpha);
	}
	
	static int dfs(int cnt) {
		if (cnt == numAlpha)
			return calc();
		
		int ret = -1;
		for (int i = 0; i < 26; i++) {
			if(containAlpha[i] == true && alphaToNum[i] == -1) {
				alphaToNum[i] = cnt + (10 - numAlpha);
				int next = dfs(cnt + 1);
				ret = (ret < next ? next : ret);
				alphaToNum[i] = -1;
			}
		}
		
		return ret;
	}
	
	static int calc() {
		int ret = 0;
		for(int i = 0; i < num.length; i++) {
			for (int j = num[i].length() - 1; j >= 0; j--) {
				ret += (alphaToNum[num[i].charAt(j) - 'A'] * pow(10, num[i].length() - 1 - j));
			}
		}
		return ret;
	}
	
	static int pow(int a, int b) {
		int ret = 1;
		for (int i = 0; i < b; i++) {
			ret *= a;
		}
		return ret;
	}
}