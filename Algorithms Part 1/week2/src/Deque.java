import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


// 1 - constant worst-case time for all operation including iterator ones
// 2 - n items must use at most 48n + 192 bytes of memory and use space proportional
// to the number of items currently in the deque.
public class Deque<Item> implements Iterable<Item> {

    // linked-list implementation
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        if (isEmpty()) {
            last = first;
            last.next = null;
        } else {
            first.next = oldFirst;
        }
        n++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue underflow");
        }

        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;   // to avoid loitering
        }

        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue underflow");
        }

        Node<Item> lastButOne = lastButOne();
        Item item = last.item;
        last = lastButOne;
        last.next = null;
        n--;
        if (isEmpty()) {
            first = null;   // to avoid loitering
        }

        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first);
    }

    private static class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private Node<Item> lastButOne() {
        Node<Item> current = first;
        if (current.next == null) {
            return current;
        } else {
            while (true) {
                if (current.next.next == null) {
                    return current;
                } else {
                    current = current.next;
                }
            }
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> queue = new Deque<>();
        queue.addFirst("3");
        queue.addFirst("2");
        queue.addFirst("1");
        queue.addLast("4");
        queue.addLast("5");
        queue.addLast("6");

        StdOut.println(queue.isEmpty());
        StdOut.println(queue.size());

        Iterator<String> i = queue.iterator();
        StdOut.println(i.next());
        StdOut.println(i.next());
        StdOut.println(i.next());
        StdOut.println(i.next());
        StdOut.println(i.next());
        StdOut.println(i.next());

        queue.removeFirst();
        queue.removeFirst();
        queue.removeLast();
        queue.removeLast();
    }
}
