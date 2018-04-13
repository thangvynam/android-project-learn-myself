import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import vn.edu.topica.broadcastreceiver_manifest.MainActivity;
import vn.edu.topica.broadcastreceiver_manifest.R;

/**
 * Created by DELL on 8/2/2017.
 */

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()!=null){
            Toast.makeText(context, "Bạn vừa bật wifi", Toast.LENGTH_SHORT).show();
        }
    }

    private void xuLyNotification(Context context) {
        NotificationCompat.Builder mbuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(context).
                setSmallIcon(R.drawable.alarm).
                setContentTitle("Thông báo").
                setContentText("Hello World ! ");

        Intent resultIntent = new Intent(context, MainActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mbuilder.setContentIntent(resultPendingIntent);
        // đây là nhạc mặc định trong hệ thống mobile bạn
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mbuilder.setSound(uri);

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(114, mbuilder.build());
    }
}
