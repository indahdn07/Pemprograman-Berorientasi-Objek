package model;

// Makanan extends MenuItem (inheritance)
public class Makanan extends MenuItem {

    private boolean pedas;

    public Makanan(String id, String nama, double harga, String deskripsi, boolean pedas) {
        super(id, nama, harga, deskripsi);
        this.pedas = pedas;
    }

    public boolean isPedas() {
        return pedas;
    }

    @Override
    public String getKategori() {
        return "MAKANAN";
    }

    // override tampilkanInfo buat tambahin info pedas/tidak
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        if (pedas == true) {
            System.out.println("           >> PEDAS");
        }
    }
}
