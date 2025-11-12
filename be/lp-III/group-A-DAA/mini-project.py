import threading
import random
import time

def merge(left, right):
    result = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result.extend(left[i:])
    result.extend(right[j:])
    return result


def merge_sort(arr):
    if len(arr) <= 1:
        return arr

    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)


def threaded_merge_sort(arr):
    if len(arr) <= 1:
        return arr

    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    left_thread = threading.Thread(target=lambda: left_half.sort())
    right_thread = threading.Thread(target=lambda: right_half.sort())

    left_thread.start()
    right_thread.start()
    left_thread.join()
    right_thread.join()

    return merge(left_half, right_half)


def compare_algorithms(size=100000):
    arr = [random.randint(0, size) for _ in range(size)]

    arr1 = arr.copy()
    arr2 = arr.copy()

    print(f"\nArray Size: {size}")

    # Standard Merge Sort
    start = time.perf_counter()
    merge_sort(arr1)
    end = time.perf_counter()
    print(f"Standard Merge Sort Time: {end - start:.5f} seconds")

    # Multithreaded Merge Sort
    start = time.perf_counter()
    threaded_merge_sort(arr2)
    end = time.perf_counter()
    print(f"Multithreaded Merge Sort Time: {end - start:.5f} seconds")


def analyze_cases(size=10000):
    # Best case (already sorted)
    best_case = list(range(size))
    # Worst case (reverse sorted)
    worst_case = list(range(size, 0, -1))

    print(f"\nAnalyzing for array size: {size}")

    start = time.perf_counter()
    merge_sort(best_case)
    best_time = time.perf_counter() - start

    start = time.perf_counter()
    merge_sort(worst_case)
    worst_time = time.perf_counter() - start

    print("for Standard Merge Sort")
    print(f"Best Case Time:  {best_time:.6f} seconds")
    print(f"Worst Case Time: {worst_time:.6f} seconds")

    start = time.perf_counter()
    merge_sort(best_case)
    best_time = time.perf_counter() - start

    start = time.perf_counter()
    merge_sort(worst_case)
    worst_time = time.perf_counter() - start

    print("\nfor Multithreaded Merge Sort")
    print(f"Best Case Time:  {best_time:.6f} seconds")
    print(f"Worst Case Time: {worst_time:.6f} seconds")

compare_algorithms(100000)
analyze_cases(20000)


# output
# Array Size: 9000000
# Standard Merge Sort Time: 40.80374 seconds
# Multithreaded Merge Sort Time: 6.73000 seconds

# Analyzing for array size: 900000
# for Standard Merge Sort
# Best Case Time:  1.666381 seconds
# Worst Case Time: 1.730930 seconds

# for Multithreaded Merge Sort
# Best Case Time:  1.648991 seconds
# Worst Case Time: 1.703887 seconds