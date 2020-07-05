package Controller;

import Model.NumberRanges;

import java.util.ArrayList;

public class NumberRangeController {

    NumberRanges numberRanges;
    ArrayList from;
    ArrayList to;

    public NumberRangeController(ArrayList from, ArrayList to) {
        numberRanges = new NumberRanges(from,to);
        this.from = from;
        this.to = to;
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
    public int maxSize(int size) {
    return numberRanges.maxSize(size);
    }
    public void FNRrun(){

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

    public void clear(){
        numberRanges.clear();
    }

    public void setFromRange (ArrayList from){
        this.from = from;
        numberRanges.setFromRange(this.from);
    }

    public void setToRange (ArrayList to){
        this.to = to;
        numberRanges.setToRange(this.from);
    }
}
