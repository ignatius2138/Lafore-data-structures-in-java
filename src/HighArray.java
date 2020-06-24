class HighArray {
    private int[] array;                 // ref to array a
    private int numberOfElementsInArray;               // number of data items

    public HighArray(int max){        // constructor
        array = new int[max];                 // create the array
        numberOfElementsInArray = 0;                        // no items yet
    }

    public int getNumberOfElementsInArray() {
        return numberOfElementsInArray;
    }

    public boolean find(long searchKey){   // find specified value
        int j;
        for(j=0; j<numberOfElementsInArray; j++)            // for each element,
            if(array[j] == searchKey)           // found item?
                break;                       // exit loop before end
        if(j == numberOfElementsInArray)                    // gone to end?
            return false;                   // yes, can't find it
        else
            return true;                    // no, found it
    }  // end find()

    public void insert(int value){    // put element into array
        array[numberOfElementsInArray] = value;             // insert it
        numberOfElementsInArray++;                      // increment size
    }

    public boolean delete(long value) {
        int j;
        for(j=0; j<numberOfElementsInArray; j++)        // look for it
            if( value == array[j] )
                break;
        if(j==numberOfElementsInArray)                  // can't find it
            return false;
        else{                        // found it
            for(int k=j; k<numberOfElementsInArray; k++) // move higher ones down
                array[k] = array[k+1];
            numberOfElementsInArray--;                   // decrement size
            return true;
        }
    }  // end delete()

    public void display(){           // displays array contents
        for(int j = 0; j< numberOfElementsInArray; j++)       // for each element,
            System.out.print(array[j] + " ");  // display it
        System.out.println("");
    }

    public int getMax(){
        int maxNumber = 0;
        if (numberOfElementsInArray == 0) {
            System.out.println("Array is empty");
        }
        else {
            for (int x:array) {
                if (x > maxNumber) maxNumber = x;
            }
        }
        return maxNumber;
    }

    public int removeMax(){
        int maxNumber = 0;
        if (numberOfElementsInArray == 0) {
            System.out.println("Array is empty");
        }
        else {
            for (int x:array) {
                if (x > maxNumber) maxNumber = x;
            }
        }
        delete(maxNumber);
        return maxNumber;
    }

}  // end class HighArray


class HighArrayApp {
    public static void main(String[] args) {
        int maxSize = 10;            // array size
        HighArray arr;                // reference to array
        arr = new HighArray(maxSize); // create the array

        arr.insert(77);               // insert 9 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(160);
        arr.insert(91);
        arr.insert(11);
        arr.insert(17);
        arr.insert(66);
        arr.insert(21);

        arr.display();                // display items

        int searchKey = 35;           // search for item
        if(arr.find(searchKey)) System.out.println("Found " + searchKey);
        else System.out.println("Can't find " + searchKey);

        arr.delete(17);          // delete 3 items
        arr.delete(55);
        arr.display();                // display items again

        System.out.println("Max number in an array is: " + arr.getMax());
        arr.removeMax();
        arr.display();

        arr.insert(15);
        arr.insert(999);
        System.out.println("Unsorted array: ");
        arr.display();
        System.out.println("Sorting array...");
        int arrayLength = arr.getNumberOfElementsInArray();
        HighArray sortedArray = new HighArray(maxSize);
        for (int x = 0; x <arrayLength; x++ ) {
            int currentMaxNumber = arr.removeMax();
            sortedArray.insert(currentMaxNumber);
        }
        sortedArray.display();


    }  // end main()
}  // end class HighArrayApp