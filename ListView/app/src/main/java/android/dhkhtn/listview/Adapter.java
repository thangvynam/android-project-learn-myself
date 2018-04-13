package android.dhkhtn.listview;

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

/**
 * Created by DELL on 3/20/2018.
 */


public class Adapter extends ArrayAdapter<Text> {
    private Activity context;
    @LayoutRes int resource;
    @NonNull List objects;
    ImageView img,img2;
    TextView txtName;
    public Adapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {

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
        img = (ImageView) row.findViewById(R.id.imgHinh);
        img2 = (ImageView) row.findViewById(R.id.imgHinh2);
        txtName= (TextView) row.findViewById(R.id.txtName);

        final Text text = (Text) this.objects.get(position);
        txtName.setText(text.getName());
        return row;



    }
}
