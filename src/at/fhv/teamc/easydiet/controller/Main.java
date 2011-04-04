/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: Main.java
 */
package at.fhv.teamc.easydiet.controller;

import at.fhv.teamc.easydiet.view.MainWindow;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Main entry point of the application
 * @author Michael
 */
public class Main {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Main.class);

    /**
     * The applications main entry point
     * @param args
     */
    public static void main(String[] args) {

        // init log4j
        DOMConfigurator.configure(MainWindow.class.getResource("../easyDietGlobalLog.xml"));

        GUIController guic = new GUIController();
        MainWindow.init(guic);
    }
}
