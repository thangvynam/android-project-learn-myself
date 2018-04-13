package vn.edu.topica.sleepnow;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtTip;
    EditText txtPass;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.tips){
            txtTip.setText("You can type anything you like but it must have 'huong' word");
            txtTip.setTextColor(Color.WHITE);
        }
        return super.onOptionsItemSelected(item);
    }
    private void addEvents() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
    }

    private void xuLy() {
        String string = txtPass.getText().toString();
        if(string.contains("huong")){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this,"Please enter again ! ",Toast.LENGTH_LONG).show();
            txtPass.setText("");
        }

    }

    private void addControls() {
        txtPass= (EditText) findViewById(R.id.txtPass);
        btnOk= (Button) findViewById(R.id.btnOk);
        txtTip= (TextView) findViewById(R.id.txtTip);
    }
}
