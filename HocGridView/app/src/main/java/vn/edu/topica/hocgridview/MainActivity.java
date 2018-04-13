package vn.edu.topica.hocgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import vn.edu.topica.adapter.HinhAdapter;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer>dsHinh;
    ArrayAdapter<Integer>adapter;
    GridView gvHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        gvHinh= (GridView) findViewById(R.id.gvHinh);
        dsHinh=new ArrayList<>();
        dsHinh.add(R.drawable.h1);
        dsHinh.add(R.drawable.h2);
        dsHinh.add(R.drawable.h3);
        adapter =new HinhAdapter(MainActivity.this,R.layout.item,dsHinh);
        gvHinh.setAdapter(adapter);
    }
}
