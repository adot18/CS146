// A data type representing a six-sided die.

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Die implements Comparable<Die> {
    private int value; // face value

    // Roll the die.
    public void roll() {
        // Code Here
        value = StdRandom.uniform(5) + 1;
    }

    // Face value of the die.
    public int value() {
        // Code Here
        return this.value;
    }

    // Does the die have the same face value as that?
    public boolean equals(Die that) {
        // Code Here
        return this.compareTo(that) == 0;

    }

    // A negative integer, zero, or positive integer depending on whether this
    // die's value is less than, equal to, or greater than the that die's value.
    public int compareTo(Die that) {
        // Code Here
        return Integer.compare(this.value, that.value);
    }

    // A string representation of the die giving the current face value.
    public String toString() {
        // Code Here
        if (this.value() == 1) {
            return "   " + "\n" + " * " + "\n" + "   ";
        } else if (this.value() == 2) {
            return "* " + "\n" + "   " + "\n" + "  *";
        } else if (this.value() == 3) {
            return "*  " + "\n" + " * " + "\n" + "  *";
        } else if (this.value() == 4) {
            return "*  *" + "\n" + "   " + "\n" + "*  *";
        } else if (this.value() == 5) {
            return "*  *" + "\n" + " * " + "\n" + "*  *";
        } else {
            return "* * *" + "\n" + "   " + "\n" + "* * *";
        }

    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]); //3
        int y = Integer.parseInt(args[1]); //3
        int z = Integer.parseInt(args[2]); //5
        Die a = new Die();
        a.roll();
        while (a.value() != x) {
            a.roll();
        }
        Die b = new Die();
        b.roll();
        while (b.value() != y) {
            b.roll();
        }
        Die c = new Die();
        c.roll();
        while (c.value() != z) {
            c.roll();
        }
        StdOut.println(a);
        StdOut.println(a.equals(b));
        StdOut.println(b.equals(c));
        StdOut.println(a.compareTo(b));
        StdOut.println(b.compareTo(c));
    }
}

