#include <iostream>
#include <vector>
#include <utility>   // for std::pair
#include <algorithm> // for std::min, std::max
#include <climits>   // for INT_MAX, INT_MIN

using namespace std;

// === 线段树节点定义 ===
// 我们需要让线段树的每个节点告诉我们两件事：
// 1. 它所管辖的数组范围内的最小值 (min_val)
// 2. 它所管辖的数组范围内的最大值 (max_val)
// 这两个信息将帮助我们快速判断一个区间内是否存在我们想要的数。
struct Node {
    int min_val;
    int max_val;
};

// === 全局变量 ===
// 将这些大的数据结构声明在 main 函数外部（作为静态或全局变量），
// 可以避免在每个测试用例中反复申请和释放大量内存，从而提高效率。
static vector<int> A_init;       // 存储初始读入的数组
static vector<Node> tree;         // 线段树本身。大小通常是 4*N
static vector<pair<int, int>> stk; // 用于非递归 Quicksort 的栈

// 定义两个特殊的返回值，表示“没有找到”
const int NOT_FOUND_GE = INT_MAX; // GE: Greater than or Equal to
const int NOT_FOUND_LE = INT_MIN; // LE: Less than or Equal to

// === 线段树核心函数 ===

/**
 * @brief 构建线段树
 * @param v 当前节点在线段树数组中的索引 (vertex)
 * @param tl 当前节点所代表的数组区间的左边界 (tree left)
 * @param tr 当前节点所代表的数组区间的右边界 (tree right)
 *
 * 这个函数会递归地从初始数组 A_init 中构建出整个线段树。
 */
void build(int v, int tl, int tr) {
    // 基本情况：如果左右边界相同，说明这是一个叶子节点，
    // 它只代表数组中的一个元素。
    if (tl == tr) {
        tree[v] = {A_init[tl], A_init[tl]};
    } else {
        // 递归情况：将当前区间 [tl, tr] 分为两半
        int tm = (tl + tr) / 2; // 中点 (tree middle)
        // 递归构建左子树 (节点索引 v*2, 区间 [tl, tm])
        build(v * 2, tl, tm);
        // 递归构建右子树 (节点索引 v*2+1, 区间 [tm+1, tr])
        build(v * 2 + 1, tm + 1, tr);
        // 回溯：当前节点的 min/max 值由其左右子节点的 min/max 值决定
        tree[v].min_val = min(tree[v * 2].min_val, tree[v * 2 + 1].min_val);
        tree[v].max_val = max(tree[v * 2].max_val, tree[v * 2 + 1].max_val);
    }
}

/**
 * @brief 更新线段树中的一个值
 * @param v 当前节点索引
 * @param tl, tr 当前节点的区间边界
 * @param pos 要更新的元素在原数组中的位置
 * @param new_val 新的值
 *
 * 当我们交换数组中两个元素时，需要调用此函数两次来更新线段树。
 */
void update(int v, int tl, int tr, int pos, int new_val) {
    // 基本情况：找到了代表 pos 的叶子节点，直接更新它的值
    if (tl == tr) {
        tree[v] = {new_val, new_val};
    } else {
        // 递归情况：判断 pos 在左子区间还是右子区间，然后深入
        int tm = (tl + tr) / 2;
        if (pos <= tm) {
            update(v * 2, tl, tm, pos, new_val);
        } else {
            update(v * 2 + 1, tm + 1, tr, pos, new_val);
        }
        // 回溯：更新当前节点的 min/max 值
        tree[v].min_val = min(tree[v * 2].min_val, tree[v * 2 + 1].min_val);
        tree[v].max_val = max(tree[v * 2].max_val, tree[v * 2 + 1].max_val);
    }
}

/**
 * @brief 获取原数组中某个位置的当前值
 * @param pos 要查询的位置
 *
 * 因为所有值都存储在线段树中，我们需要一个函数来获取它们。
 */
int get_value(int v, int tl, int tr, int pos) {
    if (tl == tr) {
        return tree[v].min_val;
    }
    int tm = (tl + tr) / 2;
    if (pos <= tm) {
        return get_value(v * 2, tl, tm, pos);
    } else {
        return get_value(v * 2 + 1, tm + 1, tr, pos);
    }
}

/**
 * @brief 在查询区间 [ql, qr] 中，找到第一个值 >= pivot 的元素的索引
 * @param ql, qr 查询区间的左右边界 (query left/right)
 * @param pivot 目标值
 * @return 找到的索引，或 NOT_FOUND_GE
 *
 * 这是本题的核心优化之一，代替了 `i` 的线性扫描。
 */
