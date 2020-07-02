package Model;

public class NumberRanges extends ActivationFormSip{
    
    private int fromRange;
    private int toRange;
    private int difference;
    private String trunk;

    public NumberRanges(int id, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String firstName, String connectionType, String projectManagerFirstName, String activationType, String status, int fromRange, int toRange, int difference) {
        super(id, customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, numberRange, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker, wanAddress, lanAddress, ipAddress, internetUser, infrastructure, routerType, CODEC, totalCalls, signalAddress, mediaAddress, sbcPort, firstName, connectionType, projectManagerFirstName, activationType, status);
        this.trunk = trunkNumber;
        this.fromRange = fromRange;
        this.toRange = toRange;
        this.difference = difference;
    }


}
