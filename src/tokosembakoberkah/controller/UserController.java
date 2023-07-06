/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import tokosembakoberkah.model.UserModel;
import tokosembakoberkah.util.DatabaseUtil;

/**
 *
 * @author Lenovo
 */
public class UserController {

    private static UserModel currentUser;

    // ...
    public static void setCurrentUser(UserModel user) {
        currentUser = user;
    }

    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public UserModel login(String username, String password) {
        UserModel user = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String status = resultSet.getString("status");

                user = new UserModel(id, nama, username, password, status);
            }
        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error login: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }

        return user;
    }
}
