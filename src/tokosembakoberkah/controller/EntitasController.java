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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import tokosembakoberkah.model.EntitasModel;
import tokosembakoberkah.util.DatabaseUtil;

public class EntitasController {

    public int getCounts() {
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM entitas");

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getCount: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getCount: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return count;
    }

    public List<EntitasModel> getEntitasByPage(int pageNumber, int pageSize) {
        List<EntitasModel> entitasList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();

            int offset = (pageNumber - 1) * pageSize;
            String query = "SELECT * FROM entitas ORDER BY id LIMIT " + offset + ", " + pageSize;

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String nama = resultSet.getString("nama");
                String no_telp = resultSet.getString("no_telp");
                String alamat = resultSet.getString("alamat");
                EntitasModel entitas = new EntitasModel(id, type, nama, no_telp, alamat);
                entitasList.add(entitas);
            }
        } catch (SQLException e) {
            System.out.println("Error getEntitasByPage: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getEntitasByPage: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return entitasList;
    }

    public boolean addEntitas(EntitasModel entitas) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO entitas (type, nama, alamat, no_telp) VALUES (?, ?, ?, ?)");
            statement.setString(1, entitas.getType());
            statement.setString(2, entitas.getNama());
            statement.setString(3, entitas.getAlamat());
            statement.setString(4, entitas.getNoTelp());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error addEntitas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error addEntitas: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    public EntitasModel getEntitasById(int id) {
        EntitasModel entitas = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM entitas WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String type = resultSet.getString("type");
                String nama = resultSet.getString("nama");
                String alamat = resultSet.getString("alamat");
                String noTlpn = resultSet.getString("no_telp");

                entitas = new EntitasModel(id, type, nama, noTlpn, alamat);
            }
        } catch (SQLException e) {
            System.out.println("Error getEntitasById: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getEntitasById: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return entitas;
    }

    public boolean updateEntitas(EntitasModel entitas) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("UPDATE entitas SET type = ?, nama = ?, no_telp = ? , alamat = ? WHERE id = ?");
            statement.setString(1, entitas.getType());
            statement.setString(2, entitas.getNama());
            statement.setString(3, entitas.getNoTelp());
            statement.setString(4, entitas.getAlamat());
            statement.setInt(5, entitas.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updateEntitas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error updateEntitas: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    public boolean deleteEntitas(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM entitas WHERE id = ?");
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleteEntitas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error deleteEntitas: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

}
