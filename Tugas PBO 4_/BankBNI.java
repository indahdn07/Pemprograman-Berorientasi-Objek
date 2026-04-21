public class BankBNI extends Bank {
    public BankBNI() {
        super("BNI");
    }

    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah : 4%");
    }

    @Override
    void transferUang(int jumlah, String rekTujuan, String bankTujuan) {
        // bankTujuan di-override biar tetep BNI sesuai soal
        String ke = "BNI"; 
        int biaya = cekBiaya(this.bankAsal, ke);

        System.out.println(">>> Log Transaksi BNI <<<");
        System.out.println("Transfer ke " + ke + " [" + rekTujuan + "]");
        System.out.println("Jumlah: Rp " + jumlah + " | Biaya: Rp " + biaya);
        System.out.println("Total : Rp " + (jumlah + biaya));
        System.out.println("Status: Transfer BNI Sukses!");
        System.out.println("---------------------------");
    }
}