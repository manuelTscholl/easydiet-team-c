/**
 * This File is part of Easy Diet
 * created on: 31.03.2011
 * created by: Michael
 * file: ComponentListenerAdapter.java
 */

package at.easydiet.teamc.view;

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

    @Override
    public void parentChanged(Component component, Container previousParent) {
        
    }

    @Override
    public void sizeChanged(Component component, int previousWidth, int previousHeight) {
        
    }

    @Override
    public void preferredSizeChanged(Component component, int previousPreferredWidth, int previousPreferredHeight) {
        
    }

    @Override
    public void widthLimitsChanged(Component component, int previousMinimumWidth, int previousMaximumWidth) {
        
    }

    @Override
    public void heightLimitsChanged(Component component, int previousMinimumHeight, int previousMaximumHeight) {
        
    }

    @Override
    public void locationChanged(Component component, int previousX, int previousY) {
        
    }

    @Override
    public void visibleChanged(Component component) {
        
    }

    @Override
    public void cursorChanged(Component component, Cursor previousCursor) {
        
    }

    @Override
    public void tooltipTextChanged(Component component, String previousTooltipText) {
        
    }

    @Override
    public void tooltipDelayChanged(Component component, int previousTooltipDelay) {
        
    }

    @Override
    public void dragSourceChanged(Component component, DragSource previousDragSource) {
        
    }

    @Override
    public void dropTargetChanged(Component component, DropTarget previousDropTarget) {
        
    }

    @Override
    public void menuHandlerChanged(Component component, MenuHandler previousMenuHandler) {
        
    }

    @Override
    public void nameChanged(Component component, String previousName) {
        
    }

}
