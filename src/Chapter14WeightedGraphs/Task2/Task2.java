package Chapter14WeightedGraphs.Task2;

import java.util.ArrayList;

public class Task2 {
    /**
     * Represents an edge between two vertices in a graph
     */
    public static class Edge {
        private static final int INFINITY = 1000000;
        private int weight;
        private EdgeReferenceGraph.Vertex endingVertex;

        public Edge(int weight, EdgeReferenceGraph.Vertex endingVertex) {
            this.weight = weight;
            this.endingVertex = endingVertex;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public EdgeReferenceGraph.Vertex getEndingVertex() {
            return endingVertex;
        }

        public void setEndingVertex(EdgeReferenceGraph.Vertex endingVertex) {
            this.endingVertex = endingVertex;
        }

        @Override
        public String toString() {
            return Integer.toString(weight) + " -> " + endingVertex.getLabel();
        }
    }

    public static class EdgeReferenceGraph {
        private static final int MAXIMUM_VERTICES = 20;
        private final Vertex[] vertexList;
        private int numVertices;

        public EdgeReferenceGraph() {
            vertexList = new Vertex[MAXIMUM_VERTICES];
        }

        public void addVertex(char label) {
            vertexList[numVertices++] = new Vertex(label);
        }

        public void addEdge(int startingVertex, int endingVertex, int weight) {
            Edge edge = new Edge(weight, vertexList[endingVertex]);
            vertexList[startingVertex].addEdge(edge);
        }

        public void displayGraph() {
            for (int i = 0; i < numVertices; i++) {
                displayVertex(i);
            }
        }

        private void displayVertex(int vertexNumber) {
            System.out.println(vertexList[vertexNumber].toString());
        }

        /**
         * Represents a vertex in a graph.
         */
        public static class Vertex {
            private final char label;
            private final ArrayList<Edge> edges;

            public Vertex(char label) {
                this.label = label;
                edges = new ArrayList<>();
            }

            public void addEdge(Edge edge) {
                edges.add(edge);
            }


            public char getLabel() {
                return label;
            }

            @Override
            public String toString() {
                String string = Character.toString(label) + "   ";
                for (Edge edge : edges) {
                    string += edge + " ";
                }
                return string;
            }
        }

        public static void main(String[] args) {
            EdgeReferenceGraph theGraph = new EdgeReferenceGraph();
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

            System.out.println("Shortest paths");
            theGraph.displayGraph();
            System.out.println();
        }
    }
}
