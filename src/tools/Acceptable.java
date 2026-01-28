/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author win
 */
public interface Acceptable {

    String NOT_EMPTY = "^(?!\\s*$).+";
    String FRAMEID = "F\\d{5}";
    String ENGINE = "E\\d{5}";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
