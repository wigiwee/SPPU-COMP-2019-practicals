#include <iostream>
#include <vector>
#include <queue>

using namespace std;

class StudentMarks {
    vector<int> marks;
    int n;

    void minHeapify(int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int smallest = i;

        if (l < n && marks[l] < marks[smallest]) {
            smallest = l;
        }

        if (r < n && marks[r] < marks[smallest]) {
            smallest = r;
        }

        if (smallest != i) {
            swap(marks[i], marks[smallest]);
            minHeapify(smallest);
        }
    }

    void maxHeapify(int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;

        if (l < n && marks[l] > marks[largest]) {
            largest = l;
        }

        if (r < n && marks[r] > marks[largest]) {
            largest = r;
        }

        if (largest != i) {
            swap(marks[i], marks[largest]);
            maxHeapify(largest);
        }
    }

public:
    StudentMarks(int size) {
        marks.resize(size);
        n = size;
    }

    void insert(int mark) {
        marks[n] = mark;
        int i = n;
        n++;

        while (i > 0 && marks[parent(i)] > marks[i]) {
            swap(marks[i], marks[parent(i)]);
            i = parent(i);
        }

        minHeapify(0);
    }

    int extractMin() {
        int min = marks[0];
        marks[0] = marks[n - 1];
        n--;
        minHeapify(0);

        return min;
    }

    int extractMax() {
        int max = marks[0];
        marks[0] = marks[n - 1];
        n--;
        maxHeapify(0);

        return max;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    void display() {
        cout << "Sorted array is  ";
        for (int i = 0; i < n; i++) {
            cout << marks[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    int numMarks;
    cout << "Enter the number of students: ";
    cin >> numMarks;

    StudentMarks marks(numMarks);

    int mark;
    for (int i = 0; i < numMarks; i++) {
        cout << "Enter the marks of student " << i + 1 << ": ";
        cin >> mark;
        marks.insert(mark);
    }

    cout << "Minimum marks: " << marks.extractMin() << endl;
    cout << "Maximum marks: " << marks.extractMax() << endl;

    marks.display();

    return 0;
}
