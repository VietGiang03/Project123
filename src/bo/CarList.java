/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Brand;
import models.Car;
import tools.Inputter;

/**
 *
 * @author win
 */
public class CarList {

    private List<Car> list;
    private String pathFile;
    private boolean saved;

    public CarList() {
        list = new ArrayList<>();
        pathFile = new File("src").getAbsolutePath() + "\\data\\cars.txt";
        saved = false;
    }

    public boolean isSaved() {
        return saved;
    }

    public void loadFromFile(BrandList brands) throws IOException {
        String carID, color, frameID, engineID;
        String line;
        String[] arr;
        Brand brand;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(pathFile));
            line = br.readLine();
            while (line != null) {
                arr = line.split(",");
                carID = arr[0].trim();
                brand = brands.getBrand(arr[1].trim());
                color = arr[2].trim();
                frameID = arr[3].trim();
                engineID = arr[4].trim();
                list.add(new Car(carID, brand, color, frameID, engineID));
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    //Open the file based on the filename to write data in line-by-line in text format
    public void saveToFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(pathFile));
            for (Car i : list) {
                pw.println(i);
            }
            saved = true;
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Search a car based on car ID
    public Car getCarByID(String carID) {
        for (Car car : list) {
            if (car.getCarID().equalsIgnoreCase(carID)) {
                return car;
            }
        }
        return null;
    }

    //Search a car by its frame ID. Use in checking frames are not duplicated.
    public Car getCarByEngineID(String engineID) {
        for (Car car : list) {
            if (car.getEngineID().equalsIgnoreCase(engineID)) {
                return car;
            }
        }
        return null;
    }

    //Search a car by its engine ID. Use in checking engines are not duplicated.
    public Car getCarByFrameID(String frameID) {
        for (Car car : list) {
            if (car.getFrameID().equalsIgnoreCase(frameID)) {
                return car;
            }
        }
        return null;
    }

    public List<Car> filterByColor(String color) {
        List<Car> result = new ArrayList<>();
        for (Car car : list) {
            if (car.getColor().equalsIgnoreCase(color)) {
                result.add(car);
            }
        }
        return result;
    }

    //Add car to the set
    public boolean add(Car x) throws Exception {
        if (x == null) {
            return false;
        }
        if (getCarByID(x.getCarID()) != null) {
            throw new Exception("This Car ID is duplicate. Try again!");
        }
        if (getCarByEngineID(x.getEngineID()) != null) {
            throw new Exception("This Engine ID is duplicate. Try again!");
        }
        if (getCarByFrameID(x.getFrameID()) != null) {
            throw new Exception("This Frame ID is duplicate. Try again!");
        }
        if (list.add(x)) {
            saved = false;
            return true;
        }
        return false;
    }

    //Remove a car based on it’s ID
    public boolean delete(String carID) throws Exception {
        Car car = getCarByID(carID);
        if (car == null) {
            throw new Exception("This car does not exist!");
        }
        if (list.remove(car)) {
            saved = false;
            return true;
        }
        return false;
    }

    //Update a car based on it’s ID
    public Car updateCar(String carID) throws Exception {
        Car car = getCarByID(carID);
        if (car == null) {
            throw new Exception("This car does not exist!");
        }
        Inputter input = new Inputter();
        car.setColor(input.getColorUpdate(car.getColor()));
        car.setFrameID(input.getFrameIDUpdate(car.getFrameID()));
        car.setEngineID(input.getEngineIDUpdate(car.getEngineID()));
        saved = false;
        return car;
    }

    //Listing cars in ascending order of brand names
    public String getTable() {
        List<Car> copy = new ArrayList<>((ArrayList) list);
        Collections.sort(copy);
        return getTable(copy);
    }

    public String getTable(List<Car> list) {
        if (list.isEmpty()) {
            return null;
        }
        String result = "";
        result += "-------------------------------------------------------------\n";
        result += String.format("%-10s | %-10s | %-10s | %-10s | %-10s\n",
                "Car ID", "Brand ID", "color", "Frame ID", "Engine ID");
        result += "-------------------------------------------------------------\n";
        for (Car car : list) {
            result += String.format("%-10s | %-10s | %-10s | %-10s | %-10s\n",
                    car.getCarID(), car.getBrand().getBrandID(), car.getColor(), car.getFrameID(), car.getEngineID());
        }
        result += "-------------------------------------------------------------\n";
        return result;
    }
}
