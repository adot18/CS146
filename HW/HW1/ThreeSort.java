// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]); //4
        int b = Integer.parseInt((args[1])); //3
        int c = Integer.parseInt((args[2])); //5


        int min = Math.min(a, b);
        int min2 = Math.min(min, c); //Smallest number of the 3

        int max = Math.max(a, b);
        int max2 = Math.max(max, c); //Largest number of the 3

        int middle = 0;

        if ((a != min2) && (b != min2)) {
            middle = c;
        } else if ((a != min2) && (c != min2)) {
            middle = b;
        }
        middle = a;


        StdOut.println(min2 + " " + middle + " " + max2);


    }
}
