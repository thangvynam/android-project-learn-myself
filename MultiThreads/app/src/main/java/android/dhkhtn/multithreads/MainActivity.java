package android.dhkhtn.multithreads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar myBarHorizontal;
    TextView txtName;
    EditText edtValue;
    Button btnStart;
    int MAX_PROGRESS =100;
    Handler handler;
    int global=0;
    int accum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBarHorizontal = (ProgressBar) findViewById(R.id.myBarHorizontal);
        txtName = (TextView) findViewById(R.id.txtPer);
        edtValue = (EditText) findViewById(R.id.edtValue);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                global =Integer.parseInt(edtValue.getText().toString());
                onStart();
            }
        });
        handler = new Handler();


    }

    @Override
    protected void onStart() {
        super.onStart();

        myBarHorizontal.setMax(global);
        myBarHorizontal.setProgress(0);
        myBarHorizontal.setVisibility(View.VISIBLE);
        Thread myBackgroundThread = new Thread( backgroundTask, "backAlias1" );
        myBackgroundThread.start();
        accum=0;
    }
    private Runnable foregroundRunnable = new Runnable() {
        @Override
        public void run() {
            btnStart.setEnabled(false);
            edtValue.setEnabled(false);
            accum+=1;
            int temp= (int) ((accum*1.0/global)*100);
            txtName.setText(temp+"%");
            myBarHorizontal.incrementProgressBy(1);
            if (accum >= myBarHorizontal.getMax()) {
                txtName.setText("100%");
                btnStart.setEnabled(true);
                edtValue.setEnabled(true);
            }
        }
    };
    private Runnable backgroundTask = new Runnable(){

        @Override
        public void run() {
            for (int n = 0; n < global; n++) {
                try {
                    Thread.sleep(0);
                    handler.post(foregroundRunnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    };
}
