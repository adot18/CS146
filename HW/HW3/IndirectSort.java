import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class IndirectSort {
    // Is v < w?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Exchange a[i] and a[j] (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Indirectly sort a[] using insertion sort, ie, not by rearranging a[],
    // but by returning an array perm[] such that perm[i] is the index of
    // the ith smallest entry in a[].
    public static int[] indexSort(Comparable[] a) {
        int size = a.length;
        int[] perm = new int[size];

        for (int k = 0; k < a.length; k++) {
            perm[k] = k;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length && less(a[perm[j]], a[perm[j + 1]]); j++) {
                exch(perm, j, j + 1);
            }

        }
        return perm;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int[] perm = indexSort(a);
        int i;
        for (i = 0; i < perm.length - 1; i++) {
            StdOut.print(a[perm[i]] + " ");
        }
        StdOut.println(a[perm[i]]);
    }
}
