package android.dhkhtn.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class resultform extends AppCompatActivity {

    public static final String NAME="name";
    public static final String PASSWORD="PASSWORD";
    public static final String BIRTHDATE ="BIRTHDATE";
    public static final String GENDER ="GENDER";
    public static final String HOBBIES = "HOBBIES";
    public static final String INFO = "INFO";

    public static final String EXIT="EXIT";
    TextView txtName,txtPassword,txtBirthdate,txtGender,txtHobbies;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultform);

        addControls();
        addEvents();
        loading();


    }

    private void loading() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(INFO);
        String username = bundle.getString(NAME);
        String password = bundle.getString(PASSWORD);
        String birthdate = bundle.getString(BIRTHDATE);
        String gender = bundle.getString(GENDER);
        String tempHobbies = "";
        ArrayList<String> hobbies = bundle.getStringArrayList(HOBBIES);
        
        txtName.setText(username);
        txtPassword.setText(password);
        txtBirthdate.setText(birthdate);
        txtGender.setText(gender);
        for(int i=0;i<hobbies.size();i++){
            if(i==hobbies.size()-1) {
                tempHobbies += hobbies.get(i);
            }else {
                tempHobbies+= hobbies.get(i)+ ',';
            }

        }
        txtHobbies.setText(tempHobbies);
        
        
    }

    private void addEvents() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishApp();
            }

            
        });

    }

    private void finishApp() {
        Intent intent = new Intent(this, registerform.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXIT, true);
        startActivity(intent);
    }

    private void addControls() {
        txtName = (TextView) findViewById(R.id.txtName);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtBirthdate = (TextView) findViewById(R.id.txtBirthdate);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtHobbies = (TextView) findViewById(R.id.txtHobbies);
        btnExit = (Button) findViewById(R.id.btnExit);
    }
}
