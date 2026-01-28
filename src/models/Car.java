/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author win
 */
public class Car implements Comparable<Car> {

    private String carID, color, frameID, engineID;
    public Brand brand;

    public Car() {

    }

    public Car(String carID, Brand brand, String color,
            String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getCarID() {
        return carID;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    //Used in the operation opf listing cars in ascending order of brand names
    @Override
    public int compareTo(Car car) {
        int val = getBrand().getBrandName().
                compareTo(car.getBrand().getBrandName());
        if (val == 0) {
            if (getBrand().getPrice() > car.getBrand().getPrice()) {
                val = -1;
            } else if (getBrand().getPrice() < car.getBrand().getPrice()) {
                val = 1;
            } else {
                val = 0;
            }
        }
        return val;
    }

    //Associating fields to a string for writing a car to file
    @Override
    public String toString() {
        return carID + ", " + brand.getBrandID() + ", " + color
                + ", " + frameID + ", " + engineID;
    }
}
