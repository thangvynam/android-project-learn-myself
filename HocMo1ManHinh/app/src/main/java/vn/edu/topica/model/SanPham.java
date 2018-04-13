package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 7/17/2017.
 */

public class SanPham implements Serializable {
    private int ma;
    private String ten;

    public SanPham(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public SanPham() {
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return this.ma+"-"+this.ten;
    }
}
