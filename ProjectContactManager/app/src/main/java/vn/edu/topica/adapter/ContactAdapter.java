package vn.edu.topica.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import vn.edu.topica.model.Contact;
import vn.edu.topica.projectcontactmanager.MainActivity;
import vn.edu.topica.projectcontactmanager.R;
import vn.edu.topica.projectcontactmanager.SmsManHinhActivity;

/**
 * Created by DELL on 7/21/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    @NonNull
    MainActivity context;
    @LayoutRes
    int resource;
    @NonNull
    List<Contact> objects;
    TextView txtPhone, txtTen;
    ImageButton btnCall, btnSms, btnDelete;

    public ContactAdapter(@NonNull MainActivity context, @LayoutRes int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        txtTen = (TextView) row.findViewById(R.id.txtTen);
        txtPhone = (TextView) row.findViewById(R.id.txtPhone);
        btnCall = (ImageButton) row.findViewById(R.id.btnCall);
        btnSms = (ImageButton) row.findViewById(R.id.btnSms);
        btnDelete = (ImageButton) row.findViewById(R.id.btnDelete);

        final Contact contact=this.objects.get(position);
        txtTen.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGoi();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoa(contact);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLySms(contact);
            }
        });
        return row;
    }

    private void xuLySms(Contact contact) {
        Intent intent=new Intent(this.context, SmsManHinhActivity.class);
        intent.putExtra("CONTACT",contact);
        this.context.startActivity(intent);

    }

    private void xuLyXoa(Contact contact) {
        this.remove(contact);
    }

    private void xuLyGoi() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + txtPhone.getText());
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.context.startActivity(intent);
    }
}
