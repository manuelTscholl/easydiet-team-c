/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: Main.java
 */
package at.easydiet.teamc.controller;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.pivot.wtk.DesktopApplicationContext;

import at.easydiet.teamc.view.EasyDiet;



/**
 * Main entry point of the app lication
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
        DOMConfigurator.configure("src.resources/log4j.xml");

        // init gui
        DesktopApplicationContext.main(EasyDiet.class, args);
    }
}
