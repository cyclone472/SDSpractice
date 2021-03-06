package P2580;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] board;
	static List<Point> l = new ArrayList<>();
	static final int[] dx = {0, 0, 0, 1, 1, 1, 2, 2, 2};
	static final int[] dy = {0, 1, 2, 0, 1, 2, 0, 1, 2};
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P2580\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		board = new int[9][9];
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				board[y][x] = Integer.parseInt(sc.next());
				if (board[y][x] == 0)
					l.add(new Point(x, y));
			}
		}
		
		dfs(0);
		
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				System.out.print(board[y][x] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean dfs(int cnt) {
		if(cnt == l.size())
			return true;
		
		boolean ans = false;
		boolean[] canSet = new boolean[10];
		for (int i = 0; i < canSet.length; i++) {
			canSet[i] = true;
		}
		int x = l.get(cnt).x;
		int y = l.get(cnt).y;
		// 같은 열과 행에 있는 숫자들은 후보에서 제거
		for (int i = 0; i < 9; i++) {
			canSet[board[y][i]] = false;
			canSet[board[i][x]] = false;
		}
		// 같은 정사각형에 있는 숫자들은 후보에서 제거
		for (int i = 0; i < 9; i++) {
			int cx = (x / 3) * 3 + dx[i];
			int cy = (y / 3) * 3 + dy[i];
			canSet[board[cy][cx]] = false;
		}
		
		for (int i = 0; i < canSet.length; i++) {
			if (canSet[i]) {
				board[y][x] = i;
				if(dfs(cnt + 1) == true)
					return true;
				board[y][x] = 0;
			}
		}
		
		return ans;
	}
}
