package com.example.bartek.flowers.DevicesList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.example.bartek.flowers.utils.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylwek on 27.06.15.
 */
public class DevicesInfiniteList extends ListActivity implements OnScrollListener {

    private DevicesListAdapter adapter = new DevicesListAdapter(this);
    private List<Device> devices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);
//        Thread thread = new Thread(new BeaconMonitorActivity(this));
    }

    @Override
    public void onScroll(AbsListView view, int firstVisible, int visibleCount, int totalCount) {

        boolean loadMore = firstVisible + visibleCount >= totalCount;

        if(loadMore) {
            adapter.setCount(adapter.getCount() + visibleCount); // or any other amount
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //TODO
    }

    public void addDevice(Device device) {
        if(device != null)
            devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public Device getDeviceAt(int position) {
        if(position >= devices.size())
            return null;
        return devices.get(position);
    }
}