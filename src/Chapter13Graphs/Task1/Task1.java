package Chapter13Graphs.Task1;

class Queue {
    private final int SIZE = 20;
    private final int[] queArray;
    private int front;
    private int rear;

    public Queue() {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int j) {
        if (rear == SIZE - 1)
            rear = -1;
        queArray[++rear] = j;
    }

    public int remove() {
        int temp = queArray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return (rear + 1 == front || (front + SIZE - 1 == rear));
    }
}

class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char lab) {
        label = lab;
        wasVisited = false;
    }
}

class Graph {
    private final Vertex[] vertexList;
    private final int[][] adjacencyMatrix;
    private int numberOfVertices;
    private final Queue theQueue;

    public Graph() {
        int MAX_VERTS = 20;
        vertexList = new Vertex[MAX_VERTS];
        adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
        numberOfVertices = 0;
        for (int j = 0; j < MAX_VERTS; j++)
            for (int k = 0; k < MAX_VERTS; k++)
                adjacencyMatrix[j][k] = 0;
        theQueue = new Queue();
    }

    public void addVertex(char lab) {
        vertexList[numberOfVertices++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
        adjacencyMatrix[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void breadthFirstSearch() {
        vertexList[0].wasVisited = true;
        theQueue.insert(0);
        int secondVertex;
        while (!theQueue.isEmpty()) {
            int firstVertex = theQueue.remove();
            while ((secondVertex = getAdjUnvisitedVertex(firstVertex)) != -1) {
                vertexList[secondVertex].wasVisited = true;
                displayVertex(firstVertex);
                displayVertex(secondVertex);
                System.out.print(" ");
                theQueue.insert(secondVertex);
            }
        }
        for (int j = 0; j < numberOfVertices; j++)
            vertexList[j].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < numberOfVertices; j++)
            if (adjacencyMatrix[v][j] == 1 && !vertexList[j].wasVisited)
                return j;
        return -1;
    }

}

class BreadthFirstSearchApp {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');
        theGraph.addVertex('G');
        theGraph.addVertex('H');
        theGraph.addVertex('I');

        theGraph.addEdge(0, 1);     // AB
        theGraph.addEdge(1, 2);     // BC
        theGraph.addEdge(2, 3);     // CD
        theGraph.addEdge(3, 4);     // DE
        theGraph.addEdge(4, 8);     // EI
        theGraph.addEdge(8, 7);     // IH
        theGraph.addEdge(7, 0);     // HA
        theGraph.addEdge(0, 5);     // AF
        theGraph.addEdge(7, 5);     // HF
        theGraph.addEdge(2, 6);     // CG
        theGraph.addEdge(4, 6);     // EG
        theGraph.addEdge(8, 6);     // IG

        System.out.print("Minimum spanning tree: ");
        theGraph.breadthFirstSearch();
        System.out.println();
    }
}
