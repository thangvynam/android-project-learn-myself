package vn.edu.topica.sleepnow;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {

    Spinner spLanguage;
    ArrayList<String>dsLanguage,dsGhiAm;
    ArrayAdapter<String>adapter;

    TextView txtLanguage;
    ImageView imgHinh;
    Button btnStart,btnStop;
    AnimationDrawable animationDrawable;

    MediaPlayer m = new MediaPlayer();
    int notificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try {
            addControls();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addEvents();
    }

    private void addEvents() {
        spLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    xuLyLanguage(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
                createNotification();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });
    }

    private void createNotification() {
        NotificationCompat.Builder mbuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(this).
                setSmallIcon(R.drawable.alarm).
                setContentTitle("Sleep now !").
                setContentText("It's time to sleep . Hope you have a nice dream !  ");

        Intent resultIntent = new Intent(this, Main3Activity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mbuilder.setContentIntent(resultPendingIntent);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mbuilder.setSound(uri);
        notificationId =1;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(notificationId, mbuilder.build());
    }

    private void xuLyBaiHat(int position) throws IOException {
        if(m.isPlaying()){
            m.stop();
            m.release();
            m = new MediaPlayer();
        }
        AssetFileDescriptor descriptor =getAssets().openFd("music/"+dsGhiAm.get(position));
        try {
            m.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        descriptor.close();
        m.prepare();
        m.setLooping(true);
        m.setVolume(1f,1f);
        m.start();
    }
    private void xuLyLanguage(int position) throws IOException {
        switch (position){
            case 0: txtLanguage.setText("Ngủ ngon nè");
                xuLyBaiHat(0);
                break;
            case 1: txtLanguage.setText("Good night baby");
                xuLyBaiHat(1);
                break;
            case 2: txtLanguage.setText("Oyasumi nasai");
                xuLyBaiHat(5);
                break;
            case 3: txtLanguage.setText(" Buenas noches");
                xuLyBaiHat(6);
                break;
            case 4: txtLanguage.setText("buonanotte");
                xuLyBaiHat(4);
                break;
            case 5: txtLanguage.setText("Bonne nuit");
                xuLyBaiHat(2);
                break;
            case 6: txtLanguage.setText("Gute Nacht");
                xuLyBaiHat(3);
                break;
        }
    }

    private void addControls() throws IOException {
        spLanguage= (Spinner) findViewById(R.id.spLanguage);
        dsLanguage = new ArrayList<>();
        dsLanguage.addAll(Arrays.asList(getResources().getStringArray(R.array.arrLanguage)));
        adapter = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_spinner_item,dsLanguage);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLanguage.setAdapter(adapter);

        txtLanguage= (TextView) findViewById(R.id.txtLanguage);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
        imgHinh.setBackgroundResource(R.drawable.hieuung);
        animationDrawable = (AnimationDrawable) imgHinh.getBackground();
        btnStart= (Button) findViewById(R.id.btnStart);
        btnStop= (Button) findViewById(R.id.btnStop);

        dsGhiAm = new ArrayList<>();
        AssetManager assetManager2 =getAssets();
        String []arr2=assetManager2.list("music");
        dsGhiAm.addAll(Arrays.asList(arr2));
        dsGhiAm.addAll(Arrays.asList(arr2));
    }
}
