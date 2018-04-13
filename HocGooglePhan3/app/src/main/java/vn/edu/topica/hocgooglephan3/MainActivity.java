package vn.edu.topica.hocgooglephan3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import vn.edu.topica.adapter.NhaHangAdapter;
import vn.edu.topica.model.NhaHang;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhSach;
    ArrayList<NhaHang>dsNhaHang;
    NhaHangAdapter nhaHangAdapter;

    public static String DATABASE_NAME = "dbNhaHang.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //copy();
        addControls();
        addEvents();
        //showAllNhaHang();
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
            byte[] buffer = new byte[1024];
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

   /* private void showAllNhaHang() {
        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor= database.query("NhaHang",null,null,null,null,null,null);
        dsNhaHang.clear();
        while (cursor.moveToNext()){
            NhaHang nhaHang= new NhaHang();
            String tenNhaHang=cursor.getString(1);
            nhaHang.setTen(tenNhaHang);
            byte[] bytes=cursor.getBlob(2);
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            nhaHang.setHinh(bitmap);
            nhaHang.setViDo(cursor.getDouble(3));
            nhaHang.setKinhDo(cursor.getDouble(4));
            dsNhaHang.add(nhaHang);

        }
        cursor.close();
        nhaHangAdapter.notifyDataSetChanged();
    }*/

    private void addEvents() {
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhaHang nhaHang= dsNhaHang.get(position);
                Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("NHAHANG",nhaHang);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvDanhSach= (ListView) findViewById(R.id.lvDanhSach);
        dsNhaHang= new ArrayList<>();
        dsNhaHang.add(new NhaHang("Nhà hàng Langfarm",R.drawable.langfarm,10.755339,106.681911));
        dsNhaHang.add(new NhaHang("Nhà hàng Hương Rừng",R.drawable.huongrung,10.746976,106.679438));
        dsNhaHang.add(new NhaHang("Nhà hàng Ái Huê 2",R.drawable.aihue,10.75264,106.664451));
        nhaHangAdapter=new NhaHangAdapter(MainActivity.this,R.layout.iem,dsNhaHang);
        lvDanhSach.setAdapter(nhaHangAdapter);

    }
}
