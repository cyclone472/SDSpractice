#include <iostream>
#include <vector>
using namespace std;

bool graph[501][501];

bool isParent(int a, int b) {
	if (a == b)
		return true;
	
	for (int i)
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, m;
	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int start, end;
		cin >> start >> end;
		graph[start][end] = true;
	}



	return 0;
}