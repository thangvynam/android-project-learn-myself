package vn.edu.topica.model;

import android.databinding.ObservableArrayList;
import android.view.View;

import java.util.Random;

/**
 * Created by DELL on 8/27/2017.
 */

public class ContactInfoList {
    public ObservableArrayList<Contact> list = new ObservableArrayList<>();
    private int totalContact=500;
    Random random = new Random();
    public ContactInfoList(ObservableArrayList<Contact> list) {
        this.list = list;
    }

    public ContactInfoList() {
        for(int i=0;i<totalContact;i++){
            String ten="Tên "+i;
            String phone="09";
            for(int j=0;j<8;j++)
            {
                phone+=random.nextInt(10);
            }
            Contact contact= new Contact(ten,phone);
            add(contact);
        }
    }
    private void add(Contact contact){
        list.add(contact);
    }
    public void add(View view){
        String ten="Tên "+(++totalContact);
        String phone="09";
        for(int j=0;j<8;j++)
        {
            phone+=random.nextInt(10);
        }
        Contact contact= new Contact(ten,phone);
        add(contact);
    }
    public void remove(View view){
        if(!list.isEmpty()){
            list.remove(0);
        }
    }

}
