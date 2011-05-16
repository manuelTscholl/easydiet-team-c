package at.easydiet.teamb.presentation.gui;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;

import at.easydiet.teamb.presentation.component.sheet.NewTimeCodeContentBorder;

/**
 * The Class NewTimeCodeSheet.
 *
 * @author TeamB
 */
public class NewTimeCodeSheet extends AbstractSheet {

	private static Logger LOGGER = Logger.getLogger(NewTimeCodeSheet.class);

	/**
	 * Lets you create a new time code.
	 */
	public NewTimeCodeSheet() {
		// get content for abstract sheet
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        
        NewTimeCodeContentBorder content = null;
        try {
			content = (NewTimeCodeContentBorder)bxmlSerializer.readObject(NewTimeCodeSheet.class, "easydiet_popup_new_timecode.bxml");
		} catch (IOException ex) {
			LOGGER.warn("Can not read 'easydiet_popup_new_timecode.bxml'", ex);
		} catch (SerializationException ex) {
			LOGGER.warn("Can not read 'easydiet_popup_new_timecode.bxml'", ex);
		}
		
		// set content
		if(content != null) {
			setMainContent(content);
		}
	}
}