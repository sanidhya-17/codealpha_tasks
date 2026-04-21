import java.util.Scanner;

public class StudentGradeTracker {

    public static String getGrade(double marks) {
        if (marks >= 90) return "A";
        else if (marks >= 80) return "B";
        else if (marks >= 70) return "C";
        else if (marks >= 60) return "D";
        else return "F";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        String[] names = new String[n];
        double[] marks = new double[n];

        for (int i = 0; i < n; i++) {
            sc.nextLine(); 

            System.out.print("\nEnter name of student " + (i + 1) + ": ");
            names[i] = sc.nextLine();

            System.out.print("Enter marks: ");
            marks[i] = sc.nextDouble();
        }

        double total = 0;
        double highest = marks[0];
        double lowest = marks[0];

        for (int i = 0; i < n; i++) {
            total += marks[i];

            if (marks[i] > highest) highest = marks[i];
            if (marks[i] < lowest) lowest = marks[i];
        }

        double average = total / n;

        System.out.println("\n===== Student Report =====");

        for (int i = 0; i < n; i++) {
            System.out.println("Name: " + names[i] +
                               ", Marks: " + marks[i] +
                               ", Grade: " + getGrade(marks[i]));
        }

        System.out.println("\nSummary:");
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);

        sc.close();
    }
}