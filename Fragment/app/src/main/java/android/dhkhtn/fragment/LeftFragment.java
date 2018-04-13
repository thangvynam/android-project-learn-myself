package android.dhkhtn.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


public class LeftFragment extends android.app.Fragment implements FragmentCallbacks{

    Context context=null;
    MainActivity main;
    private String items[] = { "Lớp 1","Lớp 2","Lớp 3","Lớp 4","Lớp 5","Lớp 6","Lớp 7","Lớp 8","Lớp 9" };
    int mSelectedItem;
    ListView listView;
    public static LeftFragment newInstance(String strArg) {
        LeftFragment fragment = new LeftFragment();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    "MainActivity must implement callbacks");
        }
    }
    public View row;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout v= (LinearLayout) inflater.inflate(R.layout.fragment_left, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                RightFragment rightFragment = new RightFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("right-frag",items[position]);
//                rightFragment.setArguments(bundle);
                String ds ="";
                switch (position){
                    case 0: ds +="0&1.\tThang Vỹ Nam\n2.\tKevin de bruyne\n3.\tSergio Agüero";break;
                    case 1: ds +="1&1.\tTrần Nguyễn Hòang Phúc\n2.\tLeo Messi\n3.\tPhilippe Coutinho";break;
                    case 2: ds +="2&1.\tNguyễn Văn A\n2.\tNguyễn Văn B";break;
                    case 3: ds +="3&1.\tNguyễn Hữu Linh\n2.\tCristian Ronaldo\n3.\tReal Marid";break;
                    case 4: ds +="4&1.\tLê Đình Phú\n2.\tToni Kroos\n3.\tLuka Modric";break;
                    case 5: ds +="5&1.\tB-ray\n2.\tĐạt G\n3.\tMasew";break;
                    case 6: ds +="6&1.\tPHP\n2.\tASP.NET\n3.\tNodeJS";break;
                    case 7: ds +="7&1.\tC++\n2.\tC#\n3.\tJava";break;
                    case 8: ds +="8&1.\tHTML\n2.\tCSS\n3.\tJavaScript";break;

                }
                if(row!=null){
                    row.setBackgroundColor(Color.WHITE);
                }
                row = view;
                view.setBackgroundColor(Color.RED);

                for(int i=0;i<listView.getChildCount();i++) {
                    if (i == position) {
                        listView.getChildAt(position).setBackgroundColor(Color.RED);
                    } else {
                        listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                    }
                }




                main.onMsgFromFragToMain("LEFT-FRAG", ds);
            }
        });
        return v;
    }



    @Override
    public void onMsgFromMainToFragment(final String strValue) {
        int position=Integer.parseInt(strValue);
        if(position ==9){
            position=0;
        }
        if(position==-1){
            position=8;
        }
        for(int i=0;i<listView.getChildCount();i++) {
            if (i == position) {
                listView.getChildAt(position).setBackgroundColor(Color.RED);
            } else {
                listView.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
        }
        String ds ="";
        switch (position){
            case 0: ds +="0&1.\tThang Vỹ Nam\n2.\tKevin de bruyne\n3.\tSergio Agüero";break;
            case 1: ds +="1&1.\tTrần Nguyễn Hòang Phúc\n2.\tLeo Messi\n3.\tPhilippe Coutinho";break;
            case 2: ds +="2&1.\tNguyễn Văn A\n2.\tNguyễn Văn B";break;
            case 3: ds +="3&1.\tNguyễn Hữu Linh\n2.\tCristian Ronaldo\n3.\tReal Marid";break;
            case 4: ds +="4&1.\tLê Đình Phú\n2.\tToni Kroos\n3.\tLuka Modric";break;
            case 5: ds +="5&1.\tB-ray\n2.\tĐạt G\n3.\tMasew";break;
            case 6: ds +="6&1.\tPHP\n2.\tASP.NET\n3.\tNodeJS";break;
            case 7: ds +="7&1.\tC++\n2.\tC#\n3.\tJava";break;
            case 8: ds +="8&1.\tHTML\n2.\tCSS\n3.\tJavaScript";break;

        }
        main.onMsgFromFragToMain("LEFT-FRAG", ds);
    }
}
