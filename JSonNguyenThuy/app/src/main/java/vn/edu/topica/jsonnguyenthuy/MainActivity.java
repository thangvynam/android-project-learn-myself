package vn.edu.topica.jsonnguyenthuy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vn.edu.topica.model.Contact;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayList<Contact>dsContact;
    ArrayAdapter<Contact>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        ContactTask contactTask = new ContactTask();
        contactTask.execute();
    }

    private void addEvents() {


    }
    class ContactTask extends AsyncTask<Void,Void,ArrayList<Contact>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            super.onPostExecute(contacts);
            adapter.clear();
            adapter.addAll(contacts);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {
            ArrayList<Contact> ds = new ArrayList<>();
            try
            {
                URL url = new URL("http://www.w3schools.com/website/customers_mysql.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader reader =new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);

                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while(line != null)
                {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }

                JSONArray jsonArray = new JSONArray();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    Contact contact =new Contact();
                    if(object.has("Name"))
                        contact.setName(object.getString("Name"));
                    if(object.has("Country"))
                        contact.setCountry(object.getString("Country"));
                    if(object.has("City"))
                        contact.setCity(object.getString("City"));
                    ds.add(contact);

                }
            }
            catch (Exception ex)
            {
                Log.e("LOI",ex.toString());
            }

            return ds;
        }
    }

    private void addControls() {
        lvContact= (ListView) findViewById(R.id.lvContact);
        dsContact=new ArrayList<>();
        adapter =new ArrayAdapter<Contact>(MainActivity.this,android.R.layout.simple_list_item_1,dsContact);
        lvContact.setAdapter(adapter);
    }
}
