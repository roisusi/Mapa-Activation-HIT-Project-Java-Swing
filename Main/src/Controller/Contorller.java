package Controller;

import Model.DataBase;
import Model.Login;
import java.sql.SQLException;
import java.util.List;

public class Contorller {
    DataBase db = new DataBase();
    public List<Login> getPeople() {
        return db.getUsers();
    }
    Login login;

    /*public void addPerson(FormEvent ev)
    {
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCatId = ev.getAgeCategory();
        String employed = ev.getEmployed();
        boolean isUS = ev.isUsCitizen();
        String citizenTax = ev.getTaxID();
        String gender = ev.getGender();
        AgeCatagory ageCatagory = null;
        switch (ageCatId)
        {
            case 0:
                ageCatagory = AgeCatagory.child;
                break;
            case 1:
                ageCatagory = AgeCatagory.adult;
                break;
            case 2:
                ageCatagory = AgeCatagory.senior;
                break;
        }

        EmploymendCatagory empCategory;
        if (employed.equals("employed"))
            empCategory = EmploymendCatagory.employed;
        else if (employed.equals("selfEmployed"))
            empCategory = EmploymendCatagory.selfEmployed;
        else if (employed.equals("unemployed"))
            empCategory = EmploymendCatagory.unemployed;
        else
            empCategory = EmploymendCatagory.other;

        Gender genderCat;
        if (gender.equals("male"))
            genderCat = Gender.male;
        else
            genderCat = Gender.female;



        person = new Person(name,occupation,ageCatagory,empCategory,citizenTax,isUS,genderCat);
        db.addPerson(person);

    }*/
/*    public void removePerson(int row)
    {
        db.removePerson(row);
    }*/

/*    public void save() throws SQLException {
        db.save();
    }*/
    public void load() throws SQLException {
        db.load();
    }
    public void disconnect(){
        db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }
}