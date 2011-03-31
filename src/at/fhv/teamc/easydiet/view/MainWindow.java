/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: MainWindow.java
 */
package at.fhv.teamc.easydiet.view;

import java.awt.Image;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.Window;

/**
 * MainWindow entry of the application's UI
 * @author Michael
 */
public class MainWindow extends ApplicationContext {

    // class variables
    private static JDesktopPane sDESKTOP;
    private static final String sTITLE;
    private static JFrame sMAIN_FRAME;
    private static final String sMAIN_XML;
    private static boolean sINITALIZED;
    private static int sWINDOW_HEIGHT;
    private static int sWINDOW_WIDTH;

    static {
        sDESKTOP = new JDesktopPane();
        sTITLE = "EasyDiet";
        sMAIN_FRAME = new JFrame(sTITLE);
        sMAIN_XML = "bxml/main.xml";
        sINITALIZED = false;
        sWINDOW_HEIGHT = 768;
        sWINDOW_WIDTH = 1024;

        // Start the callback timer, necessary for pivot
        createTimer();
    }

    /**
     * Initialize GUI
     */
    public static void init() {

        if (!sINITALIZED) {

            // init main frame window
            sMAIN_FRAME.setContentPane(sDESKTOP);
            sMAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sMAIN_FRAME.setSize(sWINDOW_WIDTH, sWINDOW_HEIGHT);
            sMAIN_FRAME.setResizable(false);
            sMAIN_FRAME.setVisible(true);

            // set application icon
            Image icon = new ImageIcon(MainWindow.class.getResource("ico.png")).getImage();
            sMAIN_FRAME.setIconImage(icon);

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

            @Override
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
        JInternalFrame internalFrame = new JInternalFrame();

        // remove title bar
        ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);

        // remove border
        internalFrame.setBorder(null);

        sDESKTOP.add(internalFrame);

        // Create the display host
        ApplicationContext.DisplayHost displayHost = new ApplicationContext.DisplayHost();
        internalFrame.add(displayHost);

        // Add the display to the display list
        displays.add(displayHost.getDisplay());

        // Load the Pivot window
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        Window window;
        try {
            window = (Window) bxmlSerializer.readObject(MainWindow.class.getResource(sMAIN_XML));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } catch (SerializationException exception) {
            throw new RuntimeException(exception);
        }

        // Open the Pivot window on the display
        window.open(displayHost.getDisplay());

        // Open and select the internal frame
        internalFrame.setLocation(0, 0);
        internalFrame.setSize(sDESKTOP.getWidth(), sDESKTOP.getHeight());
        internalFrame.setVisible(true);
        internalFrame.setResizable(false);

        // ensure that this internal frame is selected
        try {
            internalFrame.setSelected(true);
        } catch (PropertyVetoException exception) {
            throw new RuntimeException(exception);
        }
    }
}
