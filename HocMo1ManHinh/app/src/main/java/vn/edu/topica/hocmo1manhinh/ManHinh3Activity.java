package vn.edu.topica.hocmo1manhinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vn.edu.topica.model.SanPham;

public class ManHinh3Activity extends AppCompatActivity {

    TextView txtKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh3);
        addControls();
    }

    private void addControls() {
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);
        Intent intent =getIntent();
        Bundle bundle= intent.getBundleExtra("MyBundle");
        int a= bundle.getInt("I");
        char b= bundle.getChar("C");
        SanPham sp= (SanPham) bundle.getSerializable("SanPham");
        txtKetQua.setText("I= "+a+"\n"+"C= "+b+sp);
    }
}
