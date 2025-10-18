#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>
using namespace std;

void sift_up(vector<int>& heap, int index) {
    if (index == 0) return;
    int parent_index = (index - 1) / 2;
    while (index > 0 && heap[index] < heap[parent_index]) {
        swap(heap[index], heap[parent_index]);
        index = parent_index;
        parent_index = (index - 1) / 2;
    }
}

void sift_down(vector<int>& heap, int index) {
    int size = heap.size();
    int min_index = index;

    while (true) {
        int left_child_index = 2 * index + 1;
        int right_child_index = 2 * index + 2;

        if (left_child_index < size && heap[left_child_index] < heap[min_index]) {
            min_index = left_child_index;
        }
        if (right_child_index < size && heap[right_child_index] < heap[min_index]) {
            min_index = right_child_index;
        }

        if (min_index == index) {
            break;
        }

        swap(heap[index], heap[min_index]);
        index = min_index;
    }
}

void heap_push(vector<int>& heap, int value) {
    heap.push_back(value);
    sift_up(heap, heap.size() - 1);
}

void heap_pop(vector<int>& heap) {
    if (heap.empty()) {
        return;
    }
    heap[0] = heap.back();
    heap.pop_back();
    if (!heap.empty()) {
        sift_down(heap, 0);
    }
}

int heap_top(const vector<int>& heap) {
    if (heap.empty()) {
        throw out_of_range("Heap is empty");
    }
    return heap[0];
}

int main() {
    int n;
    cin >> n;

    vector<int> min_heap;
    vector<string> result_operations;

    for (int i = 0; i < n; ++i) {
        string command;
        cin >> command;

        if (command == "insert") {
            int value;
            cin >> value;
            heap_push(min_heap, value);
            result_operations.push_back("insert " + to_string(value));

        } else if (command == "getMin") {
            int value;
            cin >> value;

            while (!min_heap.empty() && heap_top(min_heap) < value) {
                heap_pop(min_heap);
                result_operations.emplace_back("removeMin");
            }

            if (min_heap.empty() || heap_top(min_heap) > value) {
                heap_push(min_heap, value);
                result_operations.emplace_back("insert " + to_string(value));
            }

            result_operations.emplace_back("getMin " + to_string(value));

        } else if (command == "removeMin") {
            if (min_heap.empty()) {
                result_operations.emplace_back("insert 0");
            } else {
                heap_pop(min_heap);
            }
            result_operations.emplace_back("removeMin");
        }
    }

    cout << result_operations.size() << "\n";
    for (const string& op : result_operations) {
        cout << op << "\n";
    }
    return 0;
}