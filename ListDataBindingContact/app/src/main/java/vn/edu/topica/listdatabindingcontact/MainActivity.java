package vn.edu.topica.listdatabindingcontact;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.topica.listdatabindingcontact.databinding.ActivityMainBinding;
import vn.edu.topica.model.ContactInfoList;
import vn.edu.topica.model.ListHeading;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        ListHeading listHeading = new ListHeading("Danh sách Contact");
        binding.setHeading(listHeading);

        ContactInfoList contactInfoList = new ContactInfoList();
        binding.setInfos(contactInfoList);

    }
}
