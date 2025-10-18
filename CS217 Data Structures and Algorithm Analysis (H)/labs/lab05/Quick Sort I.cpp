#include <iostream>
#include <vector>

using namespace std;

int PARTITION(vector<int> &A, int p, int r) {
    int pivot = A[r - 1];
    int i = p - 1;
    for (int j = p; j <= r - 1; j++) {
        if (A[j - 1] <= pivot) {
            i = i + 1;
            swap(A[j - 1], A[i - 1]);
        }
    }
    swap(A[r - 1], A[i]);
    return i + 1;
}

void QUICKSORT(vector<int> &A, int p, int r) {
    if (p < r) {
        int q = PARTITION(A, p, r);
        QUICKSORT(A, p, q - 1);
        QUICKSORT(A, q + 1, r);
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
