#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    cin >> n;
    vector<int> b(n);
    for (int i = 0; i < n; ++i) {
        cin >> b[i];
    }

    vector<int> a(n, 0);
    vector<pair<int, int>> ops;

    for (int h = 0; h < n; ++h) {
        vector<int> stopper;
        vector<int> promoter;

        for (int i = 0; i < n; ++i) {
            if (a[i] == h) {
                if (b[i] == h) {
                    stopper.push_back(i + 1);
                } else if (b[i] > h) {
                    promoter.push_back(i + 1);
                }
            }
        }

        if (stopper.empty()) {
            if (!promoter.empty()) {
                ops.emplace_back(1, h);
                for (int idx : promoter) {
                    a[idx - 1]++;
                }
            }
        } else {
            for (int idx : promoter) {
                ops.emplace_back(2, idx);
                a[idx - 1]++;
            }
        }
    }

    cout << ops.size() << '\n';
    for (auto& op : ops) {
        cout << op.first << " " << op.second << '\n';
    }

    return 0;
}