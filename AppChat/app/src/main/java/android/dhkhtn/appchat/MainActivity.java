package android.dhkhtn.appchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 1000;
    Button btnSend;
    EditText edtText;
    ListView lstMessage;
    ScrollView scrView;
    MessageAdapter aDapter;
    private DatabaseReference mDatabase;
    ArrayList<Message> messages;

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            //startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),SIGN_IN_REQUEST_CODE);
            Intent intent = new Intent(MainActivity.this, SignIn.class);
            this.startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
        } else {
            displayChatMessages();
        }

        addEvents();
    }
    private String readFile(String file){
        String text ="";
        try{
            FileInputStream fileInputStream = openFileInput(file);
            int size=fileInputStream.available();
            byte []buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            text=new String(buffer);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error reading file !", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
    private void addEvents() {
        final String nameUser =readFile("nameUser.txt");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("data").push().setValue(new Message(edtText.getText().toString(), nameUser));
                // Clear the input
                Toast.makeText(MainActivity.this, nameUser, Toast.LENGTH_SHORT).show();
                edtText.setText("");
            }
        });
    }

    private void addControls() {
        btnSend = findViewById(R.id.btnSend);
        edtText = findViewById(R.id.edtText);
        lstMessage = findViewById(R.id.lstMessage);
        scrView = findViewById(R.id.scrView);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("data");

    }

    private void displayChatMessages() {

        messages = new ArrayList<Message>();
        aDapter = new MessageAdapter(MainActivity.this,R.layout.message,messages);
        lstMessage.setAdapter(aDapter);
        mDatabase.child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message pt= (Message) dataSnapshot.getValue(Message.class);
                messages.add(pt);
                aDapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
