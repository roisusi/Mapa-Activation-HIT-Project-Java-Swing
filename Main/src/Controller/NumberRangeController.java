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
}
