package vn.edu.topica.hoccontentprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDanhBa,btnSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDanhBa();
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTinNhan();
            }
        });
    }

    private void xuLyTinNhan() {
        Intent intent =new Intent(MainActivity.this,TinNhanActivity.class);
        startActivity(intent);
    }

    private void xuLyDanhBa() {
        Intent intent =new Intent(MainActivity.this,DanhBaActivity.class);
        startActivity(intent);
    }

    private void addControls() {
        btnDanhBa= (Button) findViewById(R.id.btnDanhBa);
        btnSms= (Button) findViewById(R.id.btnSms);
    }
}
