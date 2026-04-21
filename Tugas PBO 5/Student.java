public class Student extends Person {
    private int numCourses = 0;
    private String[] courses = new String[10];
    private int[] grades = new int[10];

    public Student(String name, String address) {
        super(name, address);
    }

    public void addCourseGrade(String course, int grade) {
        courses[numCourses] = course;
        grades[numCourses] = grade;
        numCourses++;
    }

    public void printGrades() {
        for (int i = 0; i < numCourses; i++) {
            System.out.println(courses[i] + " : " + grades[i]);
        }
    }

    public double getAverageGrade() {
        if (numCourses == 0) return 0; // Biar ga error kalau matkul kosong
        int total = 0;
        for (int i = 0; i < numCourses; i++) {
            total += grades[i];
        }
        return (double) total / numCourses;
    }

    public String toString() {
        return "Student: " + getName() + "(" + getAddress() + ")";
    }
}