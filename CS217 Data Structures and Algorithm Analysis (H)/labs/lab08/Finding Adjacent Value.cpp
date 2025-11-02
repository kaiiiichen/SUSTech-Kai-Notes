#include <iostream>
#include <string>
#include <climits>
#include <map>
#include <algorithm>

using namespace std;
using ll = long long;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    if (!(cin >> n)) return 0;

    map<ll, int> m;

    m[LLONG_MIN] = 0;
    m[LLONG_MAX] = 0;

    ll first_val;
    cin >> first_val;
    m[first_val] = 1;

    for (int i = 2; i <= n; ++i) {
        ll x;
        cin >> x;

        auto it_right = m.upper_bound(x);
        auto it_left = std::prev(it_right);

        ll dL = (it_left->first == LLONG_MIN) ? LLONG_MAX : (x - it_left->first);
        ll dR = (it_right->first == LLONG_MAX) ? LLONG_MAX : (it_right->first - x);

        if (dL <= dR) {
            cout << dL << ' ' << it_left->second << '\n';
        } else {
            cout << dR << ' ' << it_right->second << '\n';
        }

        m[x] = i;
    }

    return 0;
}