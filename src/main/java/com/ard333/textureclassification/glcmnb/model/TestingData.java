package com.ard333.textureclassification.glcmnb.model;

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
