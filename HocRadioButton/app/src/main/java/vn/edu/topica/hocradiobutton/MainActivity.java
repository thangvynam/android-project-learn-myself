package vn.edu.topica.hocradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    RadioButton radNam,radNhu,radHung,radThuy;
    ImageButton btnThoat;
    ImageView imgHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        radNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked)
                   imgHinh.setImageResource(R.drawable.nam);
            }
        });
        radNhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    imgHinh.setImageResource(R.drawable.nhu);
            }
        });
        radThuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    imgHinh.setImageResource(R.drawable.thuy);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        radNam= (RadioButton) findViewById(R.id.radNam);
        radNhu= (RadioButton) findViewById(R.id.radNhu);
        radHung= (RadioButton) findViewById(R.id.radHung);
        radThuy= (RadioButton) findViewById(R.id.radThuy);
        btnThoat = (ImageButton) findViewById(R.id.btnThoat);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);

    }
}
