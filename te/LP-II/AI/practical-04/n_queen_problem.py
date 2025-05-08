class NQueensSolver:
    def __init__(self, n):
        self.n = n
        self.solutions = []
    
    def solve_backtracking(self):
        """Solves N-Queens using backtracking"""
        def is_safe(board, row, col):
            # Check this row on left side
            for i in range(col):
                if board[row][i] == 1:
                    return False
            
            # Check upper diagonal on left side
            for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
                if board[i][j] == 1:
                    return False
            
            # Check lower diagonal on left side
            for i, j in zip(range(row, self.n), range(col, -1, -1)):
                if board[i][j] == 1:
                    return False
            
            return True

        def backtrack(board, col):
            if col >= self.n:
                self.solutions.append([row[:] for row in board])
                return
            
            for i in range(self.n):
                if is_safe(board, i, col):
                    board[i][col] = 1
                    backtrack(board, col + 1)
                    board[i][col] = 0  # Backtrack
        
        # Initialize empty board
        board = [[0 for _ in range(self.n)] for _ in range(self.n)]
        backtrack(board, 0)
        return self.solutions
    
    def solve_branch_and_bound(self):
        """Solves N-Queens using branch and bound"""
        def is_safe(row, col, slash_code, backslash_code, row_lookup, slash_lookup, backslash_lookup):
            if (slash_lookup[slash_code[row][col]] or 
                backslash_lookup[backslash_code[row][col]] or 
                row_lookup[row]):
                return False
            return True

        def branch_and_bound(board, col, slash_code, backslash_code, 
                            row_lookup, slash_lookup, backslash_lookup):
            if col >= self.n:
                self.solutions.append([row[:] for row in board])
                return
            
            for i in range(self.n):
                if is_safe(i, col, slash_code, backslash_code, 
                           row_lookup, slash_lookup, backslash_lookup):
                    # Place queen and mark constraints
                    board[i][col] = 1
                    row_lookup[i] = True
                    slash_lookup[slash_code[i][col]] = True
                    backslash_lookup[backslash_code[i][col]] = True
                    
                    # Recur for next column
                    branch_and_bound(board, col + 1, slash_code, backslash_code, 
                                   row_lookup, slash_lookup, backslash_lookup)
                    
                    # Backtrack
                    board[i][col] = 0
                    row_lookup[i] = False
                    slash_lookup[slash_code[i][col]] = False
                    backslash_lookup[backslash_code[i][col]] = False

        # Initialize board
        board = [[0 for _ in range(self.n)] for _ in range(self.n)]
        
        # Helper matrices for branch and bound
        slash_code = [[0 for _ in range(self.n)] for _ in range(self.n)]
        backslash_code = [[0 for _ in range(self.n)] for _ in range(self.n)]
        
        # Arrays to tell us which rows and diagonals are occupied
        row_lookup = [False] * self.n
        
        # Initialize slash and backslash codes
        for r in range(self.n):
            for c in range(self.n):
                slash_code[r][c] = r + c
                backslash_code[r][c] = r - c + (self.n - 1)
        
        slash_lookup = [False] * (2 * self.n - 1)
        backslash_lookup = [False] * (2 * self.n - 1)
        
        branch_and_bound(board, 0, slash_code, backslash_code, 
                        row_lookup, slash_lookup, backslash_lookup)
        return self.solutions
    
    def print_solutions(self, solutions):
        """Prints all solutions in a readable format"""
        for idx, solution in enumerate(solutions, 1):
            print(f"Solution {idx}:")
            for row in solution:
                print(" ".join("Q" if cell == 1 else "." for cell in row))
            print()

# Example usage
if __name__ == "__main__":
    n = 4  # Change this value for different board sizes
    
    print("Solving N-Queens problem with Backtracking:")
    backtrack_solver = NQueensSolver(n)
    backtrack_solutions = backtrack_solver.solve_backtracking()
    backtrack_solver.print_solutions(backtrack_solutions)
    
    print("\nSolving N-Queens problem with Branch and Bound:")
    bb_solver = NQueensSolver(n)
    bb_solutions = bb_solver.solve_branch_and_bound()
    bb_solver.print_solutions(bb_solutions)
    
    print(f"Total solutions found with Backtracking: {len(backtrack_solutions)}")
    print(f"Total solutions found with Branch & Bound: {len(bb_solutions)}")