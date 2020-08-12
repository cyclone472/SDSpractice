package P1991;

import java.io.*;
import java.util.StringTokenizer;

class Node {
	String name;
	String left;
	String right;
	
	Node(String name, String left, String right) {
		this.name = name;
		this.left = left;
		this.right = right;
	}
}

public class Main {
	static int n;
	static Node[] arr;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P1991\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new Node[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();
			
			arr[i] = new Node(name, left, right);
		}

		int rootIdx = -1;
		for (int i = 0; i < n; i++) {
			if(arr[i].name.contentEquals("A")) {
				rootIdx = i;
				break;
			}
		}
		
		preorder(arr[rootIdx]);
		System.out.println();
		inorder(arr[rootIdx]);
		System.out.println();
		postorder(arr[rootIdx]);
		System.out.println();
	}

	public static void preorder(Node cnt) {
		System.out.print(cnt.name);
		if (!cnt.left.contentEquals(".")) {
			int leftIndex = -1;
			for (int i = 0; i < n; i++) {
				if (arr[i].name.equals(cnt.left)) {
					leftIndex = i;
					break;
				}
			}
			preorder(arr[leftIndex]);
		}
		if (!cnt.right.contentEquals(".")) {
			int idx = -1;
			for (int i = 0; i < n; i++) {
				if (arr[i].name.equals(cnt.right)) {
					idx = i;
					break;
				}
			}
			preorder(arr[idx]);
		}
	}
	
	public static void inorder(Node cnt) {
		if (!cnt.left.contentEquals(".")) {
			int leftIndex = -1;
			for (int i = 0; i < n; i++) {
				if (arr[i].name.equals(cnt.left)) {
					leftIndex = i;
					break;
				}
			}
			inorder(arr[leftIndex]);
		}
		System.out.print(cnt.name);
		if (!cnt.right.contentEquals(".")) {
			int idx = -1;
			for (int i = 0; i < n; i++) {
				if (arr[i].name.equals(cnt.right)) {
					idx = i;
					break;
				}
			}
			inorder(arr[idx]);
		}
	}
	
	public static void postorder(Node cnt) {
		if (!cnt.left.contentEquals(".")) {
			int leftIndex = -1;
			for (int i = 0; i < n; i++) {
				if (arr[i].name.equals(cnt.left)) {
					leftIndex = i;
					break;
				}
			}
			postorder(arr[leftIndex]);
		}
		if (!cnt.right.contentEquals(".")) {
			int idx = -1;
			for (int i = 0; i < n; i++) {
				if (arr[i].name.equals(cnt.right)) {
					idx = i;
					break;
				}
			}
			postorder(arr[idx]);
		}
		System.out.print(cnt.name);
	}
}
