package Chapter3BasicSorting;

// bubbleSort.java
// demonstrates bubble sort

class ArrayBub {
    private final long[] array;                 // ref to array a
    private int numberOfElements;               // number of data items

    public ArrayBub(int max) {         // constructor
        array = new long[max];                 // create the array
        numberOfElements = 0;                        // no items yet
    }

    public void insert(long value) {   // put element into array
        array[numberOfElements] = value;             // insert it
        numberOfElements++;                      // increment size
    }

    public void display() {            // displays array contents
        for (int j = 0; j < numberOfElements; j++)       // for each element,
            System.out.print(array[j] + " ");  // display it
        System.out.println("");
    }

    public void bubbleSort() {
        int out, in;
        for (out = numberOfElements - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {       // out of order?
                    swap(in, in + 1);
                }// swap them
            }// inner loop (forward)
            for (int i = out - 1; i > 0; i--) {//Task 1
                if (array[i] < array[i - 1]) {
                    swap(i, i - 1);
                }
            }
        }// outer loop (backward)
    }

    private void swap(int one, int two) {
        long temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    public void oddEvenSort() {//Task 4
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < array.length - 1; i += 2) {
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
            for (int i = 0; i < array.length - 1; i += 2) {
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

}

class BubbleSortApp {
    public static void main(String[] args) {
        int maxSize = 11;            // array size
        ArrayBub arr;                 // reference to array
        arr = new ArrayBub(maxSize);  // create the array

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

        arr.oddEvenSort();             // bubble sort them

        arr.display();                // display them again
    }  // end main()
}
