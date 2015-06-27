package com.example.bartek.flowers;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;

import com.example.bartek.flowers.utils.Device;
import com.kontakt.sdk.android.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.configuration.MonitorPeriod;
import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.device.BeaconDevice;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class BeaconMonitorActivity extends Activity {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;

    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_monitor_list_activity);

        beaconManager = BeaconManager.newInstance(this);
        beaconManager.setMonitorPeriod(MonitorPeriod.MINIMAL);
        beaconManager.setForceScanConfiguration(ForceScanConfiguration.DEFAULT);
        beaconManager.registerMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onMonitorStart() {} // active scan period starts

            @Override
            public void onMonitorStop() {} // passive scan period starts

            @Override
            public void onBeaconAppeared(final Region region, final BeaconDevice beaconDevice) { // beacon appeared within desired region for the first time
                //System.out.println("ADDED NEW Beacon id: " + beaconDevice.getUniqueId() + " major: " + beaconDevice.getMajor() + " minor: " + beaconDevice.getMinor());

                Device.deviceList.add(new Device(beaconDevice.getUniqueId(),((beaconDevice.getMinor() & 128)==0) ? 0 : 1));
            }
            @Override
            public void onBeaconsUpdated(final Region venue, final List<BeaconDevice> beacons) {

                for(BeaconDevice beaconDevice : beacons){
                    for (Device device : Device.deviceList) {
                        if(device.getId().equals(beaconDevice.getUniqueId())) device.setState(((beaconDevice.getMinor() & 128)==0) ? 0 : 1);
                    }
                    //System.out.println("Beacon id: "+beaconDevice.getUniqueId() + " major: "+ beaconDevice.getMajor() + " minor: " + beaconDevice.getMinor());
                }


            } // beacons that are visible within specified region are provided through this method callback. This method has the same

            @Override
            public void onRegionEntered(final Region venue) {} // Android device enters the Region for the first time

            @Override
            public void onRegionAbandoned(final Region venue) {} // Android device abandons the region
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!beaconManager.isBluetoothEnabled()) {
            final Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
        } else if(beaconManager.isConnected()) {
            try {
                beaconManager.startRanging();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        } else {
            connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        beaconManager.stopMonitoring();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
        beaconManager = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
            if(resultCode == Activity.RESULT_OK) {
                connect();
            } else {
                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
                getActionBar().setSubtitle("Bluetooth not enabled");
            }
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void connect() {
        try {
            beaconManager.connect(new OnServiceBoundListener() {
                @Override
                public void onServiceBound() {
                    try {
                        beaconManager.startMonitoring(new HashSet<Region>(Arrays.asList(Region.EVERYWHERE)));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }


}