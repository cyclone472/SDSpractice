#include <iostream>
using namespace std;

int parent[1000001];

int find(int a) {
	if (a == parent[a])
		return a;
	return parent[a] = find(parent[a]);
}

void merge(int a, int b) {
	if (a < b)
		return merge(b, a);

	int parA = find(a);
	int parB = find(b);
	parent[parB] = parA;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, m;
	cin >> n >> m;
	for (int i = 0; i < 1000001; i++) {
		parent[i] = i;
	}

	while (m--) {
		int command, a, b;
		cin >> command >> a >> b;
		
		if (command == 0)
			merge(a, b);
		else {
			if (find(a) == find(b))
				cout << "YES\n";
			else
				cout << "NO\n";
		}
	}
	return 0;
}