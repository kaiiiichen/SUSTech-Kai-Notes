#include <iostream>
#include <vector>

using namespace std;

pair<int, int> PARTITION(vector<int> &A, int p, int r) {
    int pivot = A[r - 1];
    int i = p - 1;
    int k = p - 1;
    for (int j = p; j <= r - 1; j++) {
        if (A[j - 1] == pivot) {
            k = k + 1;
            swap(A[j - 1], A[k - 1]);
        } else if (A[j - 1] < pivot) {
            i = i + 1;
            k = k + 1;
            swap(A[j - 1], A[k - 1]);
            swap(A[k - 1], A[i - 1]);
        }
    }
    swap(A[r - 1], A[k]);
    return {i + 1, k};
}

void QUICKSORT(vector<int> &A, int p, int r) {
    if (p < r) {
        pair<int, int> indices = PARTITION(A, p, r);
        int q = indices.first;
        int t = indices.second;
        QUICKSORT(A, p, q - 1);
        QUICKSORT(A, t + 1, r);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    vector<int> A(n);

    for (int i = 0; i < n; ++i) {
        if (!(cin >> A[i])) return 0;
    }

    if (n > 0) {
        QUICKSORT(A, 1, n);
    }

    for (int i = 0; i < n; ++i) {
        cout << A[i] << (i == n - 1 ? "" : " ");
    }
    cout << endl;

    return 0;
}
