package vn.edu.topica.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.topica.animation1.R;

/**
 * Created by DELL on 7/30/2017.
 */

public class MyAdapter extends ArrayAdapter<String> {
    @NonNull Activity context;
    @LayoutRes int resource;
    @NonNull List<String> objects;
    TextView txtTen;
    public MyAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        txtTen= (TextView) row.findViewById(R.id.txtTen);
        txtTen.setText(this.objects.get(position));

        Animation animation= AnimationUtils.loadAnimation(this.context,R.anim.hieuunglistview);
        row.startAnimation(animation);

        return row;


    }
}
