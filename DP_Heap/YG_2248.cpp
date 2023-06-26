#include <iostream>
using namespace std;
typedef long long ll;

string ans = "";
ll dp[32][32] = {0};

void solve(int n, int l, long long i)
{
	if (n == 0)
		return;
	if (l == 0)
	{
		for (int j = 0; j < n; j++)
			ans += '0';
		return;
	}
	long long pivot = dp[n - 1][l];
	if (i <= pivot)
	{
		ans += '0';
		solve(n - 1, l, i);
	}
	else
	{
		ans += '1';
		solve(n - 1, l - 1, i - pivot);
	}
}

int main()
{
	ios::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);
	int n, l;
	ll i;
	cin >> n >> l >> i;
	for(int k = 0; k <= n; k++)
	{
		for(int j = 0; j <= l; j++)
		{
			if(j == 0 || k == 0)
				dp[k][j] = 1;
			else
				dp[k][j] = dp[k - 1][j] + dp[k - 1][j - 1];
		}
	}
	solve(n, l, i);
	cout << ans;
}