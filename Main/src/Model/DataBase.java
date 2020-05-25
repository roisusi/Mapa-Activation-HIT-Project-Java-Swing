package Model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DataBase {
    private List<Login> users;
    private Connection con;


    public DataBase() {
        this.users = new LinkedList<Login>();
    }

/*    public void addPerson(Person person) {
        people.add(person);
    }*/

/*    public void removePerson(int index) {
        Person person = people.get(index);
        int id = person.getId();
        try {
            delete(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        people.remove(index);
    }*/

    public List<Login> getUsers() {
        return Collections.unmodifiableList(users);//prevent for other to change the list when they get REF , just get it
    }

    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            new Exception("Driver Not Found");
        }
        //String connectionUrl = "jdbc:mysql://localhost:3306/swingtest?autoReconnect=true&useSSL=false";
        String connectionUrl = "jdbc:mysql://mysql-9407-0.cloudclusters.net:9407/swingtest?autoReconnect=true&useSSL=false";
        con = DriverManager.getConnection(connectionUrl, "Roi", "prnm4400$");
        System.out.println("Connected to : " + con);

    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                System.out.println("Connection Closed");
            }
        }

    }

    public void delete(int id) throws SQLException {

        String selectSql = "select id from people where id=?";
        PreparedStatement checkStmt = con.prepareStatement(selectSql);

        String deleteSql = "delete from people where id=?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);

        for (Login login : users) {
            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }

        deleteStmt.close();

    }

/*    public void save() throws SQLException {
        String checkSql = "select count(*) as count from people where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql = "insert into people (id,name,age,employment_status,tax_id,us_citizen,gender,occupation) values(?,?,?,?,?,?,?,?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);

        String updateSql = "update people set name=?,age=?,employment_status=?,tax_id=?,us_citizen=?,gender=?,occupation=? where id=?";
        PreparedStatement updateStmt = con.prepareStatement(updateSql);


        for (Login login : users) {
            int id = person.getId();
            String name = person.getName();
            AgeCatagory age = person.getAge();
            EmploymendCatagory emp = person.getEmployed();
            String taxID = person.getTaxID();
            boolean isUS = person.isUsCitizen();
            Gender gender = person.getGender();
            String occupation = person.getOccupation();

            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting people with ID " + id);
                int col = 1;
                insertStmt.setInt(col++, id);
                insertStmt.setString(col++, name);
                insertStmt.setString(col++, age.name());
                insertStmt.setString(col++, emp.name());
                insertStmt.setString(col++, taxID);
                insertStmt.setBoolean(col++, isUS);
                insertStmt.setString(col++, gender.name());
                insertStmt.setString(col++, occupation);

                insertStmt.executeUpdate();

            } else {
                System.out.println("Updating people with ID " + id);
                int col = 1;
                updateStmt.setString(col++, name);
                updateStmt.setString(col++, age.name());
                updateStmt.setString(col++, emp.name());
                updateStmt.setString(col++, taxID);
                updateStmt.setBoolean(col++, isUS);
                updateStmt.setString(col++, gender.name());
                updateStmt.setString(col++, occupation);
                updateStmt.setInt(col++, id);

                updateStmt.executeUpdate();

            }
            System.out.println("count for person with ID " + id + " is " + count);


        }
        updateStmt.close();
        insertStmt.close();
        checkStmt.close();

    }*/

    public void load() throws SQLException {
        users.clear();
        String selectSql = "select id,Username,Password from SystemUsers";
        Statement selectStatment = con.createStatement();

        ResultSet results = selectStatment.executeQuery(selectSql);

        while (results.next()) {
            int id = results.getInt("id");
            String userName = results.getString("Username");
            String password = results.getString("Password");

            Login user = new Login(id,userName, password);
            users.add(user);
        }

        selectStatment.close();

    }
}
