package vn.edu.topica.hoccheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkAndroid,chkWindowsPhone,chkiOs;
    Button btnChon;
    TextView txtMonHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonMonHoc();
            }
        });
    }

    private void xuLyChonMonHoc() {
        StringBuilder ss=new StringBuilder();
        if(chkAndroid.isChecked())
            ss.append(chkAndroid.getText().toString()+"\n");
        if (chkWindowsPhone.isChecked())
            ss.append(chkWindowsPhone.getText().toString()+ "\n");
        if(chkiOs.isChecked())
            ss.append(chkiOs.getText().toString()+"\n");
        txtMonHoc.setText(ss.toString());
    }

    private void addControls() {
        chkAndroid = (CheckBox) findViewById(R.id.chkAndroid);
        chkiOs = (CheckBox) findViewById(R.id.chkiOs);
        chkWindowsPhone = (CheckBox) findViewById(R.id.chkWindowsPhone);
        btnChon = (Button) findViewById(R.id.btnChon);
        txtMonHoc = (TextView) findViewById(R.id.txtMonHoc);
    }

}
