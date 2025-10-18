#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <functional>
using namespace std;

int main() {
    int n;
    cin >> n;
    priority_queue<int, vector<int>, greater<int>> min_heap;
    // 使用priority_queue数据结构！
    // 默认情况下，C++ 标准库中的 std::priority_queue 就是一个最大堆（Max-Heap）
    // 当你向其中添加元素时，它会自动重新组织内部结构，以确保优先级最高的元素（也就是数值最大的元素）始终位于堆的顶部
    // 你可以通过 .top() 方法随时访问到这个最大值。
    // priority_queue< T, Container, Compare >：int (类型 T)，vector<int> (容器 Container)，greater<int> (比较器 Compare)
    // less<int> (默认选项)：a < b 时返回 true，a 的优先级就比 b 低。数值越大的元素，优先级越高。
    // greater<int> (当前选项)：a > b 时返回 true，a > b，那么 a 的优先级就比 b 低。数值越小的元素，优先级越高。
    vector<string> result_operations;
    for (int i = 0; i < n; ++i) {
        string command;
        cin >> command;
        if (command == "insert") {
            int value;
            cin >> value;
            min_heap.push(value);
            result_operations.push_back("insert " + to_string(value));
        } else if (command == "getMin") {
            int value;
            cin >> value;
            if (min_heap.size() == 0) {
                min_heap.push(value);
                result_operations.emplace_back("insert "+to_string(value));
                result_operations.emplace_back("getMin"+to_string(value));
                break;
            }
            while (min_heap.size() > 0) {
                if (value < min_heap.top()) {
                    min_heap.push(value);
                    result_operations.emplace_back("insert "+to_string(value));
                    result_operations.emplace_back("getMin"+to_string(value));
                } else if (value > min_heap.top()) {
                    min_heap.pop();
                    result_operations.emplace_back("removeMin");
                    if (min_heap.size() == 0) {
                        min_heap.push(value);
                        result_operations.emplace_back("insert "+to_string(value));
                        result_operations.emplace_back("getMin"+to_string(value));
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (command == "removeMin") {
            int min = min_heap.top();
            while (min_heap.size() > 0) {
                min_heap.pop();
                result_operations.emplace_back("removeMin");
                if (min < min_heap.top()) {
                    break;
                }
                min_heap.pop();
                result_operations.emplace_back("removeMin");
            }
        }
    }
    cout << result_operations.size() << "\n";
    for (const string& op : result_operations) {
        cout << op << "\n";
    }
    return 0;
}