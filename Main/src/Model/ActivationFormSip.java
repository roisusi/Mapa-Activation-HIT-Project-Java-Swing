package Model;

public class ActivationFormSip extends ActivationForm {
    private int id;
    private static int count=1;
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
    private String status;

    //update
    public ActivationFormSip(int id, String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber,
                             String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String areaCode, String emergencyCity,
                             String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress,String internetUser,
                             String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String expertName, String connectionType, String projectManagerFirstName,
                             String activationType , String status , int numofFails,String lastUpdate) {

        this(customerID,  customerName,  contactName,  customerPhoneNumber,  customerEmail,  customerTechName,  customerTechPhoneNumber,  pbxType,  typeOfCalls,  identificationType,  totalNumbers,  snbNumber,
                areaCode,  emergencyCity,  callOutSideCountry,  crNumber,  trunkNumber,  datePicker,  wanAddress,  lanAddress,  ipAddress,  internetUser,  infrastructure,  routerType,  CODEC,
                totalCalls,  signalAddress,  mediaAddress,  sbcPort,  expertName,  connectionType,  projectManagerFirstName, activationType,status,numofFails,lastUpdate);
        this.id = id;
    }

    //new
    public ActivationFormSip(String customerID, String customerName, String contactName, String customerPhoneNumber, String customerEmail, String customerTechName, String customerTechPhoneNumber, String pbxType, String typeOfCalls, String identificationType, int totalNumbers, String snbNumber, String areaCode, String emergencyCity, String callOutSideCountry, String crNumber, String trunkNumber, String datePicker, String wanAddress, String lanAddress, String ipAddress, String internetUser, String infrastructure, String routerType, String CODEC, int totalCalls, String signalAddress, String mediaAddress, int sbcPort, String expertName, String connectionType, String projectManagerFirstName,String activationType,String status,int numofFails,String lastUpdate) {
        super(customerID, customerName, contactName, customerPhoneNumber, customerEmail, customerTechName, customerTechPhoneNumber, pbxType, typeOfCalls, identificationType, totalNumbers, snbNumber, areaCode, emergencyCity, callOutSideCountry, crNumber, trunkNumber, datePicker, expertName, projectManagerFirstName,activationType,numofFails,lastUpdate);
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
        this.status = status;
        this.id = count;
        count ++;

    }

    @Override
    public int getNumOfFails() {
        return super.getNumOfFails();
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
    public String getExpertFirstName() {
        return super.getExpertFirstName();
    }
    @Override
    public void setExpertName(String expertName) {
        super.setExpertName(expertName);
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
    public void setCustomerID(String customerID) {
        super.setCustomerID(customerID);
    }

    @Override
    public void setCustomerName(String customerName) {
        super.setCustomerName(customerName);
    }

    @Override
    public void setContactName(String contactName) {
        super.setContactName(contactName);
    }

    @Override
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        super.setCustomerPhoneNumber(customerPhoneNumber);
    }

    @Override
    public void setCustomerEmail(String customerEmail) {
        super.setCustomerEmail(customerEmail);
    }

    @Override
    public void setCustomerTechName(String customerTechName) {
        super.setCustomerTechName(customerTechName);
    }

    @Override
    public void setCustomerTechPhoneNumber(String customerTechPhoneNumber) {
        super.setCustomerTechPhoneNumber(customerTechPhoneNumber);
    }

    @Override
    public void setPbxType(String pbxType) {
        super.setPbxType(pbxType);
    }

    @Override
    public void setTypeOfCalls(String typeOfCalls) {
        super.setTypeOfCalls(typeOfCalls);
    }

    @Override
    public void setIdentificationType(String identificationType) {
        super.setIdentificationType(identificationType);
    }

    @Override
    public void setTotalNumbers(int totalNumbers) {
        super.setTotalNumbers(totalNumbers);
    }

    @Override
    public void setSnbNumber(String snbNumber) {
        super.setSnbNumber(snbNumber);
    }

    @Override
    public void setAreaCode(String areaCode) {
        super.setAreaCode(areaCode);
    }

    @Override
    public void setEmergencyCity(String emergencyCity) {
        super.setEmergencyCity(emergencyCity);
    }

    @Override
    public void setCallOutSideCountry(String callOutSideCountry) {
        super.setCallOutSideCountry(callOutSideCountry);
    }

    @Override
    public void setCrNumber(String crNumber) {
        super.setCrNumber(crNumber);
    }

    @Override
    public void setTrunkNumber(String trunkNumber) {
        super.setTrunkNumber(trunkNumber);
    }

    @Override
    public void setDatePicker(String datePicker) {
        super.setDatePicker(datePicker);
    }

    @Override
    public void setProjectManagerFirstName(String projectManagerFirstName) {
        super.setProjectManagerFirstName(projectManagerFirstName);
    }

    @Override
    public void setActivationType(String activationType) {
        super.setActivationType(activationType);
    }

    @Override
    public void setNumOfFails(int numOfFails) {
        super.setNumOfFails(numOfFails);
    }

    public int getId() {
        return id;
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
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setCount(int count) {
        ActivationFormSip.count = count;
    }

    public void setWanAddress(String wanAddress) {
        this.wanAddress = wanAddress;
    }

    public void setLanAddress(String lanAddress) {
        this.lanAddress = lanAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setInternetUser(String internetUser) {
        this.internetUser = internetUser;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public void setRouterType(String routerType) {
        this.routerType = routerType;
    }

    public void setCODEC(String CODEC) {
        this.CODEC = CODEC;
    }

    public void setTotalCalls(int totalCalls) {
        this.totalCalls = totalCalls;
    }

    public void setSignalAddress(String signalAddress) {
        this.signalAddress = signalAddress;
    }

    public void setMediaAddress(String mediaAddress) {
        this.mediaAddress = mediaAddress;
    }

    public void setSbcPort(int sbcPort) {
        this.sbcPort = sbcPort;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }


}
