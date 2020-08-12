package P1713;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Picture {
	int init_time;
	int recommand;
	int student;
	
	public Picture (int init, int student) {
		init_time = init;
		recommand = 1;
		this.student = student;
	}

	@Override
	public String toString() {
		return "[init_time=" + init_time + ", recommand=" + recommand + ", student=" + student + "]";
	}
}

public class Main {
	static int N;
	static int[] arr;
	static Picture[] pic;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("src\\P1713\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.next());
		int tmp = Integer.parseInt(sc.next());
		arr = new int[tmp];
		pic = new Picture[N];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(sc.next());
			// �л� ������ �̹� �ִ��� Ȯ��
			boolean isExist = false;
			for(int j = 0; j < pic.length; j++) {
				// ������ ��õ���� Ƚ���� �ø�
				if (pic[j] != null && pic[j].student == arr[i]) {
					pic[j].recommand++;
					isExist = true;
					break;
				}
			}
			// ������ ����Ʋ�� ����ִ� �ڸ��� �ִ��� Ȯ��
			if (!isExist) {
				boolean canPost = false;
				int replaceIdx = -1;
				for (int j = 0; j < pic.length; j++) {
					// ����ִ� �ڸ��� ������ ����Ʋ���� �ִ´�.
					if (pic[j] == null) {
						canPost = true;
						pic[j] = new Picture(i, arr[i]);
						break;
					}
					// ����ִ� �ڸ��� ������ ��ü
					else if (replaceIdx == -1)
						replaceIdx = j;
					else {
						// ��õ���� Ƚ���� ���� �����鼭 ���� ������ �л��� ��ü
						if(pic[j].recommand < pic[replaceIdx].recommand)
							replaceIdx = j;
						else if (pic[j].recommand == pic[replaceIdx].recommand
							&& pic[j].init_time < pic[replaceIdx].init_time)
							replaceIdx = j;
					}
				}
				
				if(!canPost) {
					pic[replaceIdx] = new Picture(i, arr[i]);
				}
			}
		}		
		sc.close();

		int[] ans = new int[pic.length];
		for (int i = 0; i < pic.length; i++) {
			ans[i] = pic[i].student;
		}
		Arrays.sort(ans);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}

}