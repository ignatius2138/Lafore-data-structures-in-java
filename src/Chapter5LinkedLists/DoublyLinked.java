package Chapter5LinkedLists;

// doublyLinked.java
// Двусвязный список
// Запуск программы: C>java DoublyLinkedApp
////////////////////////////////////////////////////////////////
class Link2 {
    public long dData; // Данные
    public Link2 next; // Следующий элемент в списке
    public Link2 previous; // Предыдущий элемент в списке

    // -------------------------------------------------------------
    public Link2(long d) // Конструктор
    {
        dData = d;
    }

    // -------------------------------------------------------------
    public void displayLink() // Вывод содержимого элемента
    {
        System.out.print(dData + " ");
    }
// -------------------------------------------------------------
} // Конец класса Link

////////////////////////////////////////////////////////////////
class DoublyLinkedList {
    private Link2 first; // Ссылка на первый элемент списка
    private Link2 last; // Ссылка на последний элемент списка

    // -------------------------------------------------------------
    public DoublyLinkedList() // Конструктор
    {
        first = null; // Список пока не содержит элементов
        last = null;
    }

    // -------------------------------------------------------------
    public boolean isEmpty() // true, если список пуст
    {
        return first == null;
    }

    // -------------------------------------------------------------
    public void insertFirst(long dd) // Вставка элемента в начало списка
    {
        Link2 newLink = new Link2(dd); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            last = newLink; // newLink <-- last
        else
            first.previous = newLink; // newLink <-- старое значение firstДвусвязные списки 223
        newLink.next = first; // newLink --> старое значение first
        first = newLink; // first --> newLink
    }

    // -------------------------------------------------------------
    public void insertLast(long dd) // элемент в конец списка
    {
        Link2 newLink = new Link2(dd); // Создание нового элемента
        if (isEmpty()) // Если список не содержит элементов,
            first = newLink; // first --> newLink
        else {
            last.next = newLink; // старое значение last --> newLink
            newLink.previous = last; // старое значение last <-- newLink
        }
        last = newLink; // newLink <-- last
    }

    // -------------------------------------------------------------
    public Link2 deleteFirst() // Удаление первого элемента
    { // (предполагается, что список не пуст)
        Link2 temp = first;
        if (first.next == null) // Если только один элемент
            last = null; // null <-- last
        else
            first.next.previous = null; // null <-- старое значение next
        first = first.next; // first --> старое значение next
        return temp;
    }

    // -------------------------------------------------------------
    public Link2 deleteLast() // Удаление последнего элемента
    { // (предполагается, что список не пуст)
        Link2 temp = last;
        if (first.next == null) // Если только один элемент
            first = null; // first --> null
        else
            last.previous.next = null; // старое значение previous --> null
        last = last.previous; // старое значение previous <-- last
        return temp;
    }

    // Вставка dd в позицию после key
    public boolean insertAfter(long key, long dd) { // (предполагается, что список не пуст)
        Link2 current = first; // От начала списка
        while (current.dData != key) // Пока не будет найдено совпадение
        {
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return false; // Ключ не найден
        }
        Link2 newLink = new Link2(dd); // Создание нового элемента
        if (current == last) // Для последнего элемента списка
        {
            newLink.next = null; // newLink --> null
            last = newLink; // newLink <-- last
        } else // Не последний элемент
        {
            newLink.next = current.next; // newLink --> старое значение next
// newLink <-- старое значение next
            current.next.previous = newLink;
        }
        newLink.previous = current; // старое значение current <-- newLink
        current.next = newLink; // старое значение current --> newLink
        return true; // Ключ найден, вставка выполнена
    }

    // -------------------------------------------------------------
    public Link2 deleteKey(long key) // Удаление элемента с заданным ключом
    { // (предполагается, что список не пуст)
        Link2 current = first; // От начала списка
        while (current.dData != key) // Пока не будет найдено совпадение
        {
            current = current.next; // Переход к следующему элементу
            if (current == null)
                return null; // Ключ не найден
        }
        if (current == first) // Ключ найден; это первый элемент?
            first = current.next; // first --> старое значение next
        else // Не первый элемент
// старое значение previous --> старое значение next
            current.previous.next = current.next;
        if (current == last) // Последний элемент?
            last = current.previous; // старое значение previous <-- last
        else // Не последний элемент
// Старое значение previous <-- старое значение next
            current.next.previous = current.previous;
        return current; // Возвращение удаленного элемента
    }

    // -------------------------------------------------------------
    public void displayForward() {
        System.out.print("List (first-->last): ");
        Link2 current = first; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данныхДвусвязные списки 225
            current = current.next; // Переход к следующему элементу
        }
        System.out.println("");
    }

    // -------------------------------------------------------------
    public void displayBackward() {
        System.out.print("List (last-->first): ");
        Link2 current = last; // От начала списка
        while (current != null) // Перемещение до конца списка
        {
            current.displayLink(); // Вывод данных
            current = current.previous; // Переход к следующему элементу
        }
        System.out.println("");
    }
// -------------------------------------------------------------
} // Конец класса DoublyLinkedList

class Deque {
    private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    public void insertLeft(long item) {
        doublyLinkedList.insertFirst(item);
    }

    public void insertRight(long item) {
        doublyLinkedList.insertLast(item);
    }

    public long removeLeft() {
        return doublyLinkedList.deleteFirst().dData;
    }

    public long removeRight() {
        return doublyLinkedList.deleteLast().dData;
    }

    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    public void display() {
        String s = "Queue contents (left --> right): ";
        if (!isEmpty()) {
            doublyLinkedList.displayForward();
        }
    }
}


////////////////////////////////////////////////////////////////
class DoublyLinkedApp {
    public static void main(String[] args) { // Создание нового списка
        Deque deque = new Deque();
        deque.insertLeft(15);
        deque.insertRight(99);
        deque.insertLeft(1);
        deque.insertLeft(77);
        deque.display();
    }
} // Конец класса DoublyLinke
