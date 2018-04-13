package vn.edu.topica.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TinNhanActivity extends AppCompatActivity {

    ListView lvTinNhan;
    ArrayList<String>dsTinNhan;
    ArrayAdapter<String>tinNhanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_nhan);
        addControls();
        showAllSms();
    }

    private void showAllSms() {
        Uri uri =Uri.parse("content://sms/inbox");
        Cursor cursor =getContentResolver().query(uri,null,null,null,null);
        dsTinNhan.clear();
        while (cursor.moveToNext()){
            int indexPhoneNumber =cursor.getColumnIndex("address");
            int indexTimeStamp =cursor.getColumnIndex("date");
            int indexBody =cursor.getColumnIndex("body");

            String phone =cursor.getString(indexPhoneNumber);
            String date =cursor.getString(indexTimeStamp);
            String body =cursor.getString(indexBody);

            dsTinNhan.add(phone+"\t"+date+"\n"+body);
        }
        cursor.close();
        tinNhanAdapter.notifyDataSetChanged();
    }

    private void addControls() {
        lvTinNhan= (ListView) findViewById(R.id.lvTinNhan);
        dsTinNhan=new ArrayList<>();
        tinNhanAdapter=new ArrayAdapter<String>(TinNhanActivity.this,android.R.layout.simple_list_item_1,dsTinNhan);
        lvTinNhan.setAdapter(tinNhanAdapter);
    }
}
