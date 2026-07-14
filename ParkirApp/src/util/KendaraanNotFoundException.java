package util;

// exception custom kalau data kendaraan yang dicari gak ada di database
public class KendaraanNotFoundException extends Exception {

    public KendaraanNotFoundException(String message) {
        super(message);
    }
}
