#include <iostream>
#include <algorithm>
using namespace std;
typedef pair<int, int> pii;

int l, w, h, n, ans;
pii box[21];
bool flag = true;

void input()
{
	cin >> l >> w >> h >> n;
	for(int i = 0; i < n; i++)
	{
		int num, cnt;
		cin >> num >> cnt;
		box[num] = {(1 << num), cnt};
	}
}

void fill(int len, int wid, int hei, int start)
{
	if(len == 0 || wid == 0 || hei == 0)
	{
		return;
	}
	for(int i = start; i >= 0; i--)
	{
		if(len >= box[i].first && wid >= box[i].first && hei >= box[i].first && box[i].second > 0)
		{
			box[i].second--;
			ans++;
			fill(len - box[i].first, wid, box[i].first, i);
			fill(box[i].first, wid - box[i].first, box[i].first, i);
			fill(len, wid, hei - box[i].first, i);
			return;
		}
	}
	flag = false;
}

int main()
{
	input();
	fill(l, w, h, n - 1);
	if(flag)
	{
		cout << ans;
	}
	else
	{
		cout << -1;
	}
}