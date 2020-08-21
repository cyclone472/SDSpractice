#include <iostream>
#include <limits>
#include <algorithm>
using namespace std;

int dp[501][501];
int arr[501][2];

int solve(int, int);

int main() {
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		int r, c;
		cin >> r >> c;
		arr[i][0] = r;
		arr[i][1] = c;
	}
	for (int i = 0; i < 501; i++) {
		for (int j = 0; j < 501; j++) {
			dp[i][j] = INT_MAX;
		}
	}

	cout << solve(1, n) << '\n';

	return 0;
}

int solve(int start, int end) {
	if (start == end) {
		return 0;
	}
	if (dp[start][end] != INT_MAX) {
		return dp[start][end];
	}
	//cout << "start : " << start << ",  end : " << end << '\n';
	int ret = INT_MAX;
	for (int i = start; i < end; i++) {
		ret = min(ret, solve(start, i) + solve(i + 1, end) +
			arr[start][0] * arr[i][1] * arr[end][1]);
		//cout << "RET : " << ret << '\n';
	}

	return dp[start][end] = ret;
}