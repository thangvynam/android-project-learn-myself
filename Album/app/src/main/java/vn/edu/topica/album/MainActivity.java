package vn.edu.topica.album;

import android.app.ProgressDialog;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    CheckBox chkTuDong;
    ImageButton btnPrevious,btnNext;
    ProgressDialog progressDialog;

    Timer timer=null;
    TimerTask timerTask=null;

    int currentPosition;
    ArrayList<String> albums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyNext();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPrevious();
            }
        });
        chkTuDong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    btnPrevious.setEnabled(false);
                    btnNext.setEnabled(false);
                    xuLyAuto();

                }
                else{
                    btnPrevious.setEnabled(true);
                    btnNext.setEnabled(true);
                    if(timer!=null)
                     timer.cancel();
                }
            }
        });
    }

    private void xuLyBaiHat() throws IOException {
        MediaPlayer m = new MediaPlayer();
        if(m.isPlaying()){
            m.stop();
            m.release();
            m = new MediaPlayer();
        }
        AssetFileDescriptor descriptor =getAssets().openFd("music/Noi-Nay-Co-Anh-Son-Tung-M-TP");
        m.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
        descriptor.close();
        m.prepare();
        m.setVolume(1f,1f);
        m.setLooping(true);
        m.start();
    }

    private void xuLyAuto() {
        try {
            xuLyBaiHat();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timerTask= new TimerTask() {
            @Override
            public void run() {
                // cái này để cập nhật giao diện trong cái tiến trình ( bắt buộc nếu muốn tương tác với giao diện )
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        currentPosition++;
                        if(currentPosition==albums.size())
                            currentPosition=0;
                        ImageTask task= new ImageTask();
                        task.execute(albums.get(currentPosition));
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,0,5000);


    }

    private void xuLyPrevious() {
        currentPosition--;
        if(currentPosition==-1)
            currentPosition=albums.size()-1;
        ImageTask task= new ImageTask();
        task.execute(albums.get(currentPosition));
    }

    private void xuLyNext() {
        currentPosition++;
        if(currentPosition==albums.size())
            currentPosition=0;
        ImageTask task= new ImageTask();
        task.execute(albums.get(currentPosition));

    }
    class ImageTask extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            progressDialog.dismiss();

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
                Log.e("Loi",ex.toString());
            }
            return null;
        }
    }

    private void addControls() {
        imgHinh = (ImageView) findViewById(R.id.imgHInh);
        chkTuDong= (CheckBox) findViewById(R.id.chkTuDong);
        btnPrevious= (ImageButton) findViewById(R.id.btnPrevious);
        btnNext= (ImageButton) findViewById(R.id.btnNext);
        albums = new ArrayList<>();
        albums.add("http://avatar.nct.nixcdn.com/playlist/2017/03/03/6/9/3/e/1488511385458_500.jpg");
        albums.add("http://avatar.nct.nixcdn.com/playlist/2017/02/24/3/e/a/e/1487928635962_500.jpg");
        albums.add("http://pm1.narvii.com/6273/1a4c08a1a2bfb067dac7701d3ebb493642f287b5_hq.jpg");
        albums.add("http://www.mtrend.vn/wp-content/uploads/2016/07/image001-18.jpg");
        albums.add("http://images.sunflower.vn/wp-content/uploads/2017/02/Son-Tung-MTP-hinh-anh-3.jpg");
        albums.add("http://www.elle.vn/wp-content/uploads/2017/01/28/son-tung-mtp-4.jpg");
        albums.add("http://v2.cdn.ringring.vn/JgXXlF23iLlZaP3hSsEY/640x10000x3/image/0/0/759/777978.jpg'");
        albums.add("http://2sao.vietnamnetjsc.vn/images/2017/06/02/14/48/son-tung-mtp-5.jpg");
        albums.add("http://znews-photo-td.zadn.vn/w660/Uploaded/qfssu/2017_01_17/11463760748048.jpg");
        albums.add("http://img.saobiz.net/d/2015/07/tieu-su-cua-son-tung-m-tp.jpg");
        currentPosition=0;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Loading....");
        progressDialog.setCanceledOnTouchOutside(false);
        ImageTask task= new ImageTask();
        task.execute(albums.get(currentPosition));

    }
}
