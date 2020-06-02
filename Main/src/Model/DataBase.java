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
        //return sipActivation;
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

    public void saveActivaionSip() throws SQLException {
        String checkSql = "select count(*) as count from Activation_SIP where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql = "insert into Activation_SIP (CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,Infrastructure," +
                "TotalNumbers,TypeOfCalls,IdenteficationType,SNBnumber,NumberRange,InternetUser,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,RouterType,Codec," +
                "WanAddress,LanAddress,IPpbx,SignalIP,MediaIP,SBCport,Date,TotalCalls) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);

        String updateSql = "update Activation_SIP set CustomerID=?,CustomerName=?,ContactName=?,CustomerPhoneNumber=?,CustomerEmail=?,TechnicanName=?,TechnicanPhone=?,SwitchType=?,Infrastructure=?," +
                           "TotalNumbers=?,TypeOfCalls=?,IdenteficationType=?,SNBnumber=?,NumberRange=?,InternetUser=?,AreaCode=?,EmergancyCity=?,CallOutCountry=?,CRnumber=?,TrunkNumber=?,RouterType=?,Codec=?," +
                           "WanAddress=?,LanAddress=?,IPpbx=?,SignalIP=?,MediaIP=?,SBCport=?,Date=?,TotalCalls=? where id=?";
        PreparedStatement updateStmt = con.prepareStatement(updateSql);


        for (ActivationFormSip activationFormSip : sipActivation) {
            int id = activationFormSip.getId();
            String customerID = activationFormSip.getCustomerID();
            String customerName = activationFormSip.getCustomerName();
            String contactName = activationFormSip.getContactName();
            String customerPhoneNumber = activationFormSip.getCustomerPhoneNumber();
            String customerEmail = activationFormSip.getCustomerEmail();
            String customerTechName = activationFormSip.getCustomerTechName();
            String customerTechPhoneNumber = activationFormSip.getCustomerTechPhoneNumber();
            String pbxType = activationFormSip.getPbxType();
            String infrastructure = activationFormSip.getInfrastructure();
            int totalNumbers = activationFormSip.getTotalNumbers();
            String typeOfCalls = activationFormSip.getTypeOfCalls();
            String identificationType = activationFormSip.getIdentificationType();
            String snbNumber = activationFormSip.getSnbNumber();
            String numberRange = activationFormSip.getNumberRange();
            String internetUser = activationFormSip.getInternetUser();
            String areaCode = activationFormSip.getAreaCode();
            String emergencyCity = activationFormSip.getEmergencyCity();
            String callOutSideCountry = activationFormSip.getCallOutSideCountry();
            String crNumber = activationFormSip.getCrNumber();
            String trunkNumber = activationFormSip.getTrunkNumber();
            String routerType = activationFormSip.getRouterType();
            String CODEC = activationFormSip.getCODEC();
            String wanAddress = activationFormSip.getWanAddress();
            String lanAddress = activationFormSip.getLanAddress();
            String ipAddress = activationFormSip.getIpAddress();
            String signalAddress = activationFormSip.getSignalAddress();
            String mediaAddress = activationFormSip.getMediaAddress();
            int sbcPort = activationFormSip.getSbcPort();
            String datePicker = activationFormSip.getDatePicker();
            int totalCalls = activationFormSip.getTotalCalls();

            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting people with ID " + id);
                int col = 1;
                insertStmt.setString(col++, customerID);
                insertStmt.setString(col++, customerName);
                insertStmt.setString(col++, contactName);
                insertStmt.setString(col++, customerPhoneNumber);
                insertStmt.setString(col++, customerEmail);
                insertStmt.setString(col++, customerTechName);
                insertStmt.setString(col++, customerTechPhoneNumber);
                insertStmt.setString(col++, pbxType);
                insertStmt.setString(col++, infrastructure);
                insertStmt.setInt(col++, totalNumbers);
                insertStmt.setString(col++, typeOfCalls);
                insertStmt.setString(col++, identificationType);
                insertStmt.setString(col++, snbNumber);
                insertStmt.setString(col++, numberRange);
                insertStmt.setString(col++, internetUser);
                insertStmt.setString(col++, areaCode);
                insertStmt.setString(col++, emergencyCity);
                insertStmt.setString(col++, callOutSideCountry);
                insertStmt.setString(col++, crNumber);
                insertStmt.setString(col++, trunkNumber);
                insertStmt.setString(col++, routerType);
                insertStmt.setString(col++, CODEC);
                insertStmt.setString(col++, wanAddress);
                insertStmt.setString(col++, lanAddress);
                insertStmt.setString(col++, ipAddress);
                insertStmt.setString(col++, signalAddress);
                insertStmt.setString(col++, mediaAddress);
                insertStmt.setInt(col++, sbcPort);
                insertStmt.setString(col++, datePicker);
                insertStmt.setInt(col++, totalCalls);

                insertStmt.executeUpdate();

            } else {
                System.out.println("Updating people with ID " + id);
                int col = 1;
                updateStmt.setString(col++, customerID);
                updateStmt.setString(col++, customerName);
                updateStmt.setString(col++, contactName);
                updateStmt.setString(col++, customerPhoneNumber);
                updateStmt.setString(col++, customerEmail);
                updateStmt.setString(col++, customerTechName);
                updateStmt.setString(col++, customerTechPhoneNumber);
                updateStmt.setString(col++, pbxType);
                updateStmt.setString(col++, infrastructure);
                updateStmt.setInt(col++, totalNumbers);
                updateStmt.setString(col++, typeOfCalls);
                updateStmt.setString(col++, identificationType);
                updateStmt.setString(col++, snbNumber);
                updateStmt.setString(col++, numberRange);
                updateStmt.setString(col++, internetUser);
                updateStmt.setString(col++, areaCode);
                updateStmt.setString(col++, emergencyCity);
                updateStmt.setString(col++, callOutSideCountry);
                updateStmt.setString(col++, crNumber);
                updateStmt.setString(col++, trunkNumber);
                updateStmt.setString(col++, routerType);
                updateStmt.setString(col++, CODEC);
                updateStmt.setString(col++, wanAddress);
                updateStmt.setString(col++, lanAddress);
                updateStmt.setString(col++, ipAddress);
                updateStmt.setString(col++, signalAddress);
                updateStmt.setString(col++, mediaAddress);
                updateStmt.setInt(col++, sbcPort);
                updateStmt.setString(col++, datePicker);
                updateStmt.setInt(col++, totalCalls);
                updateStmt.setInt(col++, id);

                updateStmt.executeUpdate();
                System.out.println("count for person with ID " + id + " is " + count +  " col is : " + col);

            }
            System.out.println("count for person with ID " + id + " is " + count);

        }
        updateStmt.close();
        insertStmt.close();
        checkStmt.close();
    }

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
    public void loadCalenderSipActivation() throws SQLException {
        sipActivation.clear();
        String selectSql = "select id,CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,TypeOfCalls,IdenteficationType,TotalNumbers," +
                "SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,Date,WanAddress,LanAddress,IPpbx,InternetUser,Infrastructure," +
                "RouterType,Codec,TotalCalls,SignalIP,MediaIP,SBCport from Activation_SIP order by Date";
        Statement selectStatment = con.createStatement();

        ResultSet results = selectStatment.executeQuery(selectSql);

        while (results.next()) {
            int id = results.getInt("id");
            String CustomerID = results.getString("CustomerID");
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
            String date = results.getString("Date");
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


            ActivationFormSip activation = new ActivationFormSip(id,CustomerID,CustomerName,contactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,
                    TypeOfCalls,IdenteficationType,TotalNumbers,SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,date,WanAddress,LanAddress,IPpbx,InternetUser,
                    Infrastructure,RouterType,Codec,TotalCalls,SignalIP,MediaIP,port);
            sipActivation.add(activation);
        }
        selectStatment.close();
    }
}
