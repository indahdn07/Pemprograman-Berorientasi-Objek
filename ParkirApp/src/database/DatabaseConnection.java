package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // sesuaikan sama konfigurasi mysql masing-masing
    private static final String URL = "jdbc:mysql://localhost:3306/db_parkir?useSSL=false&serverTimezone=Asia/Jakarta";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection koneksi;

    public static Connection getConnection() throws SQLException {
        if (koneksi == null || koneksi.isClosed()) {
            try {
                // load driver secara eksplisit, biar jelas kalau jar-nya belum ada di classpath
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MySQL (mysql-connector-j) tidak ditemukan. "
                        + "Pastikan file .jar-nya sudah ada di folder lib/ dan sudah dimasukkan ke classpath.");
            }
            koneksi = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return koneksi;
    }

    public static void closeConnection() {
        try {
            if (koneksi != null && !koneksi.isClosed()) {
                koneksi.close();
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi database: " + e.getMessage());
        }
    }
}
