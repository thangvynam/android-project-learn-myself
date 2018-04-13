package vn.edu.topica.model;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.ListView;

/**
 * Created by DELL on 8/27/2017.
 */

public class ListBinder {
    @BindingAdapter("bind:items")
    public static void bindList(ListView listView, ObservableArrayList<Contact> list){
        ListAdapter listAdapter = new ListAdapter(list);
        listView.setAdapter(listAdapter);

    }
}
