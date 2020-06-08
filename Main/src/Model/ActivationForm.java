package Model;

import java.util.Date;

public abstract class ActivationForm  {

    protected int id;
    protected static int count=1;
    protected String customerID;
    protected String customerName;
    protected String contactName;
    protected String customerPhoneNumber;
    protected String customerEmail;
    protected String customerTechName;
    protected String customerTechPhoneNumber;
    protected String pbxType;
    protected String typeOfCalls;
    protected String identificationType;
    protected int totalNumbers;
    protected String snbNumber;
    protected String numberRange;
    protected String areaCode;
    protected String emergencyCity; //need to change by all Cities of EMS
    protected String callOutSideCountry;
    protected String crNumber;
    protected String trunkNumber;
    protected String datePicker;
    protected String firstName;
    protected String projectManagerFirstName;
    protected String activationType;


    public ActivationForm(int id ,String customerID ,String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker,String firstName,String projectManagerFirstName,String activationType) {
        this(customerID,customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,numberRange,areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,firstName,projectManagerFirstName,activationType);
        this.id = id;

    }
    //-- Without id For Saving --//
    public ActivationForm(String customerID ,String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker,String firstName, String projectManagerFirstName,String activationType) {
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
        this.firstName = firstName;
        this.projectManagerFirstName = projectManagerFirstName;
        this.activationType = activationType;
        this.id = count;
        count ++;
    }

    public int getId() {
        return id;
    }

    public String getActivationType() {
        return activationType;
    }

    public String getProjectManagerFirstName() {
        return projectManagerFirstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getDatePicker() {
        return datePicker;
    }
}
