package vn.edu.topica.hoclistviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.topica.adapter.DanhBaAdapter;
import vn.edu.topica.model.DanhBa;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<DanhBa>dsDanhBa;
    DanhBaAdapter danhBaAdapter;
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
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        dsDanhBa.add(new DanhBa(1,"Thang Vỹ Nam","0906368182"));
        dsDanhBa.add(new DanhBa(1,"Trương Phạm Bích Thuỳ","0903178397"));
        dsDanhBa.add(new DanhBa(1,"Thang Chí Hùng","0908132862"));
        danhBaAdapter= new DanhBaAdapter(MainActivity.this,R.layout.item,dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }
}
