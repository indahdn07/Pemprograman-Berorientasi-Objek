import java.util.ArrayDeque;
public class contoharraydeque {
    public static void main(String[] args) {
        ArrayDeque<String> antrian = new ArrayDeque<String>();
        antrian.add("Adi");
        antrian.add("Umar");
        antrian.add("Lina");

        System.err.println("Antrian awal: " + antrian);
        //fifo
        System.out.println("Antrian dipanggil: " + antrian.poll());
        //menampilkan sisa antrian
        System.out.println("Antrian setelah dipanggil: " + antrian);
        
    }
    
}
