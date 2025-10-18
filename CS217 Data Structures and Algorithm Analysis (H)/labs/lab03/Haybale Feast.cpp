#include <iostream>
#include <vector>

using namespace std;

int main() {
    int n;
    long long m;
    cin >> n >> m;

    vector<pair<int, int>> hay(n);
    int max_spicyness = 0;

    for (int i = 0; i < n; ++i) {
        int flavour, spicy;
        cin >> flavour >> spicy;
        hay[i] = {flavour, spicy};
        max_spicyness = max(max_spicyness, spicy);
    }

    long long low = 1, high = max_spicyness;
    int ans = -1;

    while (low <= high) {
        long long mid = low + (high - low) / 2;
        long long current_flavor_sum = 0;
        long long max_flavor_achieved = 0;
        bool is_possible = false;

        for (int i = 0; i < n; ++i) {
            if (hay[i].second <= mid) {
                current_flavor_sum += hay[i].first;
            } else {
                max_flavor_achieved = max(max_flavor_achieved, current_flavor_sum);
                current_flavor_sum = 0;
            }
        }
        max_flavor_achieved = max(max_flavor_achieved, current_flavor_sum);
        is_possible = (max_flavor_achieved >= m);
        if (is_possible) {
            ans = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    cout << ans;
    return 0;
}