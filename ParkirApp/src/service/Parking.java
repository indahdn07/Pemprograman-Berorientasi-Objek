package service;

import database.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// class ini yang ngurus proses parkir (masuk, keluar, laporan)
public class Parking {

    // panggil procedure sp_kendaraan_masuk
    public void kendaraanMasuk(int idKendaraan) {
        String sql = "{call sp_kendaraan_masuk(?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idKendaraan);
            cs.execute();

            System.out.println("Kendaraan berhasil dicatat masuk ke area parkir.");

        } catch (SQLException e) {
            System.out.println("Gagal mencatat kendaraan masuk: " + e.getMessage());
        }
    }

    // waktu_keluar diisi disini, nanti trigger yang otomatis ubah status jadi "Keluar"
    public void kendaraanKeluar(int idParkir) {
        String sql = "UPDATE parkir SET waktu_keluar = NOW() WHERE id_parkir = ? AND waktu_keluar IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idParkir);
            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                System.out.println("Kendaraan berhasil keluar dari area parkir.");
            } else {
                System.out.println("Data parkir tidak ditemukan atau kendaraan sudah keluar sebelumnya.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencatat kendaraan keluar: " + e.getMessage());
        }
    }

    // ambil data dari view_kendaraan_parkir
    public void lihatKendaraanMasihParkir() {
        String sql = "SELECT * FROM view_kendaraan_parkir";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n=== DAFTAR KENDARAAN YANG MASIH PARKIR ===");
            boolean adaData = false;

            while (rs.next()) {
                adaData = true;
                System.out.println("ID Parkir       : " + rs.getInt("id_parkir"));
                System.out.println("Nomor Polisi    : " + rs.getString("nomor_polisi"));
                System.out.println("Jenis Kendaraan : " + rs.getString("jenis_kendaraan"));
                System.out.println("Pemilik         : " + rs.getString("pemilik"));
                System.out.println("Waktu Masuk     : " + rs.getTimestamp("waktu_masuk"));
                System.out.println("---------------------------------------");
            }

            if (!adaData) {
                System.out.println("Tidak ada kendaraan yang sedang parkir saat ini.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data kendaraan parkir: " + e.getMessage());
        }
    }

    // panggil function fn_jumlah_kendaraan_parkir
    public int hitungJumlahParkir() {
        String sql = "SELECT fn_jumlah_kendaraan_parkir() AS total";
        int total = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Gagal menghitung jumlah kendaraan parkir: " + e.getMessage());
        }

        return total;
    }
}
