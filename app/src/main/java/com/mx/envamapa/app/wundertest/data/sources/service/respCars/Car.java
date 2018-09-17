package com.mx.envamapa.app.wundertest.data.sources.service.respCars;

import java.util.Arrays;

/**
 * Created by enya on 17/09/18.
 */

public class Car {

    private String address;
    private Double[] coordinates;
    private String engineType;
    private String exterior;
    private Integer fuel;
    private String interior;
    private String name;
    private String vin;

    public Car() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Car{" +
                "address='" + address + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", engineType='" + engineType + '\'' +
                ", exterior='" + exterior + '\'' +
                ", fuel=" + fuel +
                ", interior='" + interior + '\'' +
                ", name='" + name + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
