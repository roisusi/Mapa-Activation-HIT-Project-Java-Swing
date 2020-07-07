package Controller;

import Model.DataBase;
import Model.Login;
import Model.Users;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UsersManagerController {
    DataBase db = new DataBase();

    public void addSystemUser(Users user) { db.addUserToList(user); }

    public void connect () throws Exception { db.connect(); }

    public void disconnect() { db.disconnect(); }

    public Login getLoginUser() { return db.getLoginUser(); }

    public List<Login> getUsers() {
        return db.getLoginUsersFromList();
    }

    public List<Users> getSystemUsers() { return db.getUsersFromList(); }

    public Users getUserFirstNameLogged(){
        return db.getUserFirstNameLogged();
    }

    public boolean isUserAlreadyExists(Users user) { return db.isUserAlreadyExists(user); }

    public boolean isLoginUserAlreadyExists(Login login) {
        return db.isLoginUserAlreadyExists(login);
    }

    public void insertingUserToDataBase(Users user, int id) throws SQLException {
        db.insertingUserToDataBase(user, id);
    }
    public void insertingLoginUserToDataBase(Login login) throws SQLException {
        db.insertingLoginUserToDataBase(login);
    }

    public boolean loginUserAuthentication(String username, String password) throws SQLException {
        return db.loginUserAuthentication(username, password);
    };

    public void loadLoggedUser(int id) throws SQLException { db.loadLoggedUser(id); }

    public void loadUsersFromDataBaseToList() throws SQLException { db.loadUsersFromDataBaseToList(); }

    public void loadSystemUsersFromDataBaseToList() throws SQLException { db.loadSystemUsersFromDataBaseToList(); }

    public void removeUser(int row) { db.removeUserFromList(row); }

    /*public void updateSystemUser(ArrayList rowsList, ArrayList columnsList, ArrayList valuesList) throws SQLException {
        db.updateSystemUser(rowsList, columnsList, valuesList);
    }
    public void updateLoginUser(ArrayList rowsList, ArrayList columnsList, ArrayList valuesList) throws SQLException {
        db.updateLoginUser(rowsList, columnsList, valuesList);
    }*/

    public void updateSystemUsers(List usersList) throws SQLException {
        db.updateSystemUser(usersList);
    }

    public void updateLoginUsers(List loginList) throws SQLException {
        db.updateLoginUser(loginList);
    }
}
