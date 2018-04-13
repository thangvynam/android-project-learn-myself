package vn.edu.topica.progressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar2;
    Button btnStart;
    int xuLyHienTai=0;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressBar2.setProgress(msg.what+10);
            }
        };
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
    }

    private void xuLy() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(xuLyHienTai<100){
                    xuLyHienTai=progressBar2.getProgress();
                    handler.sendEmptyMessage(xuLyHienTai);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void addControls() {
        progressBar2= (ProgressBar) findViewById(R.id.progressBar2);
        btnStart = (Button) findViewById(R.id.btnStart);

    }
}
