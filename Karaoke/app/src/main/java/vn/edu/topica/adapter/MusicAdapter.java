package vn.edu.topica.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.topica.karaoke.MainActivity;
import vn.edu.topica.karaoke.R;
import vn.edu.topica.model.Music;

/**
 * Created by DELL on 7/14/2017.
 */

public class MusicAdapter extends ArrayAdapter<Music> {
    @NonNull Activity context;
    @LayoutRes int resource;
    @NonNull List<Music> objects;

    TextView txtMa,txtBaiHat,txtCaSi;
    ImageButton btnLike,btnDislike;

    public MusicAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<Music> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        txtBaiHat= (TextView) row.findViewById(R.id.txtBaiHat);
        txtCaSi= (TextView) row.findViewById(R.id.txtCaSi);
        txtMa= (TextView) row.findViewById(R.id.txtMa);
        btnDislike = (ImageButton) row.findViewById(R.id.btnDislike);
        btnLike = (ImageButton) row.findViewById(R.id.btnLike);

        final Music music =this.objects.get(position);
        txtMa.setText(music.getMa());
        txtCaSi.setText(music.getCaSi());
        txtBaiHat.setText(music.getTen());

        if (music.isLike())
        {
            btnLike.setVisibility(View.INVISIBLE);
            btnDislike.setVisibility(View.VISIBLE);
        }
        else
        {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.INVISIBLE);
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThich(music);
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyKhongThich(music);
            }
        });
        return row;
    }

    private void xuLyKhongThich(Music music) {
        ContentValues contentValues =new ContentValues();
        contentValues.put("YEUTHICH",0);
        MainActivity.database.update("ArirangSongList",contentValues,"MABH=?",new String[]{music.getMa()});

    }

    private void xuLyThich(Music music) {

        ContentValues contentValues =new ContentValues();
        contentValues.put("YEUTHICH",1);
        MainActivity.database.update("ArirangSongList",contentValues,"MABH=?",new String[]{music.getMa()});
    }
}
