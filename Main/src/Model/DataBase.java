package Model;

import java.sql.*;
import java.util.*;

public class DataBase {
    private List<Users> systemUsers;
    private List<Login> users;
    private List<ActivationFormSip> sipActivation;
    private ActivationFormSip singleActivationFormSip;
    private static Users loggedUser;
    private Connection con;

    public DataBase() {
        this.users = new LinkedList<Login>();
        this.systemUsers = new LinkedList<Users>();
        this.sipActivation = new LinkedList<ActivationFormSip>();
    }

    public void addActivationSipToList(ActivationFormSip sipAct) {
        sipActivation.add(sipAct);
    }

    public void addFirstNameToActivationList(int row, String firstName){
        sipActivation.get(row).setFirstName(firstName);
    }

    public void addFirstNameToActivationList(String status,int row){
        sipActivation.get(row).setStatus(status);
    }

    public void addUserToList(Users user) {
        systemUsers.add(user);
    }

    public List<Login> getLoginUsersFromList() {
        return Collections.unmodifiableList(users);//prevent for other to change the list when they get REF , just get it
    }

    public List<Users> getUsersFromList() {
        return Collections.unmodifiableList(systemUsers);
    }

    public List<ActivationFormSip> getActivationSipFromList(){
        return Collections.unmodifiableList(sipActivation);
        //return sipActivation;
    }

    public Users getUserFirstNameLogged(){
        return loggedUser;
    }

    public ActivationFormSip getSingleActivationSip(int row) throws SQLException {
        String selectSql = "select id,CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,TypeOfCalls,IdenteficationType,TotalNumbers," +
                "SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,Date,WanAddress,LanAddress,IPpbx,InternetUser,Infrastructure," +
                "RouterType,Codec,TotalCalls,SignalIP,MediaIP,SBCport,FirstName,ActivationType,Status from Activation_SIP where ";
        Statement selectStatment = con.createStatement();

        ResultSet results = selectStatment.executeQuery(selectSql);

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
            String firstName = results.getString("FirstName");
            String connectionType = results.getString("ConnectionType");
            String projectManagerFirstName = results.getString("ProjectManagerFirstName");
            String activationType = results.getString("ActivationType");
            String status = results.getString("Status");

            singleActivationFormSip= new ActivationFormSip(id,CustomerID,CustomerName,contactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,
                    TypeOfCalls,IdenteficationType,TotalNumbers,SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,date,WanAddress,LanAddress,IPpbx,InternetUser,
                    Infrastructure,RouterType,Codec,TotalCalls,SignalIP,MediaIP,port,firstName,connectionType,projectManagerFirstName,activationType,status);

        selectStatment.close();
        return singleActivationFormSip;
    }

    public boolean isUserAlreadyExists(Users user){
        boolean flag = false;

        for(Users systemUser : systemUsers) {
            if(systemUser.getFirstName().equals(user.getFirstName()) && systemUser.getLastName().equals(user.getLastName()))
                flag = true;
        }
        return flag;
    }

    public boolean isLoginUserAlreadyExists(Login login){
        boolean flag = false;

        for(Login user : users) {
            if(user.getUserName().equals(login.getUserName()))
                flag = true;
        }
        return flag;
    }

