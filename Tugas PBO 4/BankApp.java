class Bank {

    String namaBankAsal;

    Bank(String namaBankAsal) {
        this.namaBankAsal = namaBankAsal;
    }

    // Method sukuBunga - bisa di-override oleh child class
    void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // ---- OVERLOADING 1 ----
    // Transfer ke rekening lain (dalam bank yang sama)
    void transferUang(int jumlah, String rekeningTujuan) {
        int biaya = hitungBiaya(namaBankAsal, namaBankAsal);
        System.out.println("--- Transfer Sesama Bank ---");
        System.out.println("Dari Bank     : " + namaBankAsal);
        System.out.println("Ke Rekening   : " + rekeningTujuan);
        System.out.println("Jumlah        : Rp " + jumlah);
        System.out.println("Biaya Transfer: Rp " + biaya);
        System.out.println("Total Bayar   : Rp " + (jumlah + biaya));
        System.out.println("Status        : Transfer berhasil!");
        System.out.println("------------------------------------");
    }

    // ---- OVERLOADING 2 ----
    // Transfer ke rekening lain di bank berbeda
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biaya = hitungBiaya(namaBankAsal, bankTujuan);
        System.out.println("--- Transfer Beda Bank ---");
        System.out.println("Dari Bank     : " + namaBankAsal);
        System.out.println("Ke Bank       : " + bankTujuan);
        System.out.println("Ke Rekening   : " + rekeningTujuan);
        System.out.println("Jumlah        : Rp " + jumlah);
        System.out.println("Biaya Transfer: Rp " + biaya);
        System.out.println("Total Bayar   : Rp " + (jumlah + biaya));
        System.out.println("Status        : Transfer berhasil!");
        System.out.println("------------------------------------");
    }

    // ---- OVERLOADING 3 ----
    // Transfer dengan tambahan berita / keterangan
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biaya = hitungBiaya(namaBankAsal, bankTujuan);
        System.out.println("--- Transfer dengan Berita ---");
        System.out.println("Dari Bank     : " + namaBankAsal);
        System.out.println("Ke Bank       : " + bankTujuan);
        System.out.println("Ke Rekening   : " + rekeningTujuan);
        System.out.println("Jumlah        : Rp " + jumlah);
        System.out.println("Berita        : " + berita);
        System.out.println("Biaya Transfer: Rp " + biaya);
        System.out.println("Total Bayar   : Rp " + (jumlah + biaya));
        System.out.println("Status        : Transfer berhasil!");
        System.out.println("------------------------------------");
    }

    // ---- BONUS: Hitung biaya transfer berdasarkan bank tujuan ----
    // Jika sesama bank -> gratis, beda bank -> Rp 6500
    int hitungBiaya(String asal, String tujuan) {
        if (asal.equalsIgnoreCase(tujuan)) {
            return 0; // sesama bank gratis
        } else {
            return 6500; // beda bank kena biaya
        }
    }
}


// ---------- Class BankBNI (Child dari Bank) ----------
class BankBNI extends Bank {

    BankBNI() {
        super("BNI");
    }

    // Override sukuBunga - suku bunga BNI beda dari parent
    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga BNI adalah 4%");
    }

    // Override transferUang(jumlah, rekening, bank) - bankTujuan otomatis "BNI"
    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // bankTujuan dipaksa jadi "BNI" sesuai soal
        String tujuanFix = "BNI";
        int biaya = hitungBiaya(namaBankAsal, tujuanFix);

        System.out.println("--- Transfer via BNI ---");
        System.out.println("Dari Bank     : " + namaBankAsal);
        System.out.println("Ke Bank       : " + tujuanFix);
        System.out.println("Ke Rekening   : " + rekeningTujuan);
        System.out.println("Jumlah        : Rp " + jumlah);
        System.out.println("Biaya Transfer: Rp " + biaya);
        System.out.println("Total Bayar   : Rp " + (jumlah + biaya));
        System.out.println("Status        : Transfer BNI berhasil!");
        System.out.println("------------------------------------");
    }
}


// ---------- Class BankBCA (Child dari Bank) ----------
class BankBCA extends Bank {

    BankBCA() {
        super("BCA");
    }

    // Override sukuBunga - suku bunga BCA beda dari parent
    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga BCA adalah 4.5%");
    }

    // Override transferUang(jumlah, rekening, bank) - bankTujuan otomatis "BCA"
    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        // bankTujuan dipaksa jadi "BCA" sesuai soal
        String tujuanFix = "BCA";
        int biaya = hitungBiaya(namaBankAsal, tujuanFix);

        System.out.println("--- Transfer via BCA ---");
        System.out.println("Dari Bank     : " + namaBankAsal);
        System.out.println("Ke Bank       : " + tujuanFix);
        System.out.println("Ke Rekening   : " + rekeningTujuan);
        System.out.println("Jumlah        : Rp " + jumlah);
        System.out.println("Biaya Transfer: Rp " + biaya);
        System.out.println("Total Bayar   : Rp " + (jumlah + biaya));
        System.out.println("Status        : Transfer BCA berhasil!");
        System.out.println("------------------------------------");
    }
}


// ---------- Class Utama untuk Testing ----------
public class BankApp {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("       SIMULASI TRANSAKSI PERBANKAN         ");
        System.out.println("============================================\n");

        // Buat objek dari masing-masing bank
        Bank bankUmum = new Bank("Mandiri");
        BankBNI bni = new BankBNI();
        BankBCA bca = new BankBCA();


        // ==============================
        // Uji Suku Bunga (Overriding)
        // ==============================
        System.out.println("=== Suku Bunga Masing-Masing Bank ===");
        bankUmum.sukuBunga();  // output: 3% (dari parent)
        bni.sukuBunga();       // output: 4% (override BNI)
        bca.sukuBunga();       // output: 4.5% (override BCA)
        System.out.println();


        // ==============================
        // Uji Overloading - Bank Umum
        // ==============================
        System.out.println("=== TEST METHOD OVERLOADING (Bank Mandiri) ===\n");

        // Overloading 1 - sesama bank
        bankUmum.transferUang(500000, "1234567890");

        // Overloading 2 - beda bank
        bankUmum.transferUang(1000000, "0987654321", "BNI");

        // Overloading 3 - dengan berita
        bankUmum.transferUang(750000, "1122334455", "BCA", "Bayar hutang bulan lalu");


        // ==============================
        // Uji Overriding - BNI & BCA
        // ==============================
        System.out.println("=== TEST METHOD OVERRIDING ===\n");

        // BNI - bankTujuan otomatis jadi "BNI"
        bni.transferUang(2000000, "5566778899", "BRI"); // meski diisi "BRI", tetap jadi "BNI"

        // BCA - bankTujuan otomatis jadi "BCA"
        bca.transferUang(3000000, "9988776655", "Mandiri"); // meski diisi "Mandiri", tetap jadi "BCA"


        // ==============================
        // Uji Bonus - Hitung Biaya Transfer
        // ==============================
        System.out.println("=== BONUS: Cek Biaya Transfer ===");
        System.out.println("Biaya transfer sesama BNI  : Rp " + bni.hitungBiaya("BNI", "BNI"));
        System.out.println("Biaya transfer BNI ke BCA  : Rp " + bni.hitungBiaya("BNI", "BCA"));
        System.out.println("Biaya transfer BCA ke BNI  : Rp " + bca.hitungBiaya("BCA", "BNI"));
        System.out.println("Biaya transfer sesama BCA  : Rp " + bca.hitungBiaya("BCA", "BCA"));
    }
}