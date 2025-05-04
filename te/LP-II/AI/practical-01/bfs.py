import collections

def bfs(graph, root):
    visited = set()
    queue = collections.deque([root])
    while queue:
        vertex = queue.popleft()
        visited.add(vertex)
        for i in graph[vertex]:
            if i not in visited:
                queue.append(i)
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

print(bfs(graph, 0))
print(bfs(graph2, 'A'))
