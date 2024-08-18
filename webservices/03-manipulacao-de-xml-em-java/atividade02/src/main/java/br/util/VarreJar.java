/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * @author alexandrelerario
 */
public class VarreJar {

    private ArrayList listofClasses;

    @SuppressWarnings("VarreJar")
    public VarreJar(File file) {
        this.listofClasses = new ArrayList();


        try {
            JarInputStream JarFile = new JarInputStream(new FileInputStream(file));
            JarEntry jarEntry;

            while (true) {
                jarEntry = JarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if ((jarEntry.getName().endsWith(".class"))) {
                    String className = jarEntry.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    listofClasses.add(myClass);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getFirstClassName(){
        return listofClasses.get(0).toString();
    }
    
    public ArrayList getClassNames(){
        return listofClasses;
    }

}
