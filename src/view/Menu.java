/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import tools.Inputter;

/**
 *
 * @author win
 */
public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public void addItem(String s) {
        this.add(s);
    }

    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + ". " + this.get(i));
        }
    }

    public boolean confirmYesNo(String welcome) {
        Inputter input = new Inputter();
        String result = input.getString(welcome, "Just Y or N", "[YNyn]");
        return result.equalsIgnoreCase("Y");
    }

    public int getChoice() {
        Inputter input = new Inputter();
        return input.getInt("Enter your choice: ", "Just 1-> " + this.size(), "Invalid!", 1, this.size());
    }

    public void displayMessenger(String messenger) {
        System.out.println(messenger);
    }

    public void displayResult(String messenger, String data) {
        if (!data.isEmpty()) {
            System.out.println(messenger);
            System.out.println(data);
        }
    }

}
