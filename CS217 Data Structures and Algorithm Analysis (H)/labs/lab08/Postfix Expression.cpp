#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

int solve() {
    int n;
    cin >> n;

    stack<int> operand_stack;

    for (int i = 0; i < n; ++i) {
        string token;
        cin >> token;

        if (token == "+") {
            int operand2 = operand_stack.top();
            operand_stack.pop();
            int operand1 = operand_stack.top();
            operand_stack.pop();
            operand_stack.push(operand1 + operand2);
        } else if (token == "-") {
            int operand2 = operand_stack.top();
            operand_stack.pop();
            int operand1 = operand_stack.top();
            operand_stack.pop();
            operand_stack.push(operand1 - operand2);
        } else if (token == "*") {
            int operand2 = operand_stack.top();
            operand_stack.pop();
            int operand1 = operand_stack.top();
            operand_stack.pop();
            operand_stack.push(operand1 * operand2);
        } else {
            operand_stack.push(stoi(token));
        }
    }

    return operand_stack.top();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;
    while (T--) {
        cout << solve() << "\n";
    }
    return 0;
}