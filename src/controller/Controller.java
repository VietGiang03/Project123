/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.BrandList;
import bo.CarList;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Brand;
import models.Car;
import tools.Inputter;
import view.Menu;

/**
 *
 * @author Tuan Tran
 */
class Controller {

    private Menu menu;
    private BrandList brands;
    private CarList cars;
    private Inputter input;

    public Controller() {
        menu = new Menu();
        brands = new BrandList();
        cars = new CarList();
        input = new Inputter();
    }

    public void start() {
        menu.add("List all brands");
        menu.add("Add a new brand");
        menu.add("Search for a brand by ID");
        menu.add("Update a brand by ID");
        menu.add("List all brands with prices less than or equal to an input value");
        menu.add("List all cars in ascending order of brand names ");
        menu.add("Search cars by partial brand name match");
        menu.add("Add a new car");
        menu.add("Remove a car by ID");
        menu.add("Update a car by ID");
        menu.add("List all cars by a specific color ");
        menu.add("Save data to files");
        menu.add("Quit Program");
        try {
            brands.loadFromFile();
            cars.loadFromFile(brands);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        while (true) {
            menu.displayMessenger("===========Car Showroom Management==========");
            menu.showMenu();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    showListBrands();
                    break;
                case 2:
                    addNewBrand();
                    break;
                case 3:
                    searchBrandID();
                    break;
                case 4:
                    updateBrand();
                    break;
                case 5:
                    filterBrandsByPrice();
                    break;
                case 6:
                    showListCars();
                    break;
                case 7:
                    filterBrandsByName();
                    break;
                case 8:
                    addNewCar();
                    break;
                case 9:
                    removeCar();
                    break;
                case 10:
                    updateCar();
                    break;
                case 11:
                    filterCarsByColor();
                    break;
                case 12:
                    brands.saveToFile();
                    cars.saveToFile();
                    if (brands.isSaved()) {
                        menu.displayMessenger("Save file brands.txt success!");
                    }
                    if (cars.isSaved()) {
                        menu.displayMessenger("Save file cars.txt success!");
                    }
                    break;
                default:
                    if (!brands.isSaved()) {
                        brands.saveToFile();
                    }
                    if (!cars.isSaved()) {
                        cars.saveToFile();
                    }
                    if (brands.isSaved()) {
                        menu.displayMessenger("Save file brands.txt success!");
                    }
                    if (cars.isSaved()) {
                        menu.displayMessenger("Save file cars.txt success!");
                    }
                    menu.displayMessenger("Exit success!");
                    System.exit(0);
                    break;
            }
        }
    }

    public void showListBrands() {
        String result = brands.getTable();
        if (result == null) {
            System.out.println("List brands is empty!");
        } else {
            System.out.println(result);
        }
    }

    public void addNewBrand() {
        String brandID = input.getBrandID();
        if (brands.getBrand(brandID) != null) {
            System.out.println("This Brand ID is duplicate. Try again");
            return;
        }
        String name = input.getBrandName();
        String soundBrand = input.getSoundBrand();
        double price = input.getPrice();
        Brand brand = new Brand(brandID, name, soundBrand, price);
        try {
            if (brands.add(brand)) {
                System.out.println("Add brand success: " + brand.toString());
            } else {
                System.out.println("Add fail!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchBrandID() {
        String brandID = input.getBrandID();
        Brand brand = brands.getBrand(brandID);
        if (brand == null) {
            System.out.println("This brand does not exist!");
        } else {
            System.out.println(brand.toString());
        }
    }

    public void updateBrand() {
        String brandID = input.getBrandID();
        try {
            Brand brandUpdate = brands.updateBrand(brandID);
            System.out.println("After update: " + brandUpdate.toString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void filterBrandsByPrice() {
        double price = input.getPrice();
        List<Brand> result = brands.filterByPrice(price);
        if (result.isEmpty()) {
            System.out.println("Can not found any brand have price <= input");
        } else {
            System.out.println(brands.getTable(result));
        }
    }

    public void showListCars() {
        String result = cars.getTable();
        if (result == null) {
            System.out.println("List cars is empty!");
        } else {
            System.out.println(result);
        }
    }

    public void filterBrandsByName() {
        String name = input.getBrandName();
        List<Brand> result = brands.filterByBrandName(name);
        if (result.isEmpty()) {
            System.out.println("Can not found any brand name contain key!");
        } else {
            System.out.println(brands.getTable(result));
        }
    }

    public void addNewCar() {
        String carID = input.getCarID();
        if (cars.getCarByID(carID) != null) {
            System.out.println("This car ID is duplicate. Try again!");
            return;
        }
        String brandID;
        showListBrands();
        brandID = input.getBrandID();
        if (brands.getBrand(brandID) == null) {
            System.out.println("This Brand ID not found!");
            return;
        }

        String color = input.getColor();
        String frameID = input.getFrameID();
        if (cars.getCarByFrameID(frameID) != null) {
            System.out.println("This Frame ID is duplicate. Try again!");
            return;
        }
        String engineID = input.getEngineID();
        if (cars.getCarByEngineID(engineID) != null) {
            System.out.println("This Engine ID is duplicate. Try again!");
            return;
        }
        Car newCar = new Car(carID, brands.getBrand(brandID), color, frameID, engineID);
        try {
            if (cars.add(newCar)) {
                System.out.println("Add car success: " + newCar.toString());
            } else {
                System.out.println("Add fail!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeCar() {
        String carID = input.getCarID();
        try {
            if (cars.delete(carID)) {
                System.out.println("Delete success!");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCar() {
        String carID = input.getCarID();
        try {
            Car carUpdate = cars.updateCar(carID);
            System.out.println("After update: " + carUpdate.toString());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void filterCarsByColor() {
        String color = input.getColor();
        List<Car> result = cars.filterByColor(color);
        if (result.isEmpty()) {
            System.out.println("Can not found any car have this color!");
        } else {
            System.out.println(cars.getTable(result));
        }
    }
}
