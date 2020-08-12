package P9663;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] board;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P9663\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		board = new int[N];
		
		for(int i = 0; i < N; i++) {
			// board에 queen을 놓을 수 있으면 true
			board[i] = -1;
		}
		
		System.out.println(solve(0));
	}
	
	static int solve(int row) {
		if(row == N) {
			return 1;
		}
		
		int ret = 0;		
		for(int col = 0; col < N; col++) {
			if(canPlace(row, col)) {
				board[row] = col;
				ret += solve(row + 1);
				board[row] = -1;
			}
		}		
		return ret;
	}

	static boolean canPlace(int row, int col) {
	    for(int i = 0; i < row; i++) {
	    	if(board[i] == col || Math.abs(row - i) == Math.abs(col - board[i]))
	    		return false;
	    }
		return true;
	}
}
