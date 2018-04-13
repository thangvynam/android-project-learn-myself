package vn.edu.topica.hocxylycacsukien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener  {
    // khi dùng activity as Listener mới dùng implement

    EditText txtA,txtB;
    Button btnTru,btnNhan,btnChia;
    Button btnAn;
    Button btnThoat;
    View.OnClickListener suKienChiaSe=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPhepTru();
            }
        });
        suKienChiaSe = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btnNhan){
                    xuLyPhepNhan();
                }
                else if (v.getId()==R.id.btnChia){
                    xuLyPhepChia();
                }
            }
        };
        btnNhan.setOnClickListener(suKienChiaSe);
        btnChia.setOnClickListener(suKienChiaSe);
        btnAn.setOnLongClickListener(this);
        btnAn.setOnClickListener(new MyEvent());
    }

    private void xuLyPhepChia() {
        int a=Integer.parseInt(txtA.getText().toString());
        int b=Integer.parseInt(txtB.getText().toString());
        int c=a/b;
        Toast.makeText(MainActivity.this,"Chia ="+c,Toast.LENGTH_LONG).show();
    }

    private void xuLyPhepNhan() {
        int a=Integer.parseInt(txtA.getText().toString());
        int b=Integer.parseInt(txtB.getText().toString());
        int c=a*b;
        Toast.makeText(MainActivity.this,"Nhân ="+c,Toast.LENGTH_LONG).show();
    }

    private void xuLyPhepTru() {
        int a=Integer.parseInt(txtA.getText().toString());
        int b=Integer.parseInt(txtB.getText().toString());
        int c=a-b;
        Toast.makeText(MainActivity.this,"Hiệu ="+c,Toast.LENGTH_LONG).show();
    }
    private void addControls() {
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        btnTru= (Button) findViewById(R.id.btnTru);
        btnNhan= (Button) findViewById(R.id.btnNhan);
        btnChia= (Button) findViewById(R.id.btnChia);
        btnAn= (Button) findViewById(R.id.btnAn);
        btnThoat= (Button) findViewById(R.id.btnThoat);
    }

    public void xuLyPhepCong(View v ){
        int a=Integer.parseInt(txtA.getText().toString());
        int b=Integer.parseInt(txtB.getText().toString());
        int c=a+b;
        Toast.makeText(MainActivity.this,"Tổng ="+c,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId()==R.id.btnAn)
            btnAn.setVisibility(View.INVISIBLE);
        return false;
    }
    public class MyEvent implements View.OnClickListener,View.OnLongClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnThoat)
                finish();
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    public void doiManHinh(View v)
    {
        Button btnMoi= new android.support.v7.widget.AppCompatButton(MainActivity.this)
        {
            @Override
            public boolean performClick() {
                setContentView(R.layout.activity_main);
                addControls();
                addEvents();
                return super.performClick();
            }
        };
        btnMoi.setText("Author : THANG VỸ NAM !");
        btnMoi.setWidth(200);
        btnMoi.setHeight(200);
        setContentView(btnMoi);


    }

}
