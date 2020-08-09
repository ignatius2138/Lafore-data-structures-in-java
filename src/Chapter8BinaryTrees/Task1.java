package Chapter8BinaryTrees;

import java.io.*;
import java.util.*;

class Task1 {
    class Node {
        public char iData;
        public double dData;
        public Node leftChild;
        public Node rightChild;

        public void displayNode() {
            System.out.print('{');
            System.out.print(iData);
            System.out.print(", ");
            System.out.print(dData);
            System.out.print("} ");
        }

        Node(char character) {
            iData = character;
        }
    }

    class Tree {
        private Node root;


        public Tree() {
            root = null;
        }

        private Node getSuccessor(Node delNode) {
            Node successorParent = delNode;
            Node successor = delNode;
            Node current = delNode.rightChild;
            while (current != null) {
                successorParent = successor;
                successor = current;
                current = current.leftChild;
            }

            if (successor != delNode.rightChild) {
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = delNode.rightChild;
            }
            return successor;
        }

        public void traverse(int traverseType) {
            switch (traverseType) {
                case 1:
                    System.out.print("\nPreorder traversal: ");
                    preOrder(root);
                    break;
                case 2:
                    System.out.print("\nInorder traversal:  ");
                    inOrder(root);
                    break;
                case 3:
                    System.out.print("\nPostorder traversal: ");
                    postOrder(root);
                    break;
            }
            System.out.println();
        }

        private void preOrder(Node localRoot) {
            if (localRoot != null) {
                System.out.print(localRoot.iData + " ");
                preOrder(localRoot.leftChild);
                preOrder(localRoot.rightChild);
            }
        }

        private void inOrder(Node localRoot) {
            if (localRoot != null) {
                inOrder(localRoot.leftChild);
                System.out.print(localRoot.iData + " ");
                inOrder(localRoot.rightChild);
            }
        }

        private void postOrder(Node localRoot) {
            if (localRoot != null) {
                postOrder(localRoot.leftChild);
                postOrder(localRoot.rightChild);
                System.out.print(localRoot.iData + " ");
            }
        }

        public void displayTree() {
            Stack globalStack = new Stack();
            globalStack.push(root);
            int nBlanks = 32;
            boolean isRowEmpty = false;
            System.out.println(
                    "......................................................");
            while (isRowEmpty == false) {
                Stack localStack = new Stack();
                isRowEmpty = true;

                for (int j = 0; j < nBlanks; j++)
                    System.out.print(' ');

                while (globalStack.isEmpty() == false) {
                    Node temp = (Node) globalStack.pop();
                    if (temp != null) {
                        System.out.print(temp.iData);
                        localStack.push(temp.leftChild);
                        localStack.push(temp.rightChild);

                        if (temp.leftChild != null ||
                                temp.rightChild != null)
                            isRowEmpty = false;
                    } else {
                        System.out.print("--");
                        localStack.push(null);
                        localStack.push(null);
                    }
                    for (int j = 0; j < nBlanks * 2 - 2; j++)
                        System.out.print(' ');
                }
                System.out.println();
                nBlanks /= 2;
                while (localStack.isEmpty() == false)
                    globalStack.push(localStack.pop());
            }
            System.out.println(
                    "......................................................");
        }

    }

    public static void main(String[] args) throws IOException {
        Task1 homework = new Task1();
        System.out.print("Enter a string of characters: ");
        String input = getString();

        Tree[] trees = new Tree[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            trees[i] = homework.new Tree();
            trees[i].root = homework.new Node(c);
        }

        for (int i = 1; i < input.length(); i++) {
            Node tempRoot = homework.new Node('+');
            tempRoot.leftChild = trees[i - 1].root;
            tempRoot.rightChild = trees[i].root;
            Tree tempTree = homework.new Tree();
            tempTree.root = tempRoot;
            trees[i] = tempTree;
        }
        trees[input.length() - 1].displayTree();
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}

