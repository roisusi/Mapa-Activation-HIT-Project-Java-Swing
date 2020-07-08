package Model;

import View.ActivationsMoves;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private List<Users> systemUsers;
    private List<Login> users;
    private List<ActivationFormSip> sipActivation;
    private List<NumberRanges> numberRanges;
    private static Users loggedUser;
    private static Login LoginUser;
    private Connection con;

    public DataBase() {
        this.users = new LinkedList<Login>();
        this.systemUsers = new LinkedList<Users>();
        this.sipActivation = new LinkedList<ActivationFormSip>();
        this.numberRanges = new LinkedList<NumberRanges>();
    }

    public Connection getCon() { return con; }
    public void setLoggedUser(Users user) {
        loggedUser = user;
    }
    public Users getLoggedUser() {
        return loggedUser;
    }
    public void setLoginUser(Login login) {
        LoginUser = login;
    }
    public Login getLoginUser(){
        return LoginUser;
    }
    public void addActivationSipToList(ActivationFormSip sipAct) {
        sipActivation.add(sipAct);
    }
    public void addNumberRangeToList(NumberRanges sipNR) {
        numberRanges.add(sipNR);
    }
    public void addFirstNameToActivationList(int row, String firstName){
        sipActivation.get(row).setFirstName(firstName);
    }
    public void addFirstNameToActivationList(String status,int row){
        sipActivation.get(row).setStatus(status);
    }
    public List<ActivationFormSip> getActivationSipFromList(){
        return Collections.unmodifiableList(sipActivation);
    }
    public List<NumberRanges> getNumberRanges(){
        //return Collections.unmodifiableList(numberRanges);
        return numberRanges;
    }
    public void updateActivationSipToList(ActivationFormSip sipAct) {
        int i=0;
        for (ActivationFormSip activationFormSip : sipActivation ){
            if (sipAct.getId() == activationFormSip.getId()){
                sipActivation.get(i).setFirstName(sipAct.getFirstName());
                sipActivation.get(i).setStatus(sipAct.getStatus());
                sipActivation.get(i).setActivationType(sipAct.getActivationType());
                sipActivation.get(i).setAreaCode(sipAct.getAreaCode());
                sipActivation.get(i).setCallOutSideCountry(sipAct.getCallOutSideCountry());
                sipActivation.get(i).setCODEC(sipAct.getCODEC());
                sipActivation.get(i).setConnectionType(sipAct.getConnectionType());
                sipActivation.get(i).setContactName(sipAct.getContactName());
                sipActivation.get(i).setCrNumber(sipAct.getCrNumber());
                sipActivation.get(i).setCustomerEmail(sipAct.getCustomerEmail());
                sipActivation.get(i).setCustomerID(sipAct.getCustomerID());
                sipActivation.get(i).setCustomerName(sipAct.getCustomerName());
                sipActivation.get(i).setCustomerPhoneNumber(sipAct.getCustomerPhoneNumber());
                sipActivation.get(i).setCustomerTechName(sipAct.getCustomerTechName());
                sipActivation.get(i).setCustomerTechPhoneNumber(sipAct.getCustomerTechPhoneNumber());
                sipActivation.get(i).setDatePicker(sipAct.getDatePicker());
                sipActivation.get(i).setEmergencyCity(sipAct.getEmergencyCity());
                sipActivation.get(i).setIdentificationType(sipAct.getIdentificationType());
                sipActivation.get(i).setInfrastructure(sipAct.getInfrastructure());
                sipActivation.get(i).setInternetUser(sipAct.getInternetUser());
                sipActivation.get(i).setIpAddress(sipAct.getIpAddress());
                sipActivation.get(i).setLanAddress(sipAct.getLanAddress());
                sipActivation.get(i).setMediaAddress(sipAct.getMediaAddress());
                sipActivation.get(i).setPbxType(sipAct.getPbxType());
                sipActivation.get(i).setProjectManagerFirstName(sipAct.getProjectManagerFirstName());
                sipActivation.get(i).setRouterType(sipAct.getRouterType());
                sipActivation.get(i).setSbcPort(sipAct.getSbcPort());
                sipActivation.get(i).setSignalAddress(sipAct.getSignalAddress());
                sipActivation.get(i).setSnbNumber(sipAct.getSnbNumber());
                sipActivation.get(i).setTotalCalls(sipAct.getTotalCalls());
                sipActivation.get(i).setTotalNumbers(sipAct.getTotalNumbers());
                sipActivation.get(i).setTrunkNumber(sipAct.getTrunkNumber());
                sipActivation.get(i).setTypeOfCalls(sipAct.getTypeOfCalls());
                sipActivation.get(i).setWanAddress(sipAct.getWanAddress());
                sipActivation.get(i).setLastUpdate(sipAct.getLastUpdate());
                break;
            }
            i++;
        }
    }
    public void updateSystemUser(List usersList) throws SQLException {
        int size = usersList.size();
        String updateSql = "update Users set id = ?, FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Type = ?, UserNameId = ? where id = ?;";
        PreparedStatement preparedStatement = con.prepareStatement(updateSql);

        for (int i = 0; i < size; i++)
        {
            Users user = (Users) usersList.get(i);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getUsersType().toString());
            preparedStatement.setInt(7, user.getUserNameId());
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
    }
    public void updateLoginUser(List loginList) throws SQLException {
        int size = loginList.size();
        String updateSql = "update SystemUsers set id = ?, Username = ?, Password = ? where id = ?;";
        PreparedStatement preparedStatement = con.prepareStatement(updateSql);

        for (int i = 0; i < size; i++)
        {
            Login login = (Login)loginList.get(i);
            preparedStatement.setInt(1, login.getId());
            preparedStatement.setString(2, login.getUserName());
            preparedStatement.setString(3, login.getPassword());
            preparedStatement.setInt(4, login.getId());
            preparedStatement.executeUpdate();
        }
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
    public void updateActivationSipToDataBase(int id) throws SQLException {

        String checkSql = "select count(*) as count from Activation_SIP where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String updateSql = "update Activation_SIP set CustomerID=?,CustomerName=?,ContactName=?,CustomerPhoneNumber=?,CustomerEmail=?,TechnicanName=?,TechnicanPhone=?,SwitchType=?,Infrastructure=?," +
                "TotalNumbers=?,TypeOfCalls=?,IdenteficationType=?,SNBnumber=?,InternetUser=?,AreaCode=?,EmergancyCity=?,CallOutCountry=?,CRnumber=?,TrunkNumber=?,RouterType=?,Codec=?," +
                "WanAddress=?,LanAddress=?,IPpbx=?,SignalIP=?,MediaIP=?,SBCport=?,Date=?,TotalCalls=?,ConnectionType=?,ActivationType=?,Status=?,ExpertFirstName=?,ProjectManagerFirstName=?,LastUpdate=? where id=?";

        PreparedStatement updateStmt = con.prepareStatement(updateSql);
        for (ActivationFormSip activationFormSip : sipActivation) {
            if (activationFormSip.getId() == id) {
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
                String activationType = activationFormSip.getActivationType();
                String status = activationFormSip.getStatus();
                String expertFirstName = activationFormSip.getFirstName();
                String projectManagerFirstName = activationFormSip.getProjectManagerFirstName();
                String lastUpdate = activationFormSip.getLastUpdate();


                checkStmt.setInt(1, id);
                ResultSet checkResult = checkStmt.executeQuery();
                checkResult.next();

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
                updateStmt.setString(col++, connectionType);
                updateStmt.setString(col++, activationType);
                updateStmt.setString(col++, status);
                updateStmt.setString(col++, expertFirstName);
                updateStmt.setString(col++, projectManagerFirstName);
                updateStmt.setString(col++, lastUpdate);
                updateStmt.setInt(col++, id);
                updateStmt.executeUpdate();

            }
        }

        updateStmt.close();
        checkStmt.close();
    }
    public void insertingActivationSipToDataBase() throws SQLException {
        String checkSql = "select count(*) as count from Activation_SIP where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql = "insert into Activation_SIP (id,CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,Infrastructure," +
                "TotalNumbers,TypeOfCalls,IdenteficationType,SNBnumber,InternetUser,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,RouterType,Codec," +
                "WanAddress,LanAddress,IPpbx,SignalIP,MediaIP,SBCport,Date,TotalCalls,ConnectionType,ActivationType,ExpertFirstName,ProjectManagerFirstName,ActivationFailCounter,LastUpdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            int ActivationFailCounter = activationFormSip.getNumOfFails();
            String lastUpdate = activationFormSip.getLastUpdate();

            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting Activation with ID " + id);
                int col = 1;
                //-- get ID --//
                ActivationsMoves.SessionId.setNewID(id);

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
                insertStmt.setInt(col++, ActivationFailCounter);
                insertStmt.setString(col++, lastUpdate);

                insertStmt.executeUpdate();

            }
        }
        insertStmt.close();
        checkStmt.close();
    }
    public void insertingUserToDataBase(Users user, int userNameId, List<Users> usersList) throws SQLException {
        int lastMinId = 0;
        String checkSql = "select count(*) as count from Users where id = ?;";
        PreparedStatement checkStatement = con.prepareStatement(checkSql);

        String insertSql = "insert into Users (id, FirstName, LastName, Email, PhoneNumber, Type, UserNameId) values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        for (Users systemUser: usersList) {
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
    public void insertingLoginUserToDataBase(Login login, List<Login> loginList) throws SQLException {
        int lastMinId = 0;
        String checkSql = "select count(*) as count from SystemUsers where id = ?;";
        PreparedStatement checkStatement = con.prepareStatement(checkSql);

        String insertSql = "insert into SystemUsers (id, Username, Password) values (?, ?, ?);";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        for (Login loginUser: loginList) {
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
    public void insertingNumberRangeToDataBase(int activation_id) throws SQLException {


        String insertSql = "insert into NumberRange (numFrom,numTo,TrunkName,Activation_Id) values(?,?,?,?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);

        if (activation_id == 0)
            activation_id = ActivationsMoves.SessionId.getNewID();
            for (NumberRanges numberRanges : numberRanges){
            ArrayList<String> fromRange = numberRanges.getFromRange();
            ArrayList<String> toRange = numberRanges.getToRange();
            String trunkNumber = numberRanges.getTrunk();
                int i=0;
                while (i<fromRange.size() && fromRange != null && !fromRange.get(i).equals("") && toRange != null && !toRange.get(i).equals("")){
                    System.out.println("Inserting people with ID " + trunkNumber);
                    int col = 1;
                    insertStmt.setString(col++, fromRange.get(i));
                    insertStmt.setString(col++, toRange.get(i));
                    insertStmt.setString(col++, trunkNumber);
                    insertStmt.setInt(col++,activation_id);
                    insertStmt.executeUpdate();
                    i++;
                }
        }
        insertStmt.close();
    }
    public List<Login> loadSystemUsersFromDataBaseToList() throws SQLException {
        int id=0;
        List<Login> loginList = new LinkedList<Login>();
        String selectSql = "select * from SystemUsers";
        Statement selectStatement = con.createStatement();
        ResultSet results = selectStatement.executeQuery(selectSql);

        while (results.next()) {
            id = results.getInt("id");
            String userName = results.getString("Username");
            String password = results.getString("Password");

            Login login = new Login(id,userName, password);
            loginList.add(login);
        }
        selectStatement.close();
        return loginList;
    }
    public void loadCalenderSipActivationToList() throws SQLException {
        sipActivation.clear();
        String selectSql = "select id,CustomerID,CustomerName,ContactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,TypeOfCalls,IdenteficationType,TotalNumbers," +
                "SNBnumber,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,Date,WanAddress,LanAddress,IPpbx,InternetUser,Infrastructure," +
                "RouterType,Codec,TotalCalls,SignalIP,MediaIP,SBCport,ExpertFirstName,ConnectionType,ProjectManagerFirstName,ActivationType,Status,ActivationFailCounter,LastUpdate from Activation_SIP order by id";
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
            int activationFailCounter = results.getInt("ActivationFailCounter");
            String lastUpdate = results.getString("LastUpdate");

            ActivationFormSip activation = new ActivationFormSip(id,CustomerID,CustomerName,contactName,CustomerPhoneNumber,CustomerEmail,TechnicanName,TechnicanPhone,SwitchType,
                    TypeOfCalls,IdenteficationType,TotalNumbers,SNBnumber,AreaCode,EmergancyCity,CallOutCountry,CRnumber,TrunkNumber,date,WanAddress,LanAddress,IPpbx,InternetUser,
                    Infrastructure,RouterType,Codec,TotalCalls,SignalIP,MediaIP,port,firstName,connectionType,projectManagerFirstName,activationType,status,activationFailCounter,lastUpdate);
            sipActivation.add(activation);
        }
        selectStatment.close();
    }
    public List<Users> loadUsersFromDataBaseToList() throws SQLException {
        List<Users> usersList = new LinkedList<Users>();
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
            usersList.add(user);
        }
        selectStatement.close();
        return usersList;
    }
    public void loadNumberRangeFromDataBaseToList(int activation_id) throws SQLException {
        numberRanges.clear();
        String selectSql = "select NumFrom,NumTo,TrunkName from NumberRange where Activation_id=" + activation_id;
        Statement selectStatement = con.createStatement();
        ResultSet results = selectStatement.executeQuery(selectSql);

        ArrayList<String> from = new ArrayList<>();
        ArrayList<String> to = new ArrayList<>();
        String trunkName="";
        while (results.next()) {
            from.add(results.getString("NumFrom"));
            to.add(results.getString("NumTo"));
            trunkName = results.getString("TrunkName");
            NumberRanges numberRange = new NumberRanges(from,to,trunkName);
            numberRanges.add(numberRange);
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
    public void removeUserFromList(int id, int userNameId) {
        try {
            deleteUserFromDataBase(id);
            deleteLoginUserFromDataBase(userNameId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void clearListofNumberRange(){
        numberRanges.clear();
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
/*        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            new Exception("Driver Not Found");
        }
        //String connectionUrl = "jdbc:mysql://localhost:3306/swingtest?autoReconnect=true&useSSL=false";
        String connectionUrl = "jdbc:mysql://mysql-9407-0.cloudclusters.net:9407/swingtest?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8";
        con = DriverManager.getConnection(connectionUrl, "Roi", "prnm4400$");
        //System.out.println("Connected to : " + con);*/
        con = DatabaseConnection.getInstance().getConnection();
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
    public void updateNumberRangeToDataBase(int activation_id) throws SQLException {



        for (NumberRanges numberRanges : numberRanges){
            if(numberRanges.getFromRange() != null) {
                String deleteSql = "delete from NumberRange where activation_id=?";
                PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
                deleteStmt.setInt(1, activation_id);
                deleteStmt.executeUpdate();
                deleteStmt.close();
            }

      /*  String checkSql = "select * from NumberRange where id=?";
        checkStmt = con.prepareStatement(checkSql);*/

        String insertSql = "insert into NumberRange (numFrom,numTo,TrunkName,Activation_Id) values(?,?,?,?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);
        int i=0;
        if (activation_id == 0)
            activation_id = ActivationsMoves.SessionId.getNewID();

/*            ArrayList<String> fromRange = numberRanges.getFromRange();
            ArrayList<String> toRange = numberRanges.getToRange();*/
            String trunkNumber = numberRanges.getTrunk();

/*            checkStmt.setInt(1, Integer.parseInt(fromRange.get(0).toString()));
            checkResult = checkStmt.executeQuery();
            checkResult.next();*/

           // int count = checkResult.getInt(1);
            // &&
           // if (count == 0) {
            //&& !fromRange.get(i).equals("")  && !toRange.get(i).equals("")
            //fromRange != null && toRange != null &&

                while (numberRanges.getFromRange() != null && i<numberRanges.getFromRange().size() ){
                    System.out.println("Inserting Numbers with Activation ID " + activation_id);
                    int col = 1;
                    insertStmt.setString(col++, numberRanges.getFromRange().get(i));
                    insertStmt.setString(col++,  numberRanges.getToRange().get(i));
                    insertStmt.setString(col++, trunkNumber);
                    insertStmt.setInt(col++,activation_id);
                    insertStmt.executeUpdate();
                    i++;
                }
            insertStmt.close();
            }
        //}

    }
    public void failActivation(int activationId) throws SQLException {

        String updateSql = "update Activation_SIP set ActivationFailCounter=? where id=?";
        PreparedStatement updateStmt = con.prepareStatement(updateSql);

        for (ActivationFormSip activationFormSip : sipActivation) {
            if (activationFormSip.getId() == activationId) {
                int numOfFails = activationFormSip.getNumOfFails();
                numOfFails++;

                System.out.println("Updating FailActivation with ID " + activationId);
                int col = 1;

                updateStmt.setInt(col++, numOfFails);
                updateStmt.setInt(col++, activationId);
                updateStmt.executeUpdate();
            }
        }
        updateStmt.close();

    }
    public void getNumOfFails(int id){
        sipActivation.get(id).getNumOfFails();
    }
    public void clearNumberRange(){
        numberRanges.removeAll(numberRanges);
    }

}
