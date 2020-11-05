package Chapter10Trees234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DataItem {
    public DataItem(long dd) {
        dData = dd;
    }

    public long dData;

    public void displayItem() {
        System.out.print("/" + dData);
    }

}

class Node {
    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private final Node[] childArray = new Node[ORDER];
    private final DataItem[] itemArray = new DataItem[ORDER - 1];

    // connect child to this node
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null)
            child.parent = this;
    }

    // disconnect child from this node, return it
    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    public long getMinimumFromNode() {
        return itemArray[0].dData;
    }

    public Node getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return childArray[0] == null;
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem getItem(int index) {
        return itemArray[index];
    }

    public boolean isFull() {
        return numItems == ORDER - 1;
    }

    public int insertItem(DataItem newItem) {
        numItems++;
        long newKey = newItem.dData;

        for (int j = ORDER - 2; j >= 0; j--) {
            if (itemArray[j] == null)
                continue;
            else {
                long itsKey = itemArray[j].dData;
                if (newKey < itsKey)
                    itemArray[j + 1] = itemArray[j];
                else {
                    itemArray[j + 1] = newItem;
                    return j + 1;
                }
            }
        }
        itemArray[0] = newItem;
        return 0;
    }

    public DataItem removeItem() {
        DataItem temp = itemArray[numItems - 1];  // save item
        itemArray[numItems - 1] = null;           // disconnect it
        numItems--;                             // one less item
        return temp;                            // return item
    }

    public void displayNode() {
        for (int j = 0; j < numItems; j++)
            itemArray[j].displayItem();
        System.out.println("/");
    }
}

class Tree234 {
    private Node root = new Node(); // make root node

    public long getMinimum() {//Task 1
        Node current = root;
        while (!current.isLeaf()) {
            current = current.getChild(0);
        }
        return current.getMinimumFromNode();
    }

    public void inOrder() {//Task 2
        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node currentNode) {
        if (currentNode.isLeaf()) { // Base case
            // Display current node.
            for (int i = 0; i < currentNode.getNumItems(); i++) {
                currentNode.getItem(i).displayItem();
            }
            return;
        } else {
            int numChildren = currentNode.getNumItems() + 1;
            // Call ourselves for each child.
            for (int i = 0; i < numChildren; i++) {
                inOrderTraverse(currentNode.getChild(i));
                if (i < numChildren - 1) {
                    currentNode.getItem(i).displayItem();
                }
            }
        }
    }

    public void insert(long dValue) {
        Node curNode = root;
        DataItem tempItem = new DataItem(dValue);
        while (true) {
            if (curNode.isFull()) {
                split(curNode);
                curNode = curNode.getParent();

                curNode = getNextChild(curNode, dValue);
            } else if (curNode.isLeaf())
                break;
            else
                curNode = getNextChild(curNode, dValue);
        }
        curNode.insertItem(tempItem);
    }

    public void split(Node thisNode) {
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;
        itemC = thisNode.removeItem();    // remove items from
        itemB = thisNode.removeItem();    // this node
        child2 = thisNode.disconnectChild(2); // remove children
        child3 = thisNode.disconnectChild(3); // from this node
        Node newRight = new Node();       // make new node
        if (thisNode == root) {                // if this is the root,
            root = new Node();                // make new root
            parent = root;                    // root is our parent
            root.connectChild(0, thisNode);   // connect to parent
        } else                              // this node not the root
            parent = thisNode.getParent();    // get parent
        // deal with parent
        itemIndex = parent.insertItem(itemB); // item B to parent
        int n = parent.getNumItems();         // total items?
        for (int j = n - 1; j > itemIndex; j--) {// move parent's connections
            Node temp = parent.disconnectChild(j); // one child
            parent.connectChild(j + 1, temp);        // to the right
        }
        // connect newRight to parent
        parent.connectChild(itemIndex + 1, newRight);
        // deal with newRight
        newRight.insertItem(itemC);       // item C to newRight
        newRight.connectChild(0, child2); // connect to 0 and 1
        newRight.connectChild(1, child3); // on newRight
    }

    // gets appropriate child of node during search for value
    public Node getNextChild(Node theNode, long theValue) {
        int j;
        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++)          // for each item in node
        {                               // are we less?
            if (theValue < theNode.getItem(j).dData)
                return theNode.getChild(j);  // return left child
        }  // end for                   // we're greater, so
        return theNode.getChild(j);        // return right child
    }

    public void displayTree() {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node thisNode, int level, int childNumber) {
        System.out.print("level=" + level + " child=" + childNumber + " ");
        thisNode.displayNode(); // display this node
        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);
            if (nextNode != null)
                recDisplayTree(nextNode, level + 1, j);
            else
                return;
        }
    }
}

class Tree234App {
    public static void main(String[] args) throws IOException {
        Tree234 theTree = new Tree234();
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(5);
        theTree.insert(70);
        while (true) {
            System.out.print("\nEnter first letter of ");
            System.out.print("show, minimum or traverse: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'm':
                    System.out.println("Minimum element is " + theTree.getMinimum());
                    break;
                case 't':
                    theTree.inOrder();
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
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

