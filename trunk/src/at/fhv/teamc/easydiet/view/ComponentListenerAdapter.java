/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: ComponentListenerAdapter.java
 */

package at.fhv.teamc.easydiet.view;

import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentListener;
import org.apache.pivot.wtk.Container;
import org.apache.pivot.wtk.Cursor;
import org.apache.pivot.wtk.DragSource;
import org.apache.pivot.wtk.DropTarget;
import org.apache.pivot.wtk.MenuHandler;

/**
 * Adapter class for ComponentListener
 * @author Michael
 */
public abstract class ComponentListenerAdapter implements ComponentListener {

    public void parentChanged(Component component, Container previousParent) {
        
    }

    public void sizeChanged(Component component, int previousWidth, int previousHeight) {
        
    }

    public void preferredSizeChanged(Component component, int previousPreferredWidth, int previousPreferredHeight) {
        
    }

    public void widthLimitsChanged(Component component, int previousMinimumWidth, int previousMaximumWidth) {
        
    }

    public void heightLimitsChanged(Component component, int previousMinimumHeight, int previousMaximumHeight) {
        
    }

    public void locationChanged(Component component, int previousX, int previousY) {
        
    }

    public void visibleChanged(Component component) {
        
    }

    public void cursorChanged(Component component, Cursor previousCursor) {
        
    }

    public void tooltipTextChanged(Component component, String previousTooltipText) {
        
    }

    public void tooltipDelayChanged(Component component, int previousTooltipDelay) {
        
    }

    public void dragSourceChanged(Component component, DragSource previousDragSource) {
        
    }

    public void dropTargetChanged(Component component, DropTarget previousDropTarget) {
        
    }

    public void menuHandlerChanged(Component component, MenuHandler previousMenuHandler) {
        
    }

    public void nameChanged(Component component, String previousName) {
        
    }

}
