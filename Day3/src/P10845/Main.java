package P10845;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int n;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P10845\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		Deque<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch (command) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				q.add(num);
				break;
			case "front":
				if (q.isEmpty())
					System.out.println(-1);
				else
					System.out.println(q.peek());
				break;
			case "back":
				if (q.isEmpty())
					System.out.println(-1);
				else
					System.out.println(q.peekLast());
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "pop":
				if (q.isEmpty())
					System.out.println(-1);
				else
					System.out.println(q.poll());
				break;
			case "empty":
				if (q.isEmpty())
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
