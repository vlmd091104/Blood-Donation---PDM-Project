package main.sql;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectSQL {
    public static String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=test;user=sa;password=???;encrypt=true;trustServerCertificate=true";
    
    //Method
    public static String loadID(){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                Statement stmt = connection.createStatement();
                String sql = "Select top 1 ID from testing order by ID desc";

                ResultSet rs = stmt.executeQuery(sql);

                if(rs.next()){
                    int user = rs.getInt("ID");
                    if(rs.wasNull()){
                        return String.valueOf(1);
                    }else{
                        ++user;
                        return String.valueOf(user);
                    }
                }else{
                    return String.valueOf(1);
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            // resultTextArea.setText("Driver not found");
            JOptionPane.showMessageDialog(null, "Driver not found", "Message", JOptionPane.WARNING_MESSAGE);
        }
        return "There is some error";
    }
    public static void addPassword(int user, String  password){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                String sql = "Insert into testing (ID, password) values (?, ?)";
                PreparedStatement pStmt = connection.prepareStatement(sql);

                pStmt.setInt(1, user);
                pStmt.setString(2, password);

                pStmt.executeUpdate();

                // pStmt.close();
                // connection.close();
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            // resultTextArea.setText("Driver not found");
            JOptionPane.showMessageDialog(null, "Driver not found", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void isValid(int user, String password){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                String sql = "Select * from testing where ID = ? and Cast(password as nvarchar(max)) = ?";
                PreparedStatement pStmt = connection.prepareStatement(sql);

                pStmt.setInt(1, user);
                pStmt.setString(2, password);

                ResultSet rs = pStmt.executeQuery();

                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Hello", "Welcome", JOptionPane.DEFAULT_OPTION);
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong ID or password", "Warning", JOptionPane.WARNING_MESSAGE);
                }

                rs.close();
                pStmt.close();
                connection.close();
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            // resultTextArea.setText("Driver not found");
            JOptionPane.showMessageDialog(null, "Driver not found", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }
}
