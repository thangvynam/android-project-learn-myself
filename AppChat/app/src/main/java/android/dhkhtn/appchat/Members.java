package android.dhkhtn.appchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Members extends AppCompatActivity {

    RecyclerView recMembers;
    MembersAdapter adapter;
    TextView txtCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        addControls();
    }

    private void addControls() {
        recMembers= findViewById(R.id.recMembers);
        recMembers.setHasFixedSize(true); // tối ưu hóa
        recMembers.setItemViewCacheSize(20);
        recMembers.setDrawingCacheEnabled(true);
        recMembers.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recMembers.setLayoutManager(linearLayout);
        recMembers.setItemAnimator(new DefaultItemAnimator());
        txtCount = findViewById(R.id.txtCount);
        Intent intent = getIntent();
        ArrayList<ItemMembers> data = (ArrayList<ItemMembers>) intent.getSerializableExtra(Global.ARRAY_NAME);
        ArrayList<ItemMembers> realData = new ArrayList<>();
        realData.clear();
        realData.add(data.get(0));
        for(int i=1;i<data.size();i++) {
            boolean check = true;
            for (int j = 0; j < realData.size(); j++) {
                if (data.get(i).getName().equals(realData.get(j).getName()) && data.get(i).getImgUser().equals(realData.get(j).getImgUser())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                realData.add(data.get(i));
            }
        }
        txtCount.setText("("+realData.size()+")");
        adapter = new MembersAdapter(realData, getApplicationContext());
        recMembers.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
