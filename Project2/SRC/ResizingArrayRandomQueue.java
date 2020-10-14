import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    //CODE HERE
    // Items in queue
    private Item[] array;
    private int lastIndex;

    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        //CODE HERE
        @SuppressWarnings("unchecked")
        Item[] a = (Item[]) new Object[1];
        array = a;
        lastIndex = -1;
    }


    // Is the queue empty?
    public boolean isEmpty() {
        //CODE HERE
        return size() == 0;
    }

    // The number of items on the queue.
    public int size() {
        //CODE HERE
        return lastIndex + 1;
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        @SuppressWarnings("unchecked")
        Item[] newArray = (Item[]) new Object[max];
        int i = 0, j = 0;
        while (i <= lastIndex) {
            newArray[j++] = array[i++];
        }
        array = newArray;
        lastIndex = j - 1;

    }

    // Add item to the queue.
    public void enqueue(Item item) {
        //CODE HERE
        if (item == null) {
            throw new NullPointerException("Element e cannot be null.");
        }
        // array is full
        if (lastIndex + 1 == array.length) {
            resize(array.length * 2);
        }
        array[++lastIndex] = item;
    }

    // Remove and return a random item from the queue.
    public Item dequeue() {
        //CODE HERE
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty.");
        }
        int i = StdRandom.uniform(lastIndex + 1);
        Item removed = array[i];
        array[i] = array[lastIndex];
        array[lastIndex--] = null;
        // resize array if it is only 25% full
        if (size() > 0 && size() == array.length / 4) {
            resize(array.length / 2);
        }
        return removed;
    }


    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        //CODE HERE
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty.");
        }
        Item sample = null;
        while (sample == null) {
            sample = array[StdRandom.uniform(lastIndex + 1)];
        }
        return sample;
    }


    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        //CODE HERE
        return new RandomQueueIterator();

    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        //CODE HERE
        private Item[] copiedArray;
        private int copiedLastIndex;

        // Items in queue

        public RandomQueueIterator() {
            //CODE HERE
            @SuppressWarnings("unchecked")
            Item[] a = (Item[]) new Object[lastIndex + 1];
            for (int i = 0; i <= lastIndex; i++) {
                a[i] = array[i];
            }
            copiedArray = a;
            copiedLastIndex = lastIndex;
        }

        public boolean hasNext() {
            return copiedLastIndex >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            //CODE HERE
            if (!hasNext()) {
                throw new NoSuchElementException("No more item.");
            }
            int i = StdRandom.uniform(copiedLastIndex + 1);
            Item item = copiedArray[i];
            copiedArray[i] = copiedArray[copiedLastIndex];
            copiedArray[copiedLastIndex--] = null;
            return item;
        }

        // A string representation of the queue.
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (Item item : this.copiedArray) {
                s.append(item + " ");
            }
            return s.toString().substring(0, s.length() - 1);
        }

    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}

