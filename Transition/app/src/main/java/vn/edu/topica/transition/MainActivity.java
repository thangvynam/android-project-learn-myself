package vn.edu.topica.transition;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtChange;
    Button btnStart;
    TransitionDrawable transitionDrawable;
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
                start();
            }
        });
    }

    private void start() {
        transitionDrawable.reverseTransition(5000);

    }

    private void addControls() {
        txtChange= (TextView) findViewById(R.id.txtChange);
        btnStart= (Button) findViewById(R.id.btnStart);
        transitionDrawable= (TransitionDrawable) txtChange.getBackground();

    }
}
