#include <iostream>
#include <vector>
#include <string>

const int R = 11;

int charAt(const std::string& s, int d) {
    if (d < s.length()) {
        return (s[d] - '0') + 1;
    } else {
        return 0;
    }
}

void msdRadixSort(std::vector<std::string>& arr, int left, int right, int d, std::vector<std::string>& aux) {
    if (left >= right) {
        return;
    }

    std::vector<int> count(R + 1, 0);
    for (int i = left; i <= right; ++i) {
        int c = charAt(arr[i], d);
        count[c + 1]++;
    }

    for (int r = 0; r < R; ++r) {
        count[r + 1] += count[r];
    }

    for (int i = left; i <= right; ++i) {
        int c = charAt(arr[i], d);
        aux[count[c]] = arr[i];
        count[c]++;
    }

    for (int i = left; i <= right; ++i) {
        arr[i] = aux[i - left];
    }

    for (int r = 1; r < R; ++r) {
        int bucket_start = left + count[r - 1];
        int bucket_end = left + count[r] - 1;

        msdRadixSort(arr, bucket_start, bucket_end, d + 1, aux);
    }
}


int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    int n;
    std::cin >> n;

    std::vector<std::string> numbers(n);
    for (int i = 0; i < n; ++i) {
        std::cin >> numbers[i];
    }

    std::vector<std::string> aux(n);

    msdRadixSort(numbers, 0, n - 1, 0, aux);

    for (int i = 0; i < n; ++i) {
        std::cout << numbers[i] << "\n";
    }

    return 0;
}