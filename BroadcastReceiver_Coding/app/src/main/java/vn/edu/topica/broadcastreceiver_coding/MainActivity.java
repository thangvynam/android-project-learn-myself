package vn.edu.topica.broadcastreceiver_coding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEnter;
    BroadcastReceiver receiverWifi=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo()!=null){
                   btnEnter.setEnabled(true);
                Toast.makeText(MainActivity.this,"Bạn vừa bật wifi",Toast.LENGTH_LONG).show();
            }
            else
            {
                btnEnter.setEnabled(false);
                Toast.makeText(MainActivity.this,"Bạn vừa tắt wifi",Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new 	IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiverWifi, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiverWifi!=null)
            unregisterReceiver(receiverWifi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        btnEnter= (Button) findViewById(R.id.btnEnter);
    }
}
