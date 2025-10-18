#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n, p, m;
    cin >> n >> p >> m;
    long long mod = 1e9 + 7;

    vector<vector<long long>> mat_a(n, vector<long long>(p));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < p; ++j) {
            cin >> mat_a[i][j];
        }
    }

    vector<vector<long long>> mat_b(p, vector<long long>(m));
    for (int i = 0; i < p; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> mat_b[i][j];
        }
    }

    vector<vector<long long>> mat_c(n, vector<long long>(m));

    for (int i = 0; i < n; i++) {
        for (int k = 0; k < p; k++) {
            long long val = mat_a[i][k];
            for (int j = 0; j < m; j++) {
                long long product = val * mat_b[k][j];
                mat_c[i][j] = (mat_c[i][j] + (product % mod + mod) % mod) % mod;
                // Note: pay attention to summation overflow!
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cout << mat_c[i][j] << (j == m - 1 ? "" : " ");
        }
        cout << "\n";
    }

    return 0;
}
