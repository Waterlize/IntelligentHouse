package com.example.bartek.flowers.DevicesList;

import android.app.ListActivity;
import android.os.Bundle;
import com.example.bartek.flowers.BeaconMonitor;

/**
 * Created by sylwek on 27.06.15.
 */
public class DevicesInfiniteList extends ListActivity {

    private DevicesListAdapter adapter = new DevicesListAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(adapter);
        Thread thread = new Thread(new BeaconMonitor(this));
        thread.start();
    }

    public void notifyDevicesListAdapter() {
        System.out.println("what");
        adapter.notifyDataSetChanged();
    }
}