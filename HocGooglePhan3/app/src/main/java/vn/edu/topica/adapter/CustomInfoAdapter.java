package vn.edu.topica.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import vn.edu.topica.hocgooglephan3.R;
import vn.edu.topica.model.NhaHang;

/**
 * Created by DELL on 8/18/2017.
 */

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {

    Activity context;
    NhaHang nhaHang;
    public CustomInfoAdapter(){

    }
    public CustomInfoAdapter(Activity context,NhaHang nhaHang){
        this.context=context;
        this.nhaHang=nhaHang;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater =this.context.getLayoutInflater();
        View row= inflater.inflate(R.layout.item2,null);
        ImageView imgHinh2= (ImageView) row.findViewById(R.id.imgHinh2);
        TextView txtTen2= (TextView) row.findViewById(R.id.txtTen2);

        imgHinh2.setImageResource(nhaHang.getHinh());
        txtTen2.setText(nhaHang.getTen());
        return row;
    }
}
