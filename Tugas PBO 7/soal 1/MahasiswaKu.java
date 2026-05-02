public class MahasiswaKu {
    public static void main(String[] args) {
        Mahasiswa <String,String,Integer> m = new Mahasiswa<>();
        m.setNIM("1102020");
        m.setName("Ferdi");
        m.setclas(21);

        System.out.println(m.getNIM());
        System.out.println(m.getName());
        System.out.println(m.getclas());
    }
}
