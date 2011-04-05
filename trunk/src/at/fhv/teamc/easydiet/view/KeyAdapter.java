/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Michael
 * file: KeyAdapter.java
 */

package at.fhv.teamc.easydiet.view;

import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Keyboard.KeyLocation;

/**
 * Adapter for Pivot KeyListener
 * @author Michael
 */
public class KeyAdapter implements ComponentKeyListener {

    public boolean keyTyped(Component component, char character) {
        return true;
    }

    public boolean keyPressed(Component component, int keyCode, KeyLocation keyLocation) {
        return true;
    }

    public boolean keyReleased(Component component, int keyCode, KeyLocation keyLocation) {
        return true;
    }

}
