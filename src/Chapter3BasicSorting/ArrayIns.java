package Chapter3BasicSorting;

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
    public void insertionSort() {// Task 5

        int in, out;
        int copiesCounter = 0;
        int comparisonsCounter = 0;

        /*for (out = 1; out < numberOfElements; out++) {
            int temp = array[out];
            copiesCounter++;
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
                copiesCounter++;
                comparisonsCounter++;
            }
            if (in != 0) comparisonsCounter++; // captures last unsuccessful comparison
            array[in] = temp;
            copiesCounter++;
        }*/
        for (out = 1; out < numberOfElements; out++) {
            int temp = array[out];
            in = out;
            while (in > 0) {
                comparisonsCounter++;
                if (array[in - 1] >= temp) {
                    array[in] = array[in - 1];
                    in--;
                    copiesCounter++;
                } else break;
            }
            array[in] = temp;
        }
        System.out.println("Copies: " + copiesCounter + ". Comparisons: " + comparisonsCounter);
    }

    public void noDuplicatesInsertionSort() {//Task 6
        int in, out;
        for (out = 1; out < numberOfElements; out++) { // out - разделительный маркер
            int temp = array[out]; // Скопировать помеченный элемент
            in = out; // Начать перемещения с out
            while (in > 0 && array[in - 1] >= temp) { // Пока не найден меньший элемент
                if (temp == array[in - 1]) {
                    temp = -1;
                }
                array[in] = array[in - 1]; // Сдвинуть элемент вправо
                --in; // Перейти на одну позицию влево
            }
            array[in] = temp; // Вставить помеченный элемент
        }
        removeDuplicatesFromArray(getNumberOfDuplicates());
    }

    private int getNumberOfDuplicates() {
        int numberOfDuplicates = 0;
        for (int x: array) {
            if (x == -1) {
                numberOfDuplicates++;
            }
        }
        return numberOfDuplicates;
    }

    private void removeDuplicatesFromArray(int numberOfDuplicates) {
        if (numberOfDuplicates != 0) {
            for (int i = numberOfDuplicates; i < numberOfElements; i++) {
                array[i - numberOfDuplicates] = array[i];
            }
        }
        numberOfElements -= numberOfDuplicates;
    }

    //--------------------------------------------------------------
    public long median() {//Task 2
        insertionSort();
        int middle = numberOfElements / 2;
        if (numberOfElements % 2 != 0) {
            return array[middle];
        } else return (array[middle] + array[middle - 1]) / 2;
    }

    public void noDuplicates() {//Task 3
        int[] temp = new int[numberOfElements];

        // Start traversing elements
        int tempArrayIndex = 0;
        for (int i = 0; i < numberOfElements - 1; i++) {
            // If current element is not equal
            // to next element then store that
            // current element
            if (array[i] != array[i + 1]) {
                temp[tempArrayIndex] = array[i];
                tempArrayIndex++;
            }
        }

        // Store the last element as whether
        // it is unique or repeated, it hasn't been
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
        int maxSize = 19;            // array size
        ArrayIns arr;                 // reference to array
        arr = new ArrayIns(maxSize);  // create the array

        arr.insert(10);               // insert 10 items
        arr.insert(9);
        arr.insert(8);
        arr.insert(6);
        arr.insert(6);
        arr.insert(5);
        arr.insert(4);
        arr.insert(3);
        arr.insert(5);
        arr.insert(10);
        arr.insert(6);
        arr.insert(21);
        arr.insert(9);
        arr.insert(9);

        arr.display();                // display items

        arr.noDuplicatesInsertionSort();          // insertion-sort them

        arr.display();// display them again
        System.out.println("Median of an array is: " + arr.median());
        //arr.noDuplicates();
        //arr.display();

    }  // end main()
}  // end class InsertSortApp
