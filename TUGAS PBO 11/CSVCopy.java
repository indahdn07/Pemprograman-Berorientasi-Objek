import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        String sourceFile = "students.csv";
        String destFile   = "students_copy.csv";
        int jumlahBaris = 0;

        try (
            BufferedReader br = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(destFile))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                jumlahBaris++;
            }

            System.out.println("Berhasil menyalin file CSV!");
            System.out.println("Sumber  : " + sourceFile);
            System.out.println("Tujuan  : " + destFile);
            System.out.println("Total baris disalin: " + jumlahBaris);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}