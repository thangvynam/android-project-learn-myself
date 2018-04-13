package android.dhkhtn.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Adapter adapter;
    ArrayList<Text> ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        Text text = new Text("nam");
        Text text1 = new Text("phuc");
        Text text2 = new Text("linh");

        ds = new ArrayList();
        ds.add(text);
        ds.add(text1);
        ds.add(text2);

        adapter = new Adapter(MainActivity.this,R.layout.item,ds);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Position : "+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
