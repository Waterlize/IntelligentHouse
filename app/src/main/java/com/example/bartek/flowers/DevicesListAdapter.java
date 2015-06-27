package com.example.bartek.flowers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
/**
 * Created by sylwek on 27.06.15.
 */
public class DevicesListAdapter extends BaseAdapter {

    private int count = 40; /* starting amount */
    private Context context;

    public DevicesListAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO
        TextView view = new TextView(context);
        view.setText("Device " + position);
        return view;
    }

}