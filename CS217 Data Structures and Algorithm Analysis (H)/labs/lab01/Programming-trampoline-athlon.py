def converter(s):
    try:
        return int(s)
    except ValueError:
        return s

n = int(input())
rows = n
cols = 8
teams = []

for i in range(rows):
    row_original = input().split()
    row = [converter(j) for j in row_original]
    teams.append(row)

for team in teams:
    largest = team[2]
    smallest = team[2]
    for i in range(6):
        if team[i+2] > largest:
            largest = team[i+2]
        if team[i+2] < smallest:
            smallest = team[i+2]
    team[1] = team[1] * 10 + team[2] + team[3] + team[4] + team[5] + team[6] + team[7] - largest - smallest

counter = 0

for j in range(0, n):
    largest_index = j
    for i in range(j+1, n):
        if teams[i][1] > teams[largest_index][1]:
            largest_index = i
    max_team = teams[largest_index]
    for k in range(largest_index, j, -1):
        teams[k] = teams[k-1]
    teams[j] = max_team
    if j >= 3 and teams[j][1] < teams[2][1]:
        break
    counter += 1

for i in range(0, counter):
    print(teams[i][0], teams[i][1])