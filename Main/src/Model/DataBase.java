package Model;

import View.ActivationFormSIP;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DataBase {
    private List<Login> users;
    private List<ActivationFormSip> sipActivation;
    private Connection con;


    public DataBase() {
        this.users = new LinkedList<Login>();
        this.sipActivation = new LinkedList<ActivationFormSip>();
    }

    public void addActivationSip(ActivationFormSip sipAct) {
        sipActivation.add(sipAct);
    }

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

    public List<ActivationFormSip> getSipActivation(){
        return Collections.unmodifiableList(sipActivation);
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
        //System.out.println("Connected to : " + con);
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

    public void loadUsers() throws SQLException {
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
    public void loadCalanderSipActivation() throws SQLException {
        sipActivation.clear();
        String selectSql = "select CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,TypeOfCalls,IdenteficationType,TotalNumbers," +
                "SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,Date,WanAddress,LanAddress,IPpbx,InternetUser,Infrastructure," +
                "RouterType,Codec,TotalCalls,SignalIP,MediaIP,SBCport from Activation_SIP";
        Statement selectStatment = con.createStatement();

        ResultSet results = selectStatment.executeQuery(selectSql);

        while (results.next()) {
            int CustomerID = results.getInt("CustomerID");
            String CustomerName = results.getString("CustomerName");
            String contactName = results.getString("ContactName");
            String CustomerPhoneNumber = results.getString("CustomerPhoneNumber");
            String CustomerEmail = results.getString("CustomerEmail");
            String TechnicanName = results.getString("TechnicanName");
            String TechnicanPhone = results.getString("TechnicanPhone");
            String SwitchType = results.getString("SwitchType");
            String TypeOfCalls = results.getString("TypeOfCalls");
            String IdenteficationType = results.getString("IdenteficationType");
            int TotalNumbers = results.getInt("TotalNumbers");
            String SNBnumber = results.getString("SNBnumber");
            String NumberRange = results.getString("NumberRange");
            String AreaCode = results.getString("AreaCode");
            String EmergancyCity = results.getString("EmergancyCity");
            String CallOutCountry = results.getString("CallOutCountry");
            String CRnumber = results.getString("CRnumber");
            String TrunkNumber = results.getString("TrunkNumber");
            Date date = results.getDate("Date");
            String WanAddress = results.getString("WanAddress");
            String LanAddress = results.getString("LanAddress");
            String IPpbx = results.getString("IPpbx");
            String InternetUser = results.getString("InternetUser");
            String Infrastructure = results.getString("Infrastructure");
            String RouterType = results.getString("RouterType");
            String Codec = results.getString("Codec");
            int TotalCalls = results.getInt("TotalCalls");
            String SignalIP = results.getString("SignalIP");
            String MediaIP = results.getString("MediaIP");
            int port = results.getInt("SBCport");


            ActivationFormSip activation = new ActivationFormSip(CustomerID,CustomerName,contactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,
                    TypeOfCalls,IdenteficationType,TotalNumbers,SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,date,WanAddress,LanAddress,IPpbx,InternetUser,
                    Infrastructure,RouterType,Codec,TotalCalls,SignalIP,MediaIP,port);
            sipActivation.add(activation);
        }
        selectStatment.close();
    }
}
