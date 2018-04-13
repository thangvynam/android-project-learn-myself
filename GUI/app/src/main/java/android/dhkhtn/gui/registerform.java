package android.dhkhtn.gui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class registerform extends AppCompatActivity {

    public static final String NAME="name";
    public static final String PASSWORD="PASSWORD";
    public static final String BIRTHDATE ="BIRTHDATE";
    public static final String GENDER ="GENDER";
    public static final String HOBBIES = "HOBBIES";
    public static final String INFO = "INFO";
    public static final String EXIT="EXIT";

    EditText txtName,txtPassword,txtRetype,txtDate;
    Button btnSelect,btnReset,btnSignUp;
    RadioButton radMale,radFemale;
    CheckBox chkTennis,chkFutbal,chkOther;
    Calendar calendar;
    SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);
        addControls();
        addEvents();


        if (getIntent().getBooleanExtra(EXIT, false)) {
            finish();
        }

    }

    private void addControls() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRetype = (EditText) findViewById(R.id.txtRetype);
        txtDate = (EditText) findViewById(R.id.txtDate);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        radMale = (RadioButton) findViewById(R.id.radMale);
        radFemale = (RadioButton) findViewById(R.id.radFemale);
        chkTennis = (CheckBox) findViewById(R.id.chkTennis);
        chkFutbal = (CheckBox) findViewById(R.id.chkFutbal);
        chkOther = (CheckBox) findViewById(R.id.chkOther);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        calendar=Calendar.getInstance();
    }

    private void addEvents() {
        radFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radMale.isChecked()){
                    radMale.setChecked(false);
                }
            }
        });
        radMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radFemale.isChecked()){
                    radFemale.setChecked(false);
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setText("");
                txtPassword.setText("");
                txtRetype.setText("");
                txtDate.setText("");
                radFemale.setChecked(false);
                radMale.setChecked(false);
                chkFutbal.setChecked(false);
                chkTennis.setChecked(false);
                chkOther.setChecked(false);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DATE,dayOfMonth);
                        txtDate.setText(sdf1.format(calendar.getTime()));
                    }
                };
                DatePickerDialog datePickerDialog =new DatePickerDialog(registerform.this,listener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                datePickerDialog.show();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtPassword.getText().toString().compareTo("")!=0 && txtName.getText().toString().compareTo("")!=0 && txtDate.getText().toString().compareTo("")!=0
                        &&  (radFemale.isChecked() || radMale.isChecked()) && (chkFutbal.isChecked() || chkTennis.isChecked() || chkOther.isChecked())){
                    if(txtPassword.getText().toString().compareTo(txtRetype.getText().toString())==0){
                        Intent intent = new Intent(registerform.this,resultform.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(NAME,txtName.getText().toString());
                        bundle.putString(PASSWORD,txtPassword.getText().toString());
                        bundle.putString(BIRTHDATE,txtDate.getText().toString());

                        if(radFemale.isChecked()){
                            bundle.putString(GENDER,radFemale.getText().toString());
                        }else{
                            bundle.putString(GENDER,radMale.getText().toString());
                        }

                        ArrayList<String> hobbiesArray = new ArrayList<String>();
                        if(chkFutbal.isChecked())   hobbiesArray.add(chkFutbal.getText().toString());
                        if(chkOther.isChecked())    hobbiesArray.add(chkOther.getText().toString());
                        if(chkTennis.isChecked())   hobbiesArray.add(chkTennis.getText().toString());

                        bundle.putStringArrayList(HOBBIES,hobbiesArray);
                        intent.putExtra(INFO,bundle);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(registerform.this, "Please check your password !", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(registerform.this, "Please check your information !", Toast.LENGTH_SHORT).show();
                }
                

            }
        });
    }
}
