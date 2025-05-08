from collections import defaultdict
import heapq

# Heuristic values (straight-line distance to Bucharest)
dict_hn = {
    'Arad': 366, 'Bucharest': 0, 'Craiova': 160, 'Drobeta': 242, 'Eforie': 161,
    'Fagaras': 176, 'Giurgiu': 77, 'Hirsova': 151, 'Iasi': 226, 'Lugoj': 244,
    'Mehadia': 241, 'Neamt': 234, 'Oradea': 380, 'Pitesti': 100, 'Rimnicu': 193,
    'Sibiu': 253, 'Timisoara': 329, 'Urziceni': 80, 'Vaslui': 199, 'Zerind': 374
}

# Graph connections with distances
dict_gn = {
    'Arad': {'Zerind': 75, 'Timisoara': 118, 'Sibiu': 140},
    'Bucharest': {'Urziceni': 85, 'Giurgiu': 90, 'Pitesti': 101, 'Fagaras': 211},
    'Craiova': {'Drobeta': 120, 'Pitesti': 138, 'Rimnicu': 146},
    'Drobeta': {'Mehadia': 75, 'Craiova': 120},
    'Eforie': {'Hirsova': 86},
    'Fagaras': {'Sibiu': 99, 'Bucharest': 211},
    'Giurgiu': {'Bucharest': 90},
    'Hirsova': {'Eforie': 86, 'Urziceni': 98},
    'Iasi': {'Neamt': 87, 'Vaslui': 92},
    'Lugoj': {'Mehadia': 70, 'Timisoara': 111},
    'Mehadia': {'Lugoj': 70, 'Drobeta': 75},
    'Neamt': {'Iasi': 87},
    'Oradea': {'Zerind': 71, 'Sibiu': 151},
    'Pitesti': {'Rimnicu': 97, 'Bucharest': 101, 'Craiova': 138},
    'Rimnicu': {'Sibiu': 80, 'Pitesti': 97, 'Craiova': 146},
    'Sibiu': {'Rimnicu': 80, 'Fagaras': 99, 'Arad': 140, 'Oradea': 151},
    'Timisoara': {'Lugoj': 111, 'Arad': 118},
    'Urziceni': {'Bucharest': 85, 'Hirsova': 98, 'Vaslui': 142},
    'Vaslui': {'Iasi': 92, 'Urziceni': 142},
    'Zerind': {'Oradea': 71, 'Arad': 75}
}

class Node:
    def __init__(self, name, parent=None):
        self.name = name
        self.parent = parent
        self.g = 0  # Cost from start to current node
        self.h = dict_hn[name]  # Heuristic estimate to goal
        self.f = 0  # Total cost (g + h)
    
    def __eq__(self, other):
        return self.name == other.name
    
    def __lt__(self, other):
        return self.f < other.f
    
    def __repr__(self):
        return f"({self.name}, g={self.g}, h={self.h}, f={self.f})"

def astar(start, end):
    # Initialize open and closed lists
    open_list = []
    closed_list = set()
    
    # Create start and end nodes
    start_node = Node(start)
    end_node = Node(end)
    
    # Add the start node to open list
    heapq.heappush(open_list, start_node)
    
    # Loop until open list is empty
    while open_list:
        # Get the current node (node with lowest f)
        current_node = heapq.heappop(open_list)
        closed_list.add(current_node.name)
        
        # Found the goal
        if current_node == end_node:
            path = []
            total_cost = current_node.g
            while current_node:
                path.append(current_node.name)
                current_node = current_node.parent
            return path[::-1], total_cost  # Return reversed path and total cost
        
        # Generate children
        children = []
        for neighbor, distance in dict_gn[current_node.name].items():
            # Create new node
            child = Node(neighbor, current_node)
            
            # Calculate g, h, f values
            child.g = current_node.g + distance
            child.h = dict_hn[neighbor]
            child.f = child.g + child.h
            
            # Add to children list
            children.append(child)
        
        # Process each child
        for child in children:
            # Child is in closed list (already processed)
            if child.name in closed_list:
                continue
            
            # Child is already in open list and has higher cost
            found = False
            for open_node in open_list:
                if child.name == open_node.name and child.g >= open_node.g:
                    found = True
                    break
            
            if found:
                continue
            
            # Add the child to the open list
            heapq.heappush(open_list, child)
    
    # No path found
    return None, float('inf')

# Example usage
if __name__ == "__main__":
    start_city = 'Arad'
    goal_city = 'Bucharest'
    
    print(f"Finding path from {start_city} to {goal_city} using A* algorithm...")
    path, total_cost = astar(start_city, goal_city)
    
    if path:
        print("\nPath found:")
        print(" -> ".join(path))
        print(f"Total cost: {total_cost} km")
    else:
        print("No path found!")