package P3055;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Pair {
	Integer x, y;
	public Pair(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	public Integer first() {
		return x;
	}
	public Integer second() {
		return y;
	}
}

public class Main {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Pair> water_q = new LinkedList<>();
	static Queue<Pair> hog_q = new LinkedList<>();
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P3055\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		R = Integer.parseInt(sc.next());
		C = Integer.parseInt(sc.next());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int y = 0; y < R; y++) {
			String s = sc.next();
			for (int x = 0; x < C; x++) {
				char cnt = s.charAt(x);
				map[y][x] = cnt;
				if (map(x, y) == '*')
					water_q.add(new Pair(x, y));
				else if (map(x, y) == 'S')
					hog_q.add(new Pair(x, y));
			}
		}
		sc.close();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				visited[i][j] = false;
			}
		}

		int ans = 0;
		boolean canArrive = false;
		while (!hog_q.isEmpty()) {
			ans++;
			canArrive = escape();
			flood();
			
			if (canArrive)
				break;
		}

		if (canArrive)
			System.out.println(ans);
		else
			System.out.println("KAKTUS");
		
	}
	
	static char map(int x, int y) {
		if (x < 0 || x >= C || y < 0 || y >= R)
			return 'X';
		return map[y][x];
	}
	
	static void flood() {
		int size = water_q.size();
		for(int i = 0; i < size; i++) {
			Pair p = water_q.poll();
			
			for (int j = 0; j < 4; j++) {
				int x = p.x + dx[j];
				int y = p.y + dy[j];
				char cnt = map(x, y);
				if(cnt == 'S' || cnt == '.') {
					map[y][x] = '*';
					water_q.add(new Pair(x, y));
				}					
			}
		}
	}
	
	static boolean escape() {
		int size = hog_q.size();
		for(int i = 0; i < size; i++) {
			Pair p = hog_q.poll();
			if (map(p.x, p.y) == '*')
				continue;
			
			for (int j = 0; j < 4; j++) {
				int x = p.x + dx[j];
				int y = p.y + dy[j];
				char cnt = map(x, y);
				if(cnt == '.') {
					map[y][x] = 'S';
					hog_q.add(new Pair(x, y));
				}
				else if (cnt == 'D')
					return true;
			}
		}
		return false;
	}
}