import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap and false
    // otherwise. Remember to index from 1.
    private static boolean maxOrderedHeap(Comparable[] a) {
        int N = a.length;

        // For each node 1 <= i <= N / 2, if i is less than
        // either of its children, return false, meaning a[]
        // does not represent a maximum-ordered heap.
        // Otherwise, return true.
        for (int i = 1; i <= ((N - 2) / 2); i++) {
            if (2 * i + 2 < N && less(a[i], a[2 * i + 1])) {
                return false;
            }
            if (2 * i + 2 < N && less(a[i], a[2 * i + 2])) {
                return false;
            }
        }//for end
        return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}
