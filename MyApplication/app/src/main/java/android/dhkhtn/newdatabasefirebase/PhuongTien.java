package android.dhkhtn.newdatabasefirebase;

/**
 * Created by DELL on 3/27/2018.
 */

public class PhuongTien {
    public String ten;
    public Integer banh ;

    public PhuongTien() { // cái này cần phải có khi nhận ko sẽ báo lỗi
    }

    public PhuongTien(String ten, Integer banh) {
        this.ten = ten;
        this.banh = banh;
    }

    public String getTen() {
        return ten;
    }

    public Integer getBanh() {
        return banh;
    }
}
