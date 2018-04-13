package vn.edu.topica.login;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtName,txtPassword;
    CheckBox chkLuu;
    Button btnLuu;
    String temp="TrangThai";
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
        txtName = (EditText) findViewById(R.id.txtName);
        txtPassword= (EditText) findViewById(R.id.txtPassword);
        btnLuu= (Button) findViewById(R.id.btnLuu);
        chkLuu= (CheckBox) findViewById(R.id.chkLuu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences(temp,MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("Name",txtName.getText().toString());
        editor.putString("Password",txtPassword.getText().toString());
        editor.putBoolean("Save",chkLuu.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(temp,MODE_PRIVATE);
        String name=preferences.getString("Name","");
        String password=preferences.getString("Password","");
        if(preferences.getBoolean("Save",false)){
            txtName.setText(name);
            txtPassword.setText(password);
        }
    }
}
