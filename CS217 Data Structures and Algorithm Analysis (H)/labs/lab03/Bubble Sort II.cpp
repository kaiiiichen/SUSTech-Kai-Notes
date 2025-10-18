#include <iostream>
#include <vector>
using namespace std;

long long mergeAndCount(vector<int>& arr, vector<int>& temp, int left, int mid, int right) {
    int i = left;
    int j = mid;
    int k = left;
    long long count = 0;
    while ((i <= mid - 1) && (j <= right)) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
            count += (mid - i);
        }
    }
    while (i <= mid - 1) {
        temp[k++] = arr[i++];
    }
    while (j <= right) {
        temp[k++] = arr[j++];
    }
    for (i = left; i <= right; i++) {
        arr[i] = temp[i];
    }
    return count;
}

long long mergeSortAndCount(std::vector<int>& arr, std::vector<int>& temp, int left, int right) {
    long long count = 0;
    if (right > left) {
        int mid = left + (right - left) / 2;
        count += mergeSortAndCount(arr, temp, left, mid);
        count += mergeSortAndCount(arr, temp, mid + 1, right);
        count += mergeAndCount(arr, temp, left, mid + 1, right);
    }
    return count;
}

int main() {
    int n;
    cin >> n;
    vector<int> list(n);
    for (int i = 0; i < n; ++i) {
        cin >> list[i];
    }
    if (n < 2) {
        cout << 0;
        return 0;
    }
    vector<int> temp(n);
    long long total_inversions = mergeSortAndCount(list, temp, 0, n - 1);
    cout << total_inversions;
    return 0;
}