package P2805;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static long m;
	static int[] wood;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P2805\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Long.parseLong(st.nextToken());
		wood = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			wood[i] = Integer.parseInt(st.nextToken());
		
		// Binary Search
		int lo = 0, hi = 1000000000;
		while (lo + 1 < hi) {
			// 가져갈 수 있는 나무의 길이 계산
			int mid = (lo + hi) / 2;
			long totalWood = 0;
			for(int i = 0; i < n; i++) {
				totalWood += (mid < wood[i] ? wood[i] - mid : 0);
			}

			if (totalWood < m)
				hi = mid;
			else
				lo = mid;
		}
		
		System.out.println(lo);
	}

}
