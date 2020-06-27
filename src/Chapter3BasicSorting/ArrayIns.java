package Chapter3BasicSorting;

// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns {
    private final long[] array;                 // ref to array a
    private int numberOfElements;               // number of data items

    //--------------------------------------------------------------
    public ArrayIns(int max) {        // constructor
        array = new long[max];                 // create the array
        numberOfElements = 0;                        // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value) {    // put element into array
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
            long temp = array[out];            // remove marked item
            in = out;                      // start shifts at out
            while (in > 0 && array[in - 1] >= temp) {// until one is smaller,
                array[in] = array[in - 1];            // shift item to right
                --in;                       // go left one position
            }
            array[in] = temp;                  // insert marked item
        }
    }
//--------------------------------------------------------------
    public long median() {
        insertionSort();
        int middle = numberOfElements / 2;
        if (numberOfElements % 2 != 0) {
            return array[middle];
        } else return (array[middle] + array[middle - 1]) / 2;
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
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);

        arr.display();                // display items

        arr.insertionSort();          // insertion-sort them

        arr.display();// display them again
        System.out.println("Median of an array is: " + arr.median());
    }  // end main()
}  // end class InsertSortApp
