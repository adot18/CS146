// Student.java: Print the first name, last name, and an ID in special sorted formats: (1-unsorted, 2-sorted alphabetically by first name, 3-and sorted by id number).

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Student implements Comparable<Student> {
    private final String name;
    private final String lastName;
    private final int id;

    public Student(String lastName, String name, int id) {
        this.lastName = lastName;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        //FORMAT SHOULD BE FIRST_NAME LAST_NAME ID
        return name + " " + lastName + " " + id;
    }

    public int compareTo(Student st) {
        //COMPARE BY FIRST NAME
        return Integer.compare(this.name.compareTo(st.name), 0);
    }

    public static class lastNameOrder implements Comparator<Student> {
        public int compare(Student thisStudent, Student otherStudent) {
            //SORT BY ID
            return Integer.compare(thisStudent.id, otherStudent.id);
        }
    }

    public static void main(String arg[]) {
        Student[] student = new Student[6];
        student[0] = new Student("Tolaymat", "Samy", 2);
        student[1] = new Student("Liu", "Linxin", 6);
        student[2] = new Student("Kim", "Jung S.", 9);
        student[3] = new Student("Le", "Kimberly N.", 1);
        student[4] = new Student("Gonzalez", "Robert A.", 8);
        student[5] = new Student("Roscoe", "Sarah R.", 4);

        //CHANGE FOR LOOP TO ENHANCED LOOP
        StdOut.println("");
        for (Student x : student)
            System.out.println("Unsorted: " + x);

        StdOut.println("");
        Arrays.sort(student);
        for (Student value : student)
            System.out.println("Sorted by first name: " + value);

        StdOut.println("");
        Arrays.sort(student, new lastNameOrder());
        for (Student value : student)
            System.out.println("Sorted by id: " + value);
    }
}



