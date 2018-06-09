package android.dhkhtn.appchat;

import java.io.Serializable;

/**
 * Created by DELL on 4/29/2018.
 */

public class ItemMembers implements Serializable {
    private String name;
    private String imgUser;

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getName() {

        return name;
    }

    public String getImgUser() {
        return imgUser;
    }

    public ItemMembers() {

    }

    public ItemMembers(String name, String imgUser) {

        this.name = name;
        this.imgUser = imgUser;
    }
}
