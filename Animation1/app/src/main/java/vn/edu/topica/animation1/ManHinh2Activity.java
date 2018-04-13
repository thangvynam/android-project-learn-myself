package vn.edu.topica.animation1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.topica.adapter.MyAdapter;

public class ManHinh2Activity extends AppCompatActivity {

    ListView lvData;
    ArrayList<String>dsData;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        addControls();
    }

    private void addControls() {
        lvData= (ListView) findViewById(R.id.lvData);
        dsData =new ArrayList<>();
        adapter =new MyAdapter(ManHinh2Activity.this,R.layout.item,dsData);
        lvData.setAdapter(adapter);
        for(int i=0;i<5000;i++){
            dsData.add("Tên thứ "+(i+1));
        }
        adapter.notifyDataSetChanged();

    }
}
