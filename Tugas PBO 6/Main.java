import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Laptop laptopPilihan = null;

        System.out.println("=== PILIH MERK LAPTOP ===");
        System.out.println("1. Lenovo");
        System.out.println("2. Toshiba");
        System.out.println("3. MacBook");
        System.out.print("Pilih angka (1/2/3): ");
        
     
        int pilihanMerk = Integer.parseInt(sc.nextLine());

      
        if (pilihanMerk == 1) {
            laptopPilihan = new Lenovo();
        } else if (pilihanMerk == 2) {
            laptopPilihan = new Toshiba();
        } else if (pilihanMerk == 3) {
            laptopPilihan = new MacBook();
        } else {
            System.out.println("Pilihan tidak valid, default Lenovo");
            laptopPilihan = new Lenovo(); 
        }

    
        LaptopUser indah = new LaptopUser(laptopPilihan);

        System.out.println("\n=== KONTROL LAPTOP ===");
        System.out.println("Ketik ON, OFF, UP, DOWN, atau EXIT");

        while (true) {
            System.out.print("\nMasukkan Perintah: ");
            String perintah = sc.nextLine();

            if (perintah.equalsIgnoreCase("ON")) {
                indah.turnOnLaptop();
            } 
            else if (perintah.equalsIgnoreCase("OFF")) {
                indah.turnOffLaptop();
            } 
            else if (perintah.equalsIgnoreCase("UP")) {
                indah.makeLaptopLouder();
            } 
            else if (perintah.equalsIgnoreCase("DOWN")) {
                indah.makeLaptopSilence();
            } 
            else if (perintah.equalsIgnoreCase("EXIT")) {
                System.out.println("Keluar dari program...");
                break;
            } 
            else {
                System.out.println("Perintah tidak dikenali!");
            }
        }
        sc.close();
    }
}