package vn.edu.topica.hocgooglemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    Spinner spType;
    ArrayList<String>dsType;
    ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                xuLy(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void xuLy(int position) {
        switch (position){
            case 0: mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);break;
            case 1: mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);break;
            case 2: mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);break;
            case 3: mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);break;
            case 4: mMap.setMapType(GoogleMap.MAP_TYPE_NONE);break;
        }
    }
    private void addControls() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

            }
        });

        spType = (Spinner) findViewById(R.id.spType);
        dsType = new ArrayList<>();
        dsType.addAll(Arrays.asList(getResources().getStringArray(R.array.arrtype)));
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,dsType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adapter);

    }

}
