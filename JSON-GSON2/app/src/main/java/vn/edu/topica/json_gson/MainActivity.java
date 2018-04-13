package vn.edu.topica.json_gson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import vn.edu.topica.model.GoogleData;
import vn.edu.topica.model.Result;

public class MainActivity extends AppCompatActivity {

    EditText txtData;
    Button btnSearch;
    ListView lvResult;
    ArrayList<Result> dsResult;
    ArrayAdapter<Result>resultArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        
    }

    private void addEvents() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    private void search() {
    }
    class GoogleDataTask extends AsyncTask<String,Void,ArrayList<Result>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            resultArrayAdapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Result> results) {
            super.onPostExecute(results);
            resultArrayAdapter.clear();
            dsResult.addAll(results);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Result> doInBackground(String... params) {
            try
            {
                String keyword=params[0];
                String api="http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
                String linkAPI=api+ URLEncoder.encode(keyword,"UTF-8")+"&start=1"+"&rzs=8";
                URL url = new URL(linkAPI);
                InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
                GoogleData googleData = new Gson().fromJson(reader,GoogleData.class);
                return (ArrayList<Result>) googleData.getResponseData().getResults();
            }
            catch (Exception ex)
            {
                Log.e("Loi",ex.toString());
            }
            return null;
        }
    }
    private void addControls() {
        txtData= (EditText) findViewById(R.id.txtData);
        btnSearch= (Button) findViewById(R.id.btnSearch);
        lvResult = (ListView) findViewById(R.id.lvResult);
        dsResult =new ArrayList<>();
        resultArrayAdapter = new ArrayAdapter<Result>(MainActivity.this,android.R.layout.simple_list_item_1,dsResult);
        lvResult.setAdapter(resultArrayAdapter);
    }
}
