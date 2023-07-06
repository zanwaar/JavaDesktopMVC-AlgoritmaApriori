/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah.controller;

/**
 *
 * @author Lenovo
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import tokosembakoberkah.model.BarangModel;
import tokosembakoberkah.util.DatabaseUtil;

public class BarangController {

    public int getBarangCount() {
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM barang");

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getBarangCount: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getBarangCount: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return count;
    }

    public List<BarangModel> getBarangByPage(int pageNumber, int pageSize) {
        List<BarangModel> barangList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();

            int offset = (pageNumber - 1) * pageSize;
            String query = "SELECT * FROM barang ORDER BY id DESC LIMIT " + offset + ", " + pageSize;

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String kode = resultSet.getString("kode");
                String nama = resultSet.getString("nama");
                String kategori = resultSet.getString("kategori");
                String satuan = resultSet.getString("satuan");
                int stok = resultSet.getInt("stok");
                int hargaSatuan = resultSet.getInt("hargaSatuan");

                BarangModel barang = new BarangModel(id, kode, nama, kategori, satuan, stok, hargaSatuan);
                barangList.add(barang);
            }
        } catch (SQLException e) {
            System.out.println("Error getBarangByPage: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getBarangByPage: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return barangList;
    }

    public boolean addBarang(BarangModel barang) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO barang (kode, nama, kategori, satuan, stok, hargaSatuan) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, barang.getKode());
            statement.setString(2, barang.getNama());
            statement.setString(3, barang.getKategori());
            statement.setString(4, barang.getSatuan());
            statement.setInt(5, barang.getStok());
            statement.setInt(6, barang.getHargaSatuan());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error addBarang: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error addBarang: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    public boolean updateBarang(BarangModel barang) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("UPDATE barang SET kode = ?, nama = ?, kategori = ?, satuan = ?, stok = ?, hargaSatuan = ? WHERE id = ?");
            statement.setString(1, barang.getKode());
            statement.setString(2, barang.getNama());
            statement.setString(3, barang.getKategori());
            statement.setString(4, barang.getSatuan());
            statement.setInt(5, barang.getStok());
            statement.setInt(6, barang.getHargaSatuan());
            statement.setInt(7, barang.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updateBarang: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error updateBarang: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    public boolean deleteBarang(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM barang WHERE id = ?");
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleteBarang: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error deleteBarang: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    public BarangModel getBarangById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BarangModel barang = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM barang WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String kode = resultSet.getString("kode");
                String nama = resultSet.getString("nama");
                String kategori = resultSet.getString("kategori");
                String satuan = resultSet.getString("satuan");
                int stok = resultSet.getInt("stok");
                int hargaSatuan = resultSet.getInt("hargaSatuan");

                barang = new BarangModel(id, kode, nama, kategori, satuan, stok, hargaSatuan);
            }
        } catch (SQLException e) {
            System.out.println("Error getBarangById: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getBarangById: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return barang;
    }

}
