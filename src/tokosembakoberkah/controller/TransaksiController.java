/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tokosembakoberkah.model.DetailTransaksiModel;
import tokosembakoberkah.model.TransaksiModel;
import tokosembakoberkah.util.DatabaseUtil;

/**
 *
 * @author Lenovo
 */
public class TransaksiController {
public List<TransaksiModel> getAllTransaksi() {
    List<TransaksiModel> transaksiList = new ArrayList<>();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = DatabaseUtil.getConnection();
        statement = connection.prepareStatement("SELECT t.id, t.status, t.tanggal, t.idUser, t.idSp, t.invoice, t.subTotal, u.username " +
                "FROM transaksi t " +
                "INNER JOIN users u ON t.idUser = u.id");
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String status = resultSet.getString("status");
            Date tanggal = resultSet.getDate("tanggal");
            int idUser = resultSet.getInt("idUser");
            int idSp = resultSet.getInt("idSp");
            String invoice = resultSet.getString("invoice");
            double subTotal = resultSet.getDouble("subTotal");
            String username = resultSet.getString("username");

            TransaksiModel transaksi = new TransaksiModel(id, status, tanggal, idUser, invoice, subTotal, idSp);
            transaksi.setUsername(username);

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
public void insertTransaksi(TransaksiModel transaksi) {
    Connection connection = null;
    PreparedStatement transaksiStatement = null;
    PreparedStatement detailStatement = null;
    ResultSet generatedKeys = null;

    try {
        connection = DatabaseUtil.getConnection();
        connection.setAutoCommit(false);

        // Insert data transaksi utama
        String transaksiQuery = "INSERT INTO transaksi (status, tanggal, idUser, idSp, invoice, subTotal) VALUES (?, ?, ?, ?, ?, ?)";
        transaksiStatement = connection.prepareStatement(transaksiQuery, Statement.RETURN_GENERATED_KEYS);
        transaksiStatement.setString(1, transaksi.getStatus());
        transaksiStatement.setDate(2, new java.sql.Date(transaksi.getTanggal().getTime()));
        transaksiStatement.setInt(3, transaksi.getIdUser());
        transaksiStatement.setInt(4, transaksi.getIdSp());
        transaksiStatement.setString(5, transaksi.getInvoice());
        transaksiStatement.setDouble(6, transaksi.getSubTotal());
        transaksiStatement.executeUpdate();

        // Dapatkan ID transaksi yang baru saja diinsert
        generatedKeys = transaksiStatement.getGeneratedKeys();
        int transaksiId = -1;
        if (generatedKeys.next()) {
            transaksiId = generatedKeys.getInt(1);
        }

        // Insert data detail transaksi
        String detailQuery = "INSERT INTO detail_transaksi (id_transaksi, nama_barang, kategori, satuan, jumlah) VALUES (?, ?, ?, ?, ?)";
        detailStatement = connection.prepareStatement(detailQuery);
        List<DetailTransaksiModel> detailTransaksiList = transaksi.getDetailTransaksiList();
        for (DetailTransaksiModel detailTransaksi : detailTransaksiList) {
            detailStatement.setInt(1, transaksiId);
            detailStatement.setString(2, detailTransaksi.getNamaBarang());
            detailStatement.setString(3, detailTransaksi.getKategori());
            detailStatement.setString(4, detailTransaksi.getSatuan());
            detailStatement.setInt(5, detailTransaksi.getJumlah());
            detailStatement.executeUpdate();
        }

        // Commit transaksi jika tidak ada kesalahan
        connection.commit();
    } catch (SQLException e) {
        System.out.println("Error insertTransaksi: " + e.getMessage());

        // Rollback transaksi jika terjadi kesalahan
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rollback transaction: " + ex.getMessage());
            }
        }
    } finally {
        // Tutup statement, result set, dan koneksi
        DatabaseUtil.closeResultSet(generatedKeys);
        DatabaseUtil.closeStatement(detailStatement);
        DatabaseUtil.closeStatement(transaksiStatement);
        DatabaseUtil.closeConnection(connection);
    }
}


private List<DetailTransaksiModel> getDetailTransaksiByTransaksiId(int transaksiId) {
    List<DetailTransaksiModel> detailTransaksiList = new ArrayList<>();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        connection = DatabaseUtil.getConnection();
        statement = connection.prepareStatement("SELECT id, id_transaksi, nama_barang, kategori, satuan, jumlah " +
                "FROM detail_transaksi " +
                "WHERE id_transaksi = ?");
        statement.setInt(1, transaksiId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idTransaksi = resultSet.getInt("id_transaksi");
            String namaBarang = resultSet.getString("nama_barang");
            String kategori = resultSet.getString("kategori");
            String satuan = resultSet.getString("satuan");
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
}