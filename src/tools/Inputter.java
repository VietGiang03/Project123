/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author win
 */
public class Inputter {

    private Scanner ndl;

    // Default constructor
    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public int getInt(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(ndl.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public int getIntUpdate(int oldData, String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                String string = ndl.nextLine();
                if (string.trim().isEmpty()) {
                    return oldData;
                }
                int number = Integer.parseInt(string.trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public double getDouble(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(ndl.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public double getDoubleUpdate(double oldData, String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                String string = ndl.nextLine();
                if (string.trim().isEmpty()) {
                    return oldData;
                }
                double number = Double.parseDouble(string.trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = ndl.nextLine();
            if (Acceptable.isValid(str, REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    public String getStringUpdate(String oldData, String messageInfo,
            String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = ndl.nextLine();
            if (str.trim().isEmpty()) {
                return oldData;
            }
            if (Acceptable.isValid(str, REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    public String getBrandID() {
        return getString("Input brand ID: ", "ID can not blank", Acceptable.NOT_EMPTY);
    }

    public String getBrandName() {
        return getString("Input brand name: ", "Brand name can not blank", Acceptable.NOT_EMPTY);
    }

    public String getBrandNameUpdate(String oldBrand) {
        return getStringUpdate(oldBrand, "Input new brand name: ", "Brand name can not blank", Acceptable.NOT_EMPTY);
    }

    public String getSoundBrand() {
        return getString("Input Sound Brand: ", "sound brand can not blank", Acceptable.NOT_EMPTY);
    }

    public String getSoundBrandUpdate(String oldSoundBrand) {
        return getStringUpdate(oldSoundBrand, "Input new Sound Brand: ", "sound brand can not blank", Acceptable.NOT_EMPTY);
    }

    public double getPrice() {
        return getDouble("Input price: ", "price must be >0",
                "The price must be a number. Try again !", Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public double getPriceUpdate(double oldPrice) {
        return getDoubleUpdate(oldPrice, "Input new price: ", "price must be >0",
                "The price must be a number. Try again !", Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public String getCarID() {
        return getString("Input car ID: ", "Car name can not blank", Acceptable.NOT_EMPTY);
    }

    public String getColor() {
        return getString("Input color: ", "color can not blank", Acceptable.NOT_EMPTY);
    }

    public String getColorUpdate(String oldColor) {
        return getStringUpdate(oldColor, "Input new color: ", "color can not blank", Acceptable.NOT_EMPTY);
    }

    public String getFrameID() {
        return getString("Input frame ID: ", "The frame ID must be in F00000", Acceptable.FRAMEID);
    }

    public String getFrameIDUpdate(String oldFrameID) {
        return getStringUpdate(oldFrameID, "Input new frame ID: ", "The frame ID must be in F00000", Acceptable.FRAMEID);
    }

    public String getEngineID() {
        return getString("Input engine ID: ", "The engine ID must be in E00000", Acceptable.ENGINE);
    }

    public String getEngineIDUpdate(String oldEngineID) {
        return getStringUpdate(oldEngineID, "Input new engine ID: ", "The engine ID must be in E00000", Acceptable.ENGINE);
    }

}
