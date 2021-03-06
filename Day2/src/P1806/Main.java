package P1806;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n, s;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getInput();
		
		long sum = arr[0];
		int start = 0, end = 0;
		int cntLength = 1;
		int ans = 10000000;
		
		while (end < arr.length && !(end == arr.length - 1 && sum < s)) {
			if (sum < s) {
				sum += arr[++end];
				cntLength++;
			}
			else {
				ans = (cntLength < ans ? cntLength : ans);
				sum -= arr[start++];
				cntLength--;
			}
			
			if (start > end) {
				end = start;
				cntLength = 1;
				sum = arr[start];
			}
			//System.out.println("start : " + start + " end : " + end + " cntLength : " + cntLength + " sum : " + sum);
		}
		
		if (ans == 10000000)
			System.out.println(0);
		else
			System.out.println(ans);
	}
	
	static void getInput() throws Exception {
		//System.setIn(new FileInputStream("src\\P1806\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		arr[n] = -2100000000;
	}
	
}
