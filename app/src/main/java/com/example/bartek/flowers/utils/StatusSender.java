package com.example.bartek.flowers.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Bartek on 2015-06-28.
 */
public class StatusSender implements Runnable{
    String adress= "bedzieadres.com";
    int id;
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget;
    @Override
    public void run() {
        while(true) {
            for (Device device : Device.deviceList) {
                id = 7;
                device.newState();
                if (device.getId().equals("ulrQ")) id = 1;
                if (device.getId().equals("7TLc")) id = 2;
                if (device.getId().equals("XSNC")) id = 3;
                if (device.getId().equals("Yax6")) id = 4;
                if (device.getId().equals("Azhg")) id = 5;
                if (id == 7) continue;

                httpget = new HttpGet(adress + "/door/?id=door" + id + "&color=" + device.getColor());

                try {
                    //HttpResponse resposne = httpclient.execute(httpget);
                    System.out.println("Sended color " + device.getColor() +" for device " + id);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}
