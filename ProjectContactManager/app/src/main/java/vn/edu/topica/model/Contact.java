package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 7/20/2017.
 */

public class Contact implements Serializable {
    private String Name;
    private String Phone;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Contact(String name, String phone) {

        Name = name;
        Phone = phone;
    }

    public Contact() {

    }
}
