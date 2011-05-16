/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	13.05.2011
 */

package at.easydiet.teamb.presentation.util;

public enum Colors {
	INFO("#b0f282"),
	WARNING("#fef7b2"),
	ERROR("#f28282");
	
	private String _hex = "";
	
	Colors(String hex) {
		_hex = hex;
	}
	
	public String getHexCode() {
		return _hex;
	}
}
