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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tokosembakoberkah.model.DetailTransaksiModel;
import tokosembakoberkah.model.TransaksiModel;
import tokosembakoberkah.util.DatabaseUtil;

public class AlgoritmaAprioriController {

    public List<String> getAllBarang() {
        List<String> barangList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT  nama_barang FROM detail_transaksi");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String namaBarang = resultSet.getString("nama_barang");
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

    public List<String> displayResults(double minSupport, double minConfidence) {
        // Fetch data from the database
        List<String> barangList = getAllBarang();
        List<TransaksiModel> transaksiList = getAllTransaksi();

        // Dataset transaksi
        List<List<String>> transactions = new ArrayList<>();
        for (TransaksiModel transaksi : transaksiList) {
            List<String> transaction = new ArrayList<>();
            for (DetailTransaksiModel detailTransaksi : transaksi.getDetailTransaksiList()) {
                transaction.add(detailTransaksi.getNamaBarang());
            }
            transactions.add(transaction);
        }

        // Calculate itemsets
        Map<String, Integer> itemsetsL1 = generateItemsetsL1(transactions, minSupport);
        Map<String, Integer> itemsetsL2 = generateItemsetsL2(itemsetsL1, minSupport, transactions);

        List<String> resultList = new ArrayList<>();

        List<AssociationRule> associationRulesL2 = generateAssociationRules(itemsetsL1, itemsetsL2, minConfidence);


        resultList.add("\nTabel Association Rules dari Itemset Kandidat L2:");
        resultList.add("|------------------------------------------------------------|");
        for (AssociationRule rule : associationRulesL2) {
            double confidencePercentage = rule.confidence * 100;
            String formattedConfidence = String.format("%.2f", confidencePercentage); 
            resultList.add("Jika ada Product {" + rule.antecedent + "} maka kemungkinan besar juga ada  {" + rule.consequent + "} dengan persentasi " + formattedConfidence + "% |");
            resultList.add("|------------------------------------------------------------|");
        }

        return resultList;
    }

    private static Map<String, Integer> generateItemsetsL1(List<List<String>> transactions, double minSupport) {
        Map<String, Integer> itemsetsL1 = new HashMap<>();
        for (List<String> transaction : transactions) {
            for (String item : transaction) {
                itemsetsL1.put(item, itemsetsL1.getOrDefault(item, 0) + 1);
            }
        }

        Map<String, Integer> frequentItemsetsL1 = new HashMap<>();
        for (Map.Entry<String, Integer> entry : itemsetsL1.entrySet()) {
            if (entry.getValue() >= minSupport * transactions.size()) {
                frequentItemsetsL1.put(entry.getKey(), entry.getValue());
            }
        }

        return frequentItemsetsL1;
    }

    private static Map<String, Integer> generateItemsetsL2(Map<String, Integer> itemsetsL1, double minSupport, List<List<String>> transactions) {
        Map<String, Integer> itemsetsL2 = new HashMap<>();
        List<String> itemsL1 = new ArrayList<>(itemsetsL1.keySet());

        for (int i = 0; i < itemsL1.size(); i++) {
            for (int j = i + 1; j < itemsL1.size(); j++) {
                String item1 = itemsL1.get(i);
                String item2 = itemsL1.get(j);
                List<String> candidateItemset = Arrays.asList(item1, item2);
                itemsetsL2.put(String.join(", ", candidateItemset), 0);
            }
        }

        for (List<String> transaction : transactions) {
            for (Map.Entry<String, Integer> entry : itemsetsL2.entrySet()) {
                List<String> candidateItemset = Arrays.asList(entry.getKey().split(", "));
                if (transaction.containsAll(candidateItemset)) {
                    itemsetsL2.put(entry.getKey(), entry.getValue() + 1);
                }
            }
        }

        Map<String, Integer> frequentItemsetsL2 = new HashMap<>();
        for (Map.Entry<String, Integer> entry : itemsetsL2.entrySet()) {
            if (entry.getValue() >= minSupport * transactions.size()) {
                frequentItemsetsL2.put(entry.getKey(), entry.getValue());
            }
        }

        return frequentItemsetsL2;
    }

    private static List<AssociationRule> generateAssociationRules(Map<String, Integer> itemsetsL1, Map<String, Integer> itemsetsL2, double minConfidence) {
        List<AssociationRule> associationRules = new ArrayList<>();

        // Check if itemsetsL1 and itemsetsL2 are not null
        if (itemsetsL1 == null || itemsetsL2 == null) {
            return associationRules;
        }

        for (Map.Entry<String, Integer> entry : itemsetsL2.entrySet()) {
            List<String> itemset = Arrays.asList(entry.getKey().split(", "));
            int support = entry.getValue();

            List<List<String>> subsets = getSubsets(itemset);
            for (List<String> subset : subsets) {
                if (!subset.isEmpty() && itemsetsL1.containsKey(subset.get(0))) {
                    String consequent = String.join(", ", subset);
                    List<String> antecedentList = itemset.stream().filter(e -> !subset.contains(e)).collect(Collectors.toList());
                    String antecedent = String.join(", ", antecedentList);

                    // Check if the antecedent is a valid key in itemsetsL1
                    if (itemsetsL1.containsKey(antecedent)) {
                        int antecedentSupport = itemsetsL1.get(antecedent);
                        double confidence = (double) support / antecedentSupport;

                        if (confidence >= minConfidence) {
                            associationRules.add(new AssociationRule(antecedent, consequent, support, confidence));
                        }
                    }
                }
            }
        }

        return associationRules;
    }

    // Fungsi untuk mendapatkan semua subset dari sebuah itemset
    private static List<List<String>> getSubsets(List<String> items) {
        List<List<String>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for (String item : items) {
            int size = subsets.size();
            for (int i = 0; i < size; i++) {
                List<String> subset = new ArrayList<>(subsets.get(i));
                subset.add(item);
                subsets.add(subset);
            }
        }

        return subsets;
    }

    // Kelas untuk menyimpan aturan asosiasi
    private static class AssociationRule {

        private String antecedent;
        private String consequent;
        private int support;
        private double confidence;

        public AssociationRule(String antecedent, String consequent, int support, double confidence) {
            this.antecedent = antecedent;
            this.consequent = consequent;
            this.support = support;
            this.confidence = confidence;
        }
    }

}
