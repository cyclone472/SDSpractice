package P10828;

import java.util.Stack;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
	static int n;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P10828\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		Stack<Integer> s = new Stack<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch (command) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				s.push(num);
				break;
			case "top":
				if (s.empty())
					System.out.println(-1);
				else
					System.out.println(s.peek());
				break;
			case "size":
				System.out.println(s.size());
				break;
			case "pop":
				if (s.empty())
					System.out.println(-1);
				else
					System.out.println(s.pop());
				break;
			case "empty":
				if (s.empty())
					System.out.println(1);
				else
					System.out.println(0);
				break;
			default:
				System.out.println("ERROR");
			}
		}
		
	}

}
