package vn.topica.edu.model;

/**
 * Created by DELL on 7/6/2017.
 */

public class NhanVien {
    private String ten;
    private int ma;

    public NhanVien(String ten, int ma) {
        this.ten = ten;
        this.ma = ma;
    }

    public NhanVien(String s) {
        this.ten=s;
    }

    public String getTen() {
        return ten;
    }

    public int getMa() {
        return ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    @Override
    public String toString() {
        return this.ten;
    }
}
