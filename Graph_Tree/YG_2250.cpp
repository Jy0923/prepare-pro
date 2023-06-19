#include <iostream>
#include <algorithm>
#define MAX 987654321
using namespace std;

struct Node
{
	int left;
	int right;
};

Node nodes[10001];
bool parents[10001];
int depth[10001];
int low[10001];
int high[10001];
int n, root, width;
int ans, maxWidth, maxDepth;

void input()
{
	cin >> n;
	width = 1;
	for(int i = 0; i < n; i++)
	{
		int left, mid, right;
		cin >> mid >> left >> right;
		nodes[mid].left = left;
		nodes[mid].right = right;
		low[i] = MAX;
		if(left != -1)
		{
			parents[left] = true;
		}
		if(right != -1)
		{
			parents[right] = true;
		}
	}
	low[n] = MAX;
}

void searchRoot()
{
	for(int i = 1; i <= n; i++)
	{
		if(!parents[i])
		{
			root = i;
			depth[root] = 1;
			return;
		}
	}
}

void dfs(int now)
{
	int left = nodes[now].left;
	int right = nodes[now].right;
	maxDepth = max(depth[now], maxDepth);
	if(left > 0)
	{
		depth[left] = depth[now] + 1;
		dfs(left);
	}
	low[depth[now]] = min(low[depth[now]], width);
	high[depth[now]] = max(high[depth[now]], width++);
	if(right > 0)
	{
		depth[right] = depth[now] + 1;
		dfs(right);
	}
}

void searchAns()
{
	for(int i = 1; i<= maxDepth; i++)
	{
		if(high[i] - low[i] + 1> maxWidth)
		{
			maxWidth = high[i] - low[i] + 1;
			ans = i;
		}
	}
}

int main()
{
	cin.tie(0);
	cout.tie(0);
	ios::sync_with_stdio(0);
	input();
	searchRoot();
	dfs(root);
	searchAns();
	cout << ans << " " << maxWidth;
}