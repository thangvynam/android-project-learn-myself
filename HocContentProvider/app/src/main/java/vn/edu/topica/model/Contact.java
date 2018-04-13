package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 7/26/2017.
 */

public class Contact implements Serializable {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact() {

    }

    public Contact(String name, String phone) {

        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.getName()+"-"+this.getPhone();
    }
}
