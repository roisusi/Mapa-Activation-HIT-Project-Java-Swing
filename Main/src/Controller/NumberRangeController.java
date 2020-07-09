package Controller;

import Model.DataBase;
import Model.NumberRanges;
import View.ActivationsMoves;
import View.FormEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NumberRangeController {

    DataBase db = new DataBase();
    NumberRanges numberRanges;
    ArrayList from;
    ArrayList to;

    public NumberRangeController() {
        numberRanges = new NumberRanges();
    }

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

    public List<NumberRanges> getNumberRanges(){
        return db.getNumberRanges();
    }
    public void updateNumberRangeToDataBase(int activation_id) throws SQLException {
        db.updateNumberRangeToDataBase(activation_id);
    }
    public void insertingNumberRangeToDataBase() throws SQLException {
        db.insertingNumberRangeToDataBase(ActivationsMoves.SessionId.getNewID());
    }

    public void loadNumberRangeFromDataBaseToList(int activation_id) throws SQLException {
        db.loadNumberRangeFromDataBaseToList(activation_id);
    }

    public void removeNumberingRangeFromList(int id) throws SQLException {
        db.removeNumberingRangeFromList(id);
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

    public void setFromRange (ArrayList from){
        this.from = from;
        numberRanges.setFromRange(this.from);
    }

    public void setToRange (ArrayList to){
        this.to = to;
        numberRanges.setToRange(this.from);
    }

    public void clearNumberRange(){
        db.clearNumberRange();
    }

    public void disconnect(){
        //db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }

    public Connection getConnection() {
        return db.getCon();
    }
    public int removeViewCells(ArrayList from ,ArrayList to){
        return numberRanges.removeUnbalanceRangesCells(from,to);
    }

}
