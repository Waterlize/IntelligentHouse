package com.example.bartek.flowers.DevicesList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bartek.flowers.utils.Device;

/**
 * Created by sylwek on 27.06.15.
 */
public class DevicesListAdapter extends BaseAdapter {
    private Context context;

    public DevicesListAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return Device.deviceList.size();
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
        TextView view = new TextView(context);
        view.setHeight(70);
        view.setPadding(10, 20, 0, 0);
        Device currentDevice = Device.deviceList.get(position);
        view.setText(currentDevice.getName() + " " + currentDevice.getState());

        if (currentDevice.getColor().equals(Device.GREEN)) {
            view.setTextColor(Color.GREEN);
        } else if (currentDevice.getColor().equals(Device.RED)) {
            view.setTextColor(Color.RED);
        } else if (currentDevice.getColor().equals(Device.GRAY)) {
            view.setTextColor(Color.GRAY);
        }

        return view;
    }

}