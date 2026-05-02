package model;

public class Pelanggan {

    // encapsulation, semua private
    private String id;
    private String nama;
    private String noHp;
    private String alamat;

    public Pelanggan(String id, String nama, String noHp, String alamat) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void tampilkanInfo() {
        System.out.println("  Nama    : " + nama);
        System.out.println("  No HP   : " + noHp);
        System.out.println("  Alamat  : " + alamat);
    }
}
