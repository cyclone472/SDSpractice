package P2143;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int t;
	static int n, m;
	static long[] a;
	static long[] b;
	static long[] subA;
	static long[] subB;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getInput();
		
		subA = new long[n*(n+1)/2];
		subB = new long[m*(m+1)/2 + 1];
		
		int idx = 0;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				subA[idx++] = sum + a[j];
				sum += a[j];
			}
		}
		
		idx = 0;
		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int j = i; j < m; j++) {
				subB[idx++] = sum + b[j];
				sum += b[j];
			}
		}
		subB[subB.length-1] = 2100000000;
		
		Arrays.sort(subA);
		Arrays.sort(subB);
		
		long ans = 0;
		for (int i = 0; i < subA.length; i++) {
			long target = t - subA[i];
			// target의 값을 가지는 원소들의 lower, upper bound 찾기
			int low = lowerbound(target);
			int upp = upperbound(target);
			
			if (subB[low] != target)
				continue;
			ans += (upp - low);
		}
		
		System.out.println(ans);
	}
	
	static void getInput() throws Exception {
		//System.setIn(new FileInputStream("src\\P2143\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		t = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		a = new long[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			a[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		b = new long[m];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++)
			b[i] = Integer.parseInt(st.nextToken());
	}
	
	static int lowerbound(long key) {
		int lo = 0, hi = subB.length - 1;
		
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (subB[mid] < key)
				lo = mid + 1;
			else
				hi = mid;
		}
		return hi;
	}
	
	static int upperbound(long key) {
		int lo = 0, hi = subB.length - 1;
		
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (subB[mid] <= key)
				lo = mid + 1;
			else
				hi = mid;
		}
		return hi;
	}
}
