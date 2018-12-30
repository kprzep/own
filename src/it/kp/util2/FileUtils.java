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
public class FileUtils {

    public static File[] showHiddenFilesInDir(String dir) {
        return new File(dir).listFiles(File::isHidden);
    }

    public static File[] showDirectoriesInDirectory(String dir) {
        return new File(dir).listFiles(File::isDirectory);
    }

}
