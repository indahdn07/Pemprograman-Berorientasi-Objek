package model;

// Snack extends MenuItem
public class Snack extends MenuItem {

    private int beratGram;

    public Snack(String id, String nama, double harga, String deskripsi, int beratGram) {
        super(id, nama, harga, deskripsi);
        this.beratGram = beratGram;
    }

    public int getBeratGram() {
        return beratGram;
    }

    @Override
    public String getKategori() {
        return "SNACK";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("           >> " + beratGram + " gram");
    }
}
