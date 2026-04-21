import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nama dosen: ");
        String nd = sc.nextLine();
        System.out.print("Alamat dosen: ");
        String ad = sc.nextLine();

        Teacher t = new Teacher(nd, ad);

        System.out.print("Jumlah matkul yang diampu: ");
        int jm = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < jm; i++) {
            System.out.print("Matkul " + (i+1) + ": ");
            String mk = sc.nextLine();
            boolean ok = t.addCourse(mk);
            if (!ok) System.out.println("Matkul sudah ada!");
        }

        System.out.println("\n" + t);
        System.out.println("Matkul yang diampu:");
        t.printCourses();

        System.out.print("\nHapus matkul: ");
        String del = sc.nextLine();
        if (t.removeCourse(del)) {
            System.out.println("Berhasil dihapus");
        } else {
            System.out.println("Matkul tidak ditemukan");
        }

        System.out.println("\nMatkul setelah dihapus:");
        t.printCourses();

        System.out.println("---------------------------");

        System.out.print("\nNama mahasiswa: ");
        String nm = sc.nextLine();
        System.out.print("Alamat mahasiswa: ");
        String am = sc.nextLine();

        Student s = new Student(nm, am);

        System.out.print("Jumlah matkul: ");
        int jmk = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < jmk; i++) {
            System.out.print("Matkul " + (i+1) + ": ");
            String mk = sc.nextLine();
            System.out.print("Nilai: ");
            int n = Integer.parseInt(sc.nextLine());
            s.addCourseGrade(mk, n);
        }

        System.out.println("\n" + s);
        System.out.println("Daftar Nilai:");
        s.printGrades();
        System.out.println("Rata-rata: " + s.getAverageGrade());
        
        sc.close();
    }
}