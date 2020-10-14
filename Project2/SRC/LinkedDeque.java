import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    //CODE HERE

    private Node head;
    private Node tail;
    private int size;

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // Construct an empty deque.
    public LinkedDeque() {
        //CODE HERE
        this.head = null;
        this.tail = null;
        this.size = 0;

    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        //CODE HERE
        return size == 0;
    }

    // The number of items on the deque.
    public int size() {
        //CODE HERE
        return this.size;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        //CODE HERE

        if (item == null) throw new NullPointerException();
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        head.prev = null;
        if (isEmpty())
            tail = head;
        else
            oldHead.prev = head;
        size++;

    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        //CODE HERE
        if (item == null) throw new java.lang.NullPointerException();
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.next = null;
        tail.prev = oldTail;
        if (isEmpty())
            head = tail;
        else
            oldTail.next = tail;
        size++;
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        //CODE HERE
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = head.item;
        head = head.next;
        size--;
        if (isEmpty())
            tail = head;
        else
            head.prev = null;
        return item;

    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        //CODE HERE
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = tail.item;
        tail = tail.prev;
        size--;
        if (isEmpty())
            head = tail;
        else
            tail.next = null;
        return item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        //CODE HERE
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        //CODE HERE
        private Node current;


        DequeIterator() {
            //CODE HERE
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