    public void updateSystemUser(Object obj, int row, int column) throws SQLException {
        String updateSql = null;
        Users user = systemUsers.get(row);

        switch(column){
            case 0:
                updateSql = "update Users set FirstName = ? where id = ?;";
                break;
            case 1:
                updateSql = "update Users set LastName = ? where id = ?;";
                break;
            case 2:
                updateSql = "update Users set Email = ? where id = ?;";
                break;
            case 3:
                updateSql = "update Users set PhoneNumber = ? where id = ?;";
                break;
            case 4:
                updateSql = "update Users set UserType = ? where id = ?;";
                break;
        }

        PreparedStatement preparedStatement = con.prepareStatement(updateSql);
        preparedStatement.setString(1, obj.toString());
        preparedStatement.setInt(2,  user.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateLoginUser(Object obj, int row, int column) throws SQLException {
        String updateSql = null;
        Login login = users.get(row);

        switch(column){
            case 5:
                updateSql = "update SystemUsers set Usersname = ? where id = ?;";
                break;
            case 6:
                updateSql = "update SystemUsers set Password = ? where id = ?;";
                break;
        }

        PreparedStatement preparedStatement = con.prepareStatement(updateSql);
        preparedStatement.setString(1, obj.toString());
        preparedStatement.setInt(2, login.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateUserExpertFirstName(int row , String firstName) throws SQLException {
        ActivationFormSip activationFormSip = sipActivation.get(row);
        String updateSql = "update Activation_SIP set ExpertFirstName=? where id=?";
        PreparedStatement updateStmt = con.prepareStatement(updateSql);
        System.out.println("Updating people with ID " + activationFormSip.getId());
        int col = 1;
        updateStmt.setString(col++, firstName);
        updateStmt.setInt(col++, activationFormSip.getId());
        updateStmt.executeUpdate();
        updateStmt.close();
    }

    public void updateStatus(String status,int row) throws SQLException {
        ActivationFormSip activationFormSip = sipActivation.get(row);
        String updateSql = "update Activation_SIP set Status=? where id=?";
        PreparedStatement updateStmt = con.prepareStatement(updateSql);
        System.out.println("Updating people with ID " + activationFormSip.getId());
        int col = 1;
        updateStmt.setString(col++, status);
        updateStmt.setInt(col++, activationFormSip.getId());
        updateStmt.executeUpdate();
        updateStmt.close();
    }

    public void updateActivationSip() throws SQLException {

        String checkSql = "select count(*) as count from Activation_SIP where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String updateSql = "update Activation_SIP set CustomerID=?,CustomerName=?,ContactName=?,CustomerPhoneNumber=?,CustomerEmail=?,TechnicanName=?,TechnicanPhone=?,SwitchType=?,Infrastructure=?," +
                "TotalNumbers=?,TypeOfCalls=?,IdenteficationType=?,SNBnumber=?,NumberRange=?,InternetUser=?,AreaCode=?,EmergancyCity=?,CallOutCountry=?,CRnumber=?,TrunkNumber=?,RouterType=?,Codec=?," +
                "WanAddress=?,LanAddress=?,IPpbx=?,SignalIP=?,MediaIP=?,SBCport=?,Date=?,TotalCalls=?,ConnectionType=? where id=?";

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
            String connectionType = activationFormSip.getConnectionType();

            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);
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
            updateStmt.setString(col++, connectionType);
        }

        updateStmt.executeUpdate();
        updateStmt.close();
        checkStmt.close();
    }

    public void insertingActivationSipToDataBase() throws SQLException {
        String checkSql = "select count(*) as count from Activation_SIP where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql = "insert into Activation_SIP (id,CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,Infrastructure," +
                "TotalNumbers,TypeOfCalls,IdenteficationType,SNBnumber,NumberRange,InternetUser,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,RouterType,Codec," +
                "WanAddress,LanAddress,IPpbx,SignalIP,MediaIP,SBCport,Date,TotalCalls,ConnectionType,ActivationType,ExpertFirstName,ProjectManagerFirstName) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);

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
            String connectionType = activationFormSip.getConnectionType();
            String activationType = ActivationType.Sip.toString();
            String expertFirstName = activationFormSip.getFirstName();
            String projectManagerFirstName = activationFormSip.getProjectManagerFirstName();

            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting people with ID " + id);
                int col = 1;
                insertStmt.setInt(col++,id);
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
                insertStmt.setString(col++, connectionType);
                insertStmt.setString(col++, activationType);
                insertStmt.setString(col++, expertFirstName);
                insertStmt.setString(col++, projectManagerFirstName);

                insertStmt.executeUpdate();

            }
            //System.out.println("count for person with ID " + id + " is " + count);
        }
        insertStmt.close();
        checkStmt.close();
    }

    public void insertingUserToDataBase(Users user, int userNameId) throws SQLException {
        int lastMinId = 0;
        String checkSql = "select count(*) as count from Users where id = ?;";
        PreparedStatement checkStatement = con.prepareStatement(checkSql);

        String insertSql = "insert into Users (id, FirstName, LastName, Email, PhoneNumber, Type, UserNameId) values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        for (Users systemUser: systemUsers) {
            int id = systemUser.getId();

            if(id - lastMinId > 1) {
                lastMinId++;
                id = lastMinId;
                user.setUserNameId(userNameId);
                user.setId(id);

                checkStatement.setInt(1, id);
                ResultSet checkResult = checkStatement.executeQuery();
                checkResult.next();

                int count = checkResult.getInt(1);

                if (count == 0) {
                    insertStatement.setInt(1, user.getId());
                    insertStatement.setString(2, user.getFirstName());
                    insertStatement.setString(3, user.getLastName());
                    insertStatement.setString(4, user.getEmail());
                    insertStatement.setString(5, user.getPhoneNumber());
                    insertStatement.setString(6, user.getUsersType().toString());
                    insertStatement.setInt(7, user.getUserNameId());
                    insertStatement.executeUpdate();
                }
                break;
            }
            lastMinId++;
        }

        insertStatement.close();
        checkStatement.close();
    }

