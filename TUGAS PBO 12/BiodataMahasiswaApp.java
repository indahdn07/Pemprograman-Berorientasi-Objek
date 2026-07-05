import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataMahasiswaApp extends JFrame {

    // Deklarasi komponen 
    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtProdi;
    private JTextArea txtOutput;

    public BiodataMahasiswaApp() {
        // Setup Frame Utama
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Inisialisasi komponen
        txtNim = new JTextField();
        txtNama = new JTextField();
        txtProdi = new JTextField();
        txtOutput = new JTextArea();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        // Membuat Panel Input Data
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data"));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        formPanel.add(new JLabel("NIM"));
        formPanel.add(txtNim);
        formPanel.add(new JLabel("Nama"));
        formPanel.add(txtNama);
        formPanel.add(new JLabel("Program Studi"));
        formPanel.add(txtProdi);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        JButton btnTampilkan = new JButton("Tampilkan");
        JButton btnReset = new JButton("Reset");

        buttonPanel.add(btnTampilkan);
        buttonPanel.add(btnReset);

        inputPanel.add(formPanel, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Membuat Panel Output
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        txtOutput.setEditable(false);
        txtOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(txtOutput);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        // Masukkan Panel ke Main Panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // Event Handling untuk tombol Tampilkan
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtNim.getText().trim();
                String nama = txtNama.getText().trim();
                String prodi = txtProdi.getText().trim();

                // Validasi input kosong
                if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()) {
                    txtOutput.setText("Harap isi semua data (NIM, Nama, Program Studi) sebelum menekan Tampilkan.");
                } else {
                    // Pakai gabungan string (+) biasa biar kelihatan natural
                    String hasil = "========== BIODATA MAHASISWA ==========\n\n" +
                                   "NIM           : " + nim + "\n" +
                                   "Nama          : " + nama + "\n" +
                                   "Program Studi : " + prodi + "\n";
                    txtOutput.setText(hasil);
                }
            }
        });

        // Event Handling untuk tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNim.setText("");
                txtNama.setText("");
                txtProdi.setText("");
                txtOutput.setText("");
                txtNim.requestFocus();
            }
        });
    }

    public static void main(String[] args) {
        new BiodataMahasiswaApp().setVisible(true);
    }
}