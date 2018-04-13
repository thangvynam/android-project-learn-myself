package vn.edu.topica.karaoke;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import vn.edu.topica.adapter.MusicAdapter;
import vn.edu.topica.model.Music;


public class MainActivity extends AppCompatActivity {

    ArrayList<Music> dsBaiHatGoc,dsBaiHatYeuThich;
    MusicAdapter adapterBaiHatGoc,adapterBaiHatYeuThich;
    ListView lvBaiHatGoc,lvBaiHatYeuThich;
    TabHost tabHost;

    public static String DATABASE_NAME = "Arirang.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copy();
        addControls();
        addEvents();
        xuLyBaiHatGoc();

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

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t1"))
                    xuLyBaiHatGoc();
                else
                    xuLyBaiHatYeuThich();
            }
        });
    }

    private void xuLyBaiHatGoc() {
        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor= database.query("ArirangSongList",null,null,null,null,null,null);
        dsBaiHatGoc.clear();
        while (cursor.moveToNext()){
            String maBH= cursor.getString(0);
            String tenBH=cursor.getString(1);
            String tenCaSi=cursor.getString(3);
            int yeuThich= cursor.getInt(5);
            Music music =new Music(maBH,tenBH,tenCaSi,yeuThich==1);
            dsBaiHatGoc.add(music);
        }
        cursor.close();
        adapterBaiHatGoc.notifyDataSetChanged();
    }

    private void xuLyBaiHatYeuThich() {
        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor= database.query("ArirangSongList",null,"YEUTHICH=?",new String[]{"1"},null,null,null);
        dsBaiHatYeuThich.clear();
        while (cursor.moveToNext()){
            String maBH= cursor.getString(0);
            String tenBH=cursor.getString(1);
            String tenCaSi=cursor.getString(3);
            int yeuThich= cursor.getInt(5);
            Music music =new Music(maBH,tenBH,tenCaSi,yeuThich==1);
            dsBaiHatYeuThich.add(music);
        }
        cursor.close();
        adapterBaiHatYeuThich.notifyDataSetChanged();
    }


    private void addControls() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost .setup();
        TabHost.TabSpec tab1 =tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("1.Danh sách bài hát");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 =tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("2.Danh sách bài hát yêu thích ");
        tabHost.addTab(tab2);

        lvBaiHatGoc = (ListView) findViewById(R.id.lvBaiHatGoc);
        dsBaiHatGoc =new ArrayList<>();
        adapterBaiHatGoc = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatGoc);
        lvBaiHatGoc.setAdapter(adapterBaiHatGoc);

        lvBaiHatYeuThich = (ListView) findViewById(R.id.lvBaiHatYeuThich);
        dsBaiHatYeuThich =new ArrayList<>();
        adapterBaiHatYeuThich = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatYeuThich);
        lvBaiHatYeuThich.setAdapter(adapterBaiHatYeuThich);

    }
}
