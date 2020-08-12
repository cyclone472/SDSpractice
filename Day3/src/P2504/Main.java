package P2504;

import java.io.*;
import java.util.*;

class Element {
	boolean isNum;
	int num;
	char c;
	
	Element (int num) {
		this.isNum = true;
		this.num = num;
	}
	
	Element (char c) {
		this.isNum = false;
		this.c = c;
	}
}

public class Main {
	static String input;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P2504\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		Stack<Element> s = new Stack<>();
		
		input = sc.next();
		sc.close();

		boolean isValid = true;
		for (int i = 0; i < input.length(); i++) {
			char cnt = input.charAt(i);
			//System.out.println(cnt);
			if (cnt == '[' || cnt == '(') {
				Element e = new Element(cnt);
				s.push(e);
			}
			else {
				int sum = 0;
				// top의 원소가 값이거나 짝이 맞는 원소여야 함
				isValid = false;
				while (!s.isEmpty()) {
					Element top = s.pop();
					if (top.isNum) {
						sum += top.num;
					}
					else if (top.c == '(' && cnt == ')') {
						if (sum == 0)
							s.push(new Element(2));
						else
							s.push(new Element(2 * sum));
						
						isValid = true;
						break;
					}
					else if (top.c == '[' && cnt == ']') {
						if (sum == 0)
							s.push(new Element(3));
						else
							s.push(new Element(3 * sum));
						
						isValid = true;
						break;
					}
					else {
						isValid = false;
						break;
					}
				}
			}
			
			if(!isValid)
				break;
		}
		
		// 다 끝나고 나서 스택에 값들만 존재하면 다 더해서 출력
		int ans = 0;
		while (!s.isEmpty() && isValid) {
			Element top = s.pop();
			if (top.isNum)
				ans += top.num;
			else {
				isValid = false;
				break;
			}
		}
		
		if (isValid)
			System.out.println(ans);
		else
			System.out.println(0);
	}

}
