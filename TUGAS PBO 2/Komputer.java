public class Komputer {  // Komputer { menandakan bahwa kita sedang mendefinisikan sebuah class dengan nama Komputer
    String jenis_komputer; // atribut class. Mendeklarasi variabel jenis_komputer dengan tipe data String untuk menyimpan jenis komputer
    private String merk; // atribut class. Mendeklarasi variabel merk dengan tipe data String untuk menyimpan merk komputer, dengan akses modifier private

    public void setDataKomputer(String jenis, String merk) { // method yang dimiliki class Komputer. Ada method setter dan dua method getter.
        jenis_komputer = jenis; // nilai yang diterima oleh parameter jenis akan disimpan dalam variabel jenis_komputer.
        this.merk = merk; //  Kata kunci this digunakan untuk mengacu pada variabel merk yang ada di dalam class, membedakan dengan parameter merk yang ada di dalam method setDataKomputer.
    }

    public String getJenis(){  //nilai yang dikembalikan oleh method getJenis().  
        return jenis_komputer; //method akan memberikan balik nilai dari variabel jenis_komputer.
    }

    public String getMerk(){ // nilai yang dikembalikan oleh method getMerk().
        return merk;         // method akan memberikan balik nilai dari variabel merk.
    }

    public static void main(String[] args) {
        Komputer mykom = new Komputer(); //pemanggilan method setter
        mykom.setDataKomputer ("LAPTOP", "MACBOOK"); //Object mykom diisi data. Jenis komputernya "LAPTOP" dan merknya "MACBOOK".
        System.err.println(mykom.getJenis()); //pemanggilan method getter untuk jenis komputer, hasilnya akan dicetak ke layar 
        System.err.println(mykom.getMerk()); //output yang ditampilkan "LAPTOP" dan "MACBOOK" sebagai .
    }
}
