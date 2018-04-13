package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 7/17/2017.
 */

public class SinhVien implements Serializable {
    private String ten;
    private int ma;

    public SinhVien(String ten, int ma) {
        this.ten = ten;
        this.ma = ma;
    }

    public SinhVien() {
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public int getMa() {
        return ma;
    }

    @Override
    public String toString() {
        return this.ten+"-"+this.ma;
    }
}
