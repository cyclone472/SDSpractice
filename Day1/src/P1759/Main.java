package P1759;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int L, C;
	static char[] alpha;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P1759\\input"));
		Scanner sc = new Scanner(System.in);
		L = Integer.parseInt(sc.next());
		C = Integer.parseInt(sc.next());
		alpha = new char[C];
		for(int i = 0; i < alpha.length; i++) {
			alpha[i] = sc.next().charAt(0);
		}
		Arrays.sort(alpha);
		
		for(int i = 0; i < alpha.length; i++) {
			printAns(i, 1, Character.toString(alpha[i]));			
		}
	}

	static void printAns(int idx, int len, String prev) {
		if(len == L) {
			// 문자열이 1개 이상의 모음과 2개 이상의 자음을 가지는지 확인
			int vowel = 0;
			int consonant = 0;
			for(int i = 0; i < prev.length(); i++) {
				char c = prev.charAt(i);
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
					vowel++;
				else
					consonant++;
			}
			if (vowel >= 1 && consonant >= 2)
				System.out.println(prev);
			return;
		}
		
		for (int i = idx + 1; i < alpha.length; i++) {
			String s = Character.toString(alpha[i]);
			printAns(i, len+1, prev.concat(s));
		}
	}
}
