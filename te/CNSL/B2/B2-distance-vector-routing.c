#include <stdio.h>

#define INFINITY 999
#define MAX 10

void distanceVectorRouting(int costMatrix[MAX][MAX], int numNodes) {
    int distance[MAX][MAX], nextHop[MAX][MAX], i, j, k;

    // Initialize distance and nextHop matrices
    for (i = 0; i < numNodes; i++) {
        for (j = 0; j < numNodes; j++) {
            distance[i][j] = costMatrix[i][j];
            if (costMatrix[i][j] != INFINITY && i != j) {
                nextHop[i][j] = j;
            } else {
                nextHop[i][j] = -1;
            }
        }
    }

    // Update the distance matrix based on the Distance Vector Algorithm
    for (k = 0; k < numNodes; k++) {
        for (i = 0; i < numNodes; i++) {
            for (j = 0; j < numNodes; j++) {
                if (distance[i][k] + distance[k][j] < distance[i][j]) {
                    distance[i][j] = distance[i][k] + distance[k][j];
                    nextHop[i][j] = nextHop[i][k];
                }
            }
        }
    }

    // Print the routing table for each node
    for (i = 0; i < numNodes; i++) {
        printf("Routing table for router %d:\n", i + 1);
        printf("Destination\tNext Hop\tDistance\n");
        for (j = 0; j < numNodes; j++) {
            if (i != j) {
                printf("%d\t\t%d\t\t%d\n", j + 1, nextHop[i][j] + 1, distance[i][j]);
            }
        }
        printf("\n");
    }
}

int main() {
    int costMatrix[MAX][MAX], numNodes, i, j;

    printf("Enter the number of nodes: ");
    scanf("%d", &numNodes);

    printf("Enter the cost matrix:\n");
    for (i = 0; i < numNodes; i++) {
        for (j = 0; j < numNodes; j++) {
            scanf("%d", &costMatrix[i][j]);
            if (costMatrix[i][j] == 0 && i != j) {
                costMatrix[i][j] = INFINITY;
            }
        }
    }

    distanceVectorRouting(costMatrix, numNodes);

    return 0;
}
