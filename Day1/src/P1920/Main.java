package P1920;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] A;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P1920\\input"));
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(sc.next());
		}
		Arrays.sort(A);
		
		M = Integer.parseInt(sc.next());
		for (int i = 0; i < M; i++) {
			int cnt = Integer.parseInt(sc.next());
			int ret = Arrays.binarySearch(A, cnt);
			if (ret >= 0)
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

}
