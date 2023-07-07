/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah.controller;

/**
 *
 * @author Lenovo
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import tokosembakoberkah.model.DetailTransaksiModel;
import tokosembakoberkah.model.TransaksiModel;
import tokosembakoberkah.util.DatabaseUtil;

public class AlgoritmaAprioriController {

    private static final double MIN_SUPPORT = 0.2; // Nilai minimum support

    public List<String> getAllBarang() {
        List<String> barangList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT namaBarang FROM detail_transaksi");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String namaBarang = resultSet.getString("namaBarang");
                barangList.add(namaBarang);
            }
        } catch (SQLException e) {
            System.out.println("Error getAllBarang: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return barangList;
    }

    public List<TransaksiModel> getAllTransaksi() {
        List<TransaksiModel> transaksiList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM transaksi WHERE status='Barang Keluar'");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String status = resultSet.getString("status");
                Date tanggal = resultSet.getDate("tanggal");
                int idUser = resultSet.getInt("idUser");
                int idSp = resultSet.getInt("idSp");
                String invoice = resultSet.getString("invoice");
                int subTotal = resultSet.getInt("subTotal");

                TransaksiModel transaksi = new TransaksiModel(id, status, tanggal, idUser, invoice, subTotal, idSp);

                // Ambil informasi detail transaksi
                List<DetailTransaksiModel> detailTransaksiList = getDetailTransaksiByTransaksiId(id);
                transaksi.setDetailTransaksiList(detailTransaksiList);
                transaksiList.add(transaksi);
            }
        } catch (SQLException e) {
            System.out.println("Error getAllTransaksi: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return transaksiList;
    }

    private List<DetailTransaksiModel> getDetailTransaksiByTransaksiId(int transaksiId) {
        List<DetailTransaksiModel> detailTransaksiList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT id, id_transaksi, nama_barang, kategori, satuan, jumlah "
                    + "FROM detail_transaksi "
                    + "WHERE id_transaksi = ?");
            statement.setInt(1, transaksiId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idTransaksi = resultSet.getInt("id_transaksi");
                String namaBarang = resultSet.getString("nama_barang");
                String kategori = resultSet.getString("kategori");
                int satuan = resultSet.getInt("satuan");
                int jumlah = resultSet.getInt("jumlah");

                DetailTransaksiModel detailTransaksi = new DetailTransaksiModel(id, idTransaksi, namaBarang, kategori, satuan, jumlah);
                detailTransaksiList.add(detailTransaksi);
            }
        } catch (SQLException e) {
            System.out.println("Error getDetailTransaksiByTransaksiId: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return detailTransaksiList;
    }

    public Map<Set<String>, Integer> generateInitialItemsets() {
        List<TransaksiModel> transaksiList = getAllTransaksi();
        Map<Set<String>, Integer> itemsets = new HashMap<>();

        // Mendapatkan support count untuk setiap itemset
        for (TransaksiModel transaksi : transaksiList) {
            List<String> items = new ArrayList<>();

            for (DetailTransaksiModel detailTransaksi : transaksi.getDetailTransaksiList()) {
                String namaBarang = detailTransaksi.getNamaBarang();
                items.add(namaBarang);
            }

            // Menghitung support count
            generateItemsets(itemsets, items);
        }

        // Menghapus itemset dengan support count di bawah minSupport
        itemsets.entrySet().removeIf(entry -> entry.getValue() < MIN_SUPPORT * transaksiList.size());

        return itemsets;
    }

    private void generateItemsets(Map<Set<String>, Integer> itemsets, List<String> items) {
        int n = items.size();

        for (int i = 1; i < (1 << n); i++) {
            Set<String> itemset = new HashSet<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    itemset.add(items.get(j));
                }
            }

            int count = itemsets.getOrDefault(itemset, 0);
            itemsets.put(itemset, count + 1);
        }
    }

    public Map<Set<String>, Integer> generateFrequentItemsets(double minSupport) {
        Map<Set<String>, Integer> frequentItemsets = new HashMap<>();
        Map<Set<String>, Integer> candidateItemsets = generateInitialItemsets();

        // Mengulangi langkah pembangkitan dan pemfilteran itemset hingga tidak ada lagi kandidat baru
        while (!candidateItemsets.isEmpty()) {
            // Pemfilteran itemset berdasarkan minSupport
            candidateItemsets.entrySet().removeIf(entry -> entry.getValue() < minSupport);

            // Menambahkan itemset yang lolos pemfilteran ke frequentItemsets
            frequentItemsets.putAll(candidateItemsets);

            // Pembangkitan kandidat baru
            candidateItemsets = generateCandidateItemsets(candidateItemsets.keySet());
        }

        return frequentItemsets;
    }

    private Map<Set<String>, Integer> generateCandidateItemsets(Set<Set<String>> prevItemsets) {
        Map<Set<String>, Integer> candidateItemsets = new HashMap<>();
        List<Set<String>> prevItemsetList = new ArrayList<>(prevItemsets);

        for (int i = 0; i < prevItemsetList.size(); i++) {
            Set<String> itemset1 = prevItemsetList.get(i);

            for (int j = i + 1; j < prevItemsetList.size(); j++) {
                Set<String> itemset2 = prevItemsetList.get(j);

                Set<String> candidate = new HashSet<>(itemset1);
                candidate.addAll(itemset2);

                if (isFrequentSubset(candidate, prevItemsets)) {
                    candidateItemsets.put(candidate, 0);
                }
            }
        }

        return candidateItemsets;
    }

    private boolean isFrequentSubset(Set<String> candidate, Set<Set<String>> prevItemsets) {
        for (String item : candidate) {
            Set<String> subset = new HashSet<>(candidate);
            subset.remove(item);

            if (!prevItemsets.contains(subset)) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param minSupport
     * @return
     */
    public DefaultListModel<String> generateFrequentItemsets() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Map<Set<String>, Integer> frequentItemsets = generateFrequentItemsets(MIN_SUPPORT);

        for (Map.Entry<Set<String>, Integer> entry : frequentItemsets.entrySet()) {
            Set<String> itemset = entry.getKey();
            int supportCount = entry.getValue();

            String itemsetStr = itemset.toString() + " - Support Count: " + supportCount;
            listModel.addElement(itemsetStr);
        }

        return listModel;
    }
}
