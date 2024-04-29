package frontend.Backend;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/your_database";
        String user = "your_username";
        String password = "your_password";

        try {
            Warehouse warehouse = new Warehouse(url, user, password);


            warehouse.addProduct("S1", 10);
            warehouse.addProduct("S2", 15);
            warehouse.addProduct("S3", 20);

            warehouse.viewInventory();


            warehouse.addProduct("S4", 5);
            warehouse.addProduct("S5", -2);

            warehouse.viewInventory();


            warehouse.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
