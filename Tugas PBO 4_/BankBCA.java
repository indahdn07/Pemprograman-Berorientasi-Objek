public class BankBCA extends Bank {
    public BankBCA() {
        super("BCA");
    }

    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%");
    }

    @Override
    void transferUang(int jumlah, String rekTujuan, String bankTujuan) {
        String ke = "BCA";
        int biaya = cekBiaya(this.bankAsal, ke);

        System.out.println(">>> Log Transaksi BCA <<<");
        System.out.println("Transfer ke " + ke + " [" + rekTujuan + "]");
        System.out.println("Jumlah: Rp " + jumlah + " | Biaya: Rp " + biaya);
        System.out.println("Total : Rp " + (jumlah + biaya));
        System.out.println("Status: Transfer BCA Sukses!");
        System.out.println("---------------------------");
    }
}