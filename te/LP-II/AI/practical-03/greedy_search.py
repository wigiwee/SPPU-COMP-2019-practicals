# # Prim's Algorithm in Python
# INF = 9999999
# # number of vertices in graph
# N = 5
# #creating graph by adjacency matrix method
# G = [[0, 19, 5, 0, 0],
#     [19, 0, 5, 9, 2],
#     [5, 5, 0, 1, 6],
#     [0, 9, 1, 0, 1],
#     [0, 2, 6, 1, 0]]
# selected_node = [0, 0, 0, 0, 0]
# no_edge = 0
# selected_node[0] = True
# # printing for edge and weight
# print("Edge : Weight\n")
# while (no_edge < N - 1):
#     minimum = INF
#     a = 0
#     b = 0
#     for m in range(N):
#         if selected_node[m]:
#             for n in range(N):
#                 if ((not selected_node[n]) and G[m][n]):
#                     # not in selected and there is an edge
#                     if minimum > G[m][n]:
#                         minimum = G[m][n]
#                         minimuma = m
#                         a = m
#                         b = n
# print(str(a) + "-" + str(b) + ":" + str(G[a][b]))
# selected_node[b] = True
# no_edge += 1

import sys

class Graph:
    def __init__(self, vertices):
        self.V = vertices
        self.graph = [[0 for _ in range(vertices)] for _ in range(vertices)]
    
    def print_mst(self, parent):
        print("Edge \tWeight")
        for i in range(1, self.V):
            print(f"{parent[i]} - {i}\t{self.graph[i][parent[i]]}")
    
    def min_key(self, key, mst_set):
        min_val = sys.maxsize
        min_index = -1
        
        for v in range(self.V):
            if key[v] < min_val and not mst_set[v]:
                min_val = key[v]
                min_index = v
        return min_index
    
    def prim_mst(self):
        # Key values used to pick minimum weight edge
        key = [sys.maxsize] * self.V
        parent = [None] * self.V  # Array to store constructed MST
        
        # Make key 0 so this vertex is picked first
        key[0] = 0
        mst_set = [False] * self.V
        
        parent[0] = -1  # First node is always the root
        
        for _ in range(self.V):
            # Pick the minimum distance vertex from the set of vertices not yet in MST
            u = self.min_key(key, mst_set)
            
            # Add the picked vertex to the MST set
            mst_set[u] = True
            
            # Update key and parent for adjacent vertices
            for v in range(self.V):
                # graph[u][v] is non-zero only for adjacent vertices
                # mst_set[v] is False for vertices not yet in MST
                # Update the key only if graph[u][v] is smaller than key[v]
                if self.graph[u][v] > 0 and not mst_set[v] and key[v] > self.graph[u][v]:
                    key[v] = self.graph[u][v]
                    parent[v] = u
        
        self.print_mst(parent)

# Given adjacency matrix
adj_matrix = [
    [0, 19, 5, 0, 0],
    [19, 0, 5, 9, 2],
    [5, 5, 0, 1, 6],
    [0, 9, 1, 0, 1],
    [0, 2, 6, 1, 0]
]

g = Graph(len(adj_matrix))
g.graph = adj_matrix

print("Minimum Spanning Tree using Prim's Algorithm:")
g.prim_mst()