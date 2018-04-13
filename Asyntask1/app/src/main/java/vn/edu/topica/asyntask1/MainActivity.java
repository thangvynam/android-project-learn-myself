package vn.edu.topica.asyntask1;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    EditText txtSoButton;
    Button btnVe;
    ProgressBar progressBar;
    TextView txtPercent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyVeButton();
            }
        });
    }

    private void xuLyVeButton() {
        int n= Integer.parseInt(txtSoButton.getText().toString());
        ButtonTask buttonTask = new ButtonTask();
        buttonTask.execute(n);

    }
    class ButtonTask extends AsyncTask<Integer,Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            linearLayout.removeAllViews();
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setProgress(100);
            txtPercent.setText("100%");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int percent= values[0];
            int value = values[1];
            progressBar.setProgress(percent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            Button button = new Button(MainActivity.this);
            button.setLayoutParams(layoutParams);
            button.setText(value+"");
            linearLayout.addView(button);
            txtPercent.setText(percent+"%");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int n =params[0];
            Random  random = new Random();
            for(int i=0;i<n;i++)
            {
                int percent= i*100/n;
                int value = random.nextInt();
                publishProgress(percent,value);
                SystemClock.sleep(100);
            }
            return null;
        }
    }


    private void addControls() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        txtSoButton= (EditText) findViewById(R.id.txtSoButton);
        btnVe = (Button) findViewById(R.id.btnVe);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        txtPercent= (TextView) findViewById(R.id.txtPercent);
        
    }
}
