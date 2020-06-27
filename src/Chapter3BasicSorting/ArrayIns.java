package Chapter3BasicSorting;

import java.util.Arrays;
import java.util.stream.Stream;

// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns {
    public int[] getArray() {
        return array;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    private final int[] array;                 // ref to array a
    private int numberOfElements;               // number of data items

    //--------------------------------------------------------------
    public ArrayIns(int max) {        // constructor
        array = new int[max];                 // create the array
        numberOfElements = 0;                        // no items yet
    }

    //--------------------------------------------------------------
    public void insert(int value) {    // put element into array
        array[numberOfElements] = value;             // insert it
        numberOfElements++;                      // increment size
    }

    //--------------------------------------------------------------
    public void display() {           // displays array contents
        for (int j = 0; j < numberOfElements; j++)       // for each element,
            System.out.print(array[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void insertionSort() {
        int in, out;
        for (out = 1; out < numberOfElements; out++) {    // out is dividing line
            int temp = array[out];            // remove marked item
            in = out;                      // start shifts at out
            while (in > 0 && array[in - 1] >= temp) {// until one is smaller,
                array[in] = array[in - 1];            // shift item to right
                --in;                       // go left one position
            }
            array[in] = temp;                  // insert marked item
        }
    }

    //--------------------------------------------------------------
    public long median() {//Task 2
        insertionSort();
        int middle = numberOfElements / 2;
        if (numberOfElements % 2 != 0) {
            return array[middle];
        } else return (array[middle] + array[middle - 1]) / 2;
    }

    public void noDuplicates() {
        int[] temp = new int[numberOfElements];

        // Start traversing elements
        int tempArrayIndex = 0;
        for (int i = 0; i < numberOfElements - 1; i++){
            // If current element is not equal
            // to next element then store that
            // current element
            if (array[i] != array[i + 1]) {
                temp[tempArrayIndex] = array[i];
                tempArrayIndex++;
            }

        }

        // Store the last element as whether
        // it is unique or repeated, it hasn't
        // stored previously
        temp[tempArrayIndex++] = array[numberOfElements - 1];

        // Modify original array
        for (int i = 0; i < tempArrayIndex; i++)
            array[i] = temp[i];
        numberOfElements = tempArrayIndex;
    }
}

////////////////////////////////////////////////////////////////
class InsertSortApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        ArrayIns arr;                 // reference to array
        arr = new ArrayIns(maxSize);  // create the array

        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(44);
        arr.insert(0);
        arr.insert(21);
        arr.insert(77);
        arr.insert(11);
        arr.insert(0);
        arr.insert(77);
        arr.insert(33);
        arr.insert(99);
        arr.insert(15);
        arr.insert(8);

        arr.display();                // display items

        arr.insertionSort();          // insertion-sort them

        arr.display();// display them again
        System.out.println("Median of an array is: " + arr.median());
        arr.noDuplicates();
        arr.display();

    }  // end main()
}  // end class InsertSortApp
