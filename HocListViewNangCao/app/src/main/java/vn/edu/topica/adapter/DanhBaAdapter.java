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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.topica.hoclistviewnangcao.R;
import vn.edu.topica.model.DanhBa;

/**
 * Created by DELL on 7/4/2017.
 */

public class DanhBaAdapter extends ArrayAdapter<DanhBa>{
    // đối số 1 : màn hình sủ dụng layout này( giao diện này)
    @NonNull Activity context;
    // đối số 2 : cho từng dòng muốn hiện thị ( làm theo khách hàng)
    @LayoutRes int resource;
    // danh sách nguồn dữ liệu muốn hiện thị lên giao diện
    @NonNull List<DanhBa> objects;
    public DanhBaAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<DanhBa> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);

        TextView txtName= (TextView) row.findViewById(R.id.txtName);
        TextView txtPhone= (TextView) row.findViewById(R.id.txtPhone);
        ImageButton btnCall = (ImageButton) row.findViewById(R.id.btnCall);
        ImageButton btnSms = (ImageButton) row.findViewById(R.id.btnSms);
        ImageButton btnDetail = (ImageButton) row.findViewById(R.id.btnDetail);
        final DanhBa danhBa= this.objects.get(position);
        txtName.setText(danhBa.getTen());
        txtPhone.setText(danhBa.getPhone());
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChiTiet(danhBa);
            }
        });
        return row;
    }

    private void xuLyChiTiet(DanhBa danhBa) {
        Toast.makeText(this.context,"Bạn chọn "+danhBa.getTen(),Toast.LENGTH_LONG).show();
    }
}

