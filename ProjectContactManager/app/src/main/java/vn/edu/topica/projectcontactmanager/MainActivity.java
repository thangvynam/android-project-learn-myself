package vn.edu.topica.projectcontactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.topica.adapter.ContactAdapter;
import vn.edu.topica.model.Contact;

public class MainActivity extends AppCompatActivity {

    EditText txtTen,txtPhone;
    Button btnLuu;

    ArrayList<Contact> dsDanhBa;
    ContactAdapter adapter;
    ListView lvDanhBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuu();
            }
        });
    }

    private void xuLyLuu() {
        Contact contact=new Contact(txtTen.getText().toString(),txtPhone.getText().toString());
        dsDanhBa.add(contact);
        adapter.notifyDataSetChanged();
        txtTen.setText("");
        txtPhone.setText("");

    }

    private void addControls() {
        txtTen = (EditText) findViewById(R.id.txtTen);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        btnLuu = (Button) findViewById(R.id.btnLuu);

        dsDanhBa = new ArrayList<>();
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        adapter =new ContactAdapter(MainActivity.this,R.layout.item,dsDanhBa);
        lvDanhBa.setAdapter(adapter);


    }
}
