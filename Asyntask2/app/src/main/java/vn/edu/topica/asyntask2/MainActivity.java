package vn.edu.topica.asyntask2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnTaiHinh;
    ImageView imgHinh;
    ProgressDialog dialog;

    String link1="http://kenh14cdn.com/2017/photo-1-1493355845828.jpg";
    String link2="http://media.doisongphapluat.com/414/2015/5/22/Son-tung%20(1).jpg";
    String link3="http://img.saobiz.net/d/2015/07/tieu-su-cua-son-tung-m-tp-1.jpg";
    String link4="http://avatar.nct.nixcdn.com/playlist/2015/12/15/f/d/c/6/1450155936245_500.jpg";
    Random random = new Random();
    ArrayList<String> dsHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTaiHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTaiHinh();
            }
        });
    }

    private void xuLyTaiHinh() {
        int n= random.nextInt(4);
        ImageTask imageTask= new ImageTask();
        imageTask.execute(dsHinh.get(n));
    }
    class ImageTask extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try
            {
                String link=params[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(link).getContent());
                return bitmap;
            }
            catch(Exception ex)
            {
                Log.e("Loi ",ex.toString());
            }
            return null;
        }
    }


    private void addControls() {
        btnTaiHinh = (Button) findViewById(R.id.btnTaiHinh);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Thông báo ");
        dialog.setMessage("Loading .....");
        dialog.setCanceledOnTouchOutside(false);
        dsHinh= new ArrayList<>();
        dsHinh.add(link1);
        dsHinh.add(link2);
        dsHinh.add(link3);
        dsHinh.add(link4);

    }
}
