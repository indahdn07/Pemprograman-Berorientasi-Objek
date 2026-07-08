import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriterInput {
    public static void main(String[] args) {
        String csvFile = "new_students.csv";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah data yang ingin ditambahkan: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("NIM,NAMA,UMUR,PRODI");
            bw.newLine();

            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Data ke-" + (i + 1) + " ---");

                System.out.print("NIM    : ");
                String nim = scanner.nextLine().trim();

                System.out.print("Nama   : ");
                String nama = scanner.nextLine().trim();

                System.out.print("Umur   : ");
                String umur = scanner.nextLine().trim();

                System.out.print("Prodi  : ");
                String prodi = scanner.nextLine().trim();

                String line = nim + ";" + nama + ";" + umur + ";" + prodi;
                bw.write(line);
                bw.newLine();
            }

            System.out.println("\nData berhasil disimpan ke: " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}

