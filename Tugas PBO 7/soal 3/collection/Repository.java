package collection;

import java.util.ArrayList;
import java.util.List;

// generic class buat nyimpan data
// T bisa apa aja, MenuItem, OrderItem, dll
public class Repository<T> {

    private List<T> listData = new ArrayList<T>();

    public void tambah(T item) {
        listData.add(item);
    }

    public void hapus(T item) {
        listData.remove(item);
    }

    public List<T> getAll() {
        return listData;
    }

    public int ukuran() {
        return listData.size();
    }

    public void kosongkan() {
        listData.clear();
    }
}
