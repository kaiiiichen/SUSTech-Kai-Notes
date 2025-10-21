#include <stdio.h>
#include <string.h>
#include <algorithm>

typedef unsigned int u32;
typedef unsigned long long u64;

inline u32 next_integer(u32 x) {
    x ^= x << 13;
    x ^= x >> 17;
    x ^= x << 5;
    return x;
}

bool output_arr(void *a, u32 size) {
    if (size % 4) {
        return puts("-1"), 0;
    }

    u32 blocks = size / 4;
    u32 *A = (u32 *) a;
    u32 ret = size;
    u32 x = 23333333;
    for (u32 i = 0; i < blocks; i++) {
        ret = ret ^ (A[i] + x);
        x ^= x << 13;
        x ^= x >> 17;
        x ^= x << 5;
    }

    return printf("%u\n", ret), 1;
}

// ===== header ======

namespace Sorting {
    void init_data(u32 *a, int n, u32 seed) {
        for (int i = 0; i < n; i++) {
            seed = next_integer(seed);
            a[i] = seed;
        }
    }

    void sort(u32 *a, int n) {
        if (n <= 1) return;
        const int B = 256;
        u32 *tmp = new u32[n];
        u32 cnt[B];

        u32 *src = a;
        u32 *dst = tmp;
        for (int pass = 0; pass < 4; ++pass) {
            memset(cnt, 0, sizeof(cnt));
            int shift = pass * 8;
            for (int i = 0; i < n; ++i) ++cnt[(src[i] >> shift) & 0xFFu];

            u32 sum = 0;
            for (int i = 0; i < B; ++i) {
                u32 c = cnt[i];
                cnt[i] = sum;
                sum += c;
            }

            for (int i = 0; i < n; ++i) dst[cnt[(src[i] >> shift) & 0xFFu]++] = src[i];

            std::swap(src, dst);
        }

        if (src != a) memcpy(a, src, n * sizeof(u32)); // safety copy (usually not needed here)
        delete[] tmp;
    }


    void main() {
        int n;
        u32 seed;
        scanf("%d%u", &n, &seed);

        u32 *a = new u32[n];
        init_data(a, n, seed);

        sort(a, n);

        output_arr(a, n * sizeof(u32));
    }
}

int main() {
    int task_id;
    scanf("%d", &task_id);

    switch (task_id) {
        case 1:
            Sorting::main();
            break;
        default: ;
    }

    return 0;
}
