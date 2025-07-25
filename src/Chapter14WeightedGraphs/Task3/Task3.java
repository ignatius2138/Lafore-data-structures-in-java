package Chapter14WeightedGraphs.Task3;

public class Task3 {
    static class DistanceToParent {
        public int distance;
        public int parentVert;

        public DistanceToParent(int pv, int d) {
            distance = d;
            parentVert = pv;
        }
    }

    static class Vertex {
        public char label;
        public boolean isInTree;

        public Vertex(char lab) {
            label = lab;
            isInTree = false;
        }

    }

    static class Graph {
        private final int INFINITY = 1000000;
        private final Vertex[] vertexList;
        private final int[][] adjacencyMatrix;
        private int numberOfVertices;

        public Graph() {
            int MAX_VERTS = 20;
            vertexList = new Vertex[MAX_VERTS];
            adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
            numberOfVertices = 0;
            // Set adjacency matrix to infinity
            for (int j = 0; j < MAX_VERTS; j++)
                for (int k = 0; k < MAX_VERTS; k++)
                    adjacencyMatrix[j][k] = INFINITY;
        }

        public void makeTransitiveClosure() {
            System.out.println("Adjacency matrix...");
            showMatrix();
            for (int y = 0; y < numberOfVertices; y++) {
                for (int x = 0; x < numberOfVertices; x++) {
                    if (adjacencyMatrix[y][x] != INFINITY) {
                        int segment1 = adjacencyMatrix[y][x];
                        for (int z = 0; z < numberOfVertices; z++) {
                            if (adjacencyMatrix[z][y] != INFINITY) {
                                int segment2 = adjacencyMatrix[z][y];
                                int totalDistance = segment1 + segment2;
                                if (totalDistance < adjacencyMatrix[z][x] && z != x) {
                                    adjacencyMatrix[z][x] = segment1 + segment2;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("\nFloyd's matrix...");
            showMatrix();
        }

        public void showMatrix() {
            System.out.println();
            System.out.print("  ");
            for (int column = 0; column < numberOfVertices; column++) {
                System.out.print(vertexList[column].label + "  ");
            }
            System.out.println();
            for (int i = 0; i < numberOfVertices; i++) {
                System.out.print(vertexList[i].label + " ");
                for (int j = 0; j < numberOfVertices; j++) {
                    String currentElement = Integer.toString(adjacencyMatrix[i][j]);
                    if (adjacencyMatrix[i][j] == INFINITY) {
                        currentElement = "--";
                    }
                    System.out.print(currentElement + " ");
                }
                System.out.println();
            }
        }

        public void addVertex(char lab) {
            vertexList[numberOfVertices++] = new Vertex(lab);
        }

        public void addEdge(int start, int end, int weight) {
            adjacencyMatrix[start][end] = weight;  // (directed)
        }


    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');     // 0  (start)
        theGraph.addVertex('B');     // 1
        theGraph.addVertex('C');     // 2
        theGraph.addVertex('D');     // 3
        theGraph.addVertex('E');     // 4

        theGraph.addEdge(0, 1, 50);  // AB 50
        theGraph.addEdge(0, 3, 80);  // AD 80
        theGraph.addEdge(1, 2, 60);  // BC 60
        theGraph.addEdge(1, 3, 90);  // BD 90
        theGraph.addEdge(2, 4, 40);  // CE 40
        theGraph.addEdge(3, 2, 20);  // DC 20
        theGraph.addEdge(3, 4, 70);  // DE 70
        theGraph.addEdge(4, 1, 50);  // EB 50

        theGraph.makeTransitiveClosure();
        System.out.println();
    }
}
