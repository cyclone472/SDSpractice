package P1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, K;
	static String[] words;
	static boolean[] visited;
	static int selectedCount;
	static int ret = -1;
	
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("src\\P1062\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		K = Integer.parseInt(sc.next());
		words = new String[N];
		visited = new boolean[26];
		visited['n' - 'a'] = visited['t' - 'a'] =
			visited['i' - 'a'] = visited['c' - 'a'] = true;
		selectedCount = 4;
		
		for(int i = 0; i < N; i++) {
			words[i] = sc.next().replaceAll("[antic]", "");
		}
		
		if(K <= 4) {
			System.out.println(0);
		}
		else {
			dfs(0);
			System.out.println(ret);
		}
		
		/*
		System.out.println(Arrays.toString(words));
		System.out.println(Arrays.toString(visited));
		*/
	}
	
	static void dfs(int index) {
		visited[index] = true;
		selectedCount++;
		if(selectedCount == K) {
			// ���� ���¿��� ���� �� �ִ� �ܾ��� �� ���
			ret = Math.max(ret, calcAnswer());
		} else {
			for(int i = index + 1; i < 26; i++) {
				if(visited[i] == false) {
					dfs(i);
				}
			}
			
		}
		visited[index] = false;
		selectedCount--;
	}
	
	static int calcAnswer() {
		int ret = 0;
		
		for(int i = 0; i < N; i++) {
			boolean canRead = true;
			for(int j = 0; j < words[i].length(); j++) {
				int index = words[i].charAt(j) - 'a';
				if(visited[index] == false) {
					canRead = false;
					break;
				}
			}
			
			if (canRead)
				ret++;
		}
		return ret;
	}
}