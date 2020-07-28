package Chapter5LinkedLists;

public class Task3 {

    private class Link {
        public int data;
        public Link next;

        public Link(int data) {
            this.data = data;
        }
    }

    private class CircularList {
        private Link current;

        public void insert(int data) {
            Link newLink = new Link(data);
            if (current == null) {
                current = newLink;
                current.next = current;
            } else {
                newLink.next = current.next;
                current.next = newLink;
                current = newLink;
            }
        }

        public Link find(int key) {
            if (current.data != key) {
                int firstKey = current.data;
                current = current.next;
                while (current.data != key) {
                    if (current.data == firstKey) {
                        return null;
                    } else {
                        current = current.next;
                    }
                }
            }
            return current;
        }

        public void delete() {
            if (isEmpty()) {
                System.out.println("Your list is empty");
            } else {
                delete(current.data);
            }
        }

        public void delete(int key) {
            if (find(key) == null) {
                System.out.println("Cant find " + key + " to delete");
            } else {
                while (current.next.data != key) {
                    step();
                }
                if (current == current.next) {
                    current = null;
                    //System.out.println("Cant find " + key + " to delete");
                } else {
                    current.next = current.next.next;
                }
            }
        }

        public void step() {
            if (!isEmpty()) {
                current = current.next;
            }
        }

        public boolean isEmpty() {
            return current == null;
        }

        public void display() {
            if (isEmpty()) {
                System.out.println("Your list is empty");
            } else {
                int firstKey = current.data;
                System.out.print("Circular list: " + current.data);
                step();
                while (current.data != firstKey) {
                    System.out.print(", " + current.data);
                    step();
                }
                System.out.println();
            }
        }
    }

    private class Stack { //Task 4
        private final CircularList circularList = new CircularList();

        public void push(int value) {
            circularList.insert(value);
        }

        public void pop() {
            circularList.delete();
        }

        public void display() {
            circularList.display();
        }

    }

    public static void main(String[] args) {
        Task3 task3 = new Task3();
        Stack stack = task3.new Stack();
        stack.push(10);
        stack.push(15);
        stack.push(99);
        stack.display();
        stack.pop();
        stack.display();
        stack.pop();
        stack.push(237837827);
        stack.pop();
        stack.display();
    }

}

