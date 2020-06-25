class OrdArray {
    private final long[] array;                 // ref to array a
    private int numberOfElements;         // number of data items

    public OrdArray(int max) {         // constructor
        array = new long[max];             // create array
        numberOfElements = 0;
    }

    public int size() {
        return numberOfElements;
    }

    public long getValueAt(int index) {
        return array[index];
    }

    public void setValueAt(int index, long value) {
        array[index] = value;
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = numberOfElements - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (array[curIn] == searchKey)
                return curIn;              // found it
            else if (lowerBound > upperBound)
                return numberOfElements;             // can't find it
            else {                        // divide range
                if (array[curIn] < searchKey)
                    lowerBound = curIn + 1; // it's in upper half
                else
                    upperBound = curIn - 1; // it's in lower half
            }  // end else divide range
        }
    }

    public void insert(long value) {   // put element into array
        int lowerBound = 0;
        int upperBound = numberOfElements - 1;
        int j = 0;
        while (lowerBound <= upperBound) {
            j = (lowerBound + upperBound) / 2;
            if (value > array[j]) {
                lowerBound = j + 1;
                j++;
            } else upperBound = j - 1;
        }
        for (int k = numberOfElements; k > j; k--)    // move bigger ones up
            array[k] = array[k - 1];
        array[j] = value;                  // insert it
        numberOfElements++;                      // increment size
    }

    public boolean delete(long value) {
        int j = find(value);
        if (j == numberOfElements)                  // can't find it
            return false;
        else {                       // found it
            for (int k = j; k < numberOfElements; k++) // move bigger ones down
                array[k] = array[k + 1];
            numberOfElements--;                   // decrement size
            return true;
        }
    }

    public void display() {            // displays array contents
        for (int j = 0; j < numberOfElements; j++)       // for each element,
            System.out.print(array[j] + " ");  // display it
        System.out.println("");
    }

    public static OrdArray merge(OrdArray firstArray, OrdArray secondArray, int firstArrayLength, int secondArrayLength) {
        //int i = 0, j = 0, k = 0;//Indexes for arrays: firstArray - i, second j, merged - k
        OrdArray mergedArray = new OrdArray(firstArrayLength + secondArrayLength);
        /*while (i<firstArrayLength && j <secondArrayLength) {
            if (firstArray.getValueAt(i) < secondArray.getValueAt(j)) {
                mergedArray.setValueAt(k++, firstArray.getValueAt(i++));
            }
            else {
                mergedArray.setValueAt(k++, secondArray.getValueAt(j++));
            }
            mergedArray.numberOfElements++;

        }
        while (i < firstArrayLength){
            mergedArray.setValueAt(k++, firstArray.getValueAt(i++));
            mergedArray.numberOfElements++;
        }
        while (j < secondArrayLength){
            mergedArray.setValueAt(k++, secondArray.getValueAt(j++));
            mergedArray.numberOfElements++;
        }
        return mergedArray;*/
        for (int l = 0; l < firstArrayLength; l++) {
            mergedArray.insert(firstArray.getValueAt(l));
        }
        for (int l = 0; l < secondArrayLength; l++) {
            mergedArray.insert(secondArray.getValueAt(l));
        }
        return mergedArray;
    }
}

class OrderedApp {
    public static void main(String[] args) {
        int maxSize = 100;             // array size
        OrdArray arr;                  // reference to array
        arr = new OrdArray(maxSize);   // create the array

        arr.insert(77);                // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(0);
        arr.insert(66);
        arr.insert(33);

        int searchKey = 77;            // search for item
        if (arr.find(searchKey) != arr.size()) System.out.println("Found " + searchKey);
        else System.out.println("Can't find " + searchKey);
        arr.display();                 // display items

        arr.delete(0);                // delete 3 items
        arr.delete(55);
        arr.delete(99);
        arr.delete(66);

        arr.display();// display items again

        OrdArray firstArray = new OrdArray(11);
        for (int i = 0; i < 11; i++) {
            int randomNumber = (int) (Math.random() * 100);
            firstArray.insert(randomNumber);
        }
        System.out.println("First Array: ");
        firstArray.display();
        OrdArray secondArray = new OrdArray(21);
        for (int i = 0; i < 21; i++) {
            int randomNumber = (int) (Math.random() * 100);
            secondArray.insert(randomNumber);
        }
        System.out.println("Second Array: ");
        secondArray.display();
        System.out.println("Merged Array is: ");
        OrdArray.merge(firstArray, secondArray, firstArray.size(), secondArray.size()).display();
    }
}
