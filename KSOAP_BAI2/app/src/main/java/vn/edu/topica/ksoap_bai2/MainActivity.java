package vn.edu.topica.ksoap_bai2;

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
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import vn.edu.topica.config.Configuration;
import vn.edu.topica.model.Contact;

public class MainActivity extends AppCompatActivity {

    EditText txtMa;
    Button btnChon;
    TextView txtKetQua;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy();
            }
        });
    }

    private void xuLy() {
        int ma=Integer.parseInt(txtMa.getText().toString());
        ContactTask task = new ContactTask();
        task.execute(ma);
    }
    class ContactTask extends AsyncTask<Integer,Void,Contact>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtKetQua.setText("");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Contact contact) {
            super.onPostExecute(contact);
            txtKetQua.setText(contact.toString());
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Contact doInBackground(Integer... params) {
            try {
                int ma = params[0];
                SoapObject request = new SoapObject(Configuration.NAME_SPACE, Configuration.METHOD);
                request.addProperty(Configuration.PARAMETER, ma);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE se = new HttpTransportSE(Configuration.SERVER_URL);
                se.call(Configuration.SOAP_ACTION, envelope);
                Contact contact = new Contact();
                SoapObject object = (SoapObject) envelope.getResponse();
                if(object.hasProperty("Ma"))
                    contact.setMa(Integer.parseInt(object.getPropertyAsString("Ma")));
                if (object.hasProperty("Ten"))
                    contact.setName(object.getPropertyAsString("Ten"));
                if (object.hasProperty("Phone"))
                    contact.setPhone(object.getPropertyAsString("Phone"));
                return contact;
            }
            catch (Exception ex){
                Log.e("LOI: ",ex.toString());
            }
            return null;
        }
    }
    private void addControls() {
        txtMa= (EditText) findViewById(R.id.txtMa);
        btnChon= (Button) findViewById(R.id.btnChon);
        txtKetQua= (TextView) findViewById(R.id.txtKetQua);
        progressDialog =new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Loading ....");
        progressDialog.setCanceledOnTouchOutside(false);
    }
}
