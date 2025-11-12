import timeit

# Non-recursive Fibonacci function
def fibo(n):
    fib = [0, 1]
    for i in range(2, n + 1):
        fib.append(fib[i - 1] + fib[i - 2])
    return fib[n]

# Recursive Fibonacci function
def fibo_recursive(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fibo_recursive(n - 1) + fibo_recursive(n - 2)

def callFibo(isRec):
    print(
        "Fibonacci",
        "recursive:" if isRec else "non-recursive:",
        f"\ntime: {timeit.timeit(lambda: fibo_recursive(N) if isRec else fibo(N), number=RUNS):.5f}",
        f"\nO(n)\tSpace: O({'1' if isRec else 'n'})",
    )

N = int(input("Enter the value of N : \n"))
RUNS = 1000
print(f"Given N = {N}\n{RUNS} runs")


callFibo(True)
callFibo(False)