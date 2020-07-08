package Controller;

import Model.ExpertListFile;

public class ExpertToFileController {

    ExpertListFile expertListFile;

    public ExpertToFileController(String trunkNumber, int sbcPort , int totalCalls , String snbNumber , String wan, String lan , String ipExternal,
                                   String signalAddress, String mediaAddress,String areaCode, String emergencyCity , String crNumber, String callOutSideCountry) {

        expertListFile = new ExpertListFile(trunkNumber,sbcPort,totalCalls,snbNumber,wan,lan,ipExternal,signalAddress,mediaAddress,areaCode,emergencyCity,crNumber,callOutSideCountry);

    }

    public void exportToFile(){
        expertListFile.exportToFile();
    }

}
