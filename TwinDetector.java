import java.io.File;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNum;
    private int age;

    public Student(String name, int rollNum, int age) {
        this.name = name;
        this.rollNum = rollNum;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getRollNum() {
        return rollNum;
    }

    public int getAge() {
        return age;
    }

    public boolean hasSameAge(Student other) {
        return this.age == other.getAge();
    }

    public boolean isTwin(Student other) {
        boolean sameAge = this.hasSameAge(other);
        boolean sameRoll = this.rollNum == other.getRollNum();
        return sameAge && sameRoll;
    }
}

public class TwinDetector {
    public static void main(String[] args) throws Exception {
        Student[] students = new Student[500];
        int count = 0;

        try (Scanner sc = new Scanner(new File("students.txt"), "UTF-16")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 3) {
                    continue;
                }

                String name = parts[0].trim();
                int rollNum = Integer.parseInt(parts[1].trim());
                int age = Integer.parseInt(parts[2].trim());
                students[count] = new Student(name, rollNum, age);
                count++;
            }
        }

        System.out.println("Total entries = " + count);
        System.out.println("\n==============Pairs Detected====================");

        int pairs = 0;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (students[i].isTwin(students[j])) {
                    pairs++;
                    System.out.println("Pair:" + pairs + " Name: " + students[i].getName() + "," + students[j].getName());
                }
            }
        }

        System.out.println("Total number of twins = " + pairs);
    }
}