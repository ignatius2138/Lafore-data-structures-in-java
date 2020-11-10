package Chapter6Recursion.Task2;

import java.util.Arrays;

public class Task2 {
    public static class BinaryTreePrinter {

        // Holds the characters to be printed
        private final Row[] binaryTree;
        private final int rowLength;

        public BinaryTreePrinter(int rowLength) {
            this.rowLength = rowLength;
            // Number of rows = (Base 2 log of rowLength) + 1
            int numberOfRows = (int) (Math.log(rowLength) / Math.log(2)) + 1;
            binaryTree = new Row[numberOfRows];
            for (int i = 0; i < binaryTree.length; i++) {
                binaryTree[i] = new Row(rowLength);
            }
        }

        public void makeBranches() {
            recursiveMakeBranches(0, rowLength - 1, 0);
        }

        private void recursiveMakeBranches(int leftIndex, int rightIndex, int rowIndex) {
            if (leftIndex == rightIndex) {
                binaryTree[rowIndex].insertCharAt(leftIndex, 'X');
                return;
            } else {
                int midPoint = (leftIndex + rightIndex) / 2;
                binaryTree[rowIndex].insertCharAt(midPoint, 'X');
                recursiveMakeBranches(leftIndex, midPoint, rowIndex + 1);
                recursiveMakeBranches(midPoint + 1, rightIndex, rowIndex + 1);
            }
        }

        public void display() {
            for (Row row : binaryTree) {
                System.out.println(row);
            }
        }

    }

    public static class Row {
        private final char[] treeRow;

        public Row(int rowLength) {
            treeRow = new char[rowLength];
            fillRowWithDashes();
        }

        private void fillRowWithDashes() {
            Arrays.fill(treeRow, '-');
        }

        public void insertCharAt(int index, char character) {
            treeRow[index] = character;
        }

        @Override
        public String toString() {
            StringBuilder returnString = new StringBuilder();
            for (char c : treeRow) {
                returnString.append(c);
            }
            return returnString.toString();
        }
    }

    public static void main(String[] args) {
        BinaryTreePrinter printer = new BinaryTreePrinter(64);
        printer.makeBranches();
        printer.display();
    }

}
