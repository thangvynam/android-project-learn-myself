package vn.edu.topica.smsthief;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 8/4/2017.
 */


public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        Object []arrPdus= (Object[]) bundle.get("pdus");
        for(int i=0;i<arrPdus.length;i++){
            SmsMessage smsMessage =SmsMessage.createFromPdu((byte[]) arrPdus[i]);
            String noiDung=smsMessage.getMessageBody();
            String phone=smsMessage.getOriginatingAddress();
            long time =smsMessage.getTimestampMillis();
            String timeString=changeTimeToString(time);
            Toast.makeText(context,"\nPhone: "+phone+"\nNội dung : "+noiDung+"\nTime :"+time,Toast.LENGTH_LONG).show();
        }
    }

    private String changeTimeToString(long time) {
        Date date =new Date(time);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("HH:mm:ss:SSS");
        String temp=simpleDateFormat.format(date);
        return temp;
    }
}
