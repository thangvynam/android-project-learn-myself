package android.dhkhtn.appchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;


public class SignIn extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE =1 ;
    private static final int RESULT_LOAD_IMG = 2;
    Button btnSignIn;
    EditText edtEmail,edtPassword,edtNickName;
    private FirebaseAuth mAuth;
    ImageView imgCamera;
    private FirebaseStorage storage;
    private  StorageReference storageRef;
    private static boolean checkImage =false;
    private Uri filePath;
    private int DIVIDE_IMAGE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
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
                signIn();
            }
    });


    }


    private void writeToFile(String file,String data) {
        try{
            FileOutputStream fileOutputStream = openFileOutput(file,Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error saving file !", Toast.LENGTH_SHORT).show();
        }
    }


    private void signIn() {
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            writeToFile("nameUser.txt",edtNickName.getText().toString());
                            upLoadImage();



                        } else {
                            Toast.makeText(SignIn.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }

    private void upLoadImage() {
        Calendar calendar = Calendar.getInstance();
        StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");
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

                    Toast.makeText(SignIn.this, "Thất bại trong load hình", Toast.LENGTH_SHORT).show();
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    checkImage =true;
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(SignIn.this, "Thành công trong load hình", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this,MainActivity.class);
                    startActivity(intent);

                }

            });
        }else{ // lấy ảnh từ bộ nhớ lên
            mountainsRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    checkImage =true;
                    Toast.makeText(SignIn.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this,MainActivity.class);
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
        }

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgCamera.setImageBitmap(bitmap);
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
