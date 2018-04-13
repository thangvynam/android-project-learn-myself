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

import vn.edu.topica.model.TiGia;
import vn.edu.topica.tigiahoidoai.R;

/**
 * Created by DELL on 8/14/2017.
 */

public class TiGiaAdapter extends ArrayAdapter<TiGia> {
    @NonNull Activity context;
    @LayoutRes int resource;
    @NonNull List<TiGia> objects;
    public TiGiaAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<TiGia> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =  this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TiGia tiGia = this.objects.get(position);

        ImageView imgHinh= (ImageView) row.findViewById(R.id.imgHinh);
        TextView txtType= (TextView) row.findViewById(R.id.txtType);
        TextView txtBanCK= (TextView) row.findViewById(R.id.txtBanCK);
        TextView txtMuaCK= (TextView) row.findViewById(R.id.txtMuaCK);
        TextView txtBanTM= (TextView) row.findViewById(R.id.txtBanTM);
        TextView txtMuaTM= (TextView) row.findViewById(R.id.txtMuaTM);

        imgHinh.setImageBitmap(tiGia.getBitmap());
        txtType.setText(tiGia.getType());
        txtBanCK.setText(tiGia.getBanck());
        txtBanTM.setText(tiGia.getBantienmat());
        txtMuaCK.setText(tiGia.getMuack());
        txtMuaTM.setText(tiGia.getMuatienmat());
        
        return row;
    }
}
