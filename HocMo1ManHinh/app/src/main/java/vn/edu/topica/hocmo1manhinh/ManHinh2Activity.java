package vn.edu.topica.hocmo1manhinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vn.edu.topica.model.SinhVien;

public class ManHinh2Activity extends AppCompatActivity {

    TextView txtKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh3);
        addControls();
    }

    private void addControls() {
        txtKetQua  = (TextView) findViewById(R.id.txtKetQua);
        Intent intent=getIntent();
        int a=intent.getIntExtra("KIEU_INT",0);
        char b=intent.getCharExtra("KIEU_CHAR",'a');
        float c=intent.getFloatExtra("KIEU_FLOAT",1.36f);
        SinhVien sv= (SinhVien) intent.getSerializableExtra("KIEU_SINHVIEN");
        String d=intent.getStringExtra("KIEU_SRING");
        txtKetQua.setText("Kiểu int "+a
                         +"Kiểu float "+c
                         +"Kiểu char "+b
                         +"Kiểu String "+d
                         +"Kiểu sinh viên"+sv);

    }
}
