package View;

import java.util.ArrayList;
import java.util.EventObject;

public class FormEvent extends EventObject {
    private int id;
    private String customerID;
    private String customerName;
    private String contactName;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerTechName;
    private String customerTechPhoneNumber;
    private String pbxType;
    private String typeOfCalls;
    private String identificationType;
    private int totalNumbers;
    private String snbNumber;
    private String areaCode;
    private String emergencyCity; //need to change by all Cities of EMS
    private String callOutSideCountry;
    private String crNumber;
    private String trunkNumber;
    private String datePicker;
    private String wanAddress;
    private String lanAddress;
    private String ipAddress;
    private String internetUser;
    private String infrastructure;
    private String routerType;
    private String CODEC;
    private int totalCalls;
    private String signalAddress;
    private String mediaAddress;
    private int sbcPort;
    private String expertName;
    private String connectionType;
    private String projectManagerFirstName;
    private ArrayList<String> from;
    private ArrayList<String> to;
    private int numofFails;
    private String status;
    private String lastUpdate;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, int id, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String expertName, String connectionType,  String projectManagerFirstName,String status,String lastUpdate) {
        super(source);
        this.id = id;
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactName = contactName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.customerTechName = customerTechName;
        this.customerTechPhoneNumber = customerTechPhoneNumber;
        this.pbxType = pbxType;
        this.typeOfCalls = typeOfCalls;
        this.identificationType = identificationType;
        this.totalNumbers = totalNumbers;
        this.snbNumber = snbNumber;
        this.areaCode = areaCode;
        this.emergencyCity = emergencyCity;
        this.callOutSideCountry = callOutSideCountry;
        this.crNumber = crNumber;
        this.trunkNumber = trunkNumber;
        this.datePicker = datePicker;
        this.wanAddress = wanAddress;
        this.lanAddress = lanAddress;
        this.ipAddress = ipAddress;
        this.internetUser = internetUser;
        this.infrastructure = infrastructure;
        this.routerType = routerType;
        this.CODEC = CODEC;
        this.totalCalls = totalCalls;
        this.signalAddress = signalAddress;
        this.mediaAddress = mediaAddress;
        this.sbcPort = sbcPort;
        this.expertName = expertName;
        this.connectionType = connectionType;
        this.projectManagerFirstName = projectManagerFirstName;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public FormEvent(Object source, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String expertName, String connectionType, String projectManagerFirstName, int numofFails, String status, String lastUpdate) {
        super(source);
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactName = contactName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.customerTechName = customerTechName;
        this.customerTechPhoneNumber = customerTechPhoneNumber;
        this.pbxType = pbxType;
        this.typeOfCalls = typeOfCalls;
        this.identificationType = identificationType;
        this.totalNumbers = totalNumbers;
        this.snbNumber = snbNumber;
        this.areaCode = areaCode;
        this.emergencyCity = emergencyCity;
        this.callOutSideCountry = callOutSideCountry;
        this.crNumber = crNumber;
        this.trunkNumber = trunkNumber;
        this.datePicker = datePicker;
        this.wanAddress = wanAddress;
        this.lanAddress = lanAddress;
        this.ipAddress = ipAddress;
        this.internetUser = internetUser;
        this.infrastructure = infrastructure;
        this.routerType = routerType;
        this.CODEC = CODEC;
        this.totalCalls = totalCalls;
        this.signalAddress = signalAddress;
        this.mediaAddress = mediaAddress;
        this.sbcPort = sbcPort;
        this.expertName = expertName;
        this.connectionType = connectionType;
        this.projectManagerFirstName = projectManagerFirstName;
        this.numofFails = numofFails;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public FormEvent(Object source, ArrayList<String> from , ArrayList<String> to , String trunkNumber ){
        super(source);
        this.from = from;
        this.to = to;
        this.trunkNumber = trunkNumber;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getStatus() {
        return status;
    }

    public int getNumofFails() {
        return numofFails;
    }
    public int getId() {
        return id;
    }
    public String getExpertName() {
        return expertName;
    }
    public String getConnectionType() {
        return connectionType;
    }
    public String getCustomerID() {
        return customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getContactName() {
        return contactName;
    }
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public String getCustomerTechName() {
        return customerTechName;
    }
    public String getCustomerTechPhoneNumber() {
        return customerTechPhoneNumber;
    }
    public String getPbxType() {
        return pbxType;
    }
    public String getTypeOfCalls() {
        return typeOfCalls;
    }
    public String getIdentificationType() {
        return identificationType;
    }
    public int getTotalNumbers() {
        return totalNumbers;
    }
    public String getSnbNumber() {
        return snbNumber;
    }
    public String getAreaCode() {
        return areaCode;
    }
    public String getEmergencyCity() {
        return emergencyCity;
    }
    public String getCallOutSideCountry() {
        return callOutSideCountry;
    }
    public String getCrNumber() {
        return crNumber;
    }
    public String getTrunkNumber() {
        return trunkNumber;
    }
    public String getDatePicker() {
        return datePicker;
    }
    public String getWanAddress() {
        return wanAddress;
    }
    public String getLanAddress() {
        return lanAddress;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public String getInternetUser() {
        return internetUser;
    }
    public String getInfrastructure() {
        return infrastructure;
    }
    public String getRouterType() {
        return routerType;
    }
    public String getCODEC() {
        return CODEC;
    }
    public int getTotalCalls() {
        return totalCalls;
    }
    public String getSignalAddress() {
        return signalAddress;
    }
    public String getMediaAddress() {
        return mediaAddress;
    }
    public int getSbcPort() {
        return sbcPort;
    }
    public String getProjectManagerFirstName() {
        return projectManagerFirstName;
    }

    public ArrayList<String> getFrom() {
        return from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

}
