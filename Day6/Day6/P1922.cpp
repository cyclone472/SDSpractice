#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Edge {
public:
	int start;
	int end;
	int weight;

	Edge(int a, int b, int c) {
		this->start = a;
		this->end = b;
		this->weight = c;
	}

	static bool comp(const Edge* e1, const Edge* e2) {
		return (e1->weight < e2->weight);
	}
};

vector<Edge*> set;
vector<int> parent;

int find(int x) {
	if (parent[x] == x)
		return x;
	return parent[x] = find(parent[x]);
}

void merge(int a, int b) {
	int parA = find(a);
	int parB = find(b);
	parent[parA] = parB;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, m;
	cin >> n >> m;
	parent.push_back(-1);
	for (int i = 1; i <= m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		set.push_back(new Edge(a, b, c));
		parent.push_back(i);
	}

	sort(set.begin(), set.end(), Edge::comp);
	int ans = 0;
	for (int i = 0, j = 0; i < n-1; i++, j++) {
		while (find(set[j]->start) == find(set[j]->end)) {
			j++;
		}
		merge(set[j]->start, set[j]->end);
		ans += set[j]->weight;
	}
	cout << ans << '\n';

	return 0;
}