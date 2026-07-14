package main;

import java.util.List;
import model.Car;
import model.User;
import model.Vehicle;
import service.LayananKendaraan;
import service.Parking;
import util.InputHelper;
import util.KendaraanNotFoundException;

public class Main {

    static LayananKendaraan layananKendaraan = new LayananKendaraan();
    static Parking parking = new Parking();

    public static void main(String[] args) {

        User admin = new User("admin", "admin123");

        System.out.println("   APLIKASI MANAJEMEN PARKIR KENDARAAN (CLI)");

        if (!login(admin)) {
            System.out.println("Username/password salah. Program dihentikan.");
            return;
        }

        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n===== MENU UTAMA =======");
            System.out.println("1. Menu Kendaraan");
            System.out.println("2. Menu Parkir");
            System.out.println("3. Menu Laporan");
            System.out.println("0. Keluar Program");

            int pilihan = InputHelper.getInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    menuKendaraan();
                    break;
                case 2:
                    menuParkir();
                    break;
                case 3:
                    menuLaporan();
                    break;
                case 0:
                    lanjut = false;
                    System.out.println("Program selesai, terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak ada, coba lagi.");
            }
        }
    }

    // LOGIN
    static boolean login(User user) {
        System.out.println("\n-- LOGIN --");
        String username = InputHelper.getString("Username: ");
        String password = InputHelper.getString("Password: ");

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            System.out.println("Login berhasil. Selamat datang, " + username + "!");
            return true;
        }
        return false;
    }

    // MENU KENDARAAN
    static void menuKendaraan() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n---- MENU KENDARAAN ----");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Lihat Data Kendaraan");
            System.out.println("0. Kembali");

            int pilihan = InputHelper.getInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahKendaraan();
                    break;
                case 2:
                    lihatDataKendaraan();
                    break;
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Menu tidak ada, coba lagi.");
            }
        }
    }

    static void tambahKendaraan() {
        String nomorPolisi = InputHelper.getString("Nomor Polisi     : ");
        String jenis = InputHelper.getString("Jenis (Mobil/Motor): ");
        String pemilik = InputHelper.getString("Nama Pemilik     : ");

        Vehicle kendaraan;

        if (jenis.equalsIgnoreCase("Mobil")) {
            int jumlahPintu = InputHelper.getInt("Jumlah Pintu     : ");
            kendaraan = new Car(nomorPolisi, pemilik, jumlahPintu);
        } else {
            kendaraan = new Vehicle(nomorPolisi, jenis, pemilik);
        }

        layananKendaraan.tambahKendaraan(kendaraan);
    }

    static void lihatDataKendaraan() {
        List<Vehicle> daftarKendaraan = layananKendaraan.lihatSemuaKendaraan();

        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada data kendaraan.");
            return;
        }

        System.out.println("\n=== DAFTAR KENDARAAN ===");
        for (Vehicle v : daftarKendaraan) {
            v.displayInfo(); // kalau objeknya Car, otomatis tampil jumlah pintu juga
            System.out.println("-----------------------------");
        }
    }

    // MENU PARKIR
    static void menuParkir() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n---- MENU PARKIR ----");
            System.out.println("1. Kendaraan Masuk");
            System.out.println("2. Kendaraan Keluar");
            System.out.println("0. Kembali");

            int pilihan = InputHelper.getInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    prosesKendaraanMasuk();
                    break;
                case 2:
                    prosesKendaraanKeluar();
                    break;
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Menu tidak ada, coba lagi.");
            }
        }
    }

    static void prosesKendaraanMasuk() {
        String nomorPolisi = InputHelper.getString("Masukkan Nomor Polisi: ");

        try {
            Vehicle kendaraan = layananKendaraan.cariByNomorPolisi(nomorPolisi);
            parking.kendaraanMasuk(kendaraan.getIdKendaraan());
        } catch (KendaraanNotFoundException e) {
            // exception handling kalau kendaraan belum terdaftar
            System.out.println("Error: " + e.getMessage());
            System.out.println("Silakan tambahkan kendaraan ini dulu di Menu Kendaraan.");
        }
    }

    static void prosesKendaraanKeluar() {
        int idParkir = InputHelper.getInt("Masukkan ID Parkir: ");
        parking.kendaraanKeluar(idParkir);
    }

    // MENU LAPORAN
    static void menuLaporan() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n---- MENU LAPORAN ----");
            System.out.println("1. Lihat Kendaraan yang Masih Parkir");
            System.out.println("2. Lihat Total Kendaraan di Area Parkir");
            System.out.println("0. Kembali");

            int pilihan = InputHelper.getInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    parking.lihatKendaraanMasihParkir();
                    break;
                case 2:
                    int total = parking.hitungJumlahParkir();
                    System.out.println("Total kendaraan yang sedang parkir saat ini: " + total);
                    break;
                case 0:
                    kembali = true;
                    break;
                default:
                    System.out.println("Menu tidak ada, coba lagi.");
            }
        }
    }
}
