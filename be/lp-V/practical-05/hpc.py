# HPC Application for AI/ML Domain
# Parallel Matrix Multiplication using NumPy

import numpy as np
import time

# Matrix size
N = 1000

# Generate random matrices
A = np.random.rand(N, N)
B = np.random.rand(N, N)

# Start time
start = time.time()

# Matrix multiplication
C = np.dot(A, B)

# End time
end = time.time()

print("Matrix Multiplication Completed")
print("Matrix Size:", N, "x", N)
print("Execution Time:", end - start, "seconds")

# Print sample output
print("\nSample Output:")
print(C[:5, :5])