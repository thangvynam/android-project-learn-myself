package vn.edu.topica.hocspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.topica.edu.model.NhanVien;

public class MainActivity extends AppCompatActivity {

    TextView txtTen;
    Button btnXacNhan;
    ArrayList<String>arrThu;
    ArrayAdapter<String>adapterThu;
    Spinner spThu;
    int idex=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXacNhan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
        spThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idex=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void xuLy() {
        if(idex==-1){
            Toast.makeText(MainActivity.this,"Kiểm tra lại !!!",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            NhanVien nv =new NhanVien(txtTen.getText().toString());
            Toast.makeText(MainActivity.this,nv.toString()+" chọn "+arrThu.get(idex),Toast.LENGTH_LONG).show();
        }


    }

    private void addControls() {
        txtTen= (TextView) findViewById(R.id.txtTen);
        btnXacNhan= (Button) findViewById(R.id.btnXacNhan);
        spThu= (Spinner) findViewById(R.id.spThu);
        arrThu=new ArrayList<>();
        arrThu.add("Thứ 2");
        arrThu.add("Thứ 3");
        arrThu.add("Thứ 4");
        arrThu.add("Thứ 5");
        arrThu.add("Thứ 6");
        arrThu.add("Thứ 7");
        arrThu.add("Chủ Nhật");
        adapterThu=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,arrThu);
        adapterThu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spThu.setAdapter(adapterThu);
    }
}
