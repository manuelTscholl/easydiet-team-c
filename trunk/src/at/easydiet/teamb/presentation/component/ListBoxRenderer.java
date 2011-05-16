/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	13.05.2011
 */

package at.easydiet.teamb.presentation.component;


public interface ListBoxRenderer<T> {
	public String getName(T obj);
	
	public void show(T obj);
	
	public boolean delete(T obj);
}
