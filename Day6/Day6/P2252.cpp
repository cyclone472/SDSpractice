#include <iostream>
#include <list>
#include <queue>
using namespace std;

list<int> height[32001];
int numSmaller[32001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, m;
	cin >> n >> m;
	for (int i = 0; i < 32001; i++) {
		numSmaller[i] = 0;
	}
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		height[a].push_back(b);
		numSmaller[b]++;
	}

	queue<int> q;
	for (int i = 0; i < n; i++) {
		if (q.empty()) {
			for (int j = 1; j <= n; j++) {
				if (numSmaller[j] == 0)
					q.push(j);
			}
		}

		int cnt = q.front();
		q.pop();
		numSmaller[cnt] = -1;
		for (int x : height[cnt]) {
			numSmaller[x]--;
		}
		cout << cnt << ' ';
	}
	cout << '\n';

	return 0;
}