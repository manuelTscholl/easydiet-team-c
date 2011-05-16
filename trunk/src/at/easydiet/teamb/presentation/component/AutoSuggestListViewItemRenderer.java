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
package at.easydiet.teamb.presentation.component;

import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;

import org.apache.pivot.wtk.Bounds;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Insets;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.VerticalAlignment;

import at.easydiet.teamb.application.viewobject.PatientViewable;


/**
 * Default list view item renderer.
 */
public class AutoSuggestListViewItemRenderer extends BoxPane implements ListView.ItemRenderer {
	protected Label _lastName = new Label();
	protected Label _foreName = new Label();
	protected Label _birthday = new Label();
    protected Label _insuranceNumber = new Label();
    protected Label _zip = new Label();
    protected Label _street = new Label();    
    protected Label _place = new Label();
    
    final String SEPARATOR = ",\t";

    protected HashSet<Label> _labels = new HashSet<Label>();
    
    /**
     * Instantiates a new auto suggest list view item renderer.
     */
    public AutoSuggestListViewItemRenderer() {
        getStyles().put("horizontalAlignment", HorizontalAlignment.LEFT);
        getStyles().put("verticalAlignment", VerticalAlignment.CENTER);
        getStyles().put("padding", new Insets(2, 3, 2, 3));
        
        _labels.add(_lastName);
        _labels.add(_foreName);
        _labels.add(_birthday);
        _labels.add(_insuranceNumber);
        _labels.add(_zip);
        _labels.add(_street);
        _labels.add(_place);
        
        add(_lastName);
        add(_foreName);
        add(_birthday);
        add(_insuranceNumber);
        add(_zip);
        add(_street);
        add(_place);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);

        // Since this component doesn't have a parent, it won't be validated
        // via layout; ensure that it is valid here
        validate();
    }

    public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
        renderStyles(listView, selected, highlighted, disabled);

        String lastName = "";
        String foreName = "";
        String birthday = "";
        String insuranceNumber = "";
        String zip = "";
        String street = "";
        String place = "";

        // TODO: change this to PatientVO if necessary (depends on EasyBar search)
        if(item instanceof PatientViewable) {
        	PatientViewable p = (PatientViewable)item;
        	
        	lastName = p.getLastname();
        	foreName = p.getForename() + SEPARATOR;
        	//birthday = dateFormat.format(p.getBirthday()) + SEPARATOR;
        	insuranceNumber = p.getInsuranceNumber() + SEPARATOR;
        	zip = p.getZip() + SEPARATOR;
        	street = p.getStreet() + SEPARATOR;
        	place = p.getPlace();
        }

        Font font = (Font)listView.getStyles().get("font");
        _lastName.getStyles().put("font", font.deriveFont(font.getStyle() | Font.BOLD));
        _foreName.getStyles().put("font", font.deriveFont(font.getStyle() | Font.BOLD));
        
        _insuranceNumber.getStyles().put("font", font.deriveFont(font.getStyle() | Font.ITALIC));

        _lastName.setText(lastName);
        _foreName.setText(foreName);
        _birthday.setText(birthday);
        _insuranceNumber.setText(insuranceNumber);
        _zip.setText(zip);
        _street.setText(street);
        _place.setText(place);
    }

    protected void renderStyles(ListView listView, boolean selected,
        boolean highlighted, boolean disabled) {

        Font font = (Font)listView.getStyles().get("font");
        
        for(Label label : _labels) {
        	label.getStyles().put("font", font);
        }        

        Color color;
        if (listView.isEnabled() && !disabled) {
            if (selected) {
                if (listView.isFocused()) {
                    color = (Color)listView.getStyles().get("selectionColor");
                } else {
                    color = (Color)listView.getStyles().get("inactiveSelectionColor");
                }
            } else {
                color = (Color)listView.getStyles().get("color");
            }
        } else {
            color = (Color)listView.getStyles().get("disabledColor");
        }

        for(Label label : _labels) {
        	label.getStyles().put("color", color);
        }
    }

    public String toString(Object item) {
        
        String lastName = "";
        String foreName = "";
        
        // TODO: change this to PatientVO if necessary (depends on EasyBar search)
        if(item instanceof PatientViewable) {
        	PatientViewable p = (PatientViewable)item;
        	
        	lastName = p.getLastname();
        	foreName = p.getForename();
        }

        return lastName + " " + foreName;
    }

    /**
     * Gets the bounds of the text that is rendered by this renderer.
     *
     * @return
     * The bounds of the rendered text, or <tt>null</tt> if this renderer did
     * not render any text.
     */
    public Bounds getTextBounds() {
    	// TODO getTextBounds()
        return null; //(label.isVisible() ? label.getBounds() : null);
    }
}