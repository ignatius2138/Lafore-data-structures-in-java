package Chapter4StackAndQueues;

class PriorityQueue {
    private final int maxSize;
    private final long[] queueArray;
    private int numberOfElements;

    public PriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        queueArray = new long[this.maxSize];
        numberOfElements = 0;
    }

    public void insert(long newElement) {
        if (isFull()) {
            System.out.println("Priority Queue is empty");
        } else queueArray[numberOfElements++] = newElement;
    }

    public void remove() {//Task 4
        if (isEmpty()) {
            System.out.println("Priority Queue is empty");
        } else {
            int minElement = 0;
            minElement = getMinElement(minElement);
            removeMinElementAndShuffleDown(minElement);
        }
    }

    private void removeMinElementAndShuffleDown(int minElement) {
        for (int i = minElement + 1; i < numberOfElements; i++) {
            queueArray[i - 1] = queueArray[i];
        }
        numberOfElements--;
    }

    private int getMinElement(int minElement) {
        for (int i = 1; i < numberOfElements; i++) {
            if (queueArray[i] < queueArray[minElement]) {
                minElement = i;
            }
        }
        return minElement;
    }

    public void display() {//Task 4
        StringBuilder contents = new StringBuilder("");
        if (isEmpty()) {
            contents = new StringBuilder("Priority Queue is empty!");
        } else {
            int j = 0;
            for (int i = 0; i < numberOfElements; i++) {
                contents.append(queueArray[j++]).append(" ");
            }
        }
        System.out.println(contents);
    }

    public boolean isEmpty() {
        return (numberOfElements == 0);
    }

    public boolean isFull() {
        return (numberOfElements == maxSize);
    }
}

class PriorityQApp {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(5);
        priorityQueue.insert(30);
        priorityQueue.insert(50);
        priorityQueue.insert(10);
        priorityQueue.insert(40);
        priorityQueue.insert(20);
        priorityQueue.display();
        priorityQueue.remove();
        priorityQueue.display();
        priorityQueue.remove();
        priorityQueue.display();
        priorityQueue.remove();
        priorityQueue.display();
    }
}