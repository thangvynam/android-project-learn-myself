package vn.edu.topica.hocautocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtTen ;
    TextView txtThongTin;
    Button btnXacNhan;

    String []arrTinhThanh;
    ArrayAdapter<String>adapterTinhThanh;
    AutoCompleteTextView autoTinhThanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
    }

    private void xuLy() {
        txtThongTin.setText(txtTen.getText()+"\n"+autoTinhThanh.getText().toString());
    }

    private void addControls() {
        txtTen= (EditText) findViewById(R.id.txtTen);
        txtThongTin= (TextView) findViewById(R.id.txtThongTin);
        btnXacNhan= (Button) findViewById(R.id.btnXacNhan);
        autoTinhThanh = (AutoCompleteTextView) findViewById(R.id.autoTinhThanh);
        arrTinhThanh =getResources().getStringArray(R.array.arrTinhThanh);
        adapterTinhThanh=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrTinhThanh);
        autoTinhThanh.setAdapter(adapterTinhThanh);

    }
}
