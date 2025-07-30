package com.ra.entity;

public class Student {
    private String fullName;
    private String className;
    private String vehicleType;
    private String licensePlate;

    public Student() {
    }

    public Student(String fullName, String className, String vehicleType, String licensePlate) {
        this.fullName = fullName;
        this.className = className;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
