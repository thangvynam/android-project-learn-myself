package vn.edu.topica.animation1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnXoay,btnXoayManHinh,btnXoay3s,btnHieuUng;
    Animation animation=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXoay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnXoay.startAnimation(animation);
            }
        });
        btnXoayManHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout= (LinearLayout) findViewById(R.id.layoutManHInh);
                linearLayout.startAnimation(animation);
            }
        });
        btnXoay3s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaynguocmanhinh);
                btnXoay3s.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        btnHieuUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,ManHinh2Activity.class);
                startActivity(intent);
            }
        });

    }

    private void addControls() {
        btnXoay= (Button) findViewById(R.id.btnXoay);
        btnXoayManHinh= (Button) findViewById(R.id.btnXoayManHinh);
        btnXoay3s= (Button) findViewById(R.id.btnXoay3s);
        btnHieuUng= (Button) findViewById(R.id.btnHieuUng);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaycontrol);
    }
}