    public void insertingLoginUserToDataBase(Login login) throws SQLException {
        int lastMinId = 0;
        String checkSql = "select count(*) as count from SystemUsers where id = ?;";
        PreparedStatement checkStatement = con.prepareStatement(checkSql);

        String insertSql = "insert into SystemUsers (id, Username, Password) values (?, ?, ?);";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        for (Login loginUser: users) {
            int id = loginUser.getId();

            if(id - lastMinId > 1) {
                lastMinId++;
                id = lastMinId;
                login.setId(id);

                checkStatement.setInt(1, id);
                ResultSet checkResult = checkStatement.executeQuery();
                checkResult.next();

                int count = checkResult.getInt(1);

                if (count == 0) {
                    insertStatement.setInt(1, login.getId());
                    insertStatement.setString(2, login.getUserName());
                    insertStatement.setString(3, login.getPassword());
                    insertStatement.executeUpdate();
                }
                break;
            }
            lastMinId++;
        }

        insertStatement.close();
        checkStatement.close();
    }

    public void loadLoggedUser(int id) throws SQLException {
        String selectSql2 = "select id,FirstName,LastName,Email,PhoneNumber,Type,UserNameId from Users where UserNameId in (select id from SystemUsers where id="+id+")";
        Statement selectStatment2 = con.createStatement();

        ResultSet results2 = selectStatment2.executeQuery(selectSql2);

        while (results2.next()) {
            id = results2.getInt("id");
            String firstName = results2.getString("FirstName");
            String lastName = results2.getString("LastName");
            String email = results2.getString("Email");
            String phoneNumber = results2.getString("PhoneNumber");
            String usersType = results2.getString("Type");
            int userNameId = results2.getInt("UserNameId");

            switch (usersType)
            {
                case "PrimaryManager":
                    loggedUser = new PrimaryManager(id,firstName,lastName,email,phoneNumber,UsersType.PrimaryManager,userNameId);
                    break;
                case "ProjectManager":
                    loggedUser = new ProjectManager(id,firstName,lastName,email,phoneNumber,UsersType.ProjectManager,userNameId);
                    break;
                case "Expert":
                    loggedUser = new Expert(id,firstName,lastName,email,phoneNumber,UsersType.Expert,userNameId);
                    break;
            }
            //loggedUser = new Users(id,firstName,lastName,email,phoneNumber,UsersType.Expert,userNameId);

        }
        selectStatment2.close();
    }

    public void loadUsersFromDataBaseToList() throws SQLException {
        users.clear();
        int id=0;
        String selectSql = "select id,Username,Password from SystemUsers";
        Statement selectStatment = con.createStatement();

        ResultSet results = selectStatment.executeQuery(selectSql);
        while (results.next()) {
            id = results.getInt("id");
            String userName = results.getString("Username");
            String password = results.getString("Password");

            Login user = new Login(id,userName, password);
            users.add(user);
        }
        selectStatment.close();
    }

