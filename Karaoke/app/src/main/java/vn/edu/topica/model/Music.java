package vn.edu.topica.model;

import java.io.Serializable;

/**
 * Created by DELL on 7/14/2017.
 */

public class Music implements Serializable {
    private String ma;
    private String ten;
    private String caSi;
    private boolean like;

    public Music(String ma, String ten, String caSi,boolean like) {
        this.ma = ma;
        this.ten = ten;
        this.caSi = caSi;
        this.like=like;
    }

    public Music() {
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isLike() {

        return like;
    }

    public String getMa() {
        return ma;
    }
    public String getTen() {
        return ten;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }
}
