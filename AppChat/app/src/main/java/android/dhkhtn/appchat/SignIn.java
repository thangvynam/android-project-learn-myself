package android.dhkhtn.appchat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class SignIn extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE =1 ;
    private static final int RESULT_LOAD_IMG = 2;
    Button btnSignIn;
    ImageButton btnDate;
    ImageView imgCamera;
    EditText edtEmail,edtPassword,edtNickName,edtPhoneNumber;
    TextView txtDate;
    RadioButton radMale,radFemale;
    Spinner spnEmail;
    ArrayList<String >emailProvider;
    ArrayAdapter<String> adapterEmail;
    private FirebaseAuth mAuth;
    String boolCheck = "";
    int index =0;
    private FirebaseStorage storage;
    private  StorageReference storageRef;
    private Uri filePath;
    private int DIVIDE_IMAGE=0;
    Calendar calendar;
    SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy");
    Animation animScale;
    Animation animAlpha;
    Animation animRotate;;




    BroadcastReceiver receiverWifi=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo()!=null){
                btnSignIn.setVisibility(View.VISIBLE);
                edtEmail.setError(null);
                edtPhoneNumber.setError(null);
                edtPassword.setError(null);
                edtNickName.setError(null);
            }
            else
            {
                btnSignIn.setVisibility(View.INVISIBLE);
                edtEmail.setError(getString(R.string.wifi));
                edtEmail.requestFocus();
                edtPhoneNumber.setError(getString(R.string.wifi));
                edtPhoneNumber.requestFocus();
                edtPassword.setError(getString(R.string.wifi));
                edtPassword.requestFocus();
                edtNickName.setError(getString(R.string.wifi));
                edtNickName.requestFocus();
            }
        }
    };

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(receiverWifi!=null)
            unregisterReceiver(receiverWifi);
    }
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new 	IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiverWifi, intentFilter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        animScale = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        animRotate = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        mAuth = FirebaseAuth.getInstance();

        storage= FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://app-chat-500be.appspot.com");

        addControls();
        addEvents();


    }

    private void addEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(false);
                animationSet.addAnimation(animAlpha);
                animationSet.addAnimation(animScale);
                animationSet.addAnimation(animRotate);
                view.startAnimation(animationSet);
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String nickname= edtNickName.getText().toString();
                if(validationCheck(email,password,nickname)){

                    email += emailProvider.get(index);
                    signIn(email,password);
                }else{
                    switch (boolCheck){
                        case "password": {
                            edtPassword.setError(getString(R.string.requirePassword));
                            edtPassword.requestFocus();break;
                        }
                        case "email": {
                            edtEmail.setError(getString(R.string.requireEmail));
                            edtEmail.requestFocus();break;
                        }
                        case "nickname":{
                            edtNickName.setError(getString(R.string.nickname));
                            edtNickName.requestFocus();break;
                        }
                        case "passwordemail": {
                            edtEmail.setError(getString(R.string.requireEmail));
                            edtEmail.requestFocus();
                            edtPassword.setError(getString(R.string.requirePassword));
                            edtPassword.requestFocus();
                        }
                        case "passwordnickname": {
                            edtNickName.setError(getString(R.string.nickname));
                            edtNickName.requestFocus();
                            edtPassword.setError(getString(R.string.requirePassword));
                            edtPassword.requestFocus();break;
                        }
                        case "emailnickname": {
                            edtNickName.setError(getString(R.string.nickname));
                            edtNickName.requestFocus();
                            edtEmail.setError(getString(R.string.requireEmail));
                            edtEmail.requestFocus();break;
                        }
                        case "passwordemailnickname": {
                            edtNickName.setError(getString(R.string.nickname));
                            edtNickName.requestFocus();
                            edtEmail.setError(getString(R.string.requireEmail));
                            edtEmail.requestFocus();
                            edtPassword.setError(getString(R.string.requirePassword));
                            edtPassword.requestFocus();break;
                        }
                        }


                    }
                }

            });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        spnEmail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               index = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
        });
    }
    private boolean validationCheck(String email, String password,String nickname) {
        if(password.trim().length() > 6 && email.trim().length()>0 && nickname.trim().length()>0 ){
            return true;
        }else{
            if(password.trim().length() <= 6)
                boolCheck = "password";
            if(email.trim().length() == 0)
                boolCheck += "email";
            if(nickname.trim().length() ==0)
                boolCheck += "nickname";
            return false;
        }

    }

    private void datePicker() {
        DatePickerDialog.OnDateSetListener listener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DATE,dayOfMonth);
                txtDate.setText(sdf1.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog =new DatePickerDialog(this,listener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        datePickerDialog.show();
    }


    private void writeToFile(String file,String data) {
        try{
            FileOutputStream fileOutputStream = openFileOutput(file,Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();

        }
    }


    private void signIn(String email, String password) {

        final String birthday = txtDate.getText().toString();
        final String phoneNumber = edtPhoneNumber.getText().toString();
        final String sex = radMale.isChecked()?radMale.getText().toString():radFemale.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            writeToFile("nameUser.txt",edtNickName.getText().toString());
                            writeToFile("birthday.txt",birthday);
                            writeToFile("phoneNumber.txt",phoneNumber);
                            writeToFile("sex.txt",sex);
                            upLoadImage();

                        } else {
                            edtEmail.setError(getString(R.string.existEmail));
                            edtEmail.requestFocus();
                        }
                        // ...
                    }
                });


    }

    private void upLoadImage() {


        StorageReference mountainsRef = storageRef.child(edtNickName.getText().toString());
        if(DIVIDE_IMAGE ==0){ // chụp hình
            imgCamera.setDrawingCacheEnabled(true);
            imgCamera.buildDrawingCache();
            Bitmap bitmap = imgCamera.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    Toast.makeText(SignIn.this,R.string.otherPicture , Toast.LENGTH_SHORT).show();
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    writeToFile("imageUser.txt",downloadUrl.toString());

                    Intent intent = new Intent(SignIn.this,MainActivity.class);
                    intent.putExtra("NOT_BACK","NOT_BACK");
                    startActivity(intent);



                }

            });
        }else{ // lấy ảnh từ bộ nhớ lên
            mountainsRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    writeToFile("imageUser.txt",downloadUrl.toString());
                    Intent intent = new Intent(SignIn.this,MainActivity.class);

                    intent.putExtra("NOT_BACK","NOT_BACK");
                    startActivity(intent);



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(SignIn.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addControls() {

        btnSignIn=findViewById(R.id.btnSignIn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtNickName = findViewById(R.id.edtNickName);
        imgCamera = findViewById(R.id.imgCamera);
        txtDate = findViewById(R.id.txtDate);
        btnDate = findViewById(R.id.btnDate);
        calendar=Calendar.getInstance();
        txtDate.setText(sdf1.format(calendar.getTime()));
        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        spnEmail = findViewById(R.id.spnEmail);
        emailProvider = new ArrayList<>();
        emailProvider.add("@gmail.com");
        emailProvider.add("@yahoo.com");
        emailProvider.add("@outlook.com");
        emailProvider.add("@hushmail.com");
        adapterEmail=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,emailProvider);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEmail.setAdapter(adapterEmail);


        registerForContextMenu(imgCamera);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_camera,menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgCamera.setImageBitmap(imageBitmap);
            imgCamera.setRotation(imgCamera.getRotation()-90);
        }

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgCamera.setImageBitmap(bitmap);
                imgCamera.setRotation(imgCamera.getRotation()-90);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuTakePhoto) {
            DIVIDE_IMAGE =0 ;
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }

        }else{
            DIVIDE_IMAGE=1;
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        }
        return super.onContextItemSelected(item);
    }
}
