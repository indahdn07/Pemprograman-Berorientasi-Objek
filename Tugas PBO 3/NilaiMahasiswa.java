import java.util.Scanner;

// Class untuk menyimpan data mahasiswa
class Mahasiswa {
    String nim;
    String nama;
    int nilai;
    String grade;

    // Constructor
    Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.grade = tentukanGrade(nilai);
    }

    // Method untuk menentukan grade berdasarkan nilai
    String tentukanGrade(int nilai) {
        if (nilai >= 80 && nilai <= 100) {
            return "A";
        } else if (nilai >= 70 && nilai <= 79) {
            return "B";
        } else if (nilai >= 60 && nilai <= 69) {
            return "C";
        } else if (nilai >= 50 && nilai <= 59) {
            return "D";
        } else if (nilai < 50 && nilai >= 0) {
            return "E";
        } else {
            return "SALAH";
        }
    }

    // Method untuk cek apakah lulus atau tidak
    boolean isLulus() {
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }

    // Method untuk menampilkan data mahasiswa
    void tampilkan() {
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + nama);
        System.out.println("Nilai : " + nilai);
        System.out.println("Grade : " + grade);
        System.out.println("========================================");
    }
}

public class NilaiMahasiswa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = sc.nextInt();
        sc.nextLine(); // buang newline sisa

        Mahasiswa[] listMhs = new Mahasiswa[jumlah];

        // Input data mahasiswa satu per satu
        for (int i = 0; i < jumlah; i++) {
            System.out.println("\n--- Input Mahasiswa ke-" + (i + 1) + " ---");

            System.out.print("NIM   : ");
            String nim = sc.nextLine();

            System.out.print("Nama  : ");
            String nama = sc.nextLine();

            int nilai = 0;
            boolean inputValid = false;

            // Validasi input nilai
            while (!inputValid) {
                System.out.print("Nilai : ");
                nilai = sc.nextInt();
                sc.nextLine();

                if (nilai < 0 || nilai > 100) {
                    System.out.println("Input nilai anda salah! Masukkan nilai antara 0-100.");
                } else {
                    inputValid = true;
                }
            }

            listMhs[i] = new Mahasiswa(nim, nama, nilai);
        }

        // Tampilkan semua data mahasiswa
        System.out.println("\n========================================");
        for (int i = 0; i < jumlah; i++) {
            listMhs[i].tampilkan();
        }

        // Hitung statistik
        int totalNilai = 0;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;

        String namLulus = "";
        String namTidakLulus = "";
        String namA = "";
        String namB = "";
        String namC = "";
        String namD = "";
        String namE = "";

        int hitA = 0, hitB = 0, hitC = 0, hitD = 0, hitE = 0;

        for (int i = 0; i < jumlah; i++) {
            Mahasiswa m = listMhs[i];
            totalNilai += m.nilai;

            if (m.isLulus()) {
                jumlahLulus++;
                if (namLulus.equals("")) {
                    namLulus = m.nama;
                } else {
                    namLulus += ", " + m.nama;
                }
            } else {
                jumlahTidakLulus++;
                if (namTidakLulus.equals("")) {
                    namTidakLulus = m.nama;
                } else {
                    namTidakLulus += ", " + m.nama;
                }
            }

            // Kelompokkan berdasarkan grade
            switch (m.grade) {
                case "A":
                    hitA++;
                    namA = namA.equals("") ? m.nama : namA + ", " + m.nama;
                    break;
                case "B":
                    hitB++;
                    namB = namB.equals("") ? m.nama : namB + ", " + m.nama;
                    break;
                case "C":
                    hitC++;
                    namC = namC.equals("") ? m.nama : namC + ", " + m.nama;
                    break;
                case "D":
                    hitD++;
                    namD = namD.equals("") ? m.nama : namD + ", " + m.nama;
                    break;
                case "E":
                    hitE++;
                    namE = namE.equals("") ? m.nama : namE + ", " + m.nama;
                    break;
            }
        }

        double rata = (double) totalNilai / jumlah;

        // Buat string rata-rata yang lebih rapi
        // misal 80+79+90+50 / 4 = 74.75
        String rumusRata = "";
        for (int i = 0; i < jumlah; i++) {
            if (i == 0) {
                rumusRata += listMhs[i].nilai;
            } else {
                rumusRata += "+" + listMhs[i].nilai;
            }
        }

        // Tampilkan ringkasan
        System.out.println("Jumlah Mahasiswa          : " + jumlah);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + jumlahLulus + " yaitu " + (namLulus.equals("") ? "-" : namLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus + " yaitu " + (namTidakLulus.equals("") ? "-" : namTidakLulus));

        if (hitA > 0) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + hitA + " yaitu " + namA);
        if (hitB > 0) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + hitB + " yaitu " + namB);
        if (hitC > 0) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + hitC + " yaitu " + namC);
        if (hitD > 0) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + hitD + " yaitu " + namD);
        if (hitE > 0) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + hitE + " yaitu " + namE);

        System.out.printf("Rata-rata nilai mahasiswa adalah : %s / %d = %.2f%n", rumusRata, jumlah, rata);

        sc.close();
    }
}
