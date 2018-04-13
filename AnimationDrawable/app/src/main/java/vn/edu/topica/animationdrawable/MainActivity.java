package vn.edu.topica.animationdrawable;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop;
    ImageView imgMinion;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });
    }

    private void addControls() {
        btnStart= (Button) findViewById(R.id.btnStart);
        btnStop= (Button) findViewById(R.id.btnStop);
        imgMinion= (ImageView) findViewById(R.id.imgMinion);
        imgMinion.setBackgroundResource(R.drawable.hieuung);
        animationDrawable = (AnimationDrawable) imgMinion.getBackground();

    }
}
