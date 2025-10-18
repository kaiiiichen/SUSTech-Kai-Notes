#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Player {
    int id;
    int score;
    int strength;
};

bool comparePlayers(const Player& a, const Player& b) {
    if (a.score != b.score) {
        return a.score > b.score;
    }
    return a.id < b.id;
}

int main() {
    int n, r, q;
    cin >> n >> r >> q;

    int num_players = 2 * n;

    vector<Player> players(num_players);

    for (int i = 0; i < num_players; ++i) {
        players[i].id = i + 1;
        cin >> players[i].score;
    }
    for (int i = 0; i < num_players; ++i) {
        cin >> players[i].strength;
    }

    sort(players.begin(), players.end(), comparePlayers);

    for (int i = 0; i < r; ++i) {
        vector<Player> winners;
        winners.reserve(n);
        vector<Player> losers;
        losers.reserve(n);

        for (int j = 0; j < num_players; j += 2) {
            Player& p1 = players[j];
            Player& p2 = players[j + 1];

            if (p1.strength > p2.strength) {
                p1.score++;
                winners.push_back(p1);
                losers.push_back(p2);
            } else {
                p2.score++;
                winners.push_back(p2);
                losers.push_back(p1);
            }
        }
        merge(winners.begin(), winners.end(),
                   losers.begin(), losers.end(),
                   players.begin(),
                   comparePlayers);
    }

    cout << players[q - 1].id << std::endl;

    return 0;
}