/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: MainWindow.java
 */
package at.fhv.teamc.easydiet.view;

import at.fhv.teamc.easydiet.controller.GUIController;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.ApplicationContext;

/**
 * MainWindow entry of the application's UI
 * @author Michael
 */
public class MainWindow extends ApplicationContext {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MainWindow.class);
    private static JDesktopPane sDESKTOP;
    private static final String sTITLE;
    private static JFrame sMAIN_FRAME;
    private static final String sMAIN_XML;
    private static boolean sINITALIZED;
    private static int sWINDOW_HEIGHT;
    private static int sWINDOW_WIDTH;
    private static String sICON;
    private static GUIController sGUI_Controller;
    private static JInternalFrame sINTERNAL_FRAME;
    private static ArrayList<Resizable> sRESIZABLES;

    static {
        sDESKTOP = new JDesktopPane();
        sTITLE = "EasyDiet - Patient: ";
        sMAIN_FRAME = new JFrame(sTITLE);
        sMAIN_XML = "bxml/main.bxml";
        sINITALIZED = false;
        sWINDOW_HEIGHT = 768;
        sWINDOW_WIDTH = 1024;
        sICON = "ico.png";
        sRESIZABLES = new ArrayList<Resizable>();

        // Start the callback timer, necessary for pivot
        createTimer();
    }

    /**
     * Initialize GUI
     * @param guic
     */
    public static void init(GUIController guic) {

        // avoid opening more than one time
        if (!sINITALIZED) {

            // init main frame window
            sMAIN_FRAME.setContentPane(sDESKTOP);
            sMAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sMAIN_FRAME.setSize(sWINDOW_WIDTH, sWINDOW_HEIGHT);
            sMAIN_FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
            sMAIN_FRAME.setResizable(true);
            sMAIN_FRAME.setVisible(true);
            sGUI_Controller = guic;

            // set application icon
            Image icon = new ImageIcon(MainWindow.class.getResource(sICON)).getImage();
            sMAIN_FRAME.setIconImage(icon);

            // add listener for resizing desktop
            sDESKTOP.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    resize();
                }
            });

            // init pivot frame
            initPivotFrame();

            sINITALIZED = true;
        }
    }

    /**
     * Initalize Pivot Frame in new Thread
     */
    private static void initPivotFrame() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createPivotFrame();
            }
        });
    }

    /**
     * Create Pivot Frame
     */
    private static void createPivotFrame() {

        // Create the internal frame that will contain the Pivot components
        sINTERNAL_FRAME = new JInternalFrame();

        // remove title bar
        ((javax.swing.plaf.basic.BasicInternalFrameUI) sINTERNAL_FRAME.getUI()).setNorthPane(null);

        // remove border
        sINTERNAL_FRAME.setBorder(null);

        sDESKTOP.add(sINTERNAL_FRAME);

        // Create the display host
        ApplicationContext.DisplayHost displayHost = new ApplicationContext.DisplayHost();
        sINTERNAL_FRAME.add(displayHost);

        // Add the display to the display list
        displays.add(displayHost.getDisplay());

        // Load the Pivot window
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        EasyDietWindow window;
        try {
            window = (EasyDietWindow) bxmlSerializer.readObject(MainWindow.class.getResource(sMAIN_XML));
        } catch (IOException exception) {
            LOGGER.fatal(exception.toString());
            throw new RuntimeException(exception);
        } catch (SerializationException exception) {
            LOGGER.fatal(exception.toString());
            throw new RuntimeException(exception);
        }

        // Open the Pivot window on the display
        window.open(displayHost.getDisplay());

        // Open and select the internal frame
        sINTERNAL_FRAME.setLocation(0, 0);
        sINTERNAL_FRAME.setSize(sDESKTOP.getWidth(), sDESKTOP.getHeight());
        sINTERNAL_FRAME.setVisible(true);
        sINTERNAL_FRAME.setResizable(false);

        // ensure that this internal frame is selected
        try {
            sINTERNAL_FRAME.setSelected(true);
        } catch (PropertyVetoException exception) {
            LOGGER.fatal(exception.toString());
            throw new RuntimeException(exception);
        }

        // add gui controller to window
        sGUI_Controller.addWindow(window);


        // initally resize
        resize();
    }

    /**
     * Set the patient name in window title
     * @param name
     */
    public static void setPatientName(String name) {
        sMAIN_FRAME.setTitle(sTITLE + name);
    }

    /**
     * Add a new resizable component
     * @param r
     */
    public static void registerResizableComponent(Resizable r) {
        if (!sRESIZABLES.contains(r)) {
            sRESIZABLES.add(r);
        }
    }

    /**
     * Resize internal frame and all registered components
     * with set JDesktop height and width
     */
    private static void resize() {
        sINTERNAL_FRAME.setSize(sDESKTOP.getWidth(), sDESKTOP.getHeight());

        // notify all resizable components
        for(Resizable r : sRESIZABLES){
            r.resize(sDESKTOP.getHeight(), sDESKTOP.getWidth());
        }
    }
}
