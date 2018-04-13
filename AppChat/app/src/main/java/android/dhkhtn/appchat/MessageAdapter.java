package android.dhkhtn.appchat;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 4/10/2018.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    @NonNull
    Activity context;
    @LayoutRes int resource;
    @NonNull List<Message> objects;
    TextView txtNameUser,txtTime,txtMessage;
    public MessageAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Message> objects) {
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
        txtNameUser=row.findViewById(R.id.txtNameUser);
        txtTime=row.findViewById(R.id.txtTime);
        txtMessage=row.findViewById(R.id.txtMessage);

        final Message message =this.objects.get(position);
        txtMessage.setText(message.getMessageText());
        txtNameUser.setText(message.getMessageUser());
        txtTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",message.getMessageTime()));
        return row;
    }
}
