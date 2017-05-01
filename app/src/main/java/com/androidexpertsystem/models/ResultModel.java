package com.androidexpertsystem.models;

/**
 * Created by Serhii Slobodianiuk on 01.05.2017.
 */

public abstract class ResultModel {

    public int image;
    public String name;
    public String proc;
    public String ram;
    public String memory;
    public String gpu;
    public String os;
    public String display;
    public String battery;
    public String camera;
    public String usb;
    public String bluetooth;

    public ResultModel(int image, String name, String proc, String ram, String memory, String gpu, String os,
                       String display, String battery, String camera, String usb, String bluetooth) {
        this.image = image;
        this.name = name;
        this.proc = proc;
        this.ram = ram;
        this.memory = memory;
        this.gpu = gpu;
        this.os = os;
        this.display = display;
        this.battery = battery;
        this.camera = camera;
        this.usb = usb;
        this.bluetooth = bluetooth;
    }
}
