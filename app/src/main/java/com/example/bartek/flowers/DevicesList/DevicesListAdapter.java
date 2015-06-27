package com.example.bartek.flowers.DevicesList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bartek.flowers.utils.Device;

/**
 * Created by sylwek on 27.06.15.
 */
public class DevicesListAdapter extends BaseAdapter {

    private int count = 40; /* starting amount */
    private Context devicesList;

    public DevicesListAdapter(Context devicesList) {
        super();
        this.devicesList = devicesList;
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
        //Device device = Device.deviceList.get(position);
        //if(device != null) {
            TextView view = new TextView(devicesList);
            //view.setText("Device " + device.getName());
        //}

        return view;
    }

}