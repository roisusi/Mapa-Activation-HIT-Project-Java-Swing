package View;

import java.util.ArrayList;

public class ActivationsMoves {

    public static class SessionId{
        private static String userName;
        private static ArrayList fromRange;
        private static ArrayList toRange;
        public static int newID;
        public static String expertName;

        private SessionId(){
        }
        public static String setUserName(String name){
            return SessionId.userName = name;
        }
        public static String getUserName(){
            return SessionId.userName;
        }

        public static String getExpertName(){
            return SessionId.expertName;
        }
        public static String setExpertName(String name){
            return SessionId.expertName = name;
        }

        public static int getNewID() {
            return newID;
        }

        public static void setNewID(int newID) {
            SessionId.newID = newID;
        }

        public static ArrayList getFromRange() {
            return fromRange;
        }

        public static void setFromRange(ArrayList fromRange) {
            SessionId.fromRange = fromRange;
        }

        public static ArrayList getToRange() {
            return toRange;
        }

        public static void setToRange(ArrayList toRange) {
            SessionId.toRange = toRange;
        }

        public static void remove(){
            fromRange.removeAll(fromRange);
            toRange.removeAll(toRange);
            fromRange = null;
            toRange = null;

        }
    }

    public static class FormId{
        private static int activationId;

        public static int getActivationId() {
            return FormId.activationId;
        }
        public static void setActivationId(int activationId) {
            FormId.activationId = activationId;
        }
    }
}
