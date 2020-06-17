package Model;

import java.util.Date;

public class ActivationFormPri extends ActivationForm{
    private String codeLine;

    public ActivationFormPri(int id,String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String codeLine,String firstName,String projectManagerFirstName,String activationType) {
        super(customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, numberRange, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker,firstName,projectManagerFirstName,activationType);
        this.codeLine = codeLine;
    }

    public ActivationFormPri(String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker,String firstName ,String codeLine,String projectManagerFirstName,String activationType) {
        super(customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, numberRange, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker ,firstName,projectManagerFirstName,activationType);
        this.codeLine = codeLine;
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getCodeLine() {
        return codeLine;
    }

    @Override
    public String getCustomerID() {
        return super.getCustomerID();
    }

    @Override
    public String getCustomerName() {
        return super.getCustomerName();
    }

    @Override
    public String getContactName() {
        return super.getContactName();
    }

    @Override
    public String getCustomerPhoneNumber() {
        return super.getCustomerPhoneNumber();
    }

    @Override
    public String getCustomerEmail() {
        return super.getCustomerEmail();
    }

    @Override
    public String getCustomerTechName() {
        return super.getCustomerTechName();
    }

    @Override
    public String getCustomerTechPhoneNumber() {
        return super.getCustomerTechPhoneNumber();
    }

    @Override
    public String getPbxType() {
        return super.getPbxType();
    }

    @Override
    public String getTypeOfCalls() {
        return super.getTypeOfCalls();
    }

    @Override
    public String getIdentificationType() {
        return super.getIdentificationType();
    }

    @Override
    public int getTotalNumbers() {
        return super.getTotalNumbers();
    }

    @Override
    public String getSnbNumber() {
        return super.getSnbNumber();
    }

    @Override
    public String getNumberRange() {
        return super.getNumberRange();
    }

    @Override
    public String getAreaCode() {
        return super.getAreaCode();
    }

    @Override
    public String getEmergencyCity() {
        return super.getEmergencyCity();
    }

    @Override
    public String getCallOutSideCountry() {
        return super.getCallOutSideCountry();
    }

    @Override
    public String getCrNumber() {
        return super.getCrNumber();
    }

    @Override
    public String getTrunkNumber() {
        return super.getTrunkNumber();
    }

    @Override
    public String getDatePicker() {
        return super.getDatePicker();
    }

    @Override
    public String getProjectManagerFirstName() {
        return super.getProjectManagerFirstName();
    }

    @Override
    public String getActivationType() {
        return super.getActivationType();
    }
}
