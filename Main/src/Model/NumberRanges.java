package Model;

import net.sf.jasperreports.engine.virtualization.ObjectSerializer;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NumberRanges {
    private ArrayList<String> fromRange;
    private ArrayList<String> toRange;
    private String trunk;

    public NumberRanges() {
    }

    public NumberRanges(ArrayList<String> fromRange, ArrayList<String> toRange, String trunk) {
        this.fromRange = fromRange;
        this.toRange = toRange;
        this.trunk = trunk;
    }

    public NumberRanges(ArrayList<String> fromRange, ArrayList<String> toRange) {
        this.fromRange = fromRange;
        this.toRange = toRange;
    }

    public ArrayList<String> getFromRange() {
        return fromRange;
    }

    public void setFromRange(ArrayList<String> fromRange) {
        this.fromRange = fromRange;
    }

    public ArrayList<String> getToRange() {
        return toRange;
    }

    public void setToRange(ArrayList<String> toRange) {
        this.toRange = toRange;
    }

    public String getTrunk() {
        return trunk;
    }

    public String checkList(String str){
        if (str.matches("[0-9]+") || str.contains("-") || str.charAt(0) == '0') {
            if (str.contains("-")) {
                str = str.replace("-", "");
            }
            if (str.charAt(0) == '0')
                str = str.substring(1,str.length());
        }
        else
            str = "";
        return str;
    }

    public boolean checkListIsEmpty(String list){
        if (list.equals(""))
            return true;
        return false;
    }
    public void removeEmptyCells(ArrayList<String> from,ArrayList<String> to) {
        Iterator<String> iter1;
        Iterator<String> iter2;

        iter1 = from.iterator();
        iter2 = to.iterator();

        while (iter1.hasNext()) {
            String str1 = iter1.next();
            if (str1.equals("")) {
                iter1.remove();
            }
        }
        while (iter2.hasNext()) {
            String str2 = iter2.next();
            if (str2.equals("")) {
                iter2.remove();
            }
        }
    }
    public List<Integer> fnrBreakToSingles(String from, String to){
        int startRange = Integer.parseInt(from);
        int endRange = Integer.parseInt(to);
        int i=0;
        List<Integer> listOfNumbers = new ArrayList<>();

        while (startRange<=endRange) {
            listOfNumbers.add(startRange);
            startRange++;
        }
        return listOfNumbers;
    }

    public void FNRtoFileSip(ArrayList<String> from, ArrayList<String> to){
        int size = from.size();
        List<Integer> range = new ArrayList();

        List<String> fnrPrint = new ArrayList();
        List<String> fnrCreate = new ArrayList();
        int strRange =0;
        String DT="";

        int i=0,j=0;
        for (i=0 ; i < size ; i ++) {
            strRange = (Integer.parseInt(from.get(i)));
            range = fnrBreakToSingles(from.get(i),to.get(i));

            // Display the FNR for Ericsson
            for (j=0 ; j<range.size() ;j++) {
                fnrPrint.add("FGNTP:MSISDN=972" + range.get(j) + ";");
                DT += fnrPrint.get(j)  + "\n" ;
            }
            fnrPrint.removeAll(fnrPrint);
            range.removeAll(range);
        }

        for (i=0 ; i < size ; i ++) {
            strRange = (Integer.parseInt(from.get(i)));
            range = fnrBreakToSingles(from.get(i),to.get(i));

            // Create/Change the FNR for Ericsson
            for (j=0 ; j<range.size() ;j++) {
                if ((strRange / 10000000) == 74 || (strRange / 10000000) == 72 || strRange / 10000000 == 54) // 072 or partner with 074
                {
                    fnrCreate.add("FGNTI:MSISDN=972" + range.get(j) + ",NPREFIX=29;");
                } else // all the rest
                {
                    fnrCreate.add("FGNTC:MSISDN=972" + range.get(j) + ",NPREFIX=29;");
                }

                DT += fnrCreate.get(j) + "\n" ;
            }
            fnrCreate.removeAll(fnrCreate);
            range.removeAll(range);
        }
            /*
             * DT to File
             */
            String text = DT;
            try {
                File file = new File("FNR-" + ".txt");
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(text);
                output.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }


            /* // Future Devps
                // Display the FNR for Erricson
                FNR_DISPLAY[a] = "FGNTP:MSISDN=972" + ((runTIME[i]) + a) + ";";
                FNR_A_DISPLAY += FNR_DISPLAY[a] + "\n";

                // Delete the FNR for Erricson
                FNR_DEL[a] = "FGNTE:MSISDN=972" + ((runTIME[i]) + a) + ";";
                FNR_A_DEL += FNR_DEL[a] + "\n";*/

    }
    public boolean trueNumberIsraelPhoneCheck(String range){

        int prefix=29; //Sip
        int strRange = 0;
        int lstRange = 0;
        //|| (lstRange < strRange) || (lstRange - strRange) >= 30000
        String lstRange_STR = "";
        String strRange_STR = "";
        int i;

        if (!range.isEmpty()) {
            strRange = Integer.parseInt(range);
            if (((strRange >= 20000000 && strRange <= 49999999) || (strRange >= 80000000 && strRange <= 99999999) || (strRange >= 720000000 && strRange <= 729999999) || (strRange >= 740000000 && strRange <= 749999999)
                        || (strRange >= 770000000 && strRange <= 779999999) || (strRange >= 730000000 && strRange <= 739999999) || (strRange >= 760000000 && strRange <= 769999999) || (strRange >= 540000000 && strRange <= 549999999)
            ) )
                return true;
            }
        return false;
    }

    public boolean negative(int num1,int num2){
        int sum = num2-num1;
        if (sum < 0)
            return false;
        return true;
    }



    public int removeUnbalanceRangesCells(ArrayList from , ArrayList to){
        int moreRows = 0;
        int size1 = from.size();
        int size2 = to.size();
        int i=0,index1=0,index2=0;
        while(i<size1){
            if(from.get(i).equals(""))
                index1++;
            i++;
        }
        i=0;
        while(i<size2){
            if(to.get(i).equals(""))
                index2++;
            i++;
        }
        i=0;
        while (index1<index2){
            from.set(i,"");
            index1++;
            i++;
            moreRows = index2;
        }
        i=0;
        while (index1>index2){
            to.set(i,"");
            index2++;
            i++;
            moreRows = index1;
        }

        return moreRows;
    }
}
