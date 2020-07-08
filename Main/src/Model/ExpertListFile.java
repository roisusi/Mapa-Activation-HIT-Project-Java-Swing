package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExpertListFile {

    private String DT;
    private String trunkNumber;
    private int sbcPort;
    private int totalCalls;
    private String snbNumber;
    private String wan;
    private String lan;
    private String ipExternal;
    private String signalAddress;
    private String mediaAddress;
    private String areaCode;
    private String emergencyCity;
    private String crNumber;
    private String callOutSideCountry;


    public ExpertListFile(String trunkNumber, int sbcPort, int totalCalls, String snbNumber, String wan, String lan, String ipExternal, String signalAddress, String mediaAddress, String areaCode, String emergencyCity, String crNumber, String callOutSideCountry) {
        this.trunkNumber = trunkNumber;
        this.sbcPort = sbcPort;
        this.totalCalls = totalCalls;
        this.snbNumber = snbNumber;
        this.wan = wan;
        this.lan = lan;
        this.ipExternal = ipExternal;
        this.signalAddress = signalAddress;
        this.mediaAddress = mediaAddress;
        this.areaCode = areaCode;
        this.emergencyCity = emergencyCity;
        this.crNumber = crNumber;
        this.callOutSideCountry = callOutSideCountry;
    }

    public void exportToFile(){
        DT = "Trunk Name : " + trunkNumber + "\n";
        DT += "SBC Port : " + sbcPort + "\n";
        DT += "Total Calls : " + totalCalls + "\n";
        DT += "SNB Number : " + snbNumber + "\n";
        DT += "WAN Address : " + wan + "\n";
        DT += "LAN Address : " + lan + "\n";
        DT += "IP Address : " + ipExternal + "\n";
        DT += "SIG Address : " + signalAddress + "\n";
        DT += "RTP Address : " + mediaAddress + "\n";
        DT += "Area Code : " + areaCode + "\n";
        DT += "Emergency City : " + emergencyCity + "\n";
        DT += "CR-Number : " + crNumber + "\n";
        DT += "Call Outside Country allowed : " + callOutSideCountry + "\n";

        try {
            File file = new File(trunkNumber + ".txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(DT);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
