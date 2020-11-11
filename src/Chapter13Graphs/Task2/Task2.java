package Chapter13Graphs.Task2;

public class Task2 {
    static class Link {
        public int iData;
        public Link next;

        public Link(int id) {
            iData = id;
        }
    }

    static class LinkList {
        private Link first;

        public LinkList() {
            first = null;
        }

        public void insertFirst(int id) {
            Link newLink = new Link(id);
            newLink.next = first;
            first = newLink;
        }

        public Link findUnvisited(Vertex[] vertexList) { // findUnvisited link with given key
            Link current = first;
            while (vertexList[current.iData].wasVisited) {
                if (current.next == null)
                    return null;
                else
                    current = current.next;
            }
            return current;
        }
    }

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
        private final LinkList[] adjacencyList;
        private int numberOfVertices;
        private final StackX theStack;

        public Graph() {
            int MAX_VERTS = 20;
            vertexList = new Vertex[MAX_VERTS];
            adjacencyList = new LinkList[MAX_VERTS];
            for (int i = 0; i < adjacencyList.length; i++) {
                adjacencyList[i] = new LinkList();
            }
            numberOfVertices = 0;
            theStack = new StackX();
        }

        public void addVertex(char lab) {
            vertexList[numberOfVertices++] = new Vertex(lab);
        }

        public void addEdge(int start, int end) {
            adjacencyList[start].insertFirst(end);
            adjacencyList[end].insertFirst(start);
        }

        public void displayVertex(int v) {
            System.out.print(vertexList[v].label);
        }

        public void depthFirstSearch() {
            vertexList[0].wasVisited = true;
            displayVertex(0);
            theStack.push(0);
            while (!theStack.isEmpty()) {
                int v = getAdjUnvisitedVertex(theStack.peek());
                if (v == -1)
                    theStack.pop();
                else {
                    vertexList[v].wasVisited = true;
                    displayVertex(v);
                    theStack.push(v);
                }
            }
            for (int j = 0; j < numberOfVertices; j++) {
                vertexList[j].wasVisited = false;
            }
        }

        public int getAdjUnvisitedVertex(int v) {
            Link unvisitedLink = adjacencyList[v].findUnvisited(vertexList);
            if (unvisitedLink != null) {
                return unvisitedLink.iData;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');

        theGraph.addEdge(0, 1);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(0, 3);
        theGraph.addEdge(3, 4);

        System.out.print("Visits: ");
        theGraph.depthFirstSearch();
        System.out.println();
    }
}
