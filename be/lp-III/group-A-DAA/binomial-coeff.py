def binomialCoeff(n, k):
    # Create a 2D array to store values
    C = [[0 for x in range(k + 1)] for y in range(n + 1)]

    # Calculate binomial coefficients using DP
    for i in range(n + 1):
        for j in range(min(i, k) + 1):
            # Base cases
            if j == 0 or j == i:
                C[i][j] = 1
            else:
                # Use previously computed values
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j]

    return C[n][k]


# Example usage
n = 5
k = 2
print(f"Binomial Coefficient C({n}, {k}) =", binomialCoeff(n, k))