int find_first_ge(int v, int tl, int tr, int ql, int qr, int pivot) {
    // 剪枝：如果查询范围无效，或者当前节点代表的整个区间的最大值都比 pivot 小，
    // 那么这个区间内不可能有答案，直接返回“未找到”。
    if (ql > qr || ql > tr || qr < tl || tree[v].max_val < pivot) {
        return NOT_FOUND_GE;
    }
    // 基本情况：到达叶子节点，并且通过了上面的剪枝，说明它就是答案。
    if (tl == tr) {
        return tl;
    }

    int tm = (tl + tr) / 2;
    // 关键：因为要找“第一个”（索引最小的），所以优先在左子树中查找。
    int res_left = find_first_ge(v * 2, tl, tm, ql, qr, pivot);
    // 如果在左子树中找到了，那它一定是我们想要的最小索引，直接返回。
    if (res_left != NOT_FOUND_GE) {
        return res_left;
    }
    // 如果左子树没有，才去右子树中查找。
    return find_first_ge(v * 2 + 1, tm + 1, tr, ql, qr, pivot);
}

/**
 * @brief 在查询区间 [ql, qr] 中，找到最后一个值 <= pivot 的元素的索引
 * @return 找到的索引，或 NOT_FOUND_LE
 *
 * 这是另一个核心优化，代替了 `j` 的线性扫描。
 */
int find_last_le(int v, int tl, int tr, int ql, int qr, int pivot) {
    // 剪枝：如果当前区间的最小值都比 pivot 大，那就不可能有答案。
    if (ql > qr || ql > tr || qr < tl || tree[v].min_val > pivot) {
        return NOT_FOUND_LE;
    }
    if (tl == tr) {
        return tl;
    }

    int tm = (tl + tr) / 2;
    // 关键：因为要找“最后一个”（索引最大的），所以优先在右子树中查找。
    int res_right = find_last_le(v * 2 + 1, tm + 1, tr, ql, qr, pivot);
    // 如果在右子树中找到了，它就是答案。
    if (res_right != NOT_FOUND_LE) {
        return res_right;
    }
    // 如果右子树没有，才去左子树中查找。
    return find_last_le(v * 2, tl, tm, ql, qr, pivot);
}


/**
 * @brief 使用线段树加速的 PARTITION 函数
 * @param n 数组总长度，用于函数调用
 * @param p, r 当前分区的左右边界
 * @param swap_count 交换次数计数器（引用传递）
 * @return 分区点
 */
inline int PARTITION(int n, int p, int r, long long &swap_count) {
    // 1. 获取 pivot 值 (O(log N) 操作)
    int pivot_val = get_value(1, 0, n - 1, (p + r) / 2);

    int i = p - 1;
    int j = r + 1;
    while (true) {
        // 2. 使用线段树快速查找 i 和 j (O(log N) 操作)
        // 从 i+1 开始，找第一个 >= pivot_val 的数
        i = find_first_ge(1, 0, n - 1, i + 1, r, pivot_val);
        // 从 j-1 开始，找最后一个 <= pivot_val 的数
        j = find_last_le(1, 0, n - 1, p, j - 1, pivot_val);

        if (i >= j) {
            return j;
        }

        // 3. 交换 i 和 j 位置上的值
        // a. 先获取它们当前的值 (两次 O(log N) 操作)
        int val_i = get_value(1, 0, n - 1, i);
        int val_j = get_value(1, 0, n - 1, j);
        // b. 更新线段树 (两次 O(log N) 操作)
        update(1, 0, n - 1, i, val_j);
        update(1, 0, n - 1, j, val_i);

        swap_count++;
    }
}


int main() {
    // 快速 I/O
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int n;
        cin >> n;

        // 根据输入大小调整数据结构
        A_init.resize(n);
        tree.resize(4 * n);

        for (int i = 0; i < n; ++i) {
            cin >> A_init[i];
        }

        // 如果数组非空，则构建线段树 (O(N) 操作)
        if (n > 0) {
            build(1, 0, n - 1);
        }

        long long swap_count = 0;
        // Quicksort 的主逻辑，与之前的版本完全相同。
        // 我们只是把它的“引擎”(PARTITION)换成了一个更快的版本。
        if (n > 1) {
            if (stk.size() < n) {
                stk.resize(n); // 在最坏情况下，栈深度可能达到 N
            }
            int stk_top = 0;
            stk[stk_top++] = {0, n - 1};

            while (stk_top > 0) {
                auto [l, h] = stk[--stk_top];
                while (l < h) {
                    int q = PARTITION(n, l, h, swap_count);

                    // 优化：总是将较大的子问题压栈，优先处理较小的子问题
                    if (q - l < h - (q + 1)) {
                        if (q + 1 < h) {
                            stk[stk_top++] = {q + 1, h};
                        }
                        h = q;
                    } else {
                        if (l < q) {
                            stk[stk_top++] = {l, q};
                        }
                        l = q + 1;
                    }
                }
            }
        }
        cout << swap_count << "\n";
    }
    return 0;
}
