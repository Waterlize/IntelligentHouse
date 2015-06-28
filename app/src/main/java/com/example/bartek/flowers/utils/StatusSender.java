package com.example.bartek.flowers.utils;

import com.example.bartek.flowers.DevicesList.DevicesInfiniteList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Bartek on 2015-06-28.
 */
public class StatusSender implements Runnable{
    DevicesInfiniteList devicesInfiniteList;
    String adress= "http://151.80.146.205:8000";
    int id;
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget;

    public StatusSender(DevicesInfiniteList devicesInfiniteList) {
        this.devicesInfiniteList = devicesInfiniteList;
    }
    @Override
    public void run() {
        while(true) {
            for(int i=0;i<Device.deviceList.size();i++) Device.deviceList.get(i).newState();
            for (Device device : Device.deviceList) {
                id = 7;
                if (device.getId()==null) continue;
                if (device.getId().equals("ulrQ")) id = 1;
                if (device.getId().equals("7TLc")) id = 2;
                if (device.getId().equals("XSNC")) id = 3;
                if (device.getId().equals("Yax6")) id = 4;
                if (device.getId().equals("Azhg")) id = 5;
                if (id == 7) continue;

                httpget = new HttpGet(adress + "/door/?id=door" + id + "&color=" + device.getColor());

                try {
                    HttpResponse resposne = httpclient.execute(httpget);
                    System.out.println("Sended color " + device.getColor() +" for device " + id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            devicesInfiniteList.runOnUiThread(devicesInfiniteList);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
