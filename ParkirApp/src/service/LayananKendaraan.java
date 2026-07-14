package service;

import database.DatabaseConnection;
import model.Car;
import model.Vehicle;
import util.KendaraanNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// class ini yang ngatur CRUD data kendaraan ke tabel "kendaraan"
public class LayananKendaraan {

    public void tambahKendaraan(Vehicle kendaraan) {
        String sql = "INSERT INTO kendaraan (nomor_polisi, jenis_kendaraan, pemilik) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kendaraan.getNomorPolisi());
            ps.setString(2, kendaraan.getJenisKendaraan());
            ps.setString(3, kendaraan.getPemilik());
            ps.executeUpdate();

            System.out.println("Data kendaraan berhasil disimpan ke database.");

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menyimpan kendaraan: " + e.getMessage());
        }
    }

    public List<Vehicle> lihatSemuaKendaraan() {
        List<Vehicle> daftarKendaraan = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                daftarKendaraan.add(buatObjekKendaraan(rs));
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengambil data kendaraan: " + e.getMessage());
        }

        return daftarKendaraan;
    }

    public Vehicle cariByNomorPolisi(String nomorPolisi) throws KendaraanNotFoundException {
        String sql = "SELECT * FROM kendaraan WHERE nomor_polisi = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomorPolisi);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return buatObjekKendaraan(rs);
            } else {
                throw new KendaraanNotFoundException(
                        "Kendaraan dengan nomor polisi " + nomorPolisi + " tidak ditemukan di database.");
            }

        } catch (SQLException e) {
            // dibungkus jadi exception custom biar penanganan di Main lebih rapi
            throw new KendaraanNotFoundException("Gagal mengakses database: " + e.getMessage());
        }
    }

    // method ini contoh polymorphism, objek yang dikembalikan beda tergantung jenis kendaraannya
    private Vehicle buatObjekKendaraan(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_kendaraan");
        String nomorPolisi = rs.getString("nomor_polisi");
        String jenis = rs.getString("jenis_kendaraan");
        String pemilik = rs.getString("pemilik");

        if (jenis.equalsIgnoreCase("Mobil")) {
            // jumlah pintu gak disimpan di database, jadi dikasih nilai default aja
            return new Car(id, nomorPolisi, pemilik, 4);
        } else {
            return new Vehicle(id, nomorPolisi, jenis, pemilik);
        }
    }
}
