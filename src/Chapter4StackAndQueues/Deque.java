package Chapter4StackAndQueues;

class Deque {
    private final int maxSize;
    private final long[] queueArray;
    private int left;
    private int right;
    private int numberOfItems;

    public Deque(int maxSize) {
        this.maxSize = maxSize;
        queueArray = new long[this.maxSize];
        left = this.maxSize;
        right = -1;
        numberOfItems = 0;
    }

    public void insertLeft(long newElementFromLeft) {
        if (isFull()) {
            System.out.println("Deque is full");
        } else {
            if (left == 0) left = maxSize;
            queueArray[--left] = newElementFromLeft;
            if (right == -1) right = maxSize - 1;
            numberOfItems++;
        }
    }

    public void insertRight(long newElementFromRight) {
        if (isFull()) {
            System.out.println("Deque is full");
        } else {
            if (right == maxSize - 1) right = -1;
            queueArray[++right] = newElementFromRight;
            if (left == maxSize) left = 0;
            numberOfItems++;
        }
    }

    public void removeLeft() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
        } else {
            left++;
            if (left == maxSize) left = 0;
            numberOfItems--;
        }
    }

    public void removeRight() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
        } else {
            right--;
            if (right == -1) right = maxSize - 1;
            numberOfItems--;
        }
    }

    public void displayDequeContents() {
        StringBuilder contents = new StringBuilder("");
        if (numberOfItems == 0) {
            contents = new StringBuilder("Queue is empty");
        } else {
            int displayedElementPosition = left;
            for (int i = 0; i < numberOfItems; i++) {
                contents.append(queueArray[displayedElementPosition++]).append(" ");
                if (displayedElementPosition == maxSize) displayedElementPosition = 0;
            }
        }
        System.out.println(contents);
    }

    public boolean isEmpty() {
        return (numberOfItems == 0);
    }

    public boolean isFull() {
        return (numberOfItems == maxSize);
    }

    public int size() {
        return numberOfItems;
    }

    public long peekRight() {
        return queueArray[right];
    }

}

class StackUsingDeque {
    private final Deque dequeStack;

    public StackUsingDeque(int maxSize) {
        dequeStack = new Deque(maxSize);
    }

    public void push(long newElement) {
        dequeStack.insertRight(newElement);
    }

    public void pop() {
        dequeStack.removeRight();
    }

    public long peek() {
        return dequeStack.peekRight();
    }

    public boolean isEmpty() {
        return dequeStack.isEmpty();
    }

    public boolean isFull() {
        return dequeStack.isFull();
    }

    public void show() {
        dequeStack.displayDequeContents();
    }

}

class DequeApp {
    public static void main(String[] args) {
        Deque deque = new Deque(5);
        deque.insertRight(10);
        deque.insertRight(20);
        deque.insertLeft(30);
        deque.insertRight(40);
        deque.insertLeft(40);
        deque.insertRight(40);
        deque.displayDequeContents();
        deque.removeLeft();
        deque.removeRight();
        deque.removeRight();
        System.out.println(deque.size());
        deque.displayDequeContents();

        StackUsingDeque stackUsingDeque = new StackUsingDeque(5);
        stackUsingDeque.push(5);
        stackUsingDeque.push(0);
        stackUsingDeque.push(17);
        stackUsingDeque.show();
        stackUsingDeque.pop();
        stackUsingDeque.show();
        stackUsingDeque.push(100500);
        System.out.println(stackUsingDeque.peek());
    }
}
