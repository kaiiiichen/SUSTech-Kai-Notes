n, q = map(int, input().split())
books = [int(input()) for _ in range(n)]
readers = [list(map(int, input().split())) for _ in range(q)]

for j in range(0, n):
    smallest = j
    for i in range(j+1, n):
        if books[i] < books[smallest]:
            smallest = i
    books[j], books[smallest] = books[smallest], books[j]

for reader in readers:
    m = reader[0]
    code = reader[1]
    find = False
    for book in books:
        if book % (10 ** m) == code:
            print(book)
            find = True
            break
    if not find:
        print(-1)