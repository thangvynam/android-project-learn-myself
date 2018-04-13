package vn.edu.topica.sleepnow;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {

    ImageView imgPic;
    TextView txtTime;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addControls();
        addEvents();
    }

    private void addEvents() {
        animationDrawable.start();
        Calendar cal= Calendar.getInstance();
        Date t = cal.getTime();
        SimpleDateFormat sdf4 = new SimpleDateFormat("hh:mm:ss aaa");
        txtTime.setText(sdf4.format(t));
    }

    private void addControls() {
        imgPic= (ImageView) findViewById(R.id.imgPic);
        txtTime= (TextView) findViewById(R.id.txtTime);
        imgPic.setBackgroundResource(R.drawable.hieuung2);
        animationDrawable = (AnimationDrawable) imgPic.getBackground();
    }
}
