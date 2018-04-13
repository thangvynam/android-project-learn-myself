package vn.edu.topica.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    Button btnTao,btnDong;
    int notificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyTaoNotification();
            }
        });
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDongNotification();
            }
        });
    }

    private void xuLyDongNotification() {
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.cancel(notificationId);
    }

    private void xyLyTaoNotification() {
        NotificationCompat.Builder mbuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(this).
                setSmallIcon(R.drawable.alarm).
                setContentTitle("Thông báo").
                setContentText("Hello World ! ");

        Intent resultIntent = new Intent(this, HelloWorld_Activity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mbuilder.setContentIntent(resultPendingIntent);
        // đây là nhạc mặc định trong hệ thống mobile bạn
        //Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //mbuilder.setSound(uri);

        // đây là nhạc mình thêm vào
        Uri newSound= Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.gaugau);
        mbuilder.setSound(newSound);

        notificationId =1;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(notificationId, mbuilder.build());

    }

    private void addControls() {
        btnTao= (Button) findViewById(R.id.btnTao);
        btnDong= (Button) findViewById(R.id.btnDong);

    }
}
