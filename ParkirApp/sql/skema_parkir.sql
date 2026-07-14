-- ==========================================================
-- Skema Database Aplikasi Manajemen Parkir Kendaraan
-- Dibuat untuk tugas PBO (Java + MySQL)
-- ==========================================================

CREATE DATABASE IF NOT EXISTS db_parkir;
USE db_parkir;

-- -----------------------------
-- Tabel Kendaraan
-- -----------------------------
CREATE TABLE IF NOT EXISTS kendaraan (
    id_kendaraan INT AUTO_INCREMENT PRIMARY KEY,
    nomor_polisi VARCHAR(20) NOT NULL,
    jenis_kendaraan VARCHAR(20) NOT NULL,
    pemilik VARCHAR(50) NOT NULL
);

-- -----------------------------
-- Tabel Parkir
-- -----------------------------
CREATE TABLE IF NOT EXISTS parkir (
    id_parkir INT AUTO_INCREMENT PRIMARY KEY,
    id_kendaraan INT NOT NULL,
    waktu_masuk DATETIME,
    waktu_keluar DATETIME NULL,
    status VARCHAR(20) DEFAULT 'Masuk',
    CONSTRAINT fk_parkir_kendaraan FOREIGN KEY (id_kendaraan)
        REFERENCES kendaraan(id_kendaraan)
);

-- ==========================================================
-- PROCEDURE
-- Dipakai untuk menyimpan data kendaraan yang baru masuk
-- ==========================================================
DROP PROCEDURE IF EXISTS sp_kendaraan_masuk;
DELIMITER //
CREATE PROCEDURE sp_kendaraan_masuk(IN p_id_kendaraan INT)
BEGIN
    INSERT INTO parkir (id_kendaraan, waktu_masuk, status)
    VALUES (p_id_kendaraan, NOW(), 'Masuk');
END //
DELIMITER ;

-- ==========================================================
-- FUNCTION
-- Menghitung jumlah kendaraan yang statusnya masih "Masuk"
-- ==========================================================
DROP FUNCTION IF EXISTS fn_jumlah_kendaraan_parkir;
DELIMITER //
CREATE FUNCTION fn_jumlah_kendaraan_parkir()
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM parkir WHERE status = 'Masuk';
    RETURN total;
END //
DELIMITER ;

-- ==========================================================
-- TRIGGER
-- Kalau kolom waktu_keluar diisi, status otomatis jadi "Keluar"
-- ==========================================================
DROP TRIGGER IF EXISTS trg_status_keluar;
DELIMITER //
CREATE TRIGGER trg_status_keluar
BEFORE UPDATE ON parkir
FOR EACH ROW
BEGIN
    IF NEW.waktu_keluar IS NOT NULL THEN
        SET NEW.status = 'Keluar';
    END IF;
END //
DELIMITER ;

-- ==========================================================
-- VIEW
-- Menampilkan kendaraan yang masih berada di area parkir
-- ==========================================================
DROP VIEW IF EXISTS view_kendaraan_parkir;
CREATE VIEW view_kendaraan_parkir AS
SELECT p.id_parkir, k.nomor_polisi, k.jenis_kendaraan, k.pemilik, p.waktu_masuk
FROM parkir p
JOIN kendaraan k ON p.id_kendaraan = k.id_kendaraan
WHERE p.status = 'Masuk';

-- ==========================================================
-- Contoh data buat testing (boleh dihapus)
-- ==========================================================
-- INSERT INTO kendaraan (nomor_polisi, jenis_kendaraan, pemilik) VALUES ('D 1234 AB', 'Mobil', 'Budi');
-- INSERT INTO kendaraan (nomor_polisi, jenis_kendaraan, pemilik) VALUES ('D 5678 CD', 'Motor', 'Sinta');
