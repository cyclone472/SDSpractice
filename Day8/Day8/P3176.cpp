#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
#define pii pair<int, int>

vector<pii> graph[100001];
int depth[100001];
bool visited[100001];
int par[100001][18];
int largest[100001][18];
int shortest[100001][18];
int n, k;

void dfs(int, int);
void updateParent(void);

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;
	for (int i = 0; i < n - 1; i++) {
		int a, b, weight;
		cin >> a >> b >> weight;
		graph[a].push_back(make_pair(b, weight));
	}
	// preprocess
	dfs(1, 0);
	updateParent();

	cin >> k;
	while (k--) {
		int n1, n2;
		cin >> n1 >> n2;

		int lo = -1, sh = 123456789;
		// n1과 n2의 depth 맞추기
		if (depth[n1] != depth[n2]) {
			int diff = abs(depth[n1] - depth[n2]);
			while (diff != 0) {
				int jump = 0, tmp = 1;
				while (tmp < diff) {
					jump++;
					tmp *= 2;
				}

				if (depth[n1] > depth[n2]) {
					lo = max(lo, largest[n1][jump]);
					sh = min(sh, shortest[n1][jump]);
					n1 = par[n1][jump];
				}
				else {
					lo = max(lo, largest[n2][jump]);
					sh = min(sh, shortest[n2][jump]);
					n2 = par[n2][jump];
				}
				diff -= tmp;
			}
		}

		// n1-LCA, n2-LCA 중 가장 긴 경로와 짧은 경로 출력
		for (int i = 0; i < 18; i++) {
			if (par[n1][i] == par[n2][i]) {
				lo = max(lo, max(largest[n1][i], largest[n2][i]));
				sh = min(sh, min(shortest[n1][i], shortest[n2][i]));
				break;
			}
		}
		cout << lo << ' ' << sh << '\n';
	}

	return 0;
}

void dfs(int cnt, int dep) {
	visited[cnt] = true;
	depth[cnt] = dep;
	for (pii x : graph[cnt]) {
		if (!visited[x.first]) {
			par[x.first][0] = cnt;
			largest[x.first][0] = x.second;
			shortest[x.first][0] = x.second;
			dfs(x.first, dep + 1);
		}
	}
}

void updateParent() {
	for (int v = 1; v <= n; v++) {
		for (int k = 1; k <= 17; k++) {
			par[v][k] = par[par[v][k - 1]][k - 1];
			largest[v][k] = max(largest[largest[v][k - 1]][k - 1], largest[v][k - 1]);
			shortest[v][k] = min(shortest[shortest[v][k - 1]][k - 1], shortest[v][k - 1]);
		}
	}
}