package Controller;

import Model.*;
import View.CreateFormEvent;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UsersManagerController {
    DataBase db;
    Users user = new Users();
    Login login;
    List<Users> usersList;
    List<Login> loginList;

    public UsersManagerController()
    {
        db = new DataBase();
        usersList = new LinkedList<Users>();
        loginList = new LinkedList<Login>();
    }

    public Login getLoginUser() { return db.getLoginUser(); }

    public Users getUserFirstNameLogged(){
        return db.getLoggedUser();
    }

    public List<Login> getSystemUsers() {
        return loginList;
    }

    //-- Using only for tests --//
    public void setSystemUsers(List loginList) { this.loginList = loginList; }

    public List<Users> getUsers() { return usersList; }

    //-- Using only for tests --//
    public void setUsers(List usersList) { this.usersList = usersList; }

    public void addUserToUsersList() { usersList.add(user); }

    public void addLoginToLoginList() { loginList.add(login); }

    public void connect () throws Exception { db.connect(); }

    public void disconnect(){
        //db.disconnect();
    }

    public void createUser(CreateFormEvent event)
    {
        String firstName = event.getFirstName();
        String lastName = event.getLastName();
        String email = event.getEmail();
        String phoneNumber = event.getPhoneNumber();
        UsersType authenticationType = UsersType.valueOf(event.getAuthenticationType());
        String userName = event.getUserName();
        String password = event.getPassword();

        user = new Users(firstName, lastName, email, phoneNumber, authenticationType);
        login = new Login(userName, password);
    }

    public boolean checkPhoneNumber(String phone){
        return user.checkPhoneNumber(phone);
    }

    public boolean checkEmail(String email){
        return user.checkEmail(email);
    }

    public int getLoginUserIndex(Users user)
    {
        int index = 0;
        int size = loginList.size();

        for(int i = 0; i < size; i++)
        {
            if(loginList.get(i).getId() == user.getUserNameId())
                index = i;
        }
        return index;
    }

    public boolean isString(String str, int size)
    {
        return user.isString(str, size);
    }

    public boolean isUserAlreadyExists() {
        boolean flag = false;

        for (Users userList : usersList)
        {
            if(userList.getFirstName().equals(user.getFirstName()) && userList.getLastName().equals(user.getLastName()))
                flag = true;
        }
        return flag;
    }

    public boolean isLoginUserAlreadyExists() {
        boolean flag = false;

        for (Login loginUser : loginList)
        {
            if(loginUser.getUserName().equals(login.getUserName()))
                flag = true;
        }
        return flag;
    }

    public void insertingUserToDataBase() throws SQLException {
        db.insertingUserToDataBase(user, login.getId(), usersList);
    }

    public void insertingLoginUserToDataBase() throws SQLException {
        db.insertingLoginUserToDataBase(login, loginList);
    }

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

    public void loadUsersFromDataBaseToList() throws SQLException {
        usersList = db.loadUsersFromDataBaseToList();
    }

    public void loadSystemUsersFromDataBaseToList() throws SQLException {
        loginList = db.loadSystemUsersFromDataBaseToList();
    }

    public void removeUser(int row) {
        int size = loginList.size();
        Users user = usersList.get(row);
        db.removeUserFromList(user.getId(), user.getUserNameId());
        usersList.remove(row);

        for(int i = 0; i < size; i++)
        {
            if(user.getUserNameId() == loginList.get(i).getId())
            {
                loginList.remove(i);
                break;
            }
        }
    }

    public void updateSystemUsers() throws SQLException {
        db.updateSystemUser(usersList);
    }

    public void updateLoginUsers() throws SQLException {
        db.updateLoginUser(loginList);
    }
}
