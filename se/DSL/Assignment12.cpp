#include <iostream>

using namespace std;

template <typename T>
struct Node {
    T data;
    int priority;
    Node* left, *right;

    Node(const T& data, int priority) : data(data), priority(priority), left(nullptr), right(nullptr) {}
};

template <typename T>
class PriorityQueue {
private:
    Node<T>* root;

public:
    PriorityQueue() : root(nullptr) {}

    bool isEmpty() const {
        return root == nullptr;
    }

    void insert(const T& data, int priority) {
        if (isEmpty()) {
            root = new Node<T>(data, priority);
        } else {
            insertHelper(root, data, priority);
        }
    }

    T dequeue() {
        if (isEmpty()) {
            throw std::runtime_error("Priority queue is empty");
        }

        T maxData = root->data;
        root = removeMax(root);
        return maxData;
    }

private:
    void insertHelper(Node<T>* node, const T& data, int priority) {
        if (priority <= node->priority) {
            if (node->left == nullptr) {
                node->left = new Node<T>(data, priority);
            } else {
                insertHelper(node->left, data, priority);
            }
        } else {
            if (node->right == nullptr) {
                node->right = new Node<T>(data, priority);
            } else {
                insertHelper(node->right, data, priority);
            }
        }
    }

    Node<T>* removeMax(Node<T>* node) {
        if (node->right == nullptr) {
            Node<T>* temp = node->left;
            delete node;
            return temp;
        }

        node->right = removeMax(node->right);
        return node;
    }
};

int main() {
    PriorityQueue<int> pq;

    pq.insert(10, 3);
    pq.insert(5, 1);
    pq.insert(15, 2);

    cout << "Dequeue: " << pq.dequeue() << endl;
    cout << "Dequeue: " << pq.dequeue() << endl;
    cout << "Dequeue: " << pq.dequeue() << endl;
    return 0;
}
