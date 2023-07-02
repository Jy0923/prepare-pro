#include <iostream>
#include <map>
#define HASH 1511
typedef long long ll;
using namespace std;

int n, m;
string a, b;
ll sqNums[26];
int ans;
map<ll, int> ma;

void input()
{
	cin >> a >> b;
	n = a.length();
	m = b.length();
	if(n > m)
	{
		string tmpStr = a;
		a = b;
		b = tmpStr;
		int tmpNum = n;
		n = m;
		m = tmpNum;
	}
	sqNums[0] = 1;
	for(int i = 1; i < 26; i++)
	{
		sqNums[i] = sqNums[i - 1] * HASH;
	}
}

void search(int len)
{
	ma.clear();
	ll sum = 0;
	for(int i = n - 1; i > n - 1 - len; i--)
	{
		sum += sqNums[a[i] - 'a'];
	}
	ma.insert({sum, 1});
	for(int i = n - 1 - len; i >= 0; i--)
	{
		sum -= sqNums[a[i + len] - 'a'];
		sum += sqNums[a[i] - 'a'];
		ma.insert({sum, 1});
	}
	sum = 0;
	for(int i = m - 1; i > m - 1 - len; i--)
	{
		sum += sqNums[b[i] - 'a'];
	}
	if(ma[sum] == 1)
	{
		ans = len;
		return;
	}
	for(int i = m - 1 - len; i >= 0; i--)
	{
		sum -= sqNums[b[i + len] - 'a'];
		sum += sqNums[b[i] - 'a'];
		if(ma[sum] == 1)
		{
			ans = len;
			return;
		}
	}
}

int main()
{
	input();
	for(int i = n; i >= 1; i--)
	{
		search(i);
		if(ans != 0)
		{
			cout << ans;
			return (0);
		}
	}
	cout << 0;
}