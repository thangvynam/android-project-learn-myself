package android.dhkhtn.appchat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailPerson extends AppCompatActivity {

    ImageView imgUser;
    TextView txtName,txtEmail,txtPhone,txtBirthday,txtSex;
    ImageButton imgCall;
    private final int CALL_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);
        addControls();
        addEvents();

    }

    private void addEvents() {
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validationCheck())
                    callPhoneNumber();
            }
        });

    }

    private void addControls() {
        imgUser= findViewById(R.id.imgUser);
        txtName= findViewById(R.id.txtName);
        txtPhone= findViewById(R.id.txtPhone);
        txtBirthday= findViewById(R.id.txtBirthday);
        txtSex= findViewById(R.id.txtSex);
        imgCall= findViewById(R.id.imgCall);

        Intent intent = getIntent();
        Bundle extras = this.getIntent().getExtras();
        String name = extras.getString(Global.USER_NAME);
        String image  = extras.getString(Global.USER_IMAGE);
        String birthday  = extras.getString(Global.BIRTHDAY);
        String sex  = extras.getString(Global.SEX);
        String numberPhone  = extras.getString(Global.PHONE_NUMBER);

        Picasso.with(this).load(image).into(imgUser);
        imgUser.setRotation(imgUser.getRotation()-90);
        txtName.setText(name);
        txtBirthday.setText(birthday);
        txtSex.setText(sex);
        txtPhone.setText(numberPhone);

    }
    public void callPhoneNumber()
    {
        try
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST);

                    return;
                }
            }
            Intent intent= new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: "+txtPhone.getText().toString()));
            startActivity(intent);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        if(requestCode == CALL_REQUEST)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber();
            }
        }
    }
    private boolean validationCheck()
    {
        try
        {
            String phoneNo = txtPhone.getText().toString().trim();
            if (phoneNo.equalsIgnoreCase(""))
            {
                txtPhone.setError("Have not phone number");
                txtPhone.requestFocus();
                return false;
            }
            if (phoneNo.length() < 6 || phoneNo.length() > 13)
            {
                txtPhone.setError("Phone number is not correct");
                txtPhone.requestFocus();
                return false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return true;
    }

}
