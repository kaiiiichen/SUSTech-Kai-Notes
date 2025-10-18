#include <iostream>
#include <vector>
#include <string>

using namespace std;

int get_child_on_path(int k, int target_leaf_idx) {
    if (k * 2 > target_leaf_idx) {
        return -1;
    }
    int child = target_leaf_idx;
    while (child / 2 > k) {
        child = child / 2;
    }
    return child;
}

void solve() {
    int n;
    cin >> n;
    vector<int> v(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i];
    }
    vector<int> a(n);
    vector<int> current_heap(n + 1);
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
        current_heap[i + 1] = a[i];
    }

    string b(n, '0');
    bool possible = true;

    for (int i = n; i >= 2; --i) {
        int val_to_find = v[i - 1];

        int p = i;
        while (p > 0 && current_heap[p] != val_to_find) {
            p = p / 2;
        }

        if (p == 0) {
            possible = false;
            break;
        }

        bool min_possible = true;
        if (p > 1 && current_heap[p / 2] > val_to_find) {
            min_possible = false;
        }

        if (min_possible) {
            int curr = p;
            while (curr < i) {
                int child_idx = get_child_on_path(curr, i);
                if (child_idx == -1) break;
                if (val_to_find > current_heap[child_idx]) {
                    min_possible = false;
                    break;
                }
                curr = child_idx;
            }
        }

        if (min_possible) {
            b[i - 1] = '0';
        } else {
            bool max_possible = true;
            if (p > 1 && current_heap[p / 2] < val_to_find) {
                max_possible = false;
            }

            if (max_possible) {
                int curr = p;
                while (curr < i) {
                    int child_idx = get_child_on_path(curr, i);
                    if (child_idx == -1) break;
                    if (val_to_find < current_heap[child_idx]) {
                        max_possible = false;
                        break;
                    }
                    curr = child_idx;
                }
            }

            if (max_possible) {
                b[i - 1] = '1';
            } else {
                possible = false;
                break;
            }
        }

        int curr = p;
        while (curr < i) {
            int child_idx = get_child_on_path(curr, i);
            if (child_idx == -1) break;
            swap(current_heap[curr], current_heap[child_idx]);
            curr = child_idx;
        }
    }

    if (possible) {
        cout << b << endl;
    } else {
        cout << "Impossible" << endl;
    }
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}