package vn.edu.topica.model;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import vn.edu.topica.listdatabindingcontact.R;
import vn.edu.topica.listdatabindingcontact.databinding.ItemBinding;

/**
 * Created by DELL on 8/27/2017.
 */

public class ListAdapter extends BaseAdapter {
    private ObservableArrayList<Contact>list;
    private LayoutInflater inflater;

    public ListAdapter(ObservableArrayList observableArrayList) {
        this.list=observableArrayList;
    }

    public ListAdapter(ObservableArrayList<Contact> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    public ListAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null){
            inflater= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        ItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.item,parent,false);
        binding.setInfo(list.get(position));
        return binding.getRoot();
    }
}