    public void loadCalenderSipActivationToList() throws SQLException {
        sipActivation.clear();
        String selectSql = "select id,CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,TypeOfCalls,IdenteficationType,TotalNumbers," +
                "SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,Date,WanAddress,LanAddress,IPpbx,InternetUser,Infrastructure," +
                "RouterType,Codec,TotalCalls,SignalIP,MediaIP,SBCport,ExpertFirstName,ConnectionType,ProjectManagerFirstName,ActivationType,Status from Activation_SIP order by id";
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
            String firstName = results.getString("ExpertFirstName");
            String connectionType = results.getString("ConnectionType");
            String projectManagerFirstName = results.getString("ProjectManagerFirstName");
            String activationType = results.getString("ActivationType");
            String status = results.getString("Status");

            ActivationFormSip activation = new ActivationFormSip(id,CustomerID,CustomerName,contactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,
                    TypeOfCalls,IdenteficationType,TotalNumbers,SNBnumber,NumberRange,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,date,WanAddress,LanAddress,IPpbx,InternetUser,
                    Infrastructure,RouterType,Codec,TotalCalls,SignalIP,MediaIP,port,firstName,connectionType,projectManagerFirstName,activationType,status);
            sipActivation.add(activation);
        }
        selectStatment.close();
    }

    public void loadSystemUsersFromDataBaseToList() throws SQLException {
        systemUsers.clear();
        String selectSql = "select * from Users";
        Statement selectStatement = con.createStatement();
        ResultSet results = selectStatement.executeQuery(selectSql);

        while (results.next()) {
            int id = results.getInt("id");
            String firstName = results.getString("FirstName");
            String lastName = results.getString("LastName");
            String email = results.getString("Email");
            String phoneNumber = results.getString("PhoneNumber");
            UsersType type = UsersType.valueOf(results.getString("Type"));
            int userNameId = results.getInt("UserNameId");

            Users user = new Users(id, firstName, lastName, email, phoneNumber, type, userNameId);
            systemUsers.add(user);
        }
        selectStatement.close();
    }

    public void removeActivationFromList(int row) {
        ActivationFormSip activationFormSip = sipActivation.get(row);
        int id = activationFormSip.getId();
        try {
            deleteActivationFromDataBase(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sipActivation.remove(row);
    }

    public void removeUserFromList(int row) {
        Users user = systemUsers.get(row);
        int id = user.getId();
        int userNameId = user.getUserNameId();
        try {
            deleteUserFromDataBase(id);
            deleteLoginUserFromDataBase(userNameId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        systemUsers.remove(row);
    }

    public void deleteActivationFromDataBase(int id) throws SQLException {

        String selectSql = "select id from Activation_SIP where id=?";
        PreparedStatement checkStmt = con.prepareStatement(selectSql);

        String deleteSql = "delete from Activation_SIP where id=?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);

        checkStmt.setInt(1, id);
        ResultSet checkResult = checkStmt.executeQuery();
        checkResult.next();
        deleteStmt.setInt(1, id);
        deleteStmt.executeUpdate();

        deleteStmt.close();
    }

    public void deleteUserFromDataBase(int id) throws SQLException {

        String selectSql = "select id from Users where id=?";
        PreparedStatement checkStatement = con.prepareStatement(selectSql);

        String deleteSql = "delete from Users where id=?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);

        checkStatement.setInt(1, id);
        ResultSet checkResult = checkStatement.executeQuery();
        checkResult.next();
        deleteStmt.setInt(1, id);
        deleteStmt.executeUpdate();

        deleteStmt.close();
    }

    public void deleteLoginUserFromDataBase(int id) throws SQLException {

        String selectSql = "select id from SystemUsers where id=?";
        PreparedStatement checkStatement = con.prepareStatement(selectSql);

        String deleteSql = "delete from SystemUsers where id=?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);

        checkStatement.setInt(1, id);
        ResultSet checkResult = checkStatement.executeQuery();
        checkResult.next();
        deleteStmt.setInt(1, id);
        deleteStmt.executeUpdate();

        deleteStmt.close();
    }

    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            new Exception("Driver Not Found");
        }
        //String connectionUrl = "jdbc:mysql://localhost:3306/swingtest?autoReconnect=true&useSSL=false";
        String connectionUrl = "jdbc:mysql://mysql-9407-0.cloudclusters.net:9407/swingtest?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8";
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
}
