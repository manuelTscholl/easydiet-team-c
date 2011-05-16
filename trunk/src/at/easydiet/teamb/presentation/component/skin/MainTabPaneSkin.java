/**********************************
 * EasyDiet
 * --------
 * 
 * Original from: http://svn.apache.org/repos/asf/pivot/tags/v2.0/wtk-terra/src/org/apache/pivot/wtk/skin/terra/TerraTabPaneSkin.java
 * Added bug fix for selectedIndexChangeVetoed
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	20.04.2011
 */

package at.easydiet.teamb.presentation.component.skin;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

import org.apache.pivot.collections.Dictionary;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.Bounds;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonGroup;
import org.apache.pivot.wtk.ButtonGroupListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentStateListener;
import org.apache.pivot.wtk.Dimensions;
import org.apache.pivot.wtk.GraphicsUtilities;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Insets;
import org.apache.pivot.wtk.Keyboard;
import org.apache.pivot.wtk.Keyboard.KeyCode;
import org.apache.pivot.wtk.Mouse;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.Panorama;
import org.apache.pivot.wtk.Platform;
import org.apache.pivot.wtk.Point;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TabPaneAttributeListener;
import org.apache.pivot.wtk.TabPaneListener;
import org.apache.pivot.wtk.TabPaneSelectionListener;
import org.apache.pivot.wtk.Theme;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.effects.ClipDecorator;
import org.apache.pivot.wtk.effects.Transition;
import org.apache.pivot.wtk.effects.TransitionListener;
import org.apache.pivot.wtk.effects.easing.Easing;
import org.apache.pivot.wtk.effects.easing.Quadratic;
import org.apache.pivot.wtk.skin.ButtonSkin;
import org.apache.pivot.wtk.skin.ContainerSkin;
import org.apache.pivot.wtk.skin.terra.TerraTheme;

import at.easydiet.teamb.presentation.component.MainTabPane;

/**
 * Tab pane skin.
 */
