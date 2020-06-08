package Model;

public class ActivationFormSip extends ActivationForm {
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
    private String connectionType;

    public ActivationFormSip(int id, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber,
                             String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress
            , String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String firstName, String connectionType, String projectManagerFirstName,String activationType) {
        super(id, customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, numberRange, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker, firstName, projectManagerFirstName,activationType);
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
        this.connectionType = connectionType;
    }

    public ActivationFormSip(String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String numberRange, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String firstName, String connectionType, String projectManagerFirstName,String activationType) {
        super(customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, numberRange, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker, firstName, projectManagerFirstName,activationType);
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
        this.connectionType = connectionType;
    }


    @Override
    public String getActivationType() {
        return super.getActivationType();
    }

    @Override
    public String getProjectManagerFirstName() {
        return super.getProjectManagerFirstName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public int getId() {
        return super.getId();
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

    public String getConnectionType() {
        return connectionType;
    }

}
