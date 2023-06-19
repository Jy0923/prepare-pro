#include <iostream>
#include <cmath>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int n, m, pArr[100001][20], depth[100001];
vector<int> v[100001];
bool visit[100001];

void searchP()
{
	queue<int> q;
	visit[1] = true;
	q.push(1);
	depth[1] = 1;
	while (!q.empty())
	{
		int st = q.front();
		q.pop();
		for (int i = 0; i < v[st].size(); i++)
		{
			int ed = v[st][i];
			if (!visit[ed])
			{
				visit[ed] = true;
				pArr[ed][0] = st;
				depth[ed] = depth[st] + 1;
				q.push(ed);
			}
		}
	}
}

int lca(int a, int b)
{
	int da = depth[a];
	int db = depth[b];
	if (da < db)
	{
		int tmp = a;
		a = b;
		b = tmp;
		da = depth[a];
		db = depth[b];
	}
	int d = da - db;
	for (int i = 19; i >= 0; i--)
	{
		if (d &(1 << i))
		{
			a = pArr[a][i];
		}
	}
	if (a != b)
	{
		for (int i = 19; i >= 0; i--)
		{
			if (pArr[a][i] != 0 && pArr[a][i] != pArr[b][i])
			{
				a = pArr[a][i];
				b = pArr[b][i];
			}
		}
		a = pArr[a][0];
		b = pArr[b][0];
	}
	return a;
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> n;
	for (int i = 0; i < n - 1; i++)
	{
		int a, b;
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}
	searchP();
	for (int j = 1; j <= 19; j++)
	{
		for (int i = 1; i <= n; i++)
		{
			pArr[i][j] = pArr[pArr[i][j - 1]][j - 1];
		}
	}
	
	cin >> m;
	for (int i = 0; i < m; i++)
	{
		int a, b;
		cin >> a >> b;
		cout << lca(a, b) << "\n";
	}
}