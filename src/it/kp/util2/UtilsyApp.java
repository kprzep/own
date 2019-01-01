/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.kp.util2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

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
        /*
            for (File hiddenFile : FileUtils.showHiddenFilesInDir("c:/")) {
            System.out.println(hiddenFile);
            
            }
            
            for (File dirFile : FileUtils.showDirectoriesInDirectory("c:/Users")) {
            System.out.println(dirFile);
            }
         */
        /*
        try {
            XUtils.readXmlStaxFileDiag("C:/kp/samochody.xml");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilsyApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(UtilsyApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        */

        for (Samochod auto : XUtils.readXmlStaxFile("C:/kp/samochody.xml")) {
            System.out.println(auto.toString());
        }

    }

}
