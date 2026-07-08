import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderBaris {
    public static void main(String[] args) {
        String csvFile = "new_students.csv";
        String csvSplitBy = ";";
        int indeks = 0;
        int jumlahBaris = 0;

        System.out.println("NIM, NAMA, UMUR, PRODI");
        System.out.println("------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 1. PENGAMAN: Lewati kalau ada baris kosong/enter di bawah file
                if (line.trim().isEmpty()) {
                    continue;
                }

                indeks++;
                if (indeks > 1) {
                    String[] student = line.split(csvSplitBy);

                    // 2. PENGAMAN: Pastikan datanya minimal terpotong jadi 4 bagian
                    if (student.length >= 4) {
                        System.out.println(student[0] + ", " + student[1] + ", " +
                                           student[2] + ", " + student[3]);
                        jumlahBaris++;
                    } else {
                        // Kalau masuk ke sini, berarti kemungkinan pemisahnya bukan koma
                        System.out.println("Baris dilewati (format kurang lengkap): " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        System.out.println("Jumlah baris data: " + jumlahBaris);
    }
}