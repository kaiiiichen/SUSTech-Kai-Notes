import sys
n, p, m = map(int, sys.stdin.readline().split())

mat_a = []
for _ in range(n):
    mat_a.append(list(map(int, sys.stdin.readline().split())))

mat_b = []
for _ in range(p):
    mat_b.append(list(map(int, sys.stdin.readline().split())))

mat_b_T = list(zip(*mat_b))

mat_c = [[0] * m for _ in range(n)]

mod = 10**9 + 7

for i in range(n):
    row_a = mat_a[i]
    for j in range(m):
        col_b = mat_b_T[j]
        total = 0
        for k in range(p):
            total += row_a[k] * col_b[k]
        mat_c[i][j] = total % mod

for i in range(n):
    print(*mat_c[i])