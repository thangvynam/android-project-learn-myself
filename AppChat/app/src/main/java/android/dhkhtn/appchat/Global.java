package android.dhkhtn.appchat;

/**
 * Created by DELL on 4/14/2018.
 */

public class Global  {
    public static final String USER_NAME = "NAME";
    public static final String USER_IMAGE = "IMAGE";
    public static final String BIRTHDAY = "BIRTHDAY";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String SEX = "SEX";
    public static final String ARRAY_NAME = "SEX";


    private static String nameUser ;
    private static boolean notification = false;
    private static String id;
    public static boolean back = true;
    public static boolean showLogo = true;
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
