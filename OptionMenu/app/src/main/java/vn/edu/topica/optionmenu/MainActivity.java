package vn.edu.topica.optionmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnDo)
            txtText.setBackgroundColor(Color.RED);
        else if(item.getItemId()==R.id.mnuXanh)
            txtText.setBackgroundColor(Color.BLUE);
        else
            txtText.setBackgroundColor(Color.YELLOW);
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {
        txtText= (TextView) findViewById(R.id.txtText);
    }
}
