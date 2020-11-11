package Chapter14WeightedGraphs.Task1;

public class Task1 {
    static class DistanceAndParent {
        public int distance;   // distance from start to this vertex
        public int parentVert; // current parent of this vertex

        public DistanceAndParent(int pv, int d) {
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
        private final int[][] adjMat;
        private int numberOfVerts;
        private int numberOfVertsInTree;
        private final DistanceAndParent[] sPath;     // array for shortest-path data
        private int currentVert;
        private int startToCurrent;

        public Graph() {
            int MAX_VERTS = 20;
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            numberOfVerts = 0;
            numberOfVertsInTree = 0;
            for (int j = 0; j < MAX_VERTS; j++)     // set adjacency
                for (int k = 0; k < MAX_VERTS; k++)  //     matrix
                    adjMat[j][k] = INFINITY;     //     to infinity
            sPath = new DistanceAndParent[MAX_VERTS];    // shortest paths
        }

        public void addVertex(char lab) {
            vertexList[numberOfVerts++] = new Vertex(lab);
        }

        public void addEdge(int start, int end, int weight) {
            adjMat[start][end] = weight;  // (directed)
        }

        public void path() {// find all shortest paths
            for (int i = 0; i < numberOfVerts; i++) {
                vertexList[i].isInTree = true;
                numberOfVertsInTree = 1; // put it in tree
                // transfer row of distances from adjMat to sPath
                for (int j = 0; j < numberOfVerts; j++) {
                    int tempDist = adjMat[i][j];
                    sPath[j] = new DistanceAndParent(i, tempDist);
                }
                // until all vertices are in the tree
                while (numberOfVertsInTree < numberOfVerts) {
                    int indexMin = getMin();    // get minimum from sPath
                    int minDist = sPath[indexMin].distance;

                    if (minDist == INFINITY)     // if all infinite
                    {                        // or in tree,
                        //System.out.println("There are unreachable vertices");
                        break;                   // sPath is complete
                    } else {                        // reset currentVert
                        currentVert = indexMin;  // to closest vert
                        startToCurrent = sPath[indexMin].distance;
                        // minimum distance from startTree is
                        // to currentVert, and is startToCurrent
                    }
                    // put current vertex in tree
                    vertexList[currentVert].isInTree = true;
                    numberOfVertsInTree++;
                    adjust_sPath();
                }

                System.out.print(vertexList[i].label + "   ");
                displayPaths();
                numberOfVertsInTree = 0;
                for (int j = 0; j < numberOfVerts; j++)
                    vertexList[j].isInTree = false;
            }
        }

        public int getMin() {
            int minDist = INFINITY;
            int indexMin = 0;
            for (int j = 1; j < numberOfVerts; j++) {
                if (!vertexList[j].isInTree && sPath[j].distance < minDist) {
                    minDist = sPath[j].distance;
                    indexMin = j;
                }
            }
            return indexMin;
        }

        public void adjust_sPath() {
            int column = 1;
            while (column < numberOfVerts) {
                if (vertexList[column].isInTree) {
                    column++;
                    continue;
                }
                // calculate distance for one sPath entry
                // get edge from currentVert to column
                int currentToFringe = adjMat[currentVert][column];
                // add distance from start
                int startToFringe = startToCurrent + currentToFringe;
                // get distance of current sPath entry
                int sPathDist = sPath[column].distance;
                // compare distance from start with sPath entry
                if (startToFringe < sPathDist)   // if shorter,
                {                            // update sPath
                    sPath[column].parentVert = currentVert;
                    sPath[column].distance = startToFringe;
                }
                column++;
            }
        }

        public void displayPaths() {
            for (int j = 0; j < numberOfVerts; j++) {
                System.out.print(vertexList[j].label + "=");
                if (sPath[j].distance == INFINITY)
                    System.out.print("inf");
                else
                    System.out.print(sPath[j].distance);
                char parent = vertexList[sPath[j].parentVert].label;
                System.out.print("(" + parent + ") ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addEdge(0, 1, 50);
        theGraph.addEdge(0, 3, 80);
        theGraph.addEdge(1, 2, 60);
        theGraph.addEdge(1, 3, 90);
        theGraph.addEdge(2, 4, 40);
        theGraph.addEdge(3, 2, 20);
        theGraph.addEdge(3, 4, 70);
        theGraph.addEdge(4, 1, 50);
        System.out.println("Shortest paths");
        theGraph.path();
        System.out.println();
    }
}
