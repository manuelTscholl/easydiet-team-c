/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	13.04.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;

/**
 * The Class UserManagementTab.
 */
public class UserManagementTab extends AbstractTab implements Bindable {

	@Override
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
		
	}

	@Override
	public AbstractUseCaseHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create() throws ExitNotPermittedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		// TODO Auto-generated method stub
		
	}

}
