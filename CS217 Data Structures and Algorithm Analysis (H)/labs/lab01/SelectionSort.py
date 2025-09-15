n = int(input())
list = [int(x) for x in input().split()]
for j in range(0, len(list)):
    smallest = j
    for i in range(j+1, n):
        if list[i] < list[smallest]:
            smallest = i
    list[j], list[smallest] = list[smallest], list[j]
print(" ".join([str(x) for x in list]))