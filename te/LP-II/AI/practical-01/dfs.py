def dfs(graph, root):
    visited = []
    stack = []
    stack.append(root)
    while len(stack) != 0:
        vertex = stack.pop()
        if vertex in visited:
            continue
        print(vertex)
        visited.append(vertex)

        for val in graph[vertex]:
            stack.append(val)

    return visited

graph = {0: [1, 2], 1: [2], 2: [3], 3: [1, 2]}

graph2 = {
    'A': ['B', 'C', 'E'],
    'B': ['A','D', 'E'],
    'C': ['A', 'F', 'G'],
    'D': ['B'],
    'E': ['A', 'B','D'],
    'F': ['C'],
    'G': ['C']
}

print(dfs(graph, 0))
print(dfs(graph2, 'A'))