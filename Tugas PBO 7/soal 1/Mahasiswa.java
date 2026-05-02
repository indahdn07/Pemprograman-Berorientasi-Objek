public class Mahasiswa <A, B, C> {

    private A NIM;
    private B name;
    private C clas;

    public void setNIM(A NIM) {
        this.NIM = NIM;
    }

    public void setName(B name) {
        this.name = name;
    }

    public void setclas(C clas) {
        this.clas = clas;
    }

    public A getNIM() {
        return NIM;
    }

    public B getName() {
        return name;
    }

    public C getclas() {
        return clas;
    }
}
