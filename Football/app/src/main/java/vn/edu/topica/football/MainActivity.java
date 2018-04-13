package vn.edu.topica.football;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        TabHost.TabSpec tab1= tabHost.newTabSpec("t1");
        tab1.setIndicator("Player");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2= tabHost.newTabSpec("t2");
        tab2.setIndicator("Club");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);
    }
}
