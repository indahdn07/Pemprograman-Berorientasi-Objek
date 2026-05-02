package service;

import model.Makanan;
import model.Minuman;
import model.MenuItem;
import model.Snack;
import collection.Repository;

import java.util.HashMap;
import java.util.List;

public class MenuService {

    // pakai hashmap supaya pencarian by id lebih cepat
    private HashMap<String, MenuItem> menuMap = new HashMap<String, MenuItem>();
    private Repository<MenuItem> repoMenu = new Repository<MenuItem>();

    public MenuService() {
        isiMenu();
    }

    // isi data menu awal
    private void isiMenu() {
        tambah(new Makanan("M01", "Nasi Goreng", 25000, "nasi goreng telur + ayam", true));
        tambah(new Makanan("M02", "Ayam Bakar", 30000, "ayam bakar bumbu kecap", false));
        tambah(new Makanan("M03", "Mie Goreng", 20000, "mie goreng biasa", false));
        tambah(new Makanan("M04", "Nasi Uduk", 18000, "nasi uduk + lauk pauk", false));
        tambah(new Makanan("M05", "Soto Ayam", 22000, "soto ayam kuah bening", false));

        tambah(new Minuman("D01", "Es Teh Manis", 8000, "teh manis dingin", true));
        tambah(new Minuman("D02", "Jus Alpukat", 15000, "jus alpukat kental", true));
        tambah(new Minuman("D03", "Kopi Hitam", 10000, "kopi item tanpa gula", false));
        tambah(new Minuman("D04", "Air Mineral", 5000, "aqua 600ml", false));

        tambah(new Snack("S01", "Kentang Goreng", 15000, "kentang goreng crispy", 150));
        tambah(new Snack("S02", "Pisang Goreng", 12000, "pisang goreng + keju", 200));
        tambah(new Snack("S03", "Nugget Ayam", 18000, "nugget + saus tomat", 180));
    }

    public void tambah(MenuItem item) {
        menuMap.put(item.getId(), item);
        repoMenu.tambah(item);
    }

    public MenuItem cariById(String id) {
        return menuMap.get(id.toUpperCase());
    }

    public void tampilkanMenu() {
        List<MenuItem> semuaMenu = repoMenu.getAll();

        System.out.println("           DAFTAR MENU");

        // tampilin per kategori pake polimorfisme
        // getKategori() dan tampilkanInfo() beda-beda tiap subclass
        String[] daftarKategori = {"MAKANAN", "MINUMAN", "SNACK"};

        for (String kat : daftarKategori) {
            System.out.println("\n--- " + kat + " ---");
            for (MenuItem m : semuaMenu) {
                if (m.getKategori().equals(kat)) {
                    m.tampilkanInfo(); // ini polimorfisme, tiap class beda outputnya
                }
            }
        }
        System.out.println();
    }
}
