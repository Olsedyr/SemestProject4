package frontend.Backend;

import java.sql.*;

public class Warehouse {
    Connection connection;

    public Warehouse(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }


    public void viewInventory() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

        System.out.println("Current Inventory:");
        while (resultSet.next()) {
            String productName = resultSet.getString("name");
            int quantity = resultSet.getInt("quantity");
            System.out.println(productName + ": " + quantity);
        }

        resultSet.close();
        statement.close();
    }


    public void addProduct(String productName, int quantity) throws SQLException {
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (name, quantity) VALUES (?, ?)");
        preparedStatement.setString(1, productName);
        preparedStatement.setInt(2, quantity);
        preparedStatement.executeUpdate();

        System.out.println(quantity + " " + productName + "(s) added to warehouse.");

        preparedStatement.close();
    }


}