package main;

import java.util.Scanner;
import model.MenuItem;
import model.Pelanggan;
import service.MenuService;
import service.OrderService;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static MenuService menuService = new MenuService();
    static OrderService orderService = new OrderService();
    static Pelanggan pelanggan = null;

    public static void main(String[] args) {

        System.out.println("    APLIKASI PEMESANAN MAKANAN ONLINE");

        inputPelanggan();

        boolean jalan = true;
        while (jalan) {

            System.out.println("  Halo, " + pelanggan.getNama() + "!");
            System.out.println("  1. Lihat Menu");
            System.out.println("  2. Pesan Makanan");
            System.out.println("  3. Lihat Keranjang");
            System.out.println("  4. Checkout");
            System.out.println("  5. Riwayat Order");
            System.out.println("  6. Ganti Pelanggan");
            System.out.println("  0. Keluar");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                menuService.tampilkanMenu();

            } else if (input.equals("2")) {
                pesan();

            } else if (input.equals("3")) {
                orderService.lihatKeranjang();

            } else if (input.equals("4")) {
                doCheckout();

            } else if (input.equals("5")) {
                orderService.lihatRiwayat();

            } else if (input.equals("6")) {
                inputPelanggan();

            } else if (input.equals("0")) {
                System.out.println("  Terima kasih! :)");
                jalan = false;

            } else {
                System.out.println("  pilihan tidak valid");
            }
        }

        scanner.close();
    }

    static void inputPelanggan() {
        System.out.println("\n-- Data Pelanggan --");
        System.out.print("  Nama   : ");
        String nama = scanner.nextLine();

        System.out.print("  No HP  : ");
        String hp = scanner.nextLine();

        System.out.print("  Alamat : ");
        String alamat = scanner.nextLine();

        String id = "P" + System.currentTimeMillis();
        pelanggan = new Pelanggan(id, nama, hp, alamat);

        System.out.println("  >> data tersimpan!");
    }

    static void pesan() {
        menuService.tampilkanMenu();

        System.out.print("  Masukkan ID menu (contoh M01) atau 0 batal: ");
        String idMenu = scanner.nextLine().trim();

        if (idMenu.equals("0")) {
            return;
        }

        MenuItem item = menuService.cariById(idMenu);

        if (item == null) {
            System.out.println("  menu tidak ditemukan!");
            return;
        }

        System.out.println("  >> " + item.getNama() + " - Rp" + item.getHarga());
        System.out.print("  Jumlah: ");

        String inputJumlah = scanner.nextLine();

        try {
            int jumlah = Integer.parseInt(inputJumlah);
            if (jumlah <= 0) {
                System.out.println("  jumlah harus lebih dari 0");
                return;
            }
            orderService.tambahItem(item, jumlah);
        } catch (NumberFormatException e) {
            System.out.println("  input jumlah salah");
        }
    }

    static void doCheckout() {
        if (orderService.keranjangKosong()) {
            System.out.println("  keranjang masih kosong!");
            return;
        }

        orderService.lihatKeranjang();

        System.out.printf("%n  Total belanja: Rp%.0f + ongkir Rp5000%n", orderService.hitungTotal());

        System.out.println("\n  Metode bayar:");
        System.out.println("  1. Cash");
        System.out.println("  2. Transfer");
        System.out.println("  3. GoPay / OVO");
        System.out.print("  Pilih: ");

        String pil = scanner.nextLine();
        String metode = "";

        if (pil.equals("1")) {
            metode = "Cash";
        } else if (pil.equals("2")) {
            metode = "Transfer";
        } else if (pil.equals("3")) {
            metode = "GoPay/OVO";
        } else {
            System.out.println("  pilihan tidak valid");
            return;
        }

        System.out.print("\n  Yakin checkout? (y/n): ");
        String konfirm = scanner.nextLine();

        if (konfirm.equalsIgnoreCase("y")) {
            orderService.checkout(pelanggan, metode);
        } else {
            System.out.println("  checkout dibatalin");
        }
    }
}
