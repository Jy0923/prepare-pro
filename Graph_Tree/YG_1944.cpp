#include <iostream>
#include <map>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pii;

struct Line
{
	int in;
	int out;
	int dist;
};

const int dy[] = {1, -1, 0, 0};
const int dx[] = {0, 0, 1, -1};
int n, m, ans;
string board[50];
map<pii, int> keys;
int route[260][260];
bool visit[50][50];
int p[260];

int cmp(Line a, Line b)
{
	return a.dist < b.dist;
}

int find(int x)
{
	if(p[x] == x)
	{
		return x;
	}
	return p[x] = find(p[x]);
}

void unionNums(int x, int y)
{
	x = find(x);
	y = find(y);

	if(x == y)
	{
		return;
	}

	if(x < y)
	{
		p[y] = x;
	}
	else
	{
		p[x] = y;
	}
}

void input()
{
	cin >> n >> m;
	for(int i = 0; i < n; i++)
	{
		cin >> board[i];
	}
	int cnt = 0;
	for(int y = 0; y < n; y++)
	{
		for(int x = 0; x < n; x++)
		{
			if(board[y][x] == 'K' || board[y][x] == 'S')
			{
				keys[{y, x}] = cnt++;
			}
		}
	}
	for(int i = 0; i <= m; i++)
	{
		p[i] = i;
	}
}

void bfs(pii input)
{
	for(int y = 0; y < n; y++)
	{
		for(int x = 0; x < n; x++)
		{
			visit[y][x] = false;
		}
	}
	queue<pii> q;
	q.push({input.first * 100 + input.second, 0});
	while(!q.empty())
	{
		pii now = q.front();
		q.pop();
		int y = now.first / 100;
		int x = now.first % 100;
		if(board[y][x] == 'S' || board[y][x] == 'K')
		{
			int start = keys[{input.first, input.second}];
			int end = keys[{y, x}];
			if(start != end && route[start][end] == 0 && route[end][start] == 0)
			{
				route[start][end] = now.second;
			}
		}
		for(int i = 0; i < 4; i++)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n || board[ny][nx] == '1' || visit[ny][nx])
			{
				continue;
			}
			visit[ny][nx] = true;
			q.push({ny * 100 + nx, now.second + 1});
		}
	}
}

void move()
{
	for(auto it = keys.begin(); it != keys.end(); it++)
	{
		bfs(it->first);
	}
	vector<Line> v;
	for(int y = 0; y < m + 1; y++)
	{
		for(int x = 0; x < m + 1; x++)
		{
			if(route[y][x] != 0)
			{
				v.push_back({y, x, route[y][x]});
			}
		}
	}
	sort(v.begin(), v.end(), cmp);
	int cnt = 0;
	for(int i = 0; i < v.size(); i++)
	{
		Line now = v[i];
		if(cnt == m)
		{
			break;
		}
		int x = find(v[i].in);
		int y = find(v[i].out);
		if(y == x)
		{
			continue;
		}
		unionNums(y, x);
		ans += v[i].dist;
		cnt++;
	}
	if(cnt != m)
	{
		ans = -1;
	}
}

int main()
{
	input();
	move();
	cout << ans;
}