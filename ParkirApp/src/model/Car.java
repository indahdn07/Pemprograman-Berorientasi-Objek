package model;

// Car mewarisi Vehicle, ini contoh inheritance
public class Car extends Vehicle {

    private int jumlahPintu;

    public Car(String nomorPolisi, String pemilik, int jumlahPintu) {
        super(nomorPolisi, "Mobil", pemilik);
        this.jumlahPintu = jumlahPintu;
    }

    public Car(int idKendaraan, String nomorPolisi, String pemilik, int jumlahPintu) {
        super(idKendaraan, nomorPolisi, "Mobil", pemilik);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    // override method dari Vehicle -> contoh polymorphism
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Jumlah Pintu    : " + jumlahPintu);
    }
}
