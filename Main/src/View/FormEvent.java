package View;

import java.util.Date;
import java.util.EventObject;

public class FormEvent extends EventObject {
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
    private String numberRange;
    private String areaCode;
    private String emergencyCity; //need to change by all Cities of EMS
    private String callOutSideCountry;
    private String crNumber;
    private String trunkNumber;
    private Date datePicker;
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

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, Date datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort) {
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
        this.numberRange = numberRange;
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

    public String getNumberRange() {
        return numberRange;
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

    public Date getDatePicker() {
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
}
