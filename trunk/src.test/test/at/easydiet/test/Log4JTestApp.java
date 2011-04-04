/**
 * This file is part of Easy Diet.
 *   created on: 04.04.2011
 *   created by: Manuel
 *   file: Log4JTestApp.java
 */
package test.at.easydiet.test;

import org.apache.log4j.xml.DOMConfigurator;


/**
 * @author Manuel
 *
 */
public class Log4JTestApp
{
    
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Log4JTestApp.class);
    
    public static void main(String[] args)
    {
        DOMConfigurator.configure(Log4JTestApp.class.getResource("client_log4j.xml"));
        LOGGER.trace("test");
        LOGGER.debug("test");
        LOGGER.debug("test");
        LOGGER.debug("test");
        LOGGER.fatal("test");
        LOGGER.error("test");
        System.out.println("test");
    }
}


