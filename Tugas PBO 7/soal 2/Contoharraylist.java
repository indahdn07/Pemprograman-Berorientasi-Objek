import java.util.ArrayList;;

public class Contoharraylist {

    public static void main(String[] args) {

        ArrayList<String> nilai = new ArrayList<String>();
        nilai.add("A");
        nilai.add("B");
        nilai.add("C");

        for(int i = 0; i < nilai.size(); i++){
            System.out.println(nilai.get(i));
        }
        
    }
    
}
