package at.easydiet.teamb.view;

import java.net.URL;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.json.JSON;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableView.Column;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.TableViewCellRenderer;

import at.easydiet.teamb.application.handler.PatientDataHandler;
import at.easydiet.teamb.application.viewobject.PatientLikeGradeViewable;
import at.easydiet.teamb.application.viewobject.PatientLikeViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.IPatientLike;

public class PatientLikeManagementView extends BoxPane implements Bindable {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PatientLikeManagementView.class);

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map, java.net.URL, org.apache.pivot.util.Resources)
     */
    @Override
    public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2)
    {}

	
	
}
