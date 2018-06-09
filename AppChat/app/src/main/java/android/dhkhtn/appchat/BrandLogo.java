package android.dhkhtn.appchat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class BrandLogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_logo);
        Handler handler = new Handler();
        TranslateAnimation translateYAnimation = new TranslateAnimation(0f, 0f, 0f, -2200f);
        translateYAnimation.setDuration(5000l);
        translateYAnimation.setRepeatCount(Animation.INFINITE);
        translateYAnimation.setRepeatMode(Animation.REVERSE);
        handler.postDelayed(new Runnable() {
            public void run() {
                Global.showLogo=false;
                Intent intent = new Intent(BrandLogo.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        }, 3000);
    }
}
