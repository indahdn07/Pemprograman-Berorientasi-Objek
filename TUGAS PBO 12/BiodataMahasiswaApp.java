import java.awt.*;
import javax.swing.*;

public class BiodataMahasiswaApp {

    JFrame frame;
    JTextField fieldNim, fieldNama, fieldProdi;
    JTextArea areaOutput;

    public BiodataMahasiswaApp() {
        frame = new JFrame("Aplikasi Biodata Mahasiswa");
        frame.setSize(480, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // panel input
        JPanel panelInput = new JPanel();
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));
        panelInput.setLayout(new GridLayout(3, 2, 5, 5));

        fieldNim = new JTextField();
        fieldNama = new JTextField();
        fieldProdi = new JTextField();

        panelInput.add(new JLabel("NIM"));
        panelInput.add(fieldNim);
        panelInput.add(new JLabel("Nama"));
        panelInput.add(fieldNama);
        panelInput.add(new JLabel("Program Studi"));
        panelInput.add(fieldProdi);

        // panel tombol
        JPanel panelTombol = new JPanel();
        JButton btnTampilkan = new JButton("Tampilkan");
        JButton btnReset = new JButton("Reset");
        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // panel output
        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaOutput);
        scroll.setBorder(BorderFactory.createTitledBorder("Output"));

        // gabungin input + tombol di atas
        JPanel panelAtas = new JPanel();
        panelAtas.setLayout(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        frame.add(panelAtas, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        btnTampilkan.addActionListener(e -> {
            String nim = fieldNim.getText();
            String nama = fieldNama.getText();
            String prodi = fieldProdi.getText();

            if (nim.equals("") || nama.equals("") || prodi.equals("")) {
                areaOutput.setText("Data belum lengkap, isi semua field dulu.");
                return;
            }

            String hasil = "========== BIODATA MAHASISWA ==========\n\n";
            hasil += "NIM           : " + nim + "\n";
            hasil += "Nama          : " + nama + "\n";
            hasil += "Program Studi : " + prodi + "\n";

            areaOutput.setText(hasil);
        });

        btnReset.addActionListener(e -> {
            fieldNim.setText("");
            fieldNama.setText("");
            fieldProdi.setText("");
            areaOutput.setText("");
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BiodataMahasiswaApp();
    }
}