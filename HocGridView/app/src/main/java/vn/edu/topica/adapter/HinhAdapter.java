package vn.edu.topica.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.topica.hocgridview.R;

/**
 * Created by DELL on 7/9/2017.
 */

public class HinhAdapter extends ArrayAdapter<Integer> {
    @NonNull
    Activity context;
    @LayoutRes int resource;
    @NonNull List<Integer> objects;
    ArrayList<String>dsSoLuong;
    ArrayAdapter<String>adapter;
    private int hoaDo=0;
    private int hoaVang=0;
    private int hoaXanh=0;
    public HinhAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        ImageView imgHinh= (ImageView) row.findViewById(R.id.imgHinh);
        imgHinh.setImageResource(this.objects.get(position));
        Spinner spSoLuong= (Spinner) row.findViewById(R.id.spSoLuong);
        Button btnMua= (Button) row.findViewById(R.id.btnMua);
        dsSoLuong =new ArrayList<>();
        dsSoLuong.add("0");
        dsSoLuong.add("1");
        dsSoLuong.add("2");
        dsSoLuong.add("3");
        dsSoLuong.add("4");
        dsSoLuong.add("5");
        dsSoLuong.add("6");
        dsSoLuong.add("7");
        dsSoLuong.add("8");
        dsSoLuong.add("9");
        dsSoLuong.add("10");
        adapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_spinner_item,dsSoLuong);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSoLuong.setAdapter(adapter);
        final int temp=this.objects.get(position);
        spSoLuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(temp==R.drawable.h1){
                    hoaDo= Integer.parseInt(dsSoLuong.get(position));
                }
                else if(temp==R.drawable.h2)
                {
                    hoaVang= Integer.parseInt(dsSoLuong.get(position));
                }
                else
                    hoaXanh= Integer.parseInt(dsSoLuong.get(position));;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
        return row;
    }

    private void xuLy() {
        String s="Bạn chọn mua "+hoaDo+" hoa đỏ\n"
                +"Bạn chọn mua "+hoaVang+" hoa vàng\n"
                +"Bạn chọn mua "+hoaXanh+" hoa xanh";
        Toast.makeText(this.context,s,Toast.LENGTH_LONG).show();
    }
}
