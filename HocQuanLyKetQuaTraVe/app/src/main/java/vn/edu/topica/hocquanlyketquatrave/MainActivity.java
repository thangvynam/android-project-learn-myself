package vn.edu.topica.hocquanlyketquatrave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtA,txtB;
    TextView txtKetQua;
    Button btnGui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
    }

    private void xuLy() {
        Intent intent= new Intent(MainActivity.this,ManHinh2Activity.class);
        intent.putExtra("A",Integer.parseInt(txtA.getText().toString()));
        intent.putExtra("B",Integer.parseInt(txtB.getText().toString()));
        startActivityForResult(intent,99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == 33){
            txtKetQua.setText("UCLN= "+data.getIntExtra("UCLN",1));
        }
    }
    private void addControls() {
        txtB= (EditText) findViewById(R.id.txtB);
        txtA= (EditText) findViewById(R.id.txtA);
        btnGui= (Button) findViewById(R.id.btnGui);
        txtKetQua= (TextView) findViewById(R.id.txtKetQua);
    }
}
