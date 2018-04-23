package android.dhkhtn.appchat;

/**
 * Created by DELL on 4/14/2018.
 */

public class Global  {
    private static String nameUser ;
    private static boolean notification = false;
    private static String id;

    public static void setId(String id) {
        Global.id = id;
    }

    public static String getId() {

        return id;
    }

    public static void setNameUser(String nameUser) {
        Global.nameUser = nameUser;
    }

    public static void setNotification(boolean notification) {
        Global.notification = notification;
    }

    public static boolean isNotification() {

        return notification;
    }

    public static String getNameUser() {

        return nameUser;
    }
}
