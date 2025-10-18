#include <iostream>
#include <vector>
#include <algorithm>

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

    vector<pair<int, int>> operation;
    vector<int> pending, raising;

    for (int h = n; h >= 1; --h) {
        pending.insert(pending.end(), groups[h].begin(), groups[h].end());
        while (!pending.empty()) {
            if (!raising.empty()) {
                int p_idx = pending.back(); pending.pop_back();
                int r_idx = raising.back(); raising.pop_back();
                operation.emplace_back(2, p_idx);
                pending.push_back(r_idx);
            } else if (pending.size() >= 2) {
                int p_idx1 = pending.back(); pending.pop_back();
                int p_idx2 = pending.back(); pending.pop_back();
                operation.emplace_back(1, h - 1);
                operation.emplace_back(2, p_idx2);
                raising.push_back(p_idx1);
            } else {
                int p_idx = pending.back(); pending.pop_back();
                operation.emplace_back(1, h - 1);
                raising.push_back(p_idx);
            }
        }
    }

    cout << operation.size() << '\n';
    reverse(operation.begin(), operation.end());
    for (auto& op : operation) {
        cout << op.first << " " << op.second << '\n';
    }

    return 0;
}