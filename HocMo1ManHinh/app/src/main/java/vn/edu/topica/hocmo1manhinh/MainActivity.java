package vn.edu.topica.hocmo1manhinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.edu.topica.model.SanPham;
import vn.edu.topica.model.SinhVien;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyGuiBangIntent(View view) {
        Intent intent =new Intent(MainActivity.this,ManHinh2Activity.class);
        intent.putExtra("KIEU_INT",18);
        intent.putExtra("KIEU_CHAR",'x');
        intent.putExtra("KIEU_FLOAT",4.954f);
        intent.putExtra("KIEU_SRING","thang vỹ nam");
        intent.putExtra("KIEU_SINHVIEN",new SinhVien("sơn tùng mtp",1));
        startActivity(intent);
    }
    public void xuLyGuiBangBundle(View view){
        Intent intent =new Intent(MainActivity.this,ManHinh3Activity.class);
        Bundle bundle =new Bundle();
        bundle.putInt("I",145);
        bundle.putChar("C",'n');
        bundle.putSerializable("SanPham",new SanPham(10,"Trà xanh không độ"));
        intent.putExtra("MyBundle",bundle);
        startActivity(intent);
    }


}
