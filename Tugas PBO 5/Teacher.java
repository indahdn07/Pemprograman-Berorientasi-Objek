public class Teacher extends Person {
    private int numCourses = 0;
    private String[] courses = new String[10];

    public Teacher(String name, String address) {
        super(name, address);
    }

    public boolean addCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equals(course)) {
                return false; // udah ada
            }
        }
        courses[numCourses] = course;
        numCourses++;
        return true;
    }

    public boolean removeCourse(String course) {
        int pos = -1;
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equals(course)) {
                pos = i;
            }
        }

        if (pos == -1) return false; // ga ketemu

        for (int i = pos; i < numCourses - 1; i++) {
            courses[i] = courses[i+1];
        }
        numCourses--;
        return true;
    }

    public void printCourses() {
        for (int i = 0; i < numCourses; i++) {
            System.out.println("- " + courses[i]);
        }
    }

    public String toString() {
        return "Teacher: " + getName() + "(" + getAddress() + ")";
    }
}