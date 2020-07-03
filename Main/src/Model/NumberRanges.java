package Model;

import java.util.ArrayList;

public class NumberRanges {
    
    private ArrayList fromRange;
    private ArrayList toRange;
    private String trunk;

/*    public NumberRanges(int id, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String firstName, String connectionType, String projectManagerFirstName, String activationType, String status, ArrayList fromRange, ArrayList toRange) {
        super(id, customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, numberRange, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker, wanAddress, lanAddress, ipAddress, internetUser, infrastructure, routerType, CODEC, totalCalls, signalAddress, mediaAddress, sbcPort, firstName, connectionType, projectManagerFirstName, activationType, status);
        this.trunk = trunkNumber;
        this.fromRange = fromRange;
        this.toRange = toRange;
    }*/

    public NumberRanges(ArrayList fromRange, ArrayList toRange, String trunk) {
        this.fromRange = fromRange;
        this.toRange = toRange;
        this.trunk = trunk;
    }

    public ArrayList getFromRange() {
        return fromRange;
    }

    public void setFromRange(ArrayList fromRange) {
        this.fromRange = fromRange;
    }

    public ArrayList getToRange() {
        return toRange;
    }

    public void setToRange(ArrayList toRange) {
        this.toRange = toRange;
    }

    public String getTrunk() {
        return trunk;
    }

    public void setTrunk(String trunk) {
        this.trunk = trunk;
    }
}
