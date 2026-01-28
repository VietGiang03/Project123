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
import java.util.List;
import models.Brand;
import tools.Inputter;

/**
 *
 * @author win
 */
public class BrandList {

    private List<Brand> list;
    private String pathFile;
    private boolean saved;

    public BrandList() {
        list = new ArrayList<>();
        pathFile = new File("src").getAbsolutePath() + "\\data\\brands.txt";
        saved = false;
    }

    public boolean isSaved() {
        return saved;
    }

    public List<Brand> getAll() {
        return list;
    }

    public void loadFromFile() throws IOException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(pathFile));
            String[] arr;
            String line = br.readLine();
            while ((line != null)) {
                arr = line.split(",");
                String brandID = arr[0].trim();
                String brandName = arr[1].trim();
                String soundBrand = arr[2].split(":")[0].trim();
                String priceB = arr[2].split(":")[1].trim();
                double price = Double.parseDouble(priceB.substring(0, priceB.length() - 1));
                list.add(new Brand(brandID, brandName, soundBrand, price));
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + pathFile + " not found !");
        }
    }

    public void saveToFile() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(pathFile));
            for (Brand i : list) {
                pw.println(i);
            }
            pw.close();
            saved = true;
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Brand getBrand(String bID) {
        for (int i = 0; i < list.size(); i++) {
            if (bID.equals(list.get(i).getBrandID())) {
                return list.get(i);
            }
        }
        return null;
    }

    public boolean add(Brand x) throws Exception {
        if (x == null) {
            return false;
        }
        if (getBrand(x.getBrandID()) == null) {
            if (list.add(x)) {
                saved = false;
                return true;
            }
            return false;
        }
        throw new Exception("This Brand ID is duplicate. Try again!");
    }

    //Update brand_name, sound_brand, price of an existed brand
    public Brand updateBrand(String brandID) throws Exception {
        Brand brand = getBrand(brandID);
        if (brand == null) {
            throw new Exception("This brand does not exist!");
        }
        Inputter input = new Inputter();
        brand.setBrandName(input.getBrandNameUpdate(brand.getBrandName()));
        brand.setSoundBrand(input.getSoundBrandUpdate(brand.getSoundBrand()));
        brand.setPrice(input.getPriceUpdate(brand.getPrice()));
        saved = false;
        return brand;
    }

    public List<Brand> filterByPrice(double max) {
        List<Brand> result = new ArrayList<>();
        for (Brand brand : list) {
            if (brand.getPrice() <= max) {
                result.add(brand);
            }
        }
        return result;
    }

    public List<Brand> filterByBrandName(String key) {
        List<Brand> result = new ArrayList<>();
        for (Brand brand : list) {
            if (brand.getBrandName().toLowerCase().contains(key.toLowerCase())) {
                result.add(brand);
            }
        }
        return result;
    }

    //Show the list of the brands
    public String getTable() {
        return getTable(list);
    }

    public String getTable(List<Brand> list) {
        if (list.isEmpty()) {
            return null;
        }
        String result = "";
        result += "----------------------------------------------------------------------\n";
        result += String.format("%-10s | %-30s | %-15s | %-10s\n",
                "Brand ID", " brand name", "sound brand", "price");
        result += "----------------------------------------------------------------------\n";
        for (Brand brand : list) {
            result += String.format("%-10s | %-30s | %-15s | %-10s\n",
                    brand.getBrandID(), brand.getBrandName(), brand.getSoundBrand(), brand.getPrice() + "B");
        }
        result += "----------------------------------------------------------------------\n";
        return result;
    }
}
