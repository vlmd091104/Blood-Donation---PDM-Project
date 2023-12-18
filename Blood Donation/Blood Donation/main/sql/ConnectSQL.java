package main.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.frames.HomeStruct;

public class ConnectSQL {
    public static String connectionString = "jdbc:sqlserver://pdm-blood-donation.database.windows.net:1433;database=BloodDonation;user=PDM@pdm-blood-donation;password=BloodDonation2023;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    //Method

    //Home Frame
    public static int loadID(String userType){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                Statement stmt = connection.createStatement();
                if(userType.equals("donor")){
                    String sql = "Select top 1 DonorID from DonorPassword order by DonorID desc";

                    ResultSet rs = stmt.executeQuery(sql);

                    if(rs.next()){
                        int ID = rs.getInt("DonorID");
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
                    String sql = "Select top 1 StaffID from StaffPassword order by StaffID desc";

                    ResultSet rs = stmt.executeQuery(sql);

                    if(rs.next()){
                        int ID = rs.getInt("StaffID");
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
                    String sql = "Insert into DonorPassword (DonorID, userName, DonorPassword) values (?, ?, ?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);

                    pStmt.setInt(1, ID);
                    pStmt.setString(2, user);
                    pStmt.setString(3, password);

                    pStmt.executeUpdate();
                }else if(userType.equals("staff")){
                    String sql = "Insert into StaffPassword (StaffID, userName, StaffPassword) values (?, ?, ?)";
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
                    String sql_1 = "Select DonorID from DonorPassword where Cast(userName as nvarchar(max)) = ? and Cast(DonorPassword as nvarchar(max)) = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql_1);

                    pStmt.setString(1, user);
                    pStmt.setString(2, password);

                    ResultSet rs_1 = pStmt.executeQuery();

                    if(rs_1.next()){
                        int ID = rs_1.getInt("DonorID");
                        String sql_2 = "Select Concat(FirstName,' ', LastName) as name from Donor where DonorID = ?";
                        PreparedStatement pStmt_2 = connection.prepareStatement(sql_2);

                        pStmt_2.setInt(1, ID);

                        HomeStruct.setID(ID);

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
                    String sql_1 = "Select StaffID from StaffPassword where Cast(userName as nvarchar(max)) = ? and Cast(StaffPassword as nvarchar(max)) = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql_1);

                    pStmt.setString(1, user);
                    pStmt.setString(2, password);

                    ResultSet rs_1 = pStmt.executeQuery();

                    if(rs_1.next()){
                        int ID = rs_1.getInt("ID");
                        String sql_2 = "Select Concat(FirstName,' ', LastName) as name from Staff where StaffID = ?";
                        PreparedStatement pStmt_2 = connection.prepareStatement(sql_2);

                        pStmt_2.setInt(1, ID);

                        HomeStruct.setID(ID);

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
    public static boolean addData(String userType, int ID, String FirstName, String LastName, String doB , String Gender, String Email, String Phone, String Address, String Role){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(userType.equals("donor")){
                    String sql = "Insert into Donor (DonorID, FirstName, LastName, DateOfBirth, Gender, Email, Phone, Address) values (? ,? ,? ,? ,? ,? ,? ,?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(1, ID);
                    pStmt.setString(2, FirstName);
                    pStmt.setString(3, LastName);
                    pStmt.setString(4, doB);
                    pStmt.setString(5, Gender);
                    pStmt.setString(6, Email);
                    pStmt.setString(7, Phone);
                    pStmt.setString(8, Address);

                    pStmt.executeUpdate();
                    return true;
                }else if(userType.equals("staff")){
                    String sql = "Insert into Staff (StaffID, FirstName, LastName, Email, Phone, Address, Role) values (? ,? ,? ,? ,? ,? ,?)";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(1, ID);
                    pStmt.setString(2, FirstName);
                    pStmt.setString(3, LastName);
                    pStmt.setString(4, Email);
                    pStmt.setString(5, Phone);
                    pStmt.setString(6, Address);
                    pStmt.setString(7, Role);

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

    //Info frame
    public static List<String> loadData(String userType, int ID){
    List<String> results = new ArrayList<>();
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(userType.equals("donor")){
                    String sql = "Select * from Donor where DonorID = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(1, ID);

                    ResultSet rs = pStmt.executeQuery();

                    while (rs.next()) {
                        String FirstName = rs.getString("FirstName");
                        String LastName = rs.getString("LastName");
                        String doB = rs.getString("DateOfBirth");
                        String Gender = rs.getString("Gender");
                        String Email = rs.getString("Email");
                        String Phone = rs.getString("Phone");
                        String Address = rs.getString("Address");

                        results.add(FirstName);
                        results.add(LastName);
                        results.add(doB);
                        results.add(Gender);
                        results.add(Email);
                        results.add(Phone);
                        results.add(Address);
                    }
                    
                }else if(userType.equals("staff")){
                    String sql = "Select * from Staff where StaffID = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(1, ID);
                    
                    ResultSet rs = pStmt.executeQuery();

                    while (rs.next()) {
                        String FirstName = rs.getString("FirstName");
                        String LastName = rs.getString("LastName");
                        String Email = rs.getString("Email");
                        String Phone = rs.getString("Phone");
                        String Address = rs.getString("Address");
                        String Role = rs.getString("Role");

                        results.add(FirstName);
                        results.add(LastName);
                        results.add(Email);
                        results.add(Phone);
                        results.add(Address);
                        results.add(Role);
                    }
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
        }
        return results;
    }

    public static boolean updateData(String userType, int ID, String FirstName, String LastName, String doB , String Gender, String Email, String Phone, String Address, String Role){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(userType.equals("donor")){
                    String sql = "Update Donor set FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Email = ?, Phone = ?, Address = ? where DonorID = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(8, ID);
                    pStmt.setString(1, FirstName);
                    pStmt.setString(2, LastName);
                    pStmt.setString(3, doB);
                    pStmt.setString(4, Gender);
                    pStmt.setString(5, Email);
                    pStmt.setString(6, Phone);
                    pStmt.setString(7, Address);

                    pStmt.executeUpdate();
                    return true;
                }else if(userType.equals("staff")){
                    String sql = "Update Staff set FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ?, Role = ? where StaffID = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    pStmt.setInt(7, ID);
                    pStmt.setString(1, FirstName);
                    pStmt.setString(2, LastName);
                    pStmt.setString(3, Email);
                    pStmt.setString(4, Phone);
                    pStmt.setString(5, Address);
                    pStmt.setString(6, Role);

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

    public static void adminQuery(String queryText, JScrollPane resultPanel){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                Statement stmt = connection.createStatement();
                String SQL = queryText;
                ResultSet rs = stmt.executeQuery(SQL);

                DefaultTableModel model = new DefaultTableModel();

                // Get column names
                ResultSetMetaData metaData = rs.getMetaData();
                int numOfColumns = metaData.getColumnCount();
                for(int i = 1; i <= numOfColumns; i++){
                    model.addColumn(metaData.getColumnName(i));
                }

                // Get rows
                while(rs.next()){
                    Object[] row = new Object[numOfColumns];
                    for(int i = 1; i <= numOfColumns; i++){
                        row[i-1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
                JTable resultTable = new JTable(model);
                resultPanel.setViewportView(resultTable);
                JOptionPane.showMessageDialog(null, "Run query successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e){
            String message = e.getMessage();
            JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void resetPassword(String userType, int ID, String password){
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(connectionString)){
                if(password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Password can not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(userType.equals("donor")){
                    String sql = "Update DonorPassword set DonorPassword = ? where DonorID = ?";
                    PreparedStatement pStmt = connection.prepareStatement(sql);
                    
                    pStmt.setInt(2, ID);
                    pStmt.setString(1, password);

                    pStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Reset password successfully", "Message", JOptionPane.WARNING_MESSAGE);
                    }else if(userType.equals("staff")){
                        String sql = "Update StaffPassword set StaffPassword = ? where StaffID = ?";
                        PreparedStatement pStmt = connection.prepareStatement(sql);
                        
                        pStmt.setInt(2, ID);
                        pStmt.setString(1, password);

                        pStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Reset password successfully", "Message", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                String message = e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver not found, pls reset the app", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }
}