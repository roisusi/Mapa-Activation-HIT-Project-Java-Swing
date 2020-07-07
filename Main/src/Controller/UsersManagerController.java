package Controller;

import Model.*;

import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;

public class UsersManagerController {
    DataBase db;
    List<Users> usersList;
    List<Login> loginList;

    public UsersManagerController()
    {
        db = new DataBase();
        usersList = new LinkedList<Users>();
        loginList = new LinkedList<Login>();
    }

    public List<Login> getSystemUsers() {
        return loginList;
    }

    public List<Users> getUsers() { return usersList; }

    public void addUserToUsersList(Users user) { usersList.add(user); }

    public void addLoginToLoginList(Login login) { loginList.add(login); }

    public void connect () throws Exception { db.connect(); }

    public void disconnect() { db.disconnect(); }

    public Login getLoginUser() { return db.getLoginUser(); }

    public Users getUserFirstNameLogged(){
        return db.getLoggedUser();
    }

    //changed//
    public boolean isUserAlreadyExists(Users user) {
        boolean flag = false;

        for (Users userList : usersList)
        {
            if(userList.getFirstName().equals(user.getFirstName()) && userList.getLastName().equals(user.getLastName()))
                flag = true;
        }
        return flag;
    }

    //changed//
    public boolean isLoginUserAlreadyExists(Login login) {
        boolean flag = false;

        for (Login loginUser : loginList)
        {
            if(loginUser.getUserName().equals(login.getUserName()))
                flag = true;
        }
        return flag;
    }

    public void insertingUserToDataBase(Users user, int id) throws SQLException {
        db.insertingUserToDataBase(user, id);
    }
    public void insertingLoginUserToDataBase(Login login) throws SQLException {
        db.insertingLoginUserToDataBase(login);
    }

    //changed//
    public boolean loginUserAuthentication(String username, String password) {
        boolean flag = false;

        for(Login login : loginList)
        {
            if(login.getUserName().equals(username) && login.getPassword().equals(password))
            {
                db.setLoginUser(login);
                flag = true;
            }
        }
        return flag;
    };

    //changed//
    public void loadLoggedUser(int id) {

        for(Users user : usersList)
        {
            if(user.getUserNameId() == id)
            {
                switch (user.getUsersType())
                {
                    case PrimaryManager:
                        db.setLoggedUser(new PrimaryManager(user));
                        break;
                    case ProjectManager:
                        db.setLoggedUser(new ProjectManager(user));
                        break;
                    case Expert:
                        db.setLoggedUser(new Expert(user));
                        break;
                }
            }
        }
    }

    //changed//
    public void loadUsersFromDataBaseToList() throws SQLException {
        usersList = db.loadUsersFromDataBaseToList();
    }

    //changed//
    public void loadSystemUsersFromDataBaseToList() throws SQLException {
        loginList = db.loadSystemUsersFromDataBaseToList();
    }

    //changed//
    public void removeUser(int row) { db.removeUserFromList(row); }

    /*public void updateSystemUser(ArrayList rowsList, ArrayList columnsList, ArrayList valuesList) throws SQLException {
        db.updateSystemUser(rowsList, columnsList, valuesList);
    }
    public void updateLoginUser(ArrayList rowsList, ArrayList columnsList, ArrayList valuesList) throws SQLException {
        db.updateLoginUser(rowsList, columnsList, valuesList);
    }*/

    //changed//
    public void updateSystemUsers(List usersList) throws SQLException {
        db.updateSystemUser(usersList);
    }

    //changed//
    public void updateLoginUsers(List loginList) throws SQLException {
        db.updateLoginUser(loginList);
    }
}
