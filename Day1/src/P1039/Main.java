package P1039;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] N;
	static int K;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src\\P1039\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		char[] tmp = sc.next().toCharArray();
		N = new int[tmp.length];
		for (int i = 0; i < N.length; i++) {
			N[i] = tmp[i] - '0';
		}
		K = Integer.parseInt(sc.next());
		int numChange = 0;
		
		int max = 0;
		for (int i = 1; i < N.length; i++) {
			max = (max < N[i] ? N[i] : max);
		}

		if (max == 0 && N.length == 2)
			System.out.println(-1);		
		for (int i = 0; numChange < K && i < N.length; i++) {
			max = 0;
			int maxIdx = -1;
			for (int j = i; j < N.length; j++) {
				if (max < N[j]) {
					max = N[j];
					maxIdx = j;
				}
			}

			if (maxIdx != -1 && N[i] != max) {
				int tmp2 = N[i];
				N[i] = N[maxIdx];
				N[maxIdx] = tmp2;
				numChange++;
			}				
		}
		
		if (numChange < K) {
			K -= numChange;
			if (K % 2 != 0) {
				int tmp3 = N[N.length-2];
				N[N.length-2] = N[N.length-1];
				N[N.length-1] = tmp3;;		
			}
		}
		
		for (int i = 0; i < N.length; i++) {
			System.out.print(N[i]);
		}
	}

}
