/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcm.nb.model;

import java.util.ArrayList;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class TestingData {
	private final ArrayList<Double> input;
	
	public TestingData(ArrayList<Double> input) {
		this.input = input;
	}
	
	public ArrayList<Double> getInput() {
		return input;
	}
}
