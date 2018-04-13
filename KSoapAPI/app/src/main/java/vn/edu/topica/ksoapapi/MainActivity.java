package vn.edu.topica.ksoapapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import vn.edu.topica.config.Configuration;

public class MainActivity extends AppCompatActivity {

    EditText txtC;
    TextView txtF;
    Button btnF;

    // có cái dialog là tại vì cái thời gian chạy internet là không biết khi nào . Nếu ko có dialog sẽ tưởng máy bị treo
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtC = (EditText) findViewById(R.id.txtC);
        txtF = (TextView) findViewById(R.id.txtF);
        btnF = (Button) findViewById(R.id.btnF);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang load.Vui lòng chờ ....");
        progressDialog.setCanceledOnTouchOutside(false);

    }

    private void addEvents() {
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDoF();
            }
        });
    }

    private void xuLyDoF() {
        String temp = txtC.getText().toString();
        CToFTask cToFTask = new CToFTask();
        cToFTask.execute(temp);
    }

    // AsyncTask<InputData,TimeToProcess,OuputData>
    class CToFTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtF.setText("");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtF.setText(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                String c = params[0];
                //Tạo 1 yêu cầu để gửi lên server:
                SoapObject request = new SoapObject(Configuration.NAME_SPACE, Configuration.METHOD);
                //Nếu yêu cầu này có đối số (parameter):
                request.addProperty(Configuration.PARAMETER, c);
                //tạo Envelope
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                //gán request cho envelope
                envelope.setOutputSoapObject(request);
                //Nếu có truyền dữ liệu kiểu float,double qua lại thì phải đăng ký:
                //MarshalFloat marshalFloat = new MarshalFloat();
                //marshalFloat.register(envelope);
                //tạo loại kết nối lên server
                HttpTransportSE se = new HttpTransportSE(Configuration.SERVER_URL);
                //gọi lệnh thực hiện hàm (hàm này đã thực sự xử lý trên Server rồi):
                se.call(Configuration.SOAP_ACTION, envelope);
                // vì kết quả trả về theo mô tả là kiểu chuỗi (hàm này là lấy kết quả sau khi xử lý):
                // dữ liệu đơn (dùng SoapPrimitive)
                SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
                return result.toString();
            } catch (Exception ex) {
                Log.e("Loi: ",ex.toString());
            }
            return null;

        }
    }
}
