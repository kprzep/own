/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.kp.util2;

import java.io.File;

/**
 *
 * @author krzysiek2
 */
public class UtilsyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            for (File hiddenFile : FileUtils.showHiddenFilesInDir("c:/")) {
                System.out.println(hiddenFile);
            
        }
            
            for (File dirFile : FileUtils.showDirectoriesInDirectory("c:/Users")) {
                System.out.println(dirFile);
        }
    }
    
}
