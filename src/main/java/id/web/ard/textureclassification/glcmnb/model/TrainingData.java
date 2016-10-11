/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcmnb.model;

import java.util.ArrayList;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class TrainingData {
	private final ArrayList<Double> input;
	private final Integer target;

	public TrainingData(ArrayList<Double> input, Integer target) {
		this.input = input;
		this.target = target;
	}

	public ArrayList<Double> getInput() {
		return input;
	}

	public Integer getTarget() {
		return target;
	}
	
}
