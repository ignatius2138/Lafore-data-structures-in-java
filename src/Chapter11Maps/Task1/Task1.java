package Chapter11Maps.Task1;

import java.io.*;

class DataItem {
    private final int iData;

    public DataItem(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }

}

class HashTable {
    private final DataItem[] hashArray;    // array holds hash table
    private final int arraySize;
    private final DataItem nonItem;        // for deleted items

    public HashTable(int arraySize) {
        this.arraySize = arraySize;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);   // deleted item key is -1
    }

    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    public int hashFunction(int key) {
        return key % arraySize;       // hash function
    }

    public void insert(DataItem item) {//Quadratic probing Task 1
        int key = item.getKey();      // extract key
        int hashValue = hashFunction(key);  // hash the key
        int stepNumber = 0;
        int baseHashValue = hashValue;
        while (hashArray[hashValue] != null &&
                hashArray[hashValue].getKey() != -1) {
            stepNumber++;
            hashValue = (baseHashValue + (stepNumber * stepNumber)) % arraySize;
        }
        hashArray[hashValue] = item;    // insert item
    }


    public DataItem delete(int key) {
        int hashValue = hashFunction(key);
        int stepNumber = 0;
        int baseHashValue = hashValue;
        while (hashArray[hashValue] != null) {
            if (hashArray[hashValue].getKey() == key) {
                DataItem temp = hashArray[hashValue]; // save item
                hashArray[hashValue] = nonItem;       // delete item
                return temp;                        // return item
            }
            stepNumber++;
            hashValue = (baseHashValue + (stepNumber * stepNumber)) % arraySize;
        }
        return null;   // can't find item
    }

    public DataItem find(int key) {
        int hashVal = hashFunction(key);  // hash the key
        int stepNumber = 0;
        int baseHashVal = hashVal;
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];   // yes, return item
            stepNumber++;
            hashVal = (baseHashVal + (stepNumber * stepNumber)) % arraySize;
        }
        return null;                  // can't find item
    }
}

class HashTableApp {
    public static void main(String[] args) throws IOException {
        DataItem aDataItem;
        int aKey, size, n, keysPerCell;
        // get sizes
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;
        // make table
        HashTable theHashTable = new HashTable(size);

        for (int j = 0; j < n; j++) {
            aKey = (int) (java.lang.Math.random() * keysPerCell * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }

        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null) {
                        System.out.println("Found " + aKey);
                    } else
                        System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
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
