package Chapter4StackAndQueues;

// queue.java
// Работа с очередью
// Запуск программы: C>java QueueApp
////////////////////////////////////////////////////////////////
class Queue {
    private final int maxSize;
    private final long[] queueArray;
    private int front;
    private int rear;
    private int numberOfItems;

    //--------------------------------------------------------------
    public Queue(int maxSize) // Конструктор
    {
        this.maxSize = maxSize;
        queueArray = new long[this.maxSize];
        front = 0;
        rear = -1;
        numberOfItems = 0;
    }

    //--------------------------------------------------------------
    public void insert(long newElement) // Вставка элемента в конец очереди
    {
        if (isFull()) {
            System.out.println("Queue is full!");
        } else {
            if (rear == maxSize - 1) {
                rear = -1;
            } // Циклический перенос
            queueArray[++rear] = newElement; // Увеличение rear и вставка
            numberOfItems++; // Увеличение количества элементов
        }
    }

    //--------------------------------------------------------------
    public long remove() // Извлечение элемента в начале очереди
    {
        long temp = queueArray[front++]; // Выборка и увеличение front
        if (front == maxSize) {
            front = 0;
        } // Циклический перенос
        numberOfItems--; // Уменьшение количества элементов
        return temp;
    }

    //--------------------------------------------------------------
    public long peekFront() // Чтение элемента в начале очереди
    {
        return queueArray[front];
    }

    //--------------------------------------------------------------
    public boolean isEmpty() // true, если очередь пуста
    {
        return (numberOfItems == 0);
    }

    //--------------------------------------------------------------
    public boolean isFull() // true, если очередь заполненаОчереди 143
    {
        return (numberOfItems == maxSize);
    }

    //--------------------------------------------------------------
    public int size() // Количество элементов в очереди
    {
        return numberOfItems;
    }

    public String showQueueContents() { //Task 1
        StringBuilder contents = new StringBuilder("Queue contents are: ");
        if (isEmpty()) return "Queue contents are empty";
        int temp = front;
        while (temp != rear) {
            contents.append(queueArray[temp++]).append(" ");
            if (temp == maxSize) {
                temp = 0;
            }
        }
        contents.append(queueArray[temp]);
        return contents.toString();
    }

//--------------------------------------------------------------
} // Конец класса Queue

////////////////////////////////////////////////////////////////
class QueueApp {
    public static void main(String[] args) {
        Queue queue = new Queue(5);// Очередь из 5 ячеек
        queue.insert(99);
        queue.insert(12);
        queue.insert(136);
        queue.remove();
        queue.remove();
        queue.insert(21);
        queue.insert(21);
        queue.insert(21);
        System.out.println(queue.showQueueContents());
    }
} // Конец класса QueueApp
