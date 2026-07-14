package model;

// class induk buat semua jenis kendaraan
public class Vehicle {

    private int idKendaraan;
    private String nomorPolisi;
    private String jenisKendaraan;
    private String pemilik;

    public Vehicle() {
    }

    // constructor buat kendaraan baru (belum ada id, soalnya id dibuat sama database)
    public Vehicle(String nomorPolisi, String jenisKendaraan, String pemilik) {
        this.nomorPolisi = nomorPolisi;
        this.jenisKendaraan = jenisKendaraan;
        this.pemilik = pemilik;
    }

    // constructor buat kendaraan yang datanya diambil dari database (udah ada id)
    public Vehicle(int idKendaraan, String nomorPolisi, String jenisKendaraan, String pemilik) {
        this.idKendaraan = idKendaraan;
        this.nomorPolisi = nomorPolisi;
        this.jenisKendaraan = jenisKendaraan;
        this.pemilik = pemilik;
    }

    // ------- getter & setter (encapsulation) -------
    public int getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public void setNomorPolisi(String nomorPolisi) {
        this.nomorPolisi = nomorPolisi;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    // method ini bakal di-override sama class Car (polymorphism)
    public void displayInfo() {
        System.out.println("ID Kendaraan    : " + idKendaraan);
        System.out.println("Nomor Polisi    : " + nomorPolisi);
        System.out.println("Jenis Kendaraan : " + jenisKendaraan);
        System.out.println("Pemilik         : " + pemilik);
    }
}
