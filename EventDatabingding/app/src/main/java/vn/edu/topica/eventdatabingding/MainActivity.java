package vn.edu.topica.eventdatabingding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import vn.edu.topica.eventdatabingding.databinding.ActivityMainBinding;
import vn.edu.topica.models.Temperature;

public class MainActivity extends AppCompatActivity implements MainActivityContact.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        MainAcivityPresenter presenter= new MainAcivityPresenter(MainActivity.this);
        binding.setPresenter(presenter);
        binding.setTemp(new Temperature("100"));


    }

    @Override
    public void showData(Temperature temperature) {
        Toast.makeText(MainActivity.this,"Độ C = "+temperature.getCelsius(),Toast.LENGTH_LONG).show();
    }
}
