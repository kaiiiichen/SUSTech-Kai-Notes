#include <iostream>
#include <vector>
#include <functional>
#include <utility>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<vector<int>> groups(n + 1);
    for (int i = 1; i <= n; ++i) {
        int b;
        cin >> b;
        groups[b].push_back(i);
    }

    vector<pair<int, int>> ops;

    function<void(int, int)> solve =
        [&](int l, int r) {
            if (l == r) {
                return;
            }

            int mid = l + (r - l) / 2;

            for (int v = mid + 1; v <= r; ++v) {
                for (int idx : groups[v]) {
                    ops.emplace_back(2, idx);
                }
            }

            for (int h = l + 1; h <= mid; ++h) {
                ops.emplace_back(1, h);
            }

            solve(l, mid);
            solve(mid + 1, r);
    };

    solve(0, n);

    cout << ops.size() << '\n';
    for (auto& op : ops) {
        cout << op.first << " " << op.second << '\n';
    }

    return 0;
}