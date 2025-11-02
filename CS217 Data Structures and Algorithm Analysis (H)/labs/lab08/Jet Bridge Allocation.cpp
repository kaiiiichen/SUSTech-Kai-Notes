#include <bits/stdc++.h>
using namespace std;

struct Flight { int a, b; };

struct Solver {
    static vector<int> pref_by_k(vector<Flight> f, int n) {
        int m = (int)f.size();
        sort(f.begin(), f.end(), [](const Flight& x, const Flight& y){
            return x.a < y.a;
        });

        vector<int> A(m + 1), B(m + 1);
        for (int i = 1; i <= m; ++i) {
            A[i] = f[i-1].a;
            B[i] = f[i-1].b;
        }

        vector<int> nxt(m + 2, m + 1);
        for (int i = 1; i <= m; ++i) {
            int j = int(lower_bound(A.begin() + 1, A.begin() + m + 1, B[i]) - (A.begin()));
            nxt[i] = j;
        }

        vector<int> fa(m + 2);
        iota(fa.begin(), fa.end(), 0);
        function<int(int)> findp = [&](int x) -> int {
            return fa[x] == x ? x : fa[x] = findp(fa[x]);
        };
        auto erase_idx = [&](int i){ fa[i] = findp(i + 1); };

        vector<int> pref(n + 1, 0);
        int limit = min(n, m);
        for (int k = 1; k <= limit; ++k) {
            int cnt = 0;
            int pos = findp(1);
            while (pos <= m) {
                ++cnt;               // 这个桥接下航班 pos
                erase_idx(pos);      // 删除 pos
                pos = findp(nxt[pos]); // 跳到下一个可接的航班
            }
            pref[k] = pref[k-1] + cnt;
        }
        for (int k = limit + 1; k <= n; ++k) pref[k] = pref[limit];
        return pref;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m1, m2;
    if (!(cin >> n >> m1 >> m2)) return 0;

    vector<Flight> D(m1), I(m2);
    for (int i = 0; i < m1; ++i) cin >> D[i].a >> D[i].b;
    for (int i = 0; i < m2; ++i) cin >> I[i].a >> I[i].b;

    vector<int> pref1 = Solver::pref_by_k(D, n);
    vector<int> pref2 = Solver::pref_by_k(I, n);

    long long ans = 0;
    for (int k = 0; k <= n; ++k) {
        long long cur = (long long)pref1[k] + (long long)pref2[n - k];
        if (cur > ans) ans = cur;
    }
    cout << ans << '\n';
    return 0;
}
