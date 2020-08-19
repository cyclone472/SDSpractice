#include <iostream>
#include <cstring>
#include <vector>
using namespace std;

vector<int> graph[501];
vector<int> revGraph[501];
bool visited[501];
int n, m;

void getInput();
void dfs(int, vector<int>*);

int main() {
	getInput();
	
	//cout << "n : " << n << "   m : " << m << '\n';
	int ans = 0;
	for (int i = 1; i <= n; i++) {
		// i���� �����ؼ� dfs�� �ٸ� ���� Ž��
		visited[i] = true;
		dfs(i, graph);
		dfs(i, revGraph);

		// ��� ������ visit �� �� ������ �������� ����
		bool isAns = true;
		for (int j = 1; j <= n; j++) {
			if (!visited[j]) {
				isAns = false;
				break;
			}
		}

		if (isAns)
			ans++;

		memset(visited, 0, sizeof(visited));
	}
	cout << ans << '\n';

	return 0;
}

void getInput(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	memset(visited, 0, sizeof(visited));
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int start, end;
		cin >> start >> end;
		graph[start].push_back(end);
	}

	// ������ �ݴ�� ������ �׷����� ����� ��
	for (int start = 1; start <= n; start++) {
		for (int x : graph[start]) {
			revGraph[x].push_back(start);
		}
	}
}

void dfs(int cnt, vector<int>* g) {
	for (int x : g[cnt]) {
		if (!visited[x]) {
			visited[x] = true;
			dfs(x, g);
		}
	}
}