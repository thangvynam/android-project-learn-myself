package vn.edu.topica.togglebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton tgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tgButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(MainActivity.this,"Switch ON",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Switch OFF",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        tgButton= (ToggleButton) findViewById(R.id.tgButton);
    }
}
