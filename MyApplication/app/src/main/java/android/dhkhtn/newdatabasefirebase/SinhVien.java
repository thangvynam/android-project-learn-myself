package android.dhkhtn.newdatabasefirebase;

/**
 * Created by DELL on 3/13/2018.
 */

public class SinhVien {
    private String ten;
    private Integer id;

//    public SinhVien() {
//        // mặc định của Firebase cần phải có khi nhận data
//    }

    public SinhVien(String ten, Integer id) {
        this.ten = ten;
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
