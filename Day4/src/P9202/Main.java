package P9202;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static int w;
	static String[] words;
	static boolean[] findWords;
	static int b;
	static Board[] boards;
	static Trie t;
	// for DFS
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getInput();

		// words 배열을 길이와 사전순으로 정렬
		//System.out.println(Arrays.toString(words));
		Arrays.sort(words, new Comparator<String>() {
		    @Override
		    public int compare(String a, String b) {
		    	if (a.length() == b.length()) {
		    		for (int i = 0; i < a.length(); i++) {
		    			char c1 = a.charAt(i);
		    			char c2 = b.charAt(i);
		    			if (c1 != c2)
		    				return c2 - c1;
		    		}
		    	}
		        return a.length() - b.length();
		    }
		});
		//System.out.println(Arrays.toString(words));

		
		// Trie에 사전 단어들을 넣는다.
		t = new Trie();
		for (int i = 0; i < w; i++)
			t.insert(words[i]);
		
		int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		// 각각의 board에 대해 정답을 구한다.
		for (int i = 0; i < b; i++) {
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					dfs(i, x, y, t.root, "");
				}
			}			
			
			// print answer
			int maxScore = 0;
			String maxWord = "HI";
			int countWord = 0;
			for (int j = 0; j < w; j++) {
				if (findWords[j]) {
					maxScore += score[words[j].length()];
					maxWord = words[j];
					countWord++;
				}
			}
			System.out.println(maxScore + " " + maxWord + " " + countWord);
			
			for (int j = 0; j < w; j++)
				findWords[j] = false;
		}
	}

	static void getInput() {
		try {
			//System.setIn(new FileInputStream("src\\P9202\\input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			w = Integer.parseInt(br.readLine());
			words = new String[w];
			findWords = new boolean[w];
			for (int i = 0; i < w; i++) {
				words[i] = br.readLine();
				findWords[i] = false;
			}
			
			br.readLine();
			b = Integer.parseInt(br.readLine());
			boards = new Board[b];
			for (int i = 0; i < b; i++) {
				boards[i] = new Board(br.readLine(), br.readLine(), br.readLine(), br.readLine());
				br.readLine();
			}
			
			visited = new boolean[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++)
					visited[i][j] = false;
			}
		}
		catch (Exception e) {
			System.out.println("Input Error");
		}
	}
	
	static void dfs(int boardIdx, int x, int y, TrieNode tn, String s) {
		if (tn.isEnd) {
			int lo = 0, hi = w;
			int idx = (lo + hi) / 2;
			while(!words[idx].equals(s)) {
				if (words[idx].length() < s.length())
					lo = idx + 1;
				else if (words[idx].length() > s.length())
					hi = idx - 1;
				else {
		    		for (int i = 0; i < s.length(); i++) {
		    			char c1 = words[idx].charAt(i);
		    			char c2 = s.charAt(i);
		    			if (c1 < c2) {
		    				hi = idx - 1;
		    				break;
		    			}
		    			else if (c1 > c2) {
		    				lo = idx + 1;
		    				break;
		    			}
		    		}
				}
				idx = (lo + hi) / 2;
			}
			findWords[idx] = true;
		}			
		//System.out.print("hi : ");
		for (int i = 0; i < 8; i++) {
			int cx = x + dx[i], cy = y + dy[i];
			//System.out.print("boardIdx : " + boardIdx + " cx : " + cx + " cy : " + cy + " s : " + s + "\n");
			// index가 범위 안에 들어있고 아직 방문하지 않은 주사위의 경우 탐색한다.
			if (cx >= 0 && cx < 4 && cy >= 0 && cy < 4 && !visited[cy][cx]) {
				char cnt = boards[boardIdx].getChar(cx, cy);
				if (tn.hasChild(cnt)) {
					visited[cy][cx] = true;
					dfs(boardIdx, cx, cy, tn.children[cnt - 'A'], s + cnt);
					visited[cy][cx] = false;
				}
			}
		}
	}
}

class Board {
	String[] b;
	
	Board (String a, String b, String c, String d) {
		this.b = new String[4];
		this.b[0] = a;
		this.b[1] = b;
		this.b[2] = c;
		this.b[3] = d;
	}
	
	char getChar(int x, int y) {
		return b[y].charAt(x);
	}
}

class Trie {
	TrieNode root = new TrieNode();
	
	void insert(String word) {
		TrieNode cnt = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!cnt.hasChild(c)) {
				cnt.children[c - 'A'] = new TrieNode();
			}
			cnt = cnt.children[c - 'A'];			
		}
		cnt.isEnd = true;
	}
	
	boolean checkWord(String word) {
		TrieNode cnt = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (cnt.hasChild(c)) {
				cnt = cnt.children[c - 'A'];
			}
			else
				return false;
		}
		return cnt.isEnd;
	}
}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean isEnd;
	boolean isHit;
	
	boolean hasChild(char c) {
		return children[c - 'A'] != null;
	}
}