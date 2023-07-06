/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah;

/**
 *
 * @author Lenovo
 */
import javax.swing.*;
import java.awt.*;
import tokosembakoberkah.controller.UserController;
import tokosembakoberkah.view.Barang.BarangPanel;
import tokosembakoberkah.view.Dashboard.DashboardPanel;
import tokosembakoberkah.view.Navigation.Navigation;
import tokosembakoberkah.view.SupplierPelanggan.SupplierPelanggan;
import tokosembakoberkah.view.Transaksi.BarangKeluar;
import tokosembakoberkah.view.Transaksi.BarangMasuk;
import tokosembakoberkah.view.Transaksi.TransaksiPanel;

public class MainFrame extends JFrame {

    private Navigation navigation;
    private JPanel contentPanel;
    private final UserController userController;

    public MainFrame() {
        initComponents();
        userController = new UserController();
        // Buat objek Navigation
        navigation = new Navigation();

        // Buat objek DashboardPanel
        DashboardPanel dashboardPanel = new DashboardPanel();

        // Set panel konten awal sebagai panel dashboard
        contentPanel = dashboardPanel;
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        initUI();
    }

    private void initUI() {

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);

    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Frame");
        setSize(1400, 800);

        // Membuat objek panel navigasi dan panel konten
        navigation = new Navigation();
        contentPanel = new JPanel();

        // Mengatur layout utama menggunakan BorderLayout
        setLayout(new BorderLayout());

        // Menambahkan panel navigasi ke posisi WEST
        add(navigation, BorderLayout.WEST);

        // Menambahkan panel konten ke posisi CENTER
        add(contentPanel, BorderLayout.CENTER);
    }

    public void changeContentPanel(String panelName) {
        // Hapus panel konten yang ada
        getContentPane().remove(contentPanel);

        // Buat panel konten yang sesuai berdasarkan panelName
        switch (panelName) {
            case "dashboard":
                DashboardPanel dashboardPanel = new DashboardPanel();
                contentPanel = dashboardPanel;
                break;
            case "transaksi":
                TransaksiPanel transaksiPanel = new TransaksiPanel();
                contentPanel = transaksiPanel;
                break;
            case "barang":
                BarangPanel barangPanel = new BarangPanel();
                contentPanel = barangPanel;
                break;
            case "barangK":
                BarangKeluar barangkeluar = new BarangKeluar();
                contentPanel = barangkeluar;
                break;
            case "barangM":
                BarangMasuk barangmasuk = new BarangMasuk();
                contentPanel = barangmasuk;
                break;
            case "sp":
                SupplierPelanggan spPanel = new SupplierPelanggan();
                contentPanel = spPanel;
                break;
            default:
                break;
        }

        // Tambahkan panel konten baru ke MainFrame
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Perbarui tampilan MainFrame
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
