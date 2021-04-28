package com.example.dbsimple;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<String> countryList = new ArrayList<String>();
    List<String> flags = new ArrayList<String>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<String> countryList, List<String> flags) {
        this.context = context;
        this.countryList = countryList;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView country = (TextView)view.findViewById(R.id.textView);
        TextView icon = (TextView) view.findViewById(R.id.icon);
        country.setText(countryList.get(i));
        icon.setText(flags.get(i));
        return view;
    }
}