package Chapter8BinaryTrees;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Represents a node in a binary tree data structure
 */
class Node {
    private final char letter;
    private Node leftChild;
    private Node rightChild;

    public Node(char letter) {
        this.letter = letter;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Returns a String representation of this node.
     */
    @Override
    public String toString() {
        return "" + letter;
    }
}

/**
 * A binary tree
 */
class Tree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void displayTree() {
        Stack<Node> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp);
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());

                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false
        System.out.println("......................................................");
    }
}

/**
 * Accepts nodes and creates trees.
 */
class TreeCreator {
    private final Node[] nodes;
    private int index;

    public TreeCreator(int numNodes) {
        nodes = new Node[numNodes];
        index = 0;
    }

    public void add(Node node) {
        if (index < nodes.length) {
            nodes[index++] = node;
        }
    }

    public Tree createMainTree() {
        System.out.println(Arrays.toString(nodes));
        Tree mainTree = new Tree();
        // Set first node of array as root.
        mainTree.setRoot(nodes[0]);
        createTreeRecursively(mainTree.getRoot(), 1);

        // If number of nodes is even, need to be set last node manually to it's parent.
        if (nodes.length % 2 == 0) {
            int lastNodeIndex = nodes.length;
            int lastNodeParentIndex = (lastNodeIndex - 1) / 2;
            nodes[lastNodeParentIndex].setLeftChild(nodes[nodes.length - 1]);
        }

        return mainTree;
    }

    private void createTreeRecursively(Node root, int rootIndex) {
        if (rootIndex * 2 >= nodes.length) {
            return;
        } else {
            root.setLeftChild(nodes[2 * rootIndex - 1]);
            createTreeRecursively(root.getLeftChild(), 2 * rootIndex);
            root.setRightChild(nodes[2 * rootIndex]);
            createTreeRecursively(root.getRightChild(), 2 * rootIndex + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter characters to display in a tree format please");
        String input = scanner.nextLine();
        char[] chars = input.toCharArray();
        final int numberOfNodes = input.length();
        TreeCreator treeCreator = new TreeCreator(numberOfNodes);
        for (char inputChar : chars) {
            // Make new node and add.
            Node newNode = new Node(inputChar);
            treeCreator.add(newNode);
        }

        Tree mainTree = treeCreator.createMainTree();
        mainTree.displayTree();
    }
}