package vn.edu.topica.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.topica.model.Contact;

public class DanhBaActivity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> danhBaAdapter;

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControls();
        addEvents();
        showAllContactFromDevice();
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    private void showAllContactFromDevice() {

        // để truy xuất thông tin phần cứng cụ thế là lấy danh bạ
        Uri uriContact= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        // trả ra con trỏ quản lý bảng contact trong điện thoại chúng ta
        Cursor cursor=getContentResolver().query(uriContact,null,null,null,null);
        dsDanhBa.clear();
        while(cursor.moveToNext()){
            String tenCotName =ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone =ContactsContract.CommonDataKinds.Phone.NUMBER;
            int viTriCotName=cursor.getColumnIndex(tenCotName);
            int viTriCotPhone=cursor.getColumnIndex(tenCotPhone);
            dsDanhBa.add(new Contact(cursor.getString(viTriCotName),cursor.getString(viTriCotPhone)));

        }
        danhBaAdapter.notifyDataSetChanged();

    }

    private void addEvents() {

    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa =new ArrayList<>();
        danhBaAdapter =new ArrayAdapter<Contact>(DanhBaActivity.this,android.R.layout.simple_list_item_1,dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }
}
