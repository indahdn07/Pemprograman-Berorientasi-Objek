package model;

import interfaces.IDiskon;

// class untuk item yang ada di keranjang
public class OrderItem implements IDiskon {

    private MenuItem menuItem;
    private int jumlah;
    private double diskonPersen;

    public OrderItem(MenuItem menuItem, int jumlah) {
        this.menuItem = menuItem;
        this.jumlah = jumlah;
        this.diskonPersen = 0; // default ga ada diskon
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getDiskonPersen() {
        return diskonPersen;
    }

    public void setDiskonPersen(double diskonPersen) {
        this.diskonPersen = diskonPersen;
    }

    // subtotal = harga sudah diskon * jumlah
    public double getSubtotal() {
        return hargaSetelahDiskon() * jumlah;
    }

    @Override
    public double hitungDiskon() {
        return menuItem.getHarga() * (diskonPersen / 100);
    }

    @Override
    public double hargaSetelahDiskon() {
        return menuItem.getHarga() - hitungDiskon();
    }

    public void tampilkan() {
        System.out.println("- " + menuItem.getNama() + " x" + jumlah + " @Rp" + (int)menuItem.getHarga());
        if (diskonPersen > 0) {
            System.out.println("  (Diskon " + (int)diskonPersen + "%) = Rp" + (int)getSubtotal());
    } else {
            System.out.println("  = Rp" + (int)getSubtotal());
        }
    }
}
