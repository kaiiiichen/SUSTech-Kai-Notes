n = int(input())
list = [int(x) for x in input().split()]
total = 0

for i in range(1, n):
    for j in range(n, i, -1):
        if list[j-1] < list[j-2]:
            x = list[j-1]
            list[j-1] = list[j-2]
            list[j-2] = x
            total += 1

print(total)