/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fbd.utils;

/**
 *
 * @author garren
 */
public class Arithmetic {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  
    }
}
