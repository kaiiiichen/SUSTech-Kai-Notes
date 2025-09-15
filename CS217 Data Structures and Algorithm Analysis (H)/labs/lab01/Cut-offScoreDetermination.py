n, m = map(int, input().split())

list = [list(map(int, input().split())) for _ in range(n)]

s = int(m * 1.5)

for j in range(0, n):
    largest = j
    for i in range(j+1, n):
        if list[i][1] > list[largest][1]:
            largest = i
        elif list[i][1] == list[largest][1] and list[i][0] < list[largest][0]:
            largest = i
    list[j], list[largest] = list[largest], list[j]
    if j > s-1 and list[j][1] < list[s-1][1]:
        break

counter = 0

for k in range(0, n):
    if list[k][1] >= list[s-1][1]:
        counter += 1

print(list[s-1][1], counter)

for k in range(0, counter):
    print(list[k][0], list[k][1])