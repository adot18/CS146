// Ramanujan.java: Prints the integers <= N (command-line argument) that can be
// expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.MinPQ;

public class Ramanujan {
    // A data type that encapsulates a pair of numbers (i, j)
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }

    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        MinPQ<Pair> pq = new MinPQ<Pair>();
        for (int i = 1; i < Math.cbrt(N); i++) {
            Pair p = new Pair(i, i + 1);
            pq.insert(p);
        }

        while (!pq.isEmpty()) {
            Pair p1 = pq.delMin();
            if (pq.isEmpty()) {
                break;
            }
            Pair p2 = pq.min();
            if (p1.compareTo(p2) == 0 && p2.sumOfCubes <= N && p1.sumOfCubes <= N) {
                StdOut.println(p1.sumOfCubes + " = " + p1.i + "^3 + " + p1.j + "^3" + " = " + p2.i + "^3 + " + p2.j + "^3");
            } else if (p2.j < Math.cbrt(N)) {
                pq.insert(new Pair(p1.i, p1.j + 1));
            }
        }


    }
}




