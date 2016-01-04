/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.utils;

/**
 *
 * @author angel
 */
public class Utils {
    
    public static void roundTwoDecinal(double cant){
        cant = (double) Math.round(cant * 100) / 100;
    }
}
