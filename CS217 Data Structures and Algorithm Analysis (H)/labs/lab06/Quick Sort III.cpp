#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>

using namespace std;

int partition(vector<int> &A, int p, int r) {
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

int randomized_partition(vector<int> &A, int p, int r) {
    int i = p + rand() % (r - p + 1); // random in [p, r]
    swap(A[i - 1], A[r - 1]);
    return partition(A, p, r);
}

void randomized_quicksort(vector<int> &A, int p, int r) {
    if (p < r) {
        int q = randomized_partition(A, p, r);
        randomized_quicksort(A, p, q - 1);
        randomized_quicksort(A, q + 1, r);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    srand(time(0));

    int n;
    if (!(cin >> n)) return 0;

    vector<int> A(n);
    for (int i = 0; i < n; ++i) {
        if (!(cin >> A[i])) return 0;
    }

    if (n > 0) {
        randomized_quicksort(A, 1, n);
    }

    for (int i = 0; i < n; ++i) {
        cout << A[i] << (i == n - 1 ? "" : " ");
    }
    cout << "\n";

    return 0;
}