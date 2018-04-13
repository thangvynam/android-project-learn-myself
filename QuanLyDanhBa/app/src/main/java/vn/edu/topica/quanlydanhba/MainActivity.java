package vn.edu.topica.quanlydanhba;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DATABASE_NAME = "dbContact.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;

    ListView lvDanhBa;
    ArrayList<String> dsDanhBa;
    ArrayAdapter<String> danhBaAdapter;

    Button btnXoa,btnUpdate,btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // sao chép cơ sở dữ liệu vào hệ thống mobile
        copy();
        addControls();
        addEvents();
        showAllContactOnListView();
    }

    private void showAllContactOnListView() {
        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor= database.query("Contact",null,null,null,null,null,null);
        //Cursor cursor1=database.rawQuery("SELECT * from Contact",null);
        dsDanhBa.clear();
        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String name=cursor.getString(1);
            String phone =cursor.getString(2);
            dsDanhBa.add(id+" - "+name+"\n"+phone);
        }
        cursor.close();
        danhBaAdapter.notifyDataSetChanged();

    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThem();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyUpdate();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoa();
            }
        });
    }

    private void xuLyXoa() {
        database.delete("Contact","Ma=?",new String[]{"2"});
        showAllContactOnListView();
    }

    private void xuLyUpdate() {
        ContentValues contentValues =new ContentValues();
        contentValues.put("Ten","B.I");
        contentValues.put("Phone","123");
        database.update("Contact",contentValues,"Ma = ? OR Phone = ?",new String[]{"1","Unknown"});
        showAllContactOnListView();
    }

    private void xuLyThem() {

        ContentValues contentValues =new ContentValues();
        contentValues.put("Ma",4);
        contentValues.put("Ten","Sơn Tùng MTP");
        contentValues.put("Phone","Unknown");
        long res= database.insert("Contact",null,contentValues);
        Toast.makeText(MainActivity.this,"Đã thêm thành công với r="+res,Toast.LENGTH_LONG).show();
        showAllContactOnListView();

    }

    private void addControls() {
        btnThem = (Button) findViewById(R.id.btnThem);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa =new ArrayList<>();
        danhBaAdapter =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);

    }

    private void copy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()){
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Sao chép cơ sở dữ liệu vào hệ thống thành công !", Toast.LENGTH_LONG).show();
            }catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }

    private void CopyDataBaseFromAsset() {
        try
        {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
            // Path to the just created empty db
            String outFileName = getDatabasePath();
            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            // Open the empty db as the output stream
               OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[512];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch(Exception e)
        {
            Log.e("Loi_SaoChep",e.toString());
        }
    }
    //hàm này trả về đường dẫn phải lưu trữ
    private String getDatabasePath(){
       return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }

}

