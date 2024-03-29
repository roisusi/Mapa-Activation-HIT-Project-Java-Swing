package Model;

import Controller.NumberRangeController;
import com.mysql.jdbc.StringUtils;

public abstract class ActivationForm  {
    private NumberRangeController numberRangeController;


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
    protected String areaCode;
    protected String emergencyCity; //need to change by all Cities of EMS
    protected String callOutSideCountry;
    protected String crNumber;
    protected String trunkNumber;
    protected String datePicker;
    protected String expertName;
    protected String projectManagerFirstName;
    protected String activationType;
    protected int numOfFails;
    protected String lastUpdate;

    public ActivationForm(){

    }

    //-- Without id For Saving --//
    public ActivationForm(String customerID ,String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker,String expertName, String projectManagerFirstName,String activationType , int numOfFails,String lastUpdate) {
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
        this.expertName = expertName;
        this.projectManagerFirstName = projectManagerFirstName;
        this.activationType = activationType;
        this.numOfFails = numOfFails;
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getActivationType() {
        return activationType;
    }

    public String getProjectManagerFirstName() {
        return projectManagerFirstName;
    }

    public String getExpertFirstName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
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
    public int getNumOfFails() {
        return numOfFails;
    }


    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerTechName(String customerTechName) {
        this.customerTechName = customerTechName;
    }

    public void setCustomerTechPhoneNumber(String customerTechPhoneNumber) {
        this.customerTechPhoneNumber = customerTechPhoneNumber;
    }

    public void setPbxType(String pbxType) {
        this.pbxType = pbxType;
    }

    public void setTypeOfCalls(String typeOfCalls) {
        this.typeOfCalls = typeOfCalls;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public void setTotalNumbers(int totalNumbers) {
        this.totalNumbers = totalNumbers;
    }

    public void setSnbNumber(String snbNumber) {
        this.snbNumber = snbNumber;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setEmergencyCity(String emergencyCity) {
        this.emergencyCity = emergencyCity;
    }

    public void setCallOutSideCountry(String callOutSideCountry) {
        this.callOutSideCountry = callOutSideCountry;
    }

    public void setCrNumber(String crNumber) {
        this.crNumber = crNumber;
    }

    public void setTrunkNumber(String trunkNumber) {
        this.trunkNumber = trunkNumber;
    }

    public void setDatePicker(String datePicker) {
        this.datePicker = datePicker;
    }

    public void setProjectManagerFirstName(String projectManagerFirstName) {
        this.projectManagerFirstName = projectManagerFirstName;
    }

    public void setActivationType(String activationType) {
        this.activationType = activationType;
    }
    public void setNumOfFails(int numOfFails) {
        this.numOfFails = numOfFails;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public boolean checkSnb(String snb){
        numberRangeController = new NumberRangeController();
        return numberRangeController.trueNumberIsraelPhoneCheck(snb);
    }

    public boolean checkPhoneNumber(String phone){
        String text = phone;
        int count=0;
        int firstThreeDigs=3;
        if (text.length() != 11)
            return false;
        if (text.contains("-"))
            count ++;
        // check 050,052,054,055,058
        if (count != 1 || text.indexOf("-") - firstThreeDigs != 0)
            return false;
        char ch = text.charAt(2);
        if (text.charAt(0) == '0'  && text.charAt(1) == '5' && (text.charAt(2) == '0' || text.charAt(2) == '2' || text.charAt(2) == '4' || text.charAt(2) == '5' || text.charAt(2) == '8' ))
            return true;
        return false;
    }

    public boolean checkEmail(String email){
        String text = email;
        int count =0;
        if (text.contains("@")){
            count++;
        }
        if (text.contains(".co.il") || text.contains(".com") || text.contains(".net")){
            if (count == 1)
                return true;
        }

        return false;
    }

    public boolean checkInputDigits(String text) {
        boolean flag = true;
        if (!StringUtils.isStrictlyNumeric(text)) {
            return flag = false;
        }
        return flag;
    }

    public boolean checkEmptyCells(String text){
        if (text.isEmpty() || text.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
