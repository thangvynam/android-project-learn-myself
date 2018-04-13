package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 7/4/2017.
 */

public class DanhBa implements Serializable{
    private int ma;
    private String ten;
    private String phone;

    public DanhBa(int ma, String ten, String phone) {
        this.ma = ma;
        this.ten = ten;
        this.phone = phone;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getPhone() {
        return phone;
    }

    public DanhBa() {
    }
}
