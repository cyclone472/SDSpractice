package trie;

public class TrieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Trie {
	TrieNode root = new TrieNode();
	
	void insert(String word) {
		TrieNode cnt = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int idx = c - 'A';
			if (!cnt.hasChild(c)) {
				cnt.children[idx] = new TrieNode();
			}
			cnt = cnt.children[idx];
		}
		cnt.isEnd = true;
	}
	
	boolean checkWord(String word) {
		TrieNode cnt = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (cnt.hasChild(c))
				cnt = cnt.getChild(c);
			else
				return false;
		}
		return cnt.isEnd;
	}
}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean isEnd;
	boolean isHit; // ���⼱ �� ������ �̹� ã�� �ܾ����� �ߺ��˻縦 �ϱ� ���� �÷��׸� ���� ���� �ִ�.
	
	TrieNode getChild(char c) {
		return children[c - 'A'];
	}
	
	boolean hasChild(char c) {
		return (children[c - 'A'] != null);
	}
}