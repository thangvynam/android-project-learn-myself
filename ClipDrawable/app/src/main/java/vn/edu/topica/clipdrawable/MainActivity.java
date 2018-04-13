package vn.edu.topica.clipdrawable;

import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    Button btnClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyClip();
            }
        });
    }

    private void xuLyClip() {
        //min level =0 ---> max level =10.000
        ClipDrawable clipDrawable = (ClipDrawable) imgHinh.getDrawable();
        clipDrawable.setLevel(clipDrawable.getLevel()+1000);
    }

    private void addControls() {
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
        btnClip= (Button) findViewById(R.id.btnClip);
    }
}
