package util;

import java.util.Scanner;

// biar gak nulis Scanner di semua tempat, dijadiin 1 class aja
public class InputHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String label) {
        int nilai = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(label);
            String input = scanner.nextLine();
            try {
                nilai = Integer.parseInt(input.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input salah, harus angka. Coba lagi ya.");
            }
        }
        return nilai;
    }

    public static String getString(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }
}
