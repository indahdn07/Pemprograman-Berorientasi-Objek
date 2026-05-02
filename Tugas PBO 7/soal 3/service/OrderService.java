package service;

import model.MenuItem;
import model.OrderItem;
import model.Pelanggan;
import collection.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderService {

    private Repository<OrderItem> keranjang = new Repository<OrderItem>();

    // riwayat order disimpan di arraylist of hashmap
    private ArrayList<HashMap<String, Object>> riwayat = new ArrayList<HashMap<String, Object>>();

    private int nomorOrder = 1;

    public void tambahItem(MenuItem item, int jumlah) {
        List<OrderItem> isiKeranjang = keranjang.getAll();

        // cek dulu apakah item sudah ada di keranjang
        for (int i = 0; i < isiKeranjang.size(); i++) {
            OrderItem oi = isiKeranjang.get(i);
            if (oi.getMenuItem().getId().equals(item.getId())) {
                // kalau sudah ada tinggal update jumlahnya
                oi.setJumlah(oi.getJumlah() + jumlah);
                System.out.println("  >> jumlah " + item.getNama() + " jadi " + oi.getJumlah());
                return;
            }
        }

        // kalau belum ada baru tambah
        OrderItem baru = new OrderItem(item, jumlah);
        keranjang.tambah(baru);
        System.out.println("  >> " + item.getNama() + " x" + jumlah + " ditambahkan");
    }

    public void lihatKeranjang() {
        List<OrderItem> isiKeranjang = keranjang.getAll();

        System.out.println("           KERANJANG ");

        if (isiKeranjang.size() == 0) {
            System.out.println("  keranjang kosong!");
            return;
        }

        double total = 0;
        for (OrderItem oi : isiKeranjang) {
            oi.tampilkan();
            total = total + oi.getSubtotal();
        }
        System.out.println("----------------------------------------");
        System.out.printf("  TOTAL  : Rp%.0f%n", total);
    }

    public double hitungTotal() {
        double total = 0;
        for (OrderItem oi : keranjang.getAll()) {
            total = total + oi.getSubtotal();
        }
        return total;
    }

    public boolean keranjangKosong() {
        if (keranjang.ukuran() == 0) {
            return true;
        }
        return false;
    }

    public void checkout(Pelanggan pelanggan, String metodeBayar) {
        double subtotal = hitungTotal();
        double ongkir = 5000;
        double grandTotal = subtotal + ongkir;

        System.out.println("            STRUK PESANAN");;
        System.out.println("  No Order  : #" + nomorOrder);
        pelanggan.tampilkanInfo();
        System.out.println("  Pesanan:");
        for (OrderItem oi : keranjang.getAll()) {
            oi.tampilkan();
        }
        System.out.printf("  Subtotal  : Rp%.0f%n", subtotal);
        System.out.printf("  Ongkir    : Rp%.0f%n", ongkir);
        System.out.printf("  Total     : Rp%.0f%n", grandTotal);
        System.out.println("  Bayar     : " + metodeBayar);
        System.out.println("  Status    : DITERIMA");
        System.out.println("  Estimasi  : 30-45 menit");
        System.out.println("  Terima kasih sudah order! ");

        // simpen ke riwayat
        HashMap<String, Object> dataOrder = new HashMap<String, Object>();
        dataOrder.put("noOrder", nomorOrder);
        dataOrder.put("nama", pelanggan.getNama());
        dataOrder.put("total", grandTotal);
        dataOrder.put("bayar", metodeBayar);
        riwayat.add(dataOrder);

        nomorOrder++;
        keranjang.kosongkan();
    }

    public void lihatRiwayat() {
        System.out.println("          RIWAYAT PESANAN");

        if (riwayat.size() == 0) {
            System.out.println("  belum ada pesanan");
        } else {
            for (HashMap<String, Object> o : riwayat) {
                System.out.printf("  #%d | %s | Rp%.0f | %s%n",
                        o.get("noOrder"), o.get("nama"), o.get("total"), o.get("bayar"));
            }
        }
        System.out.println();
    }
}