public class MainTabPaneSkin extends ContainerSkin
    implements TabPaneListener, TabPaneSelectionListener, TabPaneAttributeListener {
    /**
     * Tab button component.
     */
    public class TabButton extends Button {
        
        /** The tab. */
        private final Component tab;

        /**
         * Instantiates a new tab button.
         *
         * @param tab the tab
         */
        public TabButton(Component tab) {
            this.tab = tab;
            super.setToggleButton(true);

            setSkin(new TabButtonSkin());
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#getButtonData()
         */
        @Override
        public Object getButtonData() {
            return TabPane.getTabData(tab);
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#setButtonData(java.lang.Object)
         */
        @Override
        public void setButtonData(Object buttonData) {
            throw new UnsupportedOperationException();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#getDataRenderer()
         */
        @Override
        public Button.DataRenderer getDataRenderer() {
            TabPane tabPane = (TabPane)MainTabPaneSkin.this.getComponent();
            return tabPane.getTabDataRenderer();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#setDataRenderer(org.apache.pivot.wtk.Button.DataRenderer)
         */
        @Override
        public void setDataRenderer(Button.DataRenderer dataRenderer) {
            throw new UnsupportedOperationException();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Component#getTooltipText()
         */
        @Override
        public String getTooltipText() {
            return TabPane.getTooltipText(tab);
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Component#setTooltipText(java.lang.String)
         */
        @Override
        public void setTooltipText(String tooltipText) {
            throw new UnsupportedOperationException();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#setToggleButton(boolean)
         */
        @Override
        public void setToggleButton(boolean toggleButton) {
            throw new UnsupportedOperationException();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#setTriState(boolean)
         */
        @Override
        public void setTriState(boolean triState) {
            throw new UnsupportedOperationException();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Button#press()
         */
        @Override
        public void press() {
            // If the tab pane is collapsible, toggle the button selection;
            // otherwise, select it
            TabPane tabPane = (TabPane)MainTabPaneSkin.this.getComponent();
            setSelected(tabPane.isCollapsible() ? !isSelected() : true);
            super.press();
        }
    }

    /**
     * Tab button skin.
     * <p>
     * Note that this class does not respect preferred size constraints,
     * because it will never be called to use them.
     */
    public class TabButtonSkin extends ButtonSkin {
        
        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.ConstrainedVisual#getPreferredWidth(int)
         */
        @Override
        public int getPreferredWidth(int height) {
            Dimensions preferredSize = getPreferredSize();
            return preferredSize.width;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.ConstrainedVisual#getPreferredHeight(int)
         */
        @Override
        public int getPreferredHeight(int width) {
            Dimensions preferredSize = getPreferredSize();
            return preferredSize.height;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.skin.ComponentSkin#getPreferredSize()
         */
        @Override
        public Dimensions getPreferredSize() {
            TabButton tabButton = (TabButton)getComponent();
            TabPane tabPane = (TabPane)MainTabPaneSkin.this.getComponent();

            Button.DataRenderer dataRenderer = tabButton.getDataRenderer();
            dataRenderer.render(tabButton.getButtonData(), tabButton, false);

            Dimensions preferredContentSize = dataRenderer.getPreferredSize();

            int preferredWidth = 0;
            int preferredHeight = 0;
            switch (tabOrientation) {
                case HORIZONTAL: {
                    preferredWidth = preferredContentSize.width
                        + buttonPadding.left + buttonPadding.right + 2;

                    preferredHeight = preferredContentSize.height
                        + buttonPadding.top + buttonPadding.bottom + 2;

                    if (tabPane.isCloseable()
                        && tabButton.isSelected()) {
                        preferredWidth += CLOSE_TRIGGER_SIZE + buttonSpacing;
                    }

                    break;
                }

                case VERTICAL: {
                    preferredWidth = preferredContentSize.height
                        + buttonPadding.top + buttonPadding.bottom + 2;

                    preferredHeight = preferredContentSize.width
                        + buttonPadding.left + buttonPadding.right + 2;

                    if (tabPane.isCloseable()
                        && tabButton.isSelected()) {
                        preferredHeight += CLOSE_TRIGGER_SIZE + buttonSpacing;
                    }

                    break;
                }
            }

            Dimensions preferredSize = new Dimensions(preferredWidth, preferredHeight);
            return preferredSize;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.skin.ComponentSkin#getBaseline(int, int)
         */
        @Override
        public int getBaseline(int width, int height) {
            TabButton tabButton = (TabButton)getComponent();

            Button.DataRenderer dataRenderer = tabButton.getDataRenderer();
            dataRenderer.render(tabButton.getButtonData(), tabButton, false);

            int clientWidth = Math.max(width - (buttonPadding.left + buttonPadding.right + 2), 0);
            int clientHeight = Math.max(height - (buttonPadding.top + buttonPadding.bottom + 2), 0);

            int baseline = dataRenderer.getBaseline(clientWidth, clientHeight);

            if (baseline != -1) {
                baseline += buttonPadding.top + 1;
            }

            return baseline;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.Visual#paint(java.awt.Graphics2D)
         */
        @Override
        public void paint(Graphics2D graphics) {
            TabButton tabButton = (TabButton)getComponent();
            TabPane tabPane = (TabPane)MainTabPaneSkin.this.getComponent();

            boolean active = (selectionChangeTransition != null
                && selectionChangeTransition.getTab() == tabButton.tab);

            Color backgroundColor, buttonBevelColor;
            if (tabButton.isSelected()
                || active) {
                backgroundColor = activeTabColor;
                buttonBevelColor = activeButtonBevelColor;
            } else {
                backgroundColor = inactiveTabColor;
                buttonBevelColor = inactiveButtonBevelColor;
            }

            int width = getWidth();
            int height = getHeight();

            // Draw the background
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

            switch(tabOrientation) {
                case HORIZONTAL: {
                    graphics.setPaint(new GradientPaint(width / 2f, 0, buttonBevelColor,
                        width / 2f, height / 2f, backgroundColor));
                    graphics.fill(new RoundRectangle2D.Double(0.5, 0.5, width - 1, height - 1 + CORNER_RADIUS,
                        CORNER_RADIUS, CORNER_RADIUS));
                    break;
                }

                case VERTICAL: {
                    graphics.setPaint(new GradientPaint(0, height / 2f, buttonBevelColor,
                        width / 2f, height / 2f, backgroundColor));
                    graphics.fill(new RoundRectangle2D.Double(0.5, 0.5, width - 1 + CORNER_RADIUS, height - 1,
                        CORNER_RADIUS, CORNER_RADIUS));
                    break;
                }
            }

            // Draw the border
            graphics.setPaint(borderColor);
            graphics.setStroke(new BasicStroke(1));

            switch(tabOrientation) {
                case HORIZONTAL: {
                    graphics.draw(new RoundRectangle2D.Double(0.5, 0.5, width - 1, height + CORNER_RADIUS - 1,
                        CORNER_RADIUS, CORNER_RADIUS));
                    break;
                }

                case VERTICAL: {
                    graphics.draw(new RoundRectangle2D.Double(0.5, 0.5, width + CORNER_RADIUS - 1, height - 1,
                        CORNER_RADIUS, CORNER_RADIUS));
                    break;
                }
            }

            if (!(tabButton.isSelected()
                || active)) {
                // Draw divider
                switch(tabOrientation) {
                    case HORIZONTAL: {
                        graphics.draw(new Line2D.Double(0.5, height - 0.5, width - 0.5, height - 0.5));
                        break;
                    }

                    case VERTICAL: {
                        graphics.draw(new Line2D.Double(width - 0.5, 0.5, width - 0.5, height - 0.5));
                        break;
                    }
                }
            }

            // Paint the content
            Button.DataRenderer dataRenderer = tabButton.getDataRenderer();
            dataRenderer.render(tabButton.getButtonData(), tabButton, false);

            Graphics2D contentGraphics = (Graphics2D)graphics.create();
            contentGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

            switch (tabOrientation) {
                case HORIZONTAL: {
                    int contentWidth = getWidth() - (buttonPadding.left + buttonPadding.right + 2);
                    if (tabPane.isCloseable()
                        && tabButton.isSelected()) {
                        contentWidth -= (CLOSE_TRIGGER_SIZE + buttonSpacing);
                    }

                    dataRenderer.setSize(Math.max(contentWidth, 0),
                        Math.max(getHeight() - (buttonPadding.top + buttonPadding.bottom + 2), 0));

                    contentGraphics.translate(buttonPadding.left + 1, buttonPadding.top + 1);

                    break;
                }

                case VERTICAL: {
                    int contentWidth = getHeight() - (buttonPadding.top + buttonPadding.bottom + 2);
                    if (tabPane.isCloseable()
                        && tabButton.isSelected()) {
                        contentWidth -= (CLOSE_TRIGGER_SIZE + buttonSpacing);
                    }

                    dataRenderer.setSize(Math.max(contentWidth, 0),
                        Math.max(getWidth() - (buttonPadding.left + buttonPadding.right + 2), 0));

                    contentGraphics.translate(buttonPadding.top + 1, buttonPadding.left + 1);
                    contentGraphics.rotate(-Math.PI / 2d);
                    contentGraphics.translate(-dataRenderer.getWidth(), 0);

                    break;
                }
            }

            contentGraphics.clipRect(0, 0, dataRenderer.getWidth(), dataRenderer.getHeight());
            dataRenderer.paint(contentGraphics);

            contentGraphics.dispose();

            // Draw the close trigger
            if (tabPane.isCloseable()
                && tabButton.isSelected()) {
                graphics.setStroke(new BasicStroke(2.5f));

                int x = 0;
                int y = 0;
                switch (tabOrientation) {
                    case HORIZONTAL: {
                        x = width - (buttonPadding.right + CLOSE_TRIGGER_SIZE + 1);
                        y = (height - CLOSE_TRIGGER_SIZE) / 2;
                        break;
                    }

                    case VERTICAL: {
                        x = (width - CLOSE_TRIGGER_SIZE) / 2;
                        y = height - (buttonPadding.bottom + CLOSE_TRIGGER_SIZE + 1);
                        break;
                    }
                }

                graphics.draw(new Line2D.Double(x, y, x + CLOSE_TRIGGER_SIZE - 1, y + CLOSE_TRIGGER_SIZE - 1));
                graphics.draw(new Line2D.Double(x, y + CLOSE_TRIGGER_SIZE - 1, x + CLOSE_TRIGGER_SIZE - 1, y));
            }
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.skin.ComponentSkin#isFocusable()
         */
        @Override
        public boolean isFocusable() {
            return false;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.skin.ComponentSkin#mouseClick(org.apache.pivot.wtk.Component, org.apache.pivot.wtk.Mouse.Button, int, int, int)
         */
        @Override
        public boolean mouseClick(Component component, Mouse.Button button, int x, int y, int count) {
            boolean consumed = super.mouseClick(component, button, x, y, count);

            TabButton tabButton = (TabButton)getComponent();
            TabPane tabPane = (TabPane)MainTabPaneSkin.this.getComponent();

            if (tabPane.isCloseable()
                && tabButton.isSelected()
                && getCloseTriggerBounds().contains(x, y)) {
                tabPane.getTabs().remove(tabButton.tab);
            } else {
                tabButton.press();
            }

            return consumed;
        }

        /**
         * Gets the font.
         *
         * @return the font
         */
        public Font getFont() {
            return buttonFont;
        }

        /**
         * Gets the color.
         *
         * @return the color
         */
        public Color getColor() {
            return buttonColor;
        }

        /**
         * Gets the disabled color.
         *
         * @return the disabled color
         */
        public Color getDisabledColor() {
            return disabledButtonColor;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.skin.ButtonSkin#stateChanged(org.apache.pivot.wtk.Button, org.apache.pivot.wtk.Button.State)
         */
        @Override
        public void stateChanged(Button button, Button.State previousState) {
            super.stateChanged(button, previousState);
            invalidateComponent();
        }

        /**
         * Gets the close trigger bounds.
         *
         * @return the close trigger bounds
         */
        public Bounds getCloseTriggerBounds() {
            Bounds bounds = null;

            // Include an extra 2 pixels around the trigger for ease of use
            switch (tabOrientation) {
                case HORIZONTAL: {
                    bounds = new Bounds(getWidth() - (CLOSE_TRIGGER_SIZE + buttonPadding.right + 1) - 2,
                        (getHeight() - CLOSE_TRIGGER_SIZE) / 2 - 2,
                        CLOSE_TRIGGER_SIZE + 4, CLOSE_TRIGGER_SIZE + 4);
                    break;
                }

                case VERTICAL: {
                    bounds = new Bounds((getWidth() - CLOSE_TRIGGER_SIZE) / 2 - 2,
                        getHeight() - (CLOSE_TRIGGER_SIZE + buttonPadding.bottom + 1) - 2,
                        CLOSE_TRIGGER_SIZE + 4, CLOSE_TRIGGER_SIZE + 4);
                    break;
                }
            }

            return bounds;
        }
    }

    /**
     * Selection change transition.
     */
    public class SelectionChangeTransition extends Transition {
        
        /** The index. */
        public final int index;
        
        /** The expand. */
        public final boolean expand;

        /** The easing. */
        private Easing easing = new Quadratic();

        /**
         * Instantiates a new selection change transition.
         *
         * @param index the index
         * @param expand the expand
         */
        public SelectionChangeTransition(int index, boolean expand) {
            super(selectionChangeDuration, selectionChangeRate, false);

            this.index = index;
            this.expand = expand;
        }

        /**
         * Gets the tab.
         *
         * @return the tab
         */
        public Component getTab() {
            TabPane tabPane = (TabPane)getComponent();
            return tabPane.getTabs().get(index);
        }

        /**
         * Gets the scale.
         *
         * @return the scale
         */
        public float getScale() {
            int elapsedTime = getElapsedTime();
            int duration = getDuration();

            float scale;
            if (expand) {
                scale = easing.easeOut(elapsedTime, 0, 1, duration);
            } else {
                scale = easing.easeIn(elapsedTime, 1, -1, duration);
            }

            return scale;
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.effects.Transition#start(org.apache.pivot.wtk.effects.TransitionListener)
         */
        @Override
        public void start(TransitionListener transitionListener) {
            TabPane tabPane = (TabPane)getComponent();

            if (expand) {
                getTab().setVisible(true);
            }

            getTab().getDecorators().add(clipDecorator);
            tabPane.setEnabled(false);

            super.start(transitionListener);
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.effects.Transition#stop()
         */
        @Override
        public void stop() {
            TabPane tabPane = (TabPane)getComponent();

            if (!expand) {
                getTab().setVisible(false);
            }

            getTab().getDecorators().remove(clipDecorator);
            tabPane.setEnabled(true);

            super.stop();
        }

        /* (non-Javadoc)
         * @see org.apache.pivot.wtk.effects.Transition#update()
         */
        @Override
        protected void update() {
            invalidateComponent();
        }
    }

    /** The tab button panorama. */
    private Panorama tabButtonPanorama = new Panorama();
    
    /** The tab button box pane. */
    private BoxPane tabButtonBoxPane = new BoxPane();
    
    /** The tab button group. */
    private ButtonGroup tabButtonGroup = new ButtonGroup();

    /** The active tab color. */
    private Color activeTabColor;
    
    /** The inactive tab color. */
    private Color inactiveTabColor;
    
    /** The border color. */
    private Color borderColor;
    
    /** The padding. */
    private Insets padding;
    
    /** The button font. */
    private Font buttonFont;
    
    /** The button color. */
    private Color buttonColor;
    
    /** The disabled button color. */
    private Color disabledButtonColor;
    
    /** The button padding. */
    private Insets buttonPadding;
    
    /** The button spacing. */
    private int buttonSpacing;

    /** The active button bevel color. */
    private Color activeButtonBevelColor;
    
    /** The inactive button bevel color. */
    private Color inactiveButtonBevelColor;

    /** The tab orientation. */
    private Orientation tabOrientation = Orientation.HORIZONTAL;

    /** The selection change duration. */
    private int selectionChangeDuration = DEFAULT_SELECTION_CHANGE_DURATION;
    
    /** The selection change rate. */
    private int selectionChangeRate = DEFAULT_SELECTION_CHANGE_RATE;

    /** The selection change transition. */
    private SelectionChangeTransition selectionChangeTransition = null;
    
    /** The clip decorator. */
    private ClipDecorator clipDecorator = new ClipDecorator();

    /** The tab state listener. */
    private ComponentStateListener tabStateListener = new ComponentStateListener.Adapter() {
        @Override
        public void enabledChanged(Component component) {
            TabPane tabPane = (TabPane)getComponent();
            int i = tabPane.getTabs().indexOf(component);
            tabButtonBoxPane.get(i).setEnabled(component.isEnabled());
        }
    };


    /** The Constant CORNER_RADIUS. */
    public static final int CORNER_RADIUS = 4;
    
    /** The Constant GRADIENT_BEVEL_THICKNESS. */
    public static final int GRADIENT_BEVEL_THICKNESS = 8;
    
    /** The Constant CLOSE_TRIGGER_SIZE. */
    private static final int CLOSE_TRIGGER_SIZE = 6;
    
    /** The Constant DEFAULT_SELECTION_CHANGE_DURATION. */
    private static final int DEFAULT_SELECTION_CHANGE_DURATION = 250;
    
    /** The Constant DEFAULT_SELECTION_CHANGE_RATE. */
    private static final int DEFAULT_SELECTION_CHANGE_RATE = 30;

    /**
     * Instantiates a new main tab pane skin.
     */
    public MainTabPaneSkin() {
        TerraTheme theme = (TerraTheme)Theme.getTheme();
        activeTabColor = theme.getColor(11);
        inactiveTabColor = theme.getColor(9);
        borderColor = theme.getColor(7);
        padding = new Insets(6);
        buttonFont = theme.getFont();
        buttonColor = theme.getColor(1);
        disabledButtonColor = theme.getColor(7);
        buttonPadding = new Insets(3, 4, 3, 4);
        buttonSpacing = 6;

        activeButtonBevelColor = TerraTheme.brighten(activeTabColor);
        inactiveButtonBevelColor = TerraTheme.brighten(inactiveTabColor);

        tabButtonBoxPane.getStyles().put("fill", true);

        tabButtonPanorama.getStyles().put("buttonBackgroundColor", borderColor);
        tabButtonPanorama.getStyles().put("buttonPadding", 6);
        tabButtonPanorama.setView(tabButtonBoxPane);

        tabButtonGroup.getButtonGroupListeners().add(new ButtonGroupListener.Adapter() {
            @Override
            public void selectionChanged(ButtonGroup buttonGroup, Button previousSelection) {
                Button button = tabButtonGroup.getSelection();
                int index = (button == null) ? -1 : tabButtonBoxPane.indexOf(button);

                TabPane tabPane = (TabPane)getComponent();
                tabPane.setSelectedIndex(index);
            }
        });

        setButtonSpacing(2);
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.skin.ContainerSkin#install(org.apache.pivot.wtk.Component)
     */
    @Override
    public void install(Component component) {
        super.install(component);

        TabPane tabPane = (TabPane)component;

        // Add this as a listener on the tab pane
        tabPane.getTabPaneListeners().add(this);
        tabPane.getTabPaneSelectionListeners().add(this);
        tabPane.getTabPaneAttributeListeners().add(this);

        // Add the tab button container
        tabPane.add(tabButtonPanorama);
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.skin.ContainerSkin#getPreferredWidth(int)
     */
    @Override
    public int getPreferredWidth(int height) {
        int preferredWidth = 0;

        TabPane tabPane = (TabPane)getComponent();

        Component selectedTab = tabPane.getSelectedTab();
        Component corner = tabPane.getCorner();

        switch (tabOrientation) {
            case HORIZONTAL: {
                if (height != -1) {
                    if (corner != null) {
                        height = Math.max(height - Math.max(corner.getPreferredHeight(-1),
                            Math.max(tabButtonPanorama.getPreferredHeight(-1) - 1, 0)), 0);
                    } else {
                        height = Math.max(height - (tabButtonPanorama.getPreferredHeight(-1) - 1), 0);
                    }

                    height = Math.max(height - (padding.top + padding.bottom + 2), 0);
                }

                preferredWidth = getPreferredTabWidth(height) + (padding.left + padding.right + 2);

                int buttonAreaPreferredWidth = tabButtonPanorama.getPreferredWidth(-1);
                if (corner != null) {
                    buttonAreaPreferredWidth += corner.getPreferredWidth(-1);
                }

                preferredWidth = Math.max(preferredWidth, buttonAreaPreferredWidth);

                break;
            }

            case VERTICAL: {
                if (height != -1) {
                    height = Math.max(height - (padding.top + padding.bottom + 2), 0);
                }

                if (selectedTab == null
                    && selectionChangeTransition == null) {
                    preferredWidth = 1;
                } else {
                    preferredWidth = getPreferredTabWidth(height) + (padding.left + padding.right);

                    if (selectionChangeTransition != null) {
                        float scale = selectionChangeTransition.getScale();
                        preferredWidth = (int)(preferredWidth * scale);
                    }

                    preferredWidth += 2;
                }

                if (corner != null) {
                    preferredWidth += Math.max(corner.getPreferredWidth(-1),
                        Math.max(tabButtonPanorama.getPreferredWidth(-1) - 1, 0));
                } else {
                    preferredWidth += Math.max(tabButtonPanorama.getPreferredWidth(-1) - 1, 0);
                }

                break;
            }
        }

        return preferredWidth;
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.skin.ContainerSkin#getPreferredHeight(int)
     */
    @Override
    public int getPreferredHeight(int width) {
        int preferredHeight = 0;

        TabPane tabPane = (TabPane)getComponent();

        Component selectedTab = tabPane.getSelectedTab();
        Component corner = tabPane.getCorner();

        switch (tabOrientation) {
            case HORIZONTAL: {
                if (width != -1) {
                    width = Math.max(width - (padding.left + padding.right + 2), 0);
                }

                if (selectedTab == null
                    && selectionChangeTransition == null) {
                    preferredHeight = 1;
                } else {
                    preferredHeight = getPreferredTabHeight(width) + (padding.top + padding.bottom);

                    if (selectionChangeTransition != null) {
                        float scale = selectionChangeTransition.getScale();
                        preferredHeight = (int)(preferredHeight * scale);
                    }

                    preferredHeight += 2;
                }

                if (corner != null) {
                    preferredHeight += Math.max(corner.getPreferredHeight(-1),
                        Math.max(tabButtonPanorama.getPreferredHeight(-1) - 1, 0));
                } else {
                    preferredHeight += Math.max(tabButtonPanorama.getPreferredHeight(-1) - 1, 0);
                }

                break;
            }

            case VERTICAL: {
                if (width != -1) {
                    if (corner != null) {
                        width = Math.max(width - Math.max(corner.getPreferredWidth(-1),
                            Math.max(tabButtonPanorama.getPreferredWidth(-1) - 1, 0)), 0);
                    } else {
                        width = Math.max(width - (tabButtonPanorama.getPreferredWidth(-1) - 1), 0);
                    }

                    width = Math.max(width - (padding.left + padding.right + 2), 0);
                }

                preferredHeight = getPreferredTabHeight(width) + (padding.top + padding.bottom + 2);

                int buttonAreaPreferredHeight = tabButtonPanorama.getPreferredHeight(-1);
                if (corner != null) {
                    buttonAreaPreferredHeight += corner.getPreferredHeight(-1);
                }

                preferredHeight = Math.max(preferredHeight, buttonAreaPreferredHeight);

                break;
            }
        }

        return preferredHeight;
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.skin.ComponentSkin#getPreferredSize()
     */
    @Override
    public Dimensions getPreferredSize() {
        TabPane tabPane = (TabPane)getComponent();

        int preferredWidth;
        int preferredHeight;

        Component selectedTab = tabPane.getSelectedTab();
        Component corner = tabPane.getCorner();

        switch (tabOrientation) {
            case HORIZONTAL: {
                if (selectedTab == null
                    && selectionChangeTransition == null) {
                    preferredWidth = getPreferredTabWidth(-1) + (padding.left + padding.right + 2);
                    preferredHeight = 1;
                } else {
                    Dimensions preferredTabSize = getPreferredTabSize();
                    preferredWidth = preferredTabSize.width + (padding.left + padding.right + 2);
                    preferredHeight = preferredTabSize.height + (padding.top + padding.bottom);

                    if (selectionChangeTransition != null) {
                        float scale = selectionChangeTransition.getScale();
                        preferredHeight = (int)(preferredHeight * scale);
                    }

                    preferredHeight += 2;
                }

                int buttonAreaPreferredWidth = tabButtonPanorama.getPreferredWidth(-1);
                if (corner != null) {
                    buttonAreaPreferredWidth += corner.getPreferredWidth(-1);
                    preferredHeight += Math.max(corner.getPreferredHeight(-1),
                        Math.max(tabButtonPanorama.getPreferredHeight(-1) - 1, 0));
                } else {
                    preferredHeight += Math.max(tabButtonPanorama.getPreferredHeight(-1) - 1, 0);
                }

                preferredWidth = Math.max(preferredWidth, buttonAreaPreferredWidth);

                break;
            }

            case VERTICAL: {
                if (selectedTab == null
                    && selectionChangeTransition == null) {
                    preferredWidth = 1;
                    preferredHeight = getPreferredTabHeight(-1) + (padding.top + padding.bottom + 2);
                } else {
                    Dimensions preferredTabSize = getPreferredTabSize();

                    preferredWidth = preferredTabSize.width + (padding.left + padding.right);
                    preferredHeight = preferredTabSize.height + (padding.top + padding.bottom + 2);

                    if (selectionChangeTransition != null) {
                        float scale = selectionChangeTransition.getScale();
                        preferredWidth = (int)(preferredWidth * scale);
                    }

                    preferredWidth += 2;
                }

                int buttonAreaPreferredHeight = tabButtonPanorama.getPreferredHeight(-1);
                if (corner != null) {
                    preferredWidth += Math.max(corner.getPreferredWidth(-1),
                        Math.max(tabButtonPanorama.getPreferredWidth(-1) - 1, 0));
                    buttonAreaPreferredHeight += corner.getPreferredHeight(-1);
                } else {
                    preferredWidth += Math.max(tabButtonPanorama.getPreferredWidth(-1) - 1, 0);
                }

                preferredHeight = Math.max(preferredHeight, buttonAreaPreferredHeight);

                break;
            }

            default: {
                preferredWidth = 0;
                preferredHeight = 0;
            }
        }

        return new Dimensions(preferredWidth, preferredHeight);
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.skin.ComponentSkin#getBaseline(int, int)
     */
    @Override
    public int getBaseline(int width, int height) {
        int baseline = -1;

        if (tabOrientation == Orientation.HORIZONTAL
            && tabButtonBoxPane.getLength() > 0) {
            TabButton firstTabButton = (TabButton)tabButtonBoxPane.get(0);

            int buttonHeight = tabButtonBoxPane.getPreferredHeight();
            baseline = firstTabButton.getBaseline(firstTabButton.getPreferredWidth(buttonHeight),
                buttonHeight);
        }

        return baseline;
    }

    /**
     * Gets the preferred tab width.
     *
     * @param height the height
     * @return the preferred tab width
     */
    private int getPreferredTabWidth(int height) {
        int preferredTabWidth = 0;

        TabPane tabPane = (TabPane)getComponent();
        for (Component tab : tabPane.getTabs()) {
            preferredTabWidth = Math.max(preferredTabWidth, tab.getPreferredWidth(height));
        }

        return preferredTabWidth;
    }

    /**
     * Gets the preferred tab height.
     *
     * @param width the width
     * @return the preferred tab height
     */
    private int getPreferredTabHeight(int width) {
        int preferredTabHeight = 0;

        TabPane tabPane = (TabPane)getComponent();
        for (Component tab : tabPane.getTabs()) {
            preferredTabHeight = Math.max(preferredTabHeight, tab.getPreferredHeight(width));
        }

        return preferredTabHeight;
    }

    /**
     * Gets the preferred tab size.
     *
     * @return the preferred tab size
     */
    private Dimensions getPreferredTabSize() {
        int preferredTabWidth = 0;
        int preferredTabHeight = 0;

        TabPane tabPane = (TabPane)getComponent();
        for (Component tab : tabPane.getTabs()) {
            Dimensions preferredSize = tab.getPreferredSize();
            preferredTabWidth = Math.max(preferredTabWidth, preferredSize.width);
            preferredTabHeight = Math.max(preferredTabHeight, preferredSize.height);
        }

        return new Dimensions(preferredTabWidth, preferredTabHeight);
    }


    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.Skin#layout()
     */
    @Override
    public void layout() {
        TabPane tabPane = (TabPane)getComponent();
        int width = getWidth();
        int height = getHeight();

        int tabX = 0;
        int tabY = 0;
        int tabWidth = 0;
        int tabHeight = 0;

        Component corner = tabPane.getCorner();
        Dimensions buttonPanoramaSize = tabButtonPanorama.getPreferredSize();

        switch (tabOrientation) {
            case HORIZONTAL: {
                int buttonPanoramaWidth = Math.min(width, buttonPanoramaSize.width);
                int buttonPanoramaHeight = buttonPanoramaSize.height;
                int buttonPanoramaY = 0;

                if (corner != null) {
                    int cornerWidth = width - buttonPanoramaWidth;
                    int cornerHeight = Math.max(corner.getPreferredHeight(-1), buttonPanoramaSize.height - 1);
                    int cornerX = buttonPanoramaWidth;
                    int cornerY = Math.max(buttonPanoramaHeight - cornerHeight - 1, 0);

                    buttonPanoramaY = Math.max(cornerHeight - buttonPanoramaHeight + 1, 0);

                    corner.setLocation(cornerX, cornerY);
                    corner.setSize(cornerWidth, cornerHeight);
                }

                tabButtonPanorama.setLocation(0, buttonPanoramaY);
                tabButtonPanorama.setSize(buttonPanoramaWidth, buttonPanoramaHeight);

                tabX = padding.left + 1;
                tabY = padding.top + buttonPanoramaY + buttonPanoramaHeight;

                tabWidth = Math.max(width - (padding.left + padding.right + 2), 0);
                tabHeight = Math.max(height - (padding.top + padding.bottom
                    + buttonPanoramaY + buttonPanoramaHeight + 1), 0);

                break;
            }

            case VERTICAL: {
                int buttonPanoramaWidth = buttonPanoramaSize.width;
                int buttonPanoramaHeight = Math.min(height,
                    buttonPanoramaSize.height);
                int buttonPanoramaX = 0;

                if (corner != null) {
                    int cornerWidth = corner.getPreferredWidth(-1);
                    int cornerHeight = height - buttonPanoramaHeight;
                    int cornerX = Math.max(buttonPanoramaWidth - cornerWidth - 1, 0);
                    int cornerY = buttonPanoramaHeight;

                    buttonPanoramaX = Math.max(cornerWidth - buttonPanoramaWidth + 1, 0);

                    corner.setLocation(cornerX, cornerY);
                    corner.setSize(cornerWidth, cornerHeight);
                }

                tabButtonPanorama.setLocation(buttonPanoramaX, 0);
                tabButtonPanorama.setSize(buttonPanoramaWidth, buttonPanoramaHeight);

                tabX = padding.left + buttonPanoramaX + buttonPanoramaWidth;
                tabY = padding.top + 1;
                tabWidth = Math.max(width - (padding.left + padding.right
                    + buttonPanoramaX + buttonPanoramaWidth + 1), 0);
                tabHeight = Math.max(height - (padding.top + padding.bottom + 2), 0);

                break;
            }
        }

        // Lay out the tabs
        for (Component tab : tabPane.getTabs()) {
            tab.setLocation(tabX, tabY);

            if (selectionChangeTransition != null
                && selectionChangeTransition.isRunning()) {
                clipDecorator.setSize(tabWidth, tabHeight);

                switch (tabOrientation) {
                    case HORIZONTAL: {
                        tab.setSize(tabWidth, getPreferredTabHeight(tabWidth));
                        break;
                    }

                    case VERTICAL: {
                        tab.setSize(getPreferredTabWidth(tabHeight), tabHeight);
                        break;
                    }
                }
            } else {
                tab.setSize(tabWidth, tabHeight);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.skin.ContainerSkin#paint(java.awt.Graphics2D)
     */
    @Override
    public void paint(Graphics2D graphics) {
        TabPane tabPane = (TabPane)getComponent();

        Bounds tabPaneBounds = tabPane.getBounds();

        // Call the base class to paint the background
        super.paint(graphics);

        // Paint the content background and border
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        switch (tabOrientation) {
            case HORIZONTAL: {
                x = 0;
                y = Math.max(tabButtonPanorama.getY() + tabButtonPanorama.getHeight() - 1, 0);
                width = tabPaneBounds.width;
                height = Math.max(tabPaneBounds.height - y, 0);

                break;
            }

            case VERTICAL: {
                x = Math.max(tabButtonPanorama.getX() + tabButtonPanorama.getWidth() - 1, 0);
                y = 0;
                width = Math.max(tabPaneBounds.width - x, 0);
                height = tabPaneBounds.height;

                break;
            }
        }

        TabButton activeTabButton;
        if (selectionChangeTransition == null) {
            activeTabButton = (TabButton)tabButtonGroup.getSelection();
        } else {
            activeTabButton = (TabButton)tabButtonBoxPane.get(selectionChangeTransition.index);
        }

        if (activeTabButton != null) {
            Bounds contentBounds = new Bounds(x, y, width, height);

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

            // Paint the background
            graphics.setPaint(activeTabColor);
            graphics.fillRect(contentBounds.x, contentBounds.y,
                contentBounds.width, contentBounds.height);

            // Draw the border
            double top = contentBounds.y + 0.5;
            double left = contentBounds.x + 0.5;
            double bottom = top + contentBounds.height - 1;
            double right = left + contentBounds.width - 1;

            graphics.setPaint(borderColor);

            // Draw the right and bottom borders
            graphics.draw(new Line2D.Double(right, top, right, bottom));
            graphics.draw(new Line2D.Double(left, bottom, right, bottom));

            // Draw the left and top borders
            switch (tabOrientation) {
                case HORIZONTAL: {
                    graphics.draw(new Line2D.Double(left, top, left, bottom));

                    Point selectedTabButtonLocation = activeTabButton.mapPointToAncestor(tabPane, 0, 0);
                    graphics.draw(new Line2D.Double(left, top, selectedTabButtonLocation.x + 0.5, top));
                    graphics.draw(new Line2D.Double(selectedTabButtonLocation.x + activeTabButton.getWidth() - 0.5,
                        top, right, top));

                    break;
                }

                case VERTICAL: {
                    graphics.draw(new Line2D.Double(left, top, right, top));

                    Point selectedTabButtonLocation = activeTabButton.mapPointToAncestor(tabPane, 0, 0);
                    graphics.draw(new Line2D.Double(left, top, left, selectedTabButtonLocation.y + 0.5));
                    graphics.draw(new Line2D.Double(left, selectedTabButtonLocation.y + activeTabButton.getHeight() - 0.5,
                        left, bottom));

                    break;
                }
            }
        }
    }

    /**
     * Gets the active tab color.
     *
     * @return the active tab color
     */
    public Color getActiveTabColor() {
        return activeTabColor;
    }

    /**
     * Sets the active tab color.
     *
     * @param activeTabColor the new active tab color
     */
    public void setActiveTabColor(Color activeTabColor) {
        if (activeTabColor == null) {
            throw new IllegalArgumentException("activeTabColor is null.");
        }

        this.activeTabColor = activeTabColor;
        activeButtonBevelColor = TerraTheme.brighten(activeTabColor);
        repaintComponent();
    }

    /**
     * Sets the active tab color.
     *
     * @param activeTabColor the new active tab color
     */
    public final void setActiveTabColor(String activeTabColor) {
        if (activeTabColor == null) {
            throw new IllegalArgumentException("activeTabColor is null.");
        }

        setActiveTabColor(GraphicsUtilities.decodeColor(activeTabColor));
    }

    /**
     * Sets the active tab color.
     *
     * @param activeTabColor the new active tab color
     */
    public final void setActiveTabColor(int activeTabColor) {
        TerraTheme theme = (TerraTheme)Theme.getTheme();
        setActiveTabColor(theme.getColor(activeTabColor));
    }

    /**
     * Gets the inactive tab color.
     *
     * @return the inactive tab color
     */
    public Color getInactiveTabColor() {
        return inactiveTabColor;
    }

    /**
     * Sets the inactive tab color.
     *
     * @param inactiveTabColor the new inactive tab color
     */
    public void setInactiveTabColor(Color inactiveTabColor) {
        if (inactiveTabColor == null) {
            throw new IllegalArgumentException("inactiveTabColor is null.");
        }

        this.inactiveTabColor = inactiveTabColor;
        inactiveButtonBevelColor = TerraTheme.brighten(inactiveTabColor);
        repaintComponent();
    }

    /**
     * Sets the inactive tab color.
     *
     * @param inactiveTabColor the new inactive tab color
     */
    public final void setInactiveTabColor(String inactiveTabColor) {
        if (inactiveTabColor == null) {
            throw new IllegalArgumentException("inactiveTabColor is null.");
        }

        setInactiveTabColor(GraphicsUtilities.decodeColor(inactiveTabColor));
    }

    /**
     * Sets the inactive tab color.
     *
     * @param inactiveTabColor the new inactive tab color
     */
    public final void setInactiveTabColor(int inactiveTabColor) {
        TerraTheme theme = (TerraTheme)Theme.getTheme();
        setInactiveTabColor(theme.getColor(inactiveTabColor));
    }

    /**
     * Gets the border color.
     *
     * @return the border color
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the border color.
     *
     * @param borderColor the new border color
     */
    public void setBorderColor(Color borderColor) {
        if (borderColor == null) {
            throw new IllegalArgumentException("borderColor is null.");
        }

        this.borderColor = borderColor;
        tabButtonPanorama.getStyles().put("buttonBackgroundColor", borderColor);
        repaintComponent();
    }

    /**
     * Sets the border color.
     *
     * @param borderColor the new border color
     */
    public final void setBorderColor(String borderColor) {
        if (borderColor == null) {
            throw new IllegalArgumentException("borderColor is null.");
        }

        setBorderColor(GraphicsUtilities.decodeColor(borderColor));
    }

    /**
     * Sets the border color.
     *
     * @param borderColor the new border color
     */
    public final void setBorderColor(int borderColor) {
        TerraTheme theme = (TerraTheme)Theme.getTheme();
        setBorderColor(theme.getColor(borderColor));
    }

    /**
     * Gets the padding.
     *
     * @return the padding
     */
    public Insets getPadding() {
        return padding;
    }

    /**
     * Sets the padding.
     *
     * @param padding the new padding
     */
    public void setPadding(Insets padding) {
        if (padding == null) {
            throw new IllegalArgumentException("padding is null.");
        }

        this.padding = padding;
        invalidateComponent();
    }

    /**
     * Sets the padding.
     *
     * @param padding the padding
     */
    public final void setPadding(Dictionary<String, ?> padding) {
        if (padding == null) {
            throw new IllegalArgumentException("padding is null.");
        }

        setPadding(new Insets(padding));
    }

    /**
     * Sets the padding.
     *
     * @param padding the new padding
     */
    public final void setPadding(int padding) {
        setPadding(new Insets(padding));
    }

    /**
     * Sets the padding.
     *
     * @param padding the new padding
     */
    public final void setPadding(Number padding) {
        if (padding == null) {
            throw new IllegalArgumentException("padding is null.");
        }

        setPadding(padding.intValue());
    }

    /**
     * Sets the padding.
     *
     * @param padding the new padding
     */
    public final void setPadding(String padding) {
        if (padding == null) {
            throw new IllegalArgumentException("padding is null.");
        }

        setPadding(Insets.decode(padding));
    }

    /**
     * Gets the button font.
     *
     * @return the button font
     */
    public Font getButtonFont() {
        return buttonFont;
    }

    /**
     * Sets the button font.
     *
     * @param buttonFont the new button font
     */
    public void setButtonFont(Font buttonFont) {
        if (buttonFont == null) {
            throw new IllegalArgumentException("buttonFont is null.");
        }

        this.buttonFont = buttonFont;
        invalidateComponent();
    }

    /**
     * Sets the button font.
     *
     * @param buttonFont the new button font
     */
    public final void setButtonFont(String buttonFont) {
        if (buttonFont == null) {
            throw new IllegalArgumentException("font is null.");
        }

        setButtonFont(decodeFont(buttonFont));
    }

    /**
     * Sets the button font.
     *
     * @param buttonFont the button font
     */
    public final void setButtonFont(Dictionary<String, ?> buttonFont) {
        if (buttonFont == null) {
            throw new IllegalArgumentException("font is null.");
        }

        setButtonFont(Theme.deriveFont(buttonFont));
    }

    /**
     * Gets the button color.
     *
     * @return the button color
     */
    public Color getButtonColor() {
        return buttonColor;
    }

    /**
     * Sets the button color.
     *
     * @param buttonColor the new button color
     */
    public void setButtonColor(Color buttonColor) {
        if (buttonColor == null) {
            throw new IllegalArgumentException("buttonColor is null.");
        }

        this.buttonColor = buttonColor;
        repaintComponent();
    }

    /**
     * Sets the button color.
     *
     * @param buttonColor the new button color
     */
    public final void setButtonColor(String buttonColor) {
        if (buttonColor == null) {
            throw new IllegalArgumentException("buttonColor is null.");
        }

        setButtonColor(GraphicsUtilities.decodeColor(buttonColor));
    }

    /**
     * Sets the button color.
     *
     * @param buttonColor the new button color
     */
    public final void setButtonColor(int buttonColor) {
        TerraTheme theme = (TerraTheme)Theme.getTheme();
        setButtonColor(theme.getColor(buttonColor));
    }

    /**
     * Gets the button padding.
     *
     * @return the button padding
     */
    public Insets getButtonPadding() {
        return buttonPadding;
    }

    /**
     * Sets the button padding.
     *
     * @param buttonPadding the new button padding
     */
    public void setButtonPadding(Insets buttonPadding) {
        if (buttonPadding == null) {
            throw new IllegalArgumentException("buttonPadding is null.");
        }

        this.buttonPadding = buttonPadding;
        invalidateComponent();
        for (Component tabButton : tabButtonBoxPane) {
            tabButton.invalidate();
        }
    }

    /**
     * Sets the button padding.
     *
     * @param buttonPadding the new button padding
     */
    public final void setButtonPadding(int buttonPadding) {
        setButtonPadding(new Insets(buttonPadding));
    }

    /**
     * Gets the button spacing.
     *
     * @return the button spacing
     */
    public int getButtonSpacing() {
        return (Integer)tabButtonBoxPane.getStyles().get("spacing");
    }

    /**
     * Sets the button spacing.
     *
     * @param buttonSpacing the new button spacing
     */
    public void setButtonSpacing(int buttonSpacing) {
        tabButtonBoxPane.getStyles().put("spacing", buttonSpacing);
    }

    /**
     * Gets the tab orientation.
     *
     * @return the tab orientation
     */
    public Orientation getTabOrientation() {
        return tabOrientation;
    }

    /**
     * Sets the tab orientation.
     *
     * @param tabOrientation the new tab orientation
     */
    public void setTabOrientation(Orientation tabOrientation) {
        if (tabOrientation == null) {
            throw new IllegalArgumentException("tabOrientation is null.");
        }

        this.tabOrientation = tabOrientation;

        // Invalidate the tab buttons since their preferred sizes have changed
        for (Component tabButton : tabButtonBoxPane) {
            tabButton.invalidate();
        }

        tabButtonBoxPane.setOrientation(tabOrientation);

        switch (tabOrientation) {
            case HORIZONTAL: {
                tabButtonBoxPane.getStyles().put("horizontalAlignment", HorizontalAlignment.LEFT);
                break;
            }

            case VERTICAL: {
                tabButtonBoxPane.getStyles().put("verticalAlignment", VerticalAlignment.TOP);
                break;
            }
        }
    }

    /**
     * Gets the selection change duration.
     *
     * @return the selection change duration
     */
    public int getSelectionChangeDuration() {
        return selectionChangeDuration;
    }

    /**
     * Sets the selection change duration.
     *
     * @param selectionChangeDuration the new selection change duration
     */
    public void setSelectionChangeDuration(int selectionChangeDuration) {
        this.selectionChangeDuration = selectionChangeDuration;
    }

    /**
     * Gets the selection change rate.
     *
     * @return the selection change rate
     */
    public int getSelectionChangeRate() {
        return selectionChangeRate;
    }

    /**
     * Sets the selection change rate.
     *
     * @param selectionChangeRate the new selection change rate
     */
    public void setSelectionChangeRate(int selectionChangeRate) {
        this.selectionChangeRate = selectionChangeRate;
    }

    /**
     * Key presses have no effect if the event has already been consumed.<p>
     * CommandModifier + {@link KeyCode#KEYPAD_1 KEYPAD_1} to
     *
     * @param component the component
     * @param keyCode the key code
     * @param keyLocation the key location
     * @return true, if successful
     * {@link KeyCode#KEYPAD_9 KEYPAD_9}<br>or CommandModifier +
     * {@link KeyCode#N1 1} to {@link KeyCode#N9 9} Select the (enabled) tab at
     * index 0 to 8 respectively<p>
     * @see Platform#getCommandModifier()
     */
    @Override
    public boolean keyPressed(Component component, int keyCode, Keyboard.KeyLocation keyLocation) {
        boolean consumed = super.keyPressed(component, keyCode, keyLocation);

        Keyboard.Modifier commandModifier = Platform.getCommandModifier();
        if (!consumed
            && Keyboard.isPressed(commandModifier)) {
            TabPane tabPane = (TabPane)getComponent();
            TabPane.TabSequence tabs = tabPane.getTabs();

            int selectedIndex = -1;

            switch (keyCode) {
                case Keyboard.KeyCode.KEYPAD_1:
                case Keyboard.KeyCode.N1: {
                    selectedIndex = 0;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_2:
                case Keyboard.KeyCode.N2: {
                    selectedIndex = 1;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_3:
                case Keyboard.KeyCode.N3: {
                    selectedIndex = 2;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_4:
                case Keyboard.KeyCode.N4: {
                    selectedIndex = 3;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_5:
                case Keyboard.KeyCode.N5: {
                    selectedIndex = 4;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_6:
                case Keyboard.KeyCode.N6: {
                    selectedIndex = 5;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_7:
                case Keyboard.KeyCode.N7: {
                    selectedIndex = 6;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_8:
                case Keyboard.KeyCode.N8: {
                    selectedIndex = 7;
                    break;
                }

                case Keyboard.KeyCode.KEYPAD_9:
                case Keyboard.KeyCode.N9: {
                    selectedIndex = 8;
                    break;
                }
            }

            if (selectedIndex >= 0
                && selectedIndex < tabs.getLength()
                && tabs.get(selectedIndex).isEnabled()) {
                tabPane.setSelectedIndex(selectedIndex);
                consumed = true;
            }
        }

        return consumed;
    }

    // Tab pane events
    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#tabInserted(org.apache.pivot.wtk.TabPane, int)
     */
    @Override
    public void tabInserted(TabPane tabPane, int index) {
        if (selectionChangeTransition != null) {
            selectionChangeTransition.end();
        }

        Component tab = tabPane.getTabs().get(index);
        tab.setVisible(false);

        // Create a new button for the tab
        TabButton tabButton = new TabButton(tab);
        tabButton.setButtonGroup(tabButtonGroup);
        tabButtonBoxPane.insert(tabButton, index);

        // Listen for state changes on the tab
        tabButton.setEnabled(tab.isEnabled());
        tab.getComponentStateListeners().add(tabStateListener);

        // If this is the first tab, select it
        if (tabPane.getTabs().getLength() == 1) {
            tabPane.setSelectedIndex(0);
        }

        invalidateComponent();
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#previewRemoveTabs(org.apache.pivot.wtk.TabPane, int, int)
     */
    @Override
    public Vote previewRemoveTabs(TabPane tabPane, int index, int count) {
        return Vote.APPROVE;
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#removeTabsVetoed(org.apache.pivot.wtk.TabPane, org.apache.pivot.util.Vote)
     */
    @Override
    public void removeTabsVetoed(TabPane tabPane, Vote vote) {
        // No-op
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#tabsRemoved(org.apache.pivot.wtk.TabPane, int, org.apache.pivot.collections.Sequence)
     */
    @Override
    public void tabsRemoved(TabPane tabPane, int index, Sequence<Component> removed) {
        if (selectionChangeTransition != null) {
            selectionChangeTransition.end();
        }

        // Remove the buttons
        Sequence<Component> removedButtons = tabButtonBoxPane.remove(index, removed.getLength());

        for (int i = 0, n = removed.getLength(); i < n; i++) {
            TabButton tabButton = (TabButton)removedButtons.get(i);
            tabButton.setButtonGroup(null);

            // Stop listening for state changes on the tab
            tabButton.tab.getComponentStateListeners().remove(tabStateListener);
        }

        invalidateComponent();
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#cornerChanged(org.apache.pivot.wtk.TabPane, org.apache.pivot.wtk.Component)
     */
    @Override
    public void cornerChanged(TabPane tabPane, Component previousCorner) {
        invalidateComponent();
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#tabDataRendererChanged(org.apache.pivot.wtk.TabPane, org.apache.pivot.wtk.Button.DataRenderer)
     */
    @Override
    public void tabDataRendererChanged(TabPane tabPane, Button.DataRenderer previousTabDataRenderer) {
        for (Component tabButton : tabButtonBoxPane) {
            tabButton.invalidate();
        }
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#closeableChanged(org.apache.pivot.wtk.TabPane)
     */
    @Override
    public void closeableChanged(TabPane tabPane) {
        Button selectedTabButton = tabButtonGroup.getSelection();

        if (selectedTabButton != null) {
            selectedTabButton.invalidate();
        }
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneListener#collapsibleChanged(org.apache.pivot.wtk.TabPane)
     */
    @Override
    public void collapsibleChanged(TabPane tabPane) {
        // No-op
    }

    // Tab pane selection events
    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneSelectionListener#previewSelectedIndexChange(org.apache.pivot.wtk.TabPane, int)
     */
    @Override
    public Vote previewSelectedIndexChange(TabPane tabPane, int selectedIndex) {
        Vote vote;

        if (tabPane.isCollapsible()) {
            if (tabPane.isShowing()
                && selectionChangeTransition == null) {
                int previousSelectedIndex = tabPane.getSelectedIndex();

                if (selectedIndex == -1) {
                    // Collapse
                    selectionChangeTransition = new SelectionChangeTransition(previousSelectedIndex, false);
                } else {
                    if (previousSelectedIndex == -1) {
                        // Expand
                        selectionChangeTransition = new SelectionChangeTransition(selectedIndex, true);
                    }
                }

                if (selectionChangeTransition != null) {
                    selectionChangeTransition.start(new TransitionListener() {
                        @Override
                        public void transitionCompleted(Transition transition) {
                            TabPane tabPane = (TabPane)getComponent();

                            SelectionChangeTransition selectionChangeTransition =
                                (SelectionChangeTransition)transition;

                            int selectedIndex;
                            if (selectionChangeTransition.expand) {
                                selectedIndex = selectionChangeTransition.index;
                            } else {
                                selectedIndex = -1;
                            }

                            tabPane.setSelectedIndex(selectedIndex);

                            MainTabPaneSkin.this.selectionChangeTransition = null;
                        }
                    });
                }
            }

            if (selectionChangeTransition == null
                || !selectionChangeTransition.isRunning()) {
                vote = Vote.APPROVE;
            } else {
                vote = Vote.DEFER;
            }
        } else {
            vote = Vote.APPROVE;
        }

        return vote;
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneSelectionListener#selectedIndexChangeVetoed(org.apache.pivot.wtk.TabPane, org.apache.pivot.util.Vote)
     */
    @Override
    public void selectedIndexChangeVetoed(TabPane tabPane, Vote reason) {
    	Component component = getComponent();
    	if (component instanceof MainTabPane) {
    		MainTabPane mtp = (MainTabPane)component;
    		
            final Button button = (Button)tabButtonBoxPane.get(mtp.getSelectedIndex());
            button.setSelected(true);
    	}
    	
    	if (reason == Vote.DENY
            && selectionChangeTransition != null) {
            // NOTE We stop, rather than end, the transition so the completion
            // event isn't fired; if the event fires, the listener will set
            // the selection state
            selectionChangeTransition.stop();
            selectionChangeTransition = null;
            invalidateComponent();
        }
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneSelectionListener#selectedIndexChanged(org.apache.pivot.wtk.TabPane, int)
     */
    @Override
    public void selectedIndexChanged(TabPane tabPane, int previousSelectedIndex) {
        int selectedIndex = tabPane.getSelectedIndex();

        if (selectedIndex != previousSelectedIndex) {
            // This was not an indirect selection change
            if (selectedIndex == -1) {
                Button button = tabButtonGroup.getSelection();
                if (button != null) {
                    button.setSelected(false);
                }
            } else {
                final Button button = (Button)tabButtonBoxPane.get(selectedIndex);
                button.setSelected(true);

                Component selectedTab = tabPane.getTabs().get(selectedIndex);
                selectedTab.setVisible(true);
                selectedTab.requestFocus();

                ApplicationContext.queueCallback(new Runnable(){
                    @Override
                    public void run() {
                        button.scrollAreaToVisible(0, 0, button.getWidth(), button.getHeight());
                    }
                });
            }

            if (previousSelectedIndex != -1) {
                Component previousSelectedTab = tabPane.getTabs().get(previousSelectedIndex);
                previousSelectedTab.setVisible(false);
            }
        }

        if (selectedIndex == -1
            || previousSelectedIndex == -1) {
            invalidateComponent();
        }
    }

    // Tab pane attribute events
    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneAttributeListener#tabDataChanged(org.apache.pivot.wtk.TabPane, org.apache.pivot.wtk.Component, java.lang.Object)
     */
    @Override
    public void tabDataChanged(TabPane tabPane, Component component, Object previousTabData) {
        int i = tabPane.getTabs().indexOf(component);
        tabButtonBoxPane.get(i).invalidate();
    }

    /* (non-Javadoc)
     * @see org.apache.pivot.wtk.TabPaneAttributeListener#tooltipTextChanged(org.apache.pivot.wtk.TabPane, org.apache.pivot.wtk.Component, java.lang.String)
     */
    @Override
    public void tooltipTextChanged(TabPane tabPane, Component component, String previousTooltipText) {
        // No-op
    }
}
