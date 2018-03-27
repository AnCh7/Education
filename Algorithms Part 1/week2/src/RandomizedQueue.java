import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    // Resizing-array implementation.
    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];
        Integer k = 0;
        for (int i = 0; i < q.length; i++) {
            Item item = q[(first + i) % q.length];
            if (item != null) {
                temp[k] = item;
                k++;
            }
        }

        q = temp;
        first = 0;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // double size of array if necessary and recopy to front of array
        if (n == q.length) {
            resize(2 * q.length);
        }

        for (int i = 0; i < q.length; i++) {
            if (q[i] == null) {
                q[i] = item;
                break;
            }
        }

        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (q.length == 0) {
            throw new NoSuchElementException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        StdRandom.shuffle(q);

        Item item = q[first];
        if (item == null) {
            int i;
            for (i = 0; i < q.length; i++) {
                if (q[i] != null) {
                    item = q[i];
                    q[i] = null; // to avoid loitering
                    break;
                }
            }
            if (i == q.length) {
                throw new NoSuchElementException("Empty");
            }
        } else {
            q[first] = null; // to avoid loitering
        }

        n--;

        if (first == q.length) {
            first = 0; // wrap-around
        }

        // shrink size of array if necessary
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (q.length == 0) {
            throw new NoSuchElementException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }

        StdRandom.shuffle(q);

        Item item = q[first];
        if (item == null) {
            int i;
            for (i = 0; i < q.length; i++) {
                if (q[i] != null) {
                    item = q[i];
                    break;
                }
            }
            if (i == q.length) {
                throw new NoSuchElementException("Empty");
            }
        }

        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(q);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] items;
        private int i = 0;

        public RandomizedQueueIterator(Item[] data) {
            items = data;
            StdRandom.shuffle(items);
        }

        public boolean hasNext() {
            return i < q.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = q[i];

            if (item == null) {
                for (int k = i; k < q.length; k++, i++) {
                    if (q[k] != null) {
                        item = q[k];
                        break;
                    }
                }
            }
            i++;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");

        Iterator<String> iterator1 = queue.iterator();
        Iterator<String> iterator2 = queue.iterator();

        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator1.next());
        StdOut.println(iterator2.next());
        StdOut.println(iterator2.next());
        StdOut.println(iterator2.next());
        StdOut.println(iterator2.next());

        StdOut.println(queue.size());
        StdOut.println(queue.isEmpty());

        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());

        StdOut.println(queue.size());
        StdOut.println(queue.isEmpty());
    }
}
