package com.example.bartek.flowers.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


/**
 * Created by Bartek on 2015-06-27.
 */
public class Device extends Observable {
    static public List<Device> deviceList = Collections.synchronizedList(new ArrayList<Device>());

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String GRAY = "gray";

    String color;
    String id;
    String name;
    Integer state;
    long lastUpdate;

    public Device(String id, Integer state) {
        this.id = id;
        this.name = "Device "+ id;
        this.setState(state);
    }

    public Device(String id, String name, Integer state) {
        this.id = id;
        this.name = name;
        this.setState(state);
    }

    public void setState(Integer state) {
        this.state = state;
        this.color = GRAY;
        this.lastUpdate=System.currentTimeMillis()/1000;
        if (this.state.equals(1)) this.color = GREEN;
        if (this.state.equals(0)) this.color = RED;
    }
    public void newState(){
        if(((System.currentTimeMillis()/ 1000) - this.lastUpdate)>15) this.setState(-1);
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getState() {
        return state;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (lastUpdate != device.lastUpdate) return false;
        if (color != null ? !color.equals(device.color) : device.color != null) return false;
        if (!id.equals(device.id)) return false;
        if (!name.equals(device.name)) return false;
        return !(state != null ? !state.equals(device.state) : device.state != null);

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (int) (lastUpdate ^ (lastUpdate >>> 32));
        return result;
    }
}