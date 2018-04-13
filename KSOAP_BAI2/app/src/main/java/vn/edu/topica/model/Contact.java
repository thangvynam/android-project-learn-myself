package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 8/6/2017.
 */

public class Contact{
    private String ten;
    private String phone;
    private int Ma;

    public Contact() {
    }

    public Contact(String name, String phone, int ma) {

        ten = name;
        this.phone = phone;
        Ma = ma;
    }
    public String getName() {
        return ten;
    }

    public void setName(String name) {
        ten = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    @Override
    public String toString() {
        return this.ten+"-"+this.phone+"-"+this.Ma+" ";
    }
}
