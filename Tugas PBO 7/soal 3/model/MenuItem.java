package model;

import interfaces.IOrderable;

public abstract class MenuItem implements IOrderable {

    private String id;
    private String nama;
    private double harga;
    private String deskripsi;

    public MenuItem(String id, String nama, double harga, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    // getter setter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // abstract method wajib diisi di subclass
    public abstract String getKategori();

    @Override
    public double hitungHarga() {
        return harga;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("  [" + id + "] " + nama + " - Rp" + (int)harga + " - " + deskripsi);
    }
}
