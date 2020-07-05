package Model;

import net.sf.jasperreports.engine.virtualization.ObjectSerializer;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class NumberRanges {
    private int size;
    private ArrayList fromRange;
    private ArrayList toRange;
    private String trunk;

    public NumberRanges(ArrayList fromRange, ArrayList toRange, String trunk) {
        this.fromRange = fromRange;
        this.toRange = toRange;
        this.trunk = trunk;
    }

    public NumberRanges(ArrayList fromRange, ArrayList toRange) {
        this.fromRange = fromRange;
        this.toRange = toRange;
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
        Iterator<String> iter;

        iter = from.iterator();
        while (iter.hasNext()) {
            String str = iter.next();

            if (str.equals(""))
                iter.remove();
        }
        iter = to.iterator();
        while (iter.hasNext()) {
            String str = iter.next();

            if (str.equals(""))
                iter.remove();
        }
    }
    public int maxSize(int size){
        return this.size = size;
    }
    public void FNRrun(){

    }
    public void FNRtoFile(){
/*        int[] runTIME = new int[ranges];

        String FNR_A_MODE = "" ;
        String FNR_A_DISPLAY = "" ;
        String FNR_A_DEL = "" ;
        String FNR_A_CR = "" ;



        int FNR_Choose = 0;
        String FNRstr="";


        for (int i = 0 ; i < ranges ; i ++) // the the total ranges of the customer
        {
            valid = false;
            while (!valid)
            {
                try
                {
                    strRange_STR = JOptionPane.showInputDialog("Please Enter " + (i+1) + " start range");
                    strRange = Integer.parseInt(strRange_STR);
                    lstRange_STR = JOptionPane.showInputDialog("Please Enter " + (i+1) + " last range");
                    lstRange = Integer.parseInt(lstRange_STR);

                    {
                        JOptionPane.showMessageDialog(null, "The Range " + strRange + "-"+ lstRange + " Is wrong range please enter again");
                    }
                    else
                    {
                        ranges = amount_OF_ranges;
                        valid = true;
                    }
                } catch (NumberFormatException e)
                {
                    //error
                    if (strRange_STR == null || lstRange_STR == null)
                        System.exit(0);
                    else
                        JOptionPane.showConfirmDialog(null,"Error, not a number. Please try again.",null,JOptionPane.CLOSED_OPTION);
                }
            }
            runTIME[i] = strRange;
            int chkNUMBERRANGE = lstRange + 1 - strRange ;
            String[] FNR_DISPLAY = new String[chkNUMBERRANGE];
            String[] FNR_MODE = new String[chkNUMBERRANGE];
            String[] FNR_DEL = new String[chkNUMBERRANGE];
            String[] FNR_CR = new String[chkNUMBERRANGE];
            String[] NOKIA_disp = new String[chkNUMBERRANGE];
            String[] NOKIA_mode = new String[chkNUMBERRANGE];
            String[] NOKIA_del = new String[chkNUMBERRANGE];
            String[] NOKIA_cr = new String[chkNUMBERRANGE];

            switch (FNR_Choose)
            {
                case 1: //Change the FNR
                    for (int a = 0 ; a < chkNUMBERRANGE ; a++)
                    {
                        // Display the FNR for Erricson
                        FNR_DISPLAY[a] = "FGNTP:MSISDN=972" + ((runTIME[i]) + a) + ";";
                        FNR_A_DISPLAY += FNR_DISPLAY[a] + "\n";

                        if ((strRange / 10000000) == 74 || (strRange / 10000000) == 72 || strRange / 10000000 == 54) // 072 or partner with 074
                        {
                            FNR_CR[a] = "FGNTI:MSISDN=972" + ((runTIME[i]) + a) + ",NPREFIX=" + prefix +";";
                            FNR_A_CR += FNR_CR[a] + "\n";
                        }
                        else // all the rest
                        {
                            FNR_MODE[a] = "FGNTC:MSISDN=972" + ((runTIME[i]) + a) + ",NPREFIX=" + prefix +";";
                            FNR_A_MODE += FNR_MODE[a] + "\n";
                        }

                        if (strRange / 10000000 == 74 || strRange / 10000000 == 72 || strRange / 10000000 == 54)
                        {
                            NOKIA_disp[a] = "DISPNPENTRY:Purpose=numberPortability ,Filter=Shorter or equal ,Input NADI=national ,Input number=\"" + ((runTIME[i]) + a) +"\";" ;
                            NOKIA_cr[a]   = "CRNPENTRY:Purpose=Number portability,Input NADI=National,Input number=\""+ ((runTIME[i]) + a) + "\" ,Admin. state=Unlocked,Reference to TPROF=1920;" ;

                        }
                        else
                        {
                            NOKIA_disp[a] = "DISPNPENTRY:Purpose=numberPortability ,Filter=Shorter or equal ,Input NADI=national ,Input number=\"" + ((runTIME[i]) + a) +"\";" ;
                            NOKIA_mode[a] = "MODNPENTRY:Purpose=Number portability,Input NADI=National,Input number=\""+ ((runTIME[i]) + a) + "\" ,Admin. state=Unlocked,Reference to TPROF=1160;" ;

                        }
                    }
                    break;

                case 2: //delete
                    for (int a = 0 ; a < chkNUMBERRANGE ; a++)
                    {
                        // Display the FNR for Erricson
                        FNR_DISPLAY[a] = "FGNTP:MSISDN=972" + ((runTIME[i]) + a) + ";";
                        FNR_A_DISPLAY += FNR_DISPLAY[a] + "\n";

                        // Delete the FNR for Erricson
                        FNR_DEL[a] = "FGNTE:MSISDN=972" + ((runTIME[i]) + a) + ";";
                        FNR_A_DEL += FNR_DEL[a] + "\n";

                    }

                    break;
            }
        }

        *//*
         * THIS IS THE SINGLE MAKER
         *//*

        int sigNUM = 0;
        String sigNUMstr = "";
        String FNR_Single_A = "";
        String FNR_Single_DISP_A = "";
        String yes ="";
        yes = JOptionPane.showInputDialog("Do you need to add a single number ? Y/N");
        if (yes==null)
            System.exit(0);
        if (yes.equals("Y") || yes.equals("y"))
        {
            int index_number_sig = 0;
            String strSigNumber = "";
            valid = false;
            while (!valid)
            {
                try {
                    strSigNumber = JOptionPane.showInputDialog("How many u need ? (1-50)");
                    index_number_sig = Integer.parseInt(strSigNumber);
                    if (index_number_sig < 0 || index_number_sig > 50)
                        JOptionPane.showMessageDialog(null, index_number_sig + " Is wrong number please enter from 1-50");
                    else
                        valid = true;
                } catch (NumberFormatException e) {
                    //error
                    if (strSigNumber == null)
                        System.exit(0);
                    else
                        JOptionPane.showConfirmDialog(null,"Error, not a number. Please try again.",null,JOptionPane.CLOSED_OPTION);
                }
            }

            int[] SINGLE = new int[index_number_sig];
            String[] FNR_Single = new String[index_number_sig];
            String[] FNR_Single_DISP = new String[index_number_sig];
            String[] NOKIA_Single_disp = new String[index_number_sig];
            String[] NOKIA_Single_mode = new String[index_number_sig];
            String[] NOKIA_Single_cr = new String[index_number_sig];
            String[] NOKIA_Single_del = new String[index_number_sig];
            String sigtg_str = "";

            *//*
             * Range check for Single
             *//*
            for (int i = 0 ; i < SINGLE.length ; i ++)
            {
                valid = false;
                while (!valid)
                {
                    try {
                        sigNUMstr = JOptionPane.showInputDialog("Please Enter the " + (i+1) + " number");
                        sigNUM = Integer.parseInt(sigNUMstr);
                        if ((sigNUM < 20000000 || sigNUM > 49999999) && (sigNUM < 80000000 || sigNUM > 99999999) && (sigNUM < 740000000 || sigNUM > 749999999)
                                && (sigNUM < 770000000 || sigNUM > 779999999) && (sigNUM < 730000000 || sigNUM > 739999999) && (sigNUM < 720000000 || sigNUM > 729999999)
                                && (sigNUM < 760000000 || sigNUM > 769999999) && (sigNUM < 540000000 || sigNUM > 549999999))
                        {
                            JOptionPane.showMessageDialog(null, sigNUM + " Is wrong number please enter again");
                        }
                        else
                        {
                            SINGLE[i] = (sigNUM - (sigNUM%10)) ;
                        }
                    } catch (NumberFormatException e)
                    {
                        //error
                        if (sigNUMstr == null || sigtg_str == null)
                            System.exit(0);
                        else
                            JOptionPane.showConfirmDialog(null,"Error, not a number. Please try again.",null,JOptionPane.CLOSED_OPTION);

                    }
                }

                *//*
                 * FNR MAKER for Single
                 *//*


                switch (FNR_Choose)
                {


                    case 1:

                        if ((sigNUM / 10000000) == 74 || ((sigNUM / 10000000) == 72) || ((sigNUM / 10000000) == 54)) // *** Create ***
                        {
                            FNR_Single_DISP[i] = "FGNTP:MSISDN=972" + sigNUM + ";";
                            FNR_Single[i] = "FGNTI:MSISDN=972" + sigNUM  + ",NPREFIX=" + prefix +";";
                            FNR_Single_DISP_A += FNR_Single_DISP[i] + "\n";
                            FNR_Single_A += FNR_Single[i] + "\n";
                        }

                        else                // *** Modify ***
                        {

                            FNR_Single_DISP[i] = "FGNTP:MSISDN=972" + sigNUM + ";";
                            FNR_Single[i] = "FGNTC:MSISDN=972" + sigNUM  + ",NPREFIX=" + prefix +";";
                            FNR_Single_DISP_A += FNR_Single_DISP[i] + "\n";
                            FNR_Single_A += FNR_Single[i] + "\n";
                        }

                        break;


                    case 2: // *** Delete ***

                        FNR_Single_DISP[i] = "FGNTP:MSISDN=972" + sigNUM + ";";
                        FNR_Single[i] = "FGNTE:MSISDN=972" + sigNUM  + ",NPREFIX=" + prefix +";";

                        FNR_Single_DISP_A += FNR_Single_DISP[i] + "\n";
                        FNR_Single_A += FNR_Single[i] + "\n";


                        break;
                }
            }
        }
        String DT = FNR_A_DISPLAY + FNR_Single_DISP_A + "\n" + FNR_A_CR + FNR_A_MODE + FNR_A_DEL + FNR_Single_A ;

        *//*
         * DT to text
         *//*
        System.out.println(DT);
        String text = DT;
        try {
            File file = new File("FNR-" + ".txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }*/

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

    public void clear(){
        fromRange.removeAll(fromRange);
        toRange.removeAll(toRange);
    }
}
