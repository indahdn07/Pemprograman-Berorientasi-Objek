public class BankApp {
    public static void main(String[] args) {
        // Objek bank
        Bank mandiri = new Bank("Mandiri");
        BankBNI bni = new BankBNI();
        BankBCA bca = new BankBCA();

        // Cek bunga (Overriding)
        System.out.println("INFO BUNGA BANK:");
        mandiri.sukuBunga();
        bni.sukuBunga();
        bca.sukuBunga();
        System.out.println();

        // Tes Overloading di Mandiri
        System.out.println("UJI COBA TRANSFER MANDIRI:");
        mandiri.transferUang(200000, "999111");
        mandiri.transferUang(500000, "888222", "BNI");
        mandiri.transferUang(100000, "777333", "BCA", "Uang Kas");

        // Tes Overriding BNI & BCA
        System.out.println("\nUJI COBA OVERRIDING:");
        bni.transferUang(1000000, "555666", "Mandiri"); // akan otomatis jadi BNI
        bca.transferUang(1500000, "444333", "BRI");     // akan otomatis jadi BCA
    }
}