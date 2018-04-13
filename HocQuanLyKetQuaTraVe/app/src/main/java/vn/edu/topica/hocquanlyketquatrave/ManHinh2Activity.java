package vn.edu.topica.hocquanlyketquatrave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManHinh2Activity extends AppCompatActivity {

    TextView txtNhan;
    Button btnUCLN;
    Intent intent;
    int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnUCLN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UCLN();
            }
        });
    }

    private void UCLN() {
        int min=Math.min(a,b);
        int ucln=1;
        for(int i=min;i>1;i--) {
            if (a % i == 0 && b % i == 0) {
                ucln=i;break;
            }
        }

        intent.putExtra("UCLN",ucln);
        setResult(33,intent);
        finish();
    }

    private void addControls() {
        txtNhan = (TextView) findViewById(R.id.txtNhan);
        btnUCLN = (Button) findViewById(R.id.btnUCLN);
        intent = getIntent();
        a = intent.getIntExtra("A", -1);
        b = intent.getIntExtra("B", -1);
        txtNhan.setText("a="+a+" & b= "+b);
    }

}
