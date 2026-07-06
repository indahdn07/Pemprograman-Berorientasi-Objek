import java.sql.*;
import java.util.Scanner;

public class TokoRetailApp {

    static Connection conn;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        conn = DriverManager.getConnection("jdbc:sqlite:toko_retail.db");

        // bikin tabel kalo belom ada
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS barang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kode TEXT," +
                "nama_barang TEXT," +
                "harga INTEGER," +
                "stok INTEGER)");

        // cek udah ada data belom, kalo belom isi data default
        ResultSet cek = stmt.executeQuery("SELECT COUNT(*) FROM barang");
        cek.next();
        if (cek.getInt(1) == 0) {
            stmt.execute("INSERT INTO barang (kode, nama_barang, harga, stok) VALUES ('B001','Roti Tawar',10000,100)");
            stmt.execute("INSERT INTO barang (kode, nama_barang, harga, stok) VALUES ('B002','Malkist',2000,100)");
            stmt.execute("INSERT INTO barang (kode, nama_barang, harga, stok) VALUES ('B003','Kopi Kapal Api',3000,100)");
        }

        boolean lanjut = true;
        while (lanjut) {
            System.out.println("");
            System.out.println("=========================");
            System.out.println("     MENU TOKO RETAIL");
            System.out.println("=========================");
            System.out.println("1. Tampil Semua Data");
            System.out.println("2. Tambah Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Hapus Data");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");

            String pil = input.nextLine();

            if (pil.equals("1")) {
                tampil();
            } else if (pil.equals("2")) {
                tambah();
            } else if (pil.equals("3")) {
                cari();
            } else if (pil.equals("4")) {
                ubah();
            } else if (pil.equals("5")) {
                hapus();
            } else if (pil.equals("0")) {
                System.out.println("Terima kasih!");
                lanjut = false;
            } else {
                System.out.println("Pilihan salah, coba lagi.");
            }
        }

        conn.close();
    }

    static void tampil() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM barang");

        System.out.println("");
        System.out.println("DAFTAR BARANG TOKO RETAIL");
        System.out.println("No | Kode | Nama Barang     | Harga  | Stok");

        int no = 1;
        int total = 0;
        while (rs.next()) {
            System.out.println(no + " | " + rs.getString("kode") + " | " + rs.getString("nama_barang")
                    + " | " + rs.getInt("harga") + " | " + rs.getInt("stok"));
            no++;
            total++;
        }
        System.out.println("Total barang : " + total);
    }

    static void tambah() throws SQLException {
        System.out.print("Kode Barang : ");
        String kode = input.nextLine();
        System.out.print("Nama Barang : ");
        String nama = input.nextLine();
        System.out.print("Harga : ");
        int harga = Integer.parseInt(input.nextLine());
        System.out.print("Stok : ");
        int stok = Integer.parseInt(input.nextLine());

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?,?,?,?)");
        ps.setString(1, kode);
        ps.setString(2, nama);
        ps.setInt(3, harga);
        ps.setInt(4, stok);
        ps.executeUpdate();

        System.out.println("Data berhasil ditambah.");
    }

    static void cari() throws SQLException {
        System.out.print("Cari (kode/nama) : ");
        String kata = input.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM barang WHERE kode LIKE ? OR nama_barang LIKE ?");
        ps.setString(1, "%" + kata + "%");
        ps.setString(2, "%" + kata + "%");
        ResultSet rs = ps.executeQuery();

        boolean ketemu = false;
        while (rs.next()) {
            ketemu = true;
            System.out.println(rs.getString("kode") + " | " + rs.getString("nama_barang")
                    + " | " + rs.getInt("harga") + " | " + rs.getInt("stok"));
        }

        if (!ketemu) {
            System.out.println("Data tidak ditemukan.");
        }
    }

    static void ubah() throws SQLException {
        System.out.print("Kode barang yang mau diubah : ");
        String kode = input.nextLine();

        PreparedStatement cekPs = conn.prepareStatement("SELECT * FROM barang WHERE kode = ?");
        cekPs.setString(1, kode);
        ResultSet rs = cekPs.executeQuery();

        if (!rs.next()) {
            System.out.println("Data tidak ada.");
            return;
        }

        System.out.println("Data lama : " + rs.getString("nama_barang") + ", " + rs.getInt("harga") + ", " + rs.getInt("stok"));

        System.out.print("Nama baru : ");
        String nama = input.nextLine();
        System.out.print("Harga baru : ");
        int harga = Integer.parseInt(input.nextLine());
        System.out.print("Stok baru : ");
        int stok = Integer.parseInt(input.nextLine());

        PreparedStatement ps = conn.prepareStatement(
                "UPDATE barang SET nama_barang=?, harga=?, stok=? WHERE kode=?");
        ps.setString(1, nama);
        ps.setInt(2, harga);
        ps.setInt(3, stok);
        ps.setString(4, kode);
        ps.executeUpdate();

        System.out.println("Data berhasil diubah.");
    }

    static void hapus() throws SQLException {
        System.out.print("Kode barang yang mau dihapus : ");
        String kode = input.nextLine();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM barang WHERE kode = ?");
        ps.setString(1, kode);
        int hasil = ps.executeUpdate();

        if (hasil > 0) {
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }
}
