public class Bank {
    public String bankAsal;

    public Bank(String bankAsal) {
        this.bankAsal = bankAsal;
    }

    void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // Variasi 1: Sesama bank
    void transferUang(int jumlah, String rekTujuan) {
        int biaya = cekBiaya(bankAsal, bankAsal);
        System.out.println("--- Transfer Sesama " + bankAsal + " ---");
        System.out.println("Ke Rekening: " + rekTujuan);
        System.out.println("Nominal     : Rp " + jumlah);
        System.out.println("Admin       : Rp " + biaya);
        System.out.println("Total       : Rp " + (jumlah + biaya));
        System.out.println("Status      : Berhasil!");
        System.out.println("---------------------------");
    }

    // Variasi 2: Beda bank
    void transferUang(int jumlah, String rekTujuan, String bankTujuan) {
        int biaya = cekBiaya(bankAsal, bankTujuan);
        System.out.println("--- Transfer antar Bank ---");
        System.out.println("Dari " + bankAsal + " ke " + bankTujuan);
        System.out.println("No Rekening: " + rekTujuan);
        System.out.println("Nominal     : Rp " + jumlah);
        System.out.println("Admin       : Rp " + biaya);
        System.out.println("Total       : Rp " + (jumlah + biaya));
        System.out.println("Status      : Berhasil!");
        System.out.println("---------------------------");
    }

    // Variasi 3: Pakai berita
    void transferUang(int jumlah, String rekTujuan, String bankTujuan, String pesan) {
        int biaya = cekBiaya(bankAsal, bankTujuan);
        System.out.println("--- Transfer (Ada Berita) ---");
        System.out.println("Tujuan      : " + bankTujuan + " (" + rekTujuan + ")");
        System.out.println("Pesan       : " + pesan);
        System.out.println("Nominal     : Rp " + jumlah);
        System.out.println("Admin       : Rp " + biaya);
        System.out.println("Total       : Rp " + (jumlah + biaya));
        System.out.println("Status      : Berhasil!");
        System.out.println("---------------------------");
    }

    // Bonus hitung biaya
    public int cekBiaya(String dr, String ke) {
        if (dr.equalsIgnoreCase(ke)) {
            return 0;
        } else {
            return 6500;
        }
    }
}