package Controller;

import Model.DataBase;
import Model.NumberRanges;
import View.FormEvent;

import java.util.ArrayList;

public class NumberRangeController {

    DataBase db = new DataBase();
    NumberRanges numberRanges;
    ArrayList from;
    ArrayList to;

    public NumberRangeController(ArrayList from, ArrayList to) {
        numberRanges = new NumberRanges(from,to);
        this.from = from;
        this.to = to;
    }

    public void addNumberRange(FormEvent ev){
        from = ev.getFrom();
        to = ev.getTo();
        String trunkNumber = ev.getTrunkNumber();

        numberRanges = new NumberRanges(from,to,trunkNumber);
        db.addNumberRangeToList(numberRanges);
    }

    public String checkList(String list){
        return numberRanges.checkList(list);
    }
    public boolean checkListIsEmpty(String list){
        boolean isEmpty = numberRanges.checkListIsEmpty(list);
        return isEmpty;
    }

    public void removeEmptyCells(ArrayList from,ArrayList to){
        numberRanges.removeEmptyCells(from,to);
    }

    public void FNRtoFileSip(ArrayList from , ArrayList to){
        numberRanges.FNRtoFileSip(from,to);
    }
    public boolean trueNumberIsraelPhoneCheck(String range){
        return numberRanges.trueNumberIsraelPhoneCheck(range);
    }
    public boolean negative(int num1,int num2){
        return numberRanges.negative(num1,num2);
    }

    public ArrayList getFrom(){
        return numberRanges.getFromRange();
    }

    public ArrayList getTo(){
        return numberRanges.getToRange();
    }

/*    public void clear(){
        numberRanges.clearList();
    }*/

    public void setFromRange (ArrayList from){
        this.from = from;
        numberRanges.setFromRange(this.from);
    }

    public void setToRange (ArrayList to){
        this.to = to;
        numberRanges.setToRange(this.from);
    }
}
