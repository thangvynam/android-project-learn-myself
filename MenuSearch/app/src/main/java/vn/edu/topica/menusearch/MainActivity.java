package vn.edu.topica.menusearch;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView lvTinhThanh;
    ArrayList<String> dsTinhThanh;
    ArrayAdapter<String>adapterTinhThanh;

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
        lvTinhThanh= (ListView) findViewById(R.id.lvTinhThanh);
        dsTinhThanh = new ArrayList<>();
        dsTinhThanh.addAll(Arrays.asList(getResources().getStringArray(R.array.arrTinhThanh)));
        adapterTinhThanh =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsTinhThanh);
        lvTinhThanh.setAdapter(adapterTinhThanh);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menusearch_main,menu);
        MenuItem menuItem =menu.findItem(R.id.mnuSearch);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTinhThanh.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}
