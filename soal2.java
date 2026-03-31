import javax.swing.JOptionPane; 

public class soal2 {
    public static void main(String[] args) {
      
        String pertanyaan = JOptionPane.showInputDialog(null, "Anda sedang belajar apa?", "Input", JOptionPane.QUESTION_MESSAGE);
        
       
        JOptionPane.showMessageDialog(null, "Belajar " + pertanyaan + " sangat mudah", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}