package vn.edu.topica.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.topica.hocgooglephan3.R;
import vn.edu.topica.model.NhaHang;

/**
 * Created by DELL on 8/18/2017.
 */

public class NhaHangAdapter extends ArrayAdapter<NhaHang> {

    @NonNull Activity context;
    @LayoutRes int resource;
    @NonNull List<NhaHang> object;
    public NhaHangAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<NhaHang> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.object=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        ImageView imgHinh= (ImageView) row.findViewById(R.id.imgHinh);
        TextView txtTen= (TextView) row.findViewById(R.id.txtTen);
        NhaHang nhaHang = this.object.get(position);
        txtTen.setText(nhaHang.getTen());
        imgHinh.setImageResource(nhaHang.getHinh());
        return row;
    }
}
