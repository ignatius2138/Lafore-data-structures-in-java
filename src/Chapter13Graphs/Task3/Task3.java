package Chapter13Graphs.Task3;

public class Task3 {
    static class StackX {
        private final int[] stackArray;
        private int top;

        public StackX() {
            int SIZE = 20;
            stackArray = new int[SIZE];
            top = -1;
        }

        public void push(int j) {
            stackArray[++top] = j;
        }

        public int pop() {
            return stackArray[top--];
        }

        public int peek() {
            return stackArray[top];
        }

        public boolean isEmpty() {
            return (top == -1);
        }

    }

    static class Vertex {
        public char label;
        public boolean wasVisited;

        public Vertex(char lab) {
            label = lab;
            wasVisited = false;
        }
    }

    static class Graph {
        private final Vertex[] vertexList;
        private final int[][] adjMat;
        private int nVerts;
        private final StackX theStack;

        public Graph() {
            int MAX_VERTS = 20;
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            for (int y = 0; y < MAX_VERTS; y++)
                for (int x = 0; x < MAX_VERTS; x++)
                    adjMat[x][y] = 0;
            theStack = new StackX();
        }

        public void addVertex(char lab) {
            vertexList[nVerts++] = new Vertex(lab);
        }

        public void addEdge(int start, int end) {
            adjMat[start][end] = 1;
        }

        public void displayVertex(int v) {
            System.out.print(vertexList[v].label);
        }

        public void depthFirstSearch() {
            System.out.println();
            for (int i = 0; i < nVerts; i++) {
                vertexList[i].wasVisited = true;
                displayVertex(i);
                theStack.push(i);

                while (!theStack.isEmpty()) {     // until stack empty,
                    // get an unvisited vertex adjacent to stack top
                    int v = getAdjUnvisitedVertex(theStack.peek());
                    if (v == -1)                  // if no such vertex,
                        theStack.pop();
                    else {                       // if it exists
                        vertexList[v].wasVisited = true;
                        displayVertex(v);
                        theStack.push(v);
                    }
                }
                // stack is empty, so we're done
                for (int j = 0; j < nVerts; j++)          // reset flags
                    vertexList[j].wasVisited = false;
                System.out.println();
            }
        }

        public int getAdjUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++)
                if (adjMat[v][j] == 1 && !vertexList[j].wasVisited)
                    return j;
            return -1;
        }
    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addEdge(0, 2);
        theGraph.addEdge(1, 0);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(4, 2);
        theGraph.addEdge(3, 4);
        System.out.print("Connectivity Table: ");
        theGraph.depthFirstSearch();
        System.out.println();
    }

}
