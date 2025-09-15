n = int(input())
list = [int(x) for x in input().split()]
for j in range(1, len(list)):
    key = list[j]
    i = j - 1
    while i >= 0 and list[i] > key:
        list[i+1] = list[i]
        i = i - 1
    list[i+1] = key
print(" ".join([str(x) for x in list]))