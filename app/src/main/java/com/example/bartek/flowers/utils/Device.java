package com.example.bartek.flowers.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;


/**
 * Created by Bartek on 2015-06-27.
 */
public class Device extends Observable {
    static public List<Device> deviceList = new ArrayList<Device>();

    public static final String RED = "red";
    public static final String GREEN = "green";

    String color;
    String id;
    String name;
    Integer state;
    Date lastUpdate;

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
        this.color="black";
        if (this.state.equals(1)) this.color = GREEN;
        if (this.state.equals(0)) this.color = RED;
        this.lastUpdate=new java.util.Date();
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (color != null ? !color.equals(device.color) : device.color != null) return false;
        if (!id.equals(device.id)) return false;
        if (!name.equals(device.name)) return false;
        if (state != null ? !state.equals(device.state) : device.state != null) return false;
        return !(lastUpdate != null ? !lastUpdate.equals(device.lastUpdate) : device.lastUpdate != null);

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}