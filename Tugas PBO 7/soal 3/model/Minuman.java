package model;

// Minuman extends MenuItem
public class Minuman extends MenuItem {

    private boolean dingin;

    public Minuman(String id, String nama, double harga, String deskripsi, boolean dingin) {
        super(id, nama, harga, deskripsi);
        this.dingin = dingin;
    }

    public boolean isDingin() {
        return dingin;
    }

    @Override
    public String getKategori() {
        return "MINUMAN";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        if (dingin) {
            System.out.println("           >> Dingin");
        } else {
            System.out.println("           >> Panas");
        }
    }
}
