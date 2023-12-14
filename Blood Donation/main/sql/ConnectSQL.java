package main.sql;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectSQL {
    public static String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=test;user=sa;password=????;encrypt=true;trustServerCertificate=true";
    
    //Method

    //Home Frame
    public static int loadID(String userType){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                Statement stmt = connection.createStatement();
                if(userType.equals("donor")){
                    String sql = "Select top 1 ID from userPassword order by ID desc";

                    ResultSet rs = stmt.executeQuery(sql);

                    if(rs.next()){
                        int ID = rs.getInt("ID");
                        if(rs.wasNull()){
                            return 1;
                        }else{
                            ++ID;
                            return ID;
                        }
                    }else{
                        return 1;
                    }
                }else if(userType.equals("staff")){
                    String sql = "Select top 1 ID from staffPassword order by ID desc";

                    ResultSet rs = stmt.executeQuery(sql);

                    if(rs.next()){
                        int ID = rs.getInt("ID");
                        if(rs.wasNull()){
                            return 1;
                        }else{
                            ++ID;
                            return ID;
                        }
                    }else{
                        return 1;
                    }
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
        }
        return 0;
    }
    public static void addPassword(String userType, int ID, String user, String  password){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(userType.equals("donor")){
                    String sql = "Insert into userPassword (ID, userName, password) values (?, ?, ?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);

                    pStmt.setInt(1, ID);
                    pStmt.setString(2, user);
                    pStmt.setString(3, password);

                    pStmt.executeUpdate();
                }else if(userType.equals("staff")){
                    String sql = "Insert into staffPassword (ID, userName, password) values (?, ?, ?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);

                    pStmt.setInt(1, ID);
                    pStmt.setString(2, user);
                    pStmt.setString(3, password);

                    pStmt.executeUpdate();
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static boolean isValid(String userType, String user, String password){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(userType.equals("donor")){
                    String sql_1 = "Select ID from userPassword where Cast(userName as nvarchar(max)) = ? and Cast(password as nvarchar(max)) = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql_1);

                    pStmt.setString(1, user);
                    pStmt.setString(2, password);

                    ResultSet rs_1 = pStmt.executeQuery();

                    if(rs_1.next()){
                        int ID = rs_1.getInt("ID");
                        String sql_2 = "Select Concat(firstName,' ', lastName) as name from userTable where ID = ?";
                        PreparedStatement pStmt_2 = connection.prepareStatement(sql_2);

                        pStmt_2.setInt(1, ID);

                        ResultSet rs_2 = pStmt_2.executeQuery();

                        if(rs_2.next()){
                            String name = rs_2.getString("name");
                            JOptionPane.showMessageDialog(null, "Welcome back " + name, "Welcome", JOptionPane.DEFAULT_OPTION);

                            return true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Wrong ID or password", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }else if(userType.equals("staff")){
                    String sql_1 = "Select ID from staffPassword where Cast(userName as nvarchar(max)) = ? and Cast(password as nvarchar(max)) = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql_1);

                    pStmt.setString(1, user);
                    pStmt.setString(2, password);

                    ResultSet rs_1 = pStmt.executeQuery();

                    if(rs_1.next()){
                        int ID = rs_1.getInt("ID");
                        String sql_2 = "Select Concat(firstName,' ', lastName) as name from staffTable where ID = ?";
                        PreparedStatement pStmt_2 = connection.prepareStatement(sql_2);

                        pStmt_2.setInt(1, ID);

                        ResultSet rs_2 = pStmt_2.executeQuery();

                        if(rs_2.next()){
                            String name = rs_2.getString("name");
                            JOptionPane.showMessageDialog(null, "Welcome back " + name, "Welcome", JOptionPane.DEFAULT_OPTION);

                            return true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Wrong ID or password", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }else if(userType == "admin"){
                    String sql = "Select * from adminPassword where Cast(userName as nvarchar(max)) = ? and Cast(password as nvarchar(max)) = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql);

                    pStmt.setString(1, user);
                    pStmt.setString(2, password);

                    ResultSet rs_1 = pStmt.executeQuery();

                    if(rs_1.next()){
                        JOptionPane.showMessageDialog(null, "Welcome back Admin", "Welcome", JOptionPane.DEFAULT_OPTION);
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "Wrong ID or password", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return false;
    }
    
    //SignUp Frame
    public static boolean addData(String userType, int ID, String firstName, String lastName, String doB , String gender, String email, String phone, String address, String role){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(userType.equals("donor")){
                    String sql = "Insert into userTable (ID, firstName, lastName, doB, gender, email, phone, address) values (? ,? ,? ,? ,? ,? ,? ,?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(1, ID);
                    pStmt.setString(2, firstName);
                    pStmt.setString(3, lastName);
                    pStmt.setString(4, doB);
                    pStmt.setString(5, gender);
                    pStmt.setString(6, email);
                    pStmt.setString(7, phone);
                    pStmt.setString(8, address);

                    pStmt.executeUpdate();
                    return true;
                }else if(userType.equals("staff")){
                    String sql = "Insert into staffTable (ID, firstName, lastName, email, phone, address, role) values (? ,? ,? ,? ,? ,? ,?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(1, ID);
                    pStmt.setString(2, firstName);
                    pStmt.setString(3, lastName);
                    pStmt.setString(4, email);
                    pStmt.setString(5, phone);
                    pStmt.setString(6, address);
                    pStmt.setString(7, role);

                    pStmt.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return false;
    }
}