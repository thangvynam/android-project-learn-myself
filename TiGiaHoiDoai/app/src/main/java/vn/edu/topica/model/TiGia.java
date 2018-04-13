package vn.edu.topica.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by DELL on 8/14/2017.
 */

public class TiGia implements Serializable {
    private String type;
    private String imageurl;
    private Bitmap bitmap;
    private String muatienmat;
    private String bantienmat;
    private String muack;
    private String banck;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMuatienmat() {
        return muatienmat;
    }

    public void setMuatienmat(String muatienmat) {
        this.muatienmat = muatienmat;
    }

    public String getBantienmat() {
        return bantienmat;
    }

    public void setBantienmat(String bantienmat) {
        this.bantienmat = bantienmat;
    }

    public String getMuack() {
        return muack;
    }

    public void setMuack(String muack) {
        this.muack = muack;
    }

    public String getBanck() {
        return banck;
    }

    public void setBanck(String banck) {
        this.banck = banck;
    }

    public TiGia() {

    }

    public TiGia(String type, String imageurl, Bitmap bitmap, String muatienmat, String bantienmat, String muack, String banck) {

        this.type = type;
        this.imageurl = imageurl;
        this.bitmap = bitmap;
        this.muatienmat = muatienmat;
        this.bantienmat = bantienmat;
        this.muack = muack;
        this.banck = banck;
    }
}
