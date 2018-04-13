package vn.edu.topica.hocassetsvasharepreference;

import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView txtFont;

    ArrayList<String>dsFont;
    ArrayAdapter<String>fontAdapter;
    ListView lvFont;

    ArrayList<String>dsBaiHat;
    ArrayAdapter<String>baiHatAdapter;
    ListView lvBaiHat;

    String tenLuuTru="TrangThaiFont";
    MediaPlayer m = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            addControls();
        } catch (IOException e) {
            Log.e("LOI_FONT",e.toString());
        }
        addEvents();
    }

    private void addEvents() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeFont(position);
            }
        });
        lvBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    xuLyBaiHat(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void xuLyBaiHat(int position) throws IOException {
        if(m.isPlaying()){
        m.stop();
        m.release();
        m = new MediaPlayer();
    }
    AssetFileDescriptor descriptor =getAssets().openFd("music/"+dsBaiHat.get(position));
        m.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
        descriptor.close();

        m.prepare();
        m.setVolume(1f,1f);
        m.setLooping(true);
        m.start();
}

    private void changeFont(int position) {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font/"+dsFont.get(position));
        txtFont.setTypeface(typeface);

        SharedPreferences preferences=getSharedPreferences(tenLuuTru,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("FONT_CHU","font/"+dsFont.get(position));
        // lệnh này bắt buộc phải có vì 3 dòng trên là đánh dấu lưu thôi , dòng này là xác nhận lưu
        editor.commit();
    }

    private void addControls() throws IOException {
        txtFont= (TextView) findViewById(R.id.txtFont);
        lvFont= (ListView) findViewById(R.id.lvFont);
        dsFont =new ArrayList<>();
        fontAdapter =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsFont);
        lvFont.setAdapter(fontAdapter);

        AssetManager assetManager =getAssets();
        String []arr=assetManager.list("font");
        dsFont.addAll(Arrays.asList(arr));
        fontAdapter.notifyDataSetChanged();

        lvBaiHat= (ListView) findViewById(R.id.lvBaiHat);
        dsBaiHat =new ArrayList<>();
        baiHatAdapter =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsBaiHat);
        lvBaiHat.setAdapter(baiHatAdapter);

        AssetManager assetManager2 =getAssets();
        String []arr2=assetManager2.list("music");
        dsBaiHat.addAll(Arrays.asList(arr2));
        baiHatAdapter.notifyDataSetChanged();

        SharedPreferences preferences=getSharedPreferences(tenLuuTru,MODE_PRIVATE);
        String temp=preferences.getString("FONT_CHU","");
        if(temp.length()>0)
        {
            Typeface typeface=Typeface.createFromAsset(getAssets(),temp);
            txtFont.setTypeface(typeface);
        }


    }
}
