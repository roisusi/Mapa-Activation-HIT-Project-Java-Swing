package View;

import Controller.Controller;
import Model.ActivationFormSip;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ActivationsMoves {

    public static class SessionId{
        private static String userName;
        private static String isApproved;

        public static String setUserName(String userName){
            return SessionId.userName = userName;
        }
        public static String getUserName(){
            return SessionId.userName;
        }
        public static String isApproved(String isApproved){
            return SessionId.isApproved = isApproved;
        }
        public static String isApproved(){
            return SessionId.isApproved;
        }
    }

    public static class FormId{
        private static int activationId;

        public static void setActivationId() {
        }

        public static int getActivationId() {
            return FormId.activationId;
        }
        public static void setActivationId(int activationId) {
            FormId.activationId = activationId;
        }
    }

    public static class LoadActivationFromList{
        private static List<ActivationFormSip> activationFormSips;
        private static Controller controller;

        public static List<ActivationFormSip> getActivationSip(){
            controller = new Controller();
            try {
                controller.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                controller.loadCalenderSipActivationToList();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            activationFormSips = controller.getSipActivation();
            controller.disconnect();
            return activationFormSips;
        }
    }
}
