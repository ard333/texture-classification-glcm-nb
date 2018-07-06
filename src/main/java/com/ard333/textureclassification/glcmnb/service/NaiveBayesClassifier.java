package com.ard333.textureclassification.glcmnb.service;

import com.ard333.textureclassification.glcmnb.model.TrainingData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class NaiveBayesClassifier {
	
	private ArrayList<TrainingData> trainingData;
	private HashMap<Integer, ArrayList<Double>> mean;
	private HashMap<Integer, ArrayList<Double>> variance;
	private HashMap<Integer, Double> prior;
	private HashMap<Integer, ArrayList<Double>> densityProb;
	
	public NaiveBayesClassifier(ArrayList<TrainingData> trainingData) {
		this.trainingData = trainingData;
		mean = new HashMap<>();
		variance = new HashMap<>();
		prior = new HashMap<>();
		densityProb = new HashMap<>();
	}
	
	public void train() {
		calcMean();
		calcVariance();
	}
	
	private void calcMean () {
		int trainingDataSize = trainingData.size();
		for (int i = 0; i < trainingDataSize; i++) {
			int target = trainingData.get(i).getTarget();
			if (prior.containsKey(target)) {
				prior.replace(target, prior.get(target), prior.get(target)+1.0);
			} else {
				prior.put(target, 1.0);
			}
			for (int j = 0; j < trainingData.get(i).getInput().size(); j++) {
				int key = trainingData.get(i).getTarget();
				if (!mean.containsKey(key)) {
					mean.put(key, new ArrayList<>());
				}
				try {
					mean.get(key).get(j);
				} catch (IndexOutOfBoundsException e) {
					mean.get(key).add(j, 0.0);
				}
				mean.get(key).set(j,
					mean.get(key).get(j) + trainingData.get(i).getInput().get(j)
				);
			}
		}
		for(Map.Entry<Integer, ArrayList<Double>> entry : mean.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				entry.getValue().set(i, entry.getValue().get(i) / prior.get(entry.getKey()));
			}
		}
		for(Map.Entry<Integer, Double> entry : prior.entrySet()) {
			entry.setValue(entry.getValue()/trainingDataSize);
		}
	}
	
	private void calcVariance() {
		for (int i = 0; i < trainingData.size(); i++) {
			for (int j = 0; j < trainingData.get(i).getInput().size(); j++) {
				int key = trainingData.get(i).getTarget();
				if (!variance.containsKey(key)) {
					variance.put(key, new ArrayList<>());
				}
				try {
					variance.get(key).get(j);
				} catch (IndexOutOfBoundsException e) {
					variance.get(key).add(j, 0.0);
				}
				variance.get(key).set(j,
					variance.get(key).get(j) + Math.pow( trainingData.get(i).getInput().get(j) - mean.get(key).get(j), 2)
				);
			}
		}
		for(Map.Entry<Integer, ArrayList<Double>> entry : variance.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				entry.getValue().set(i, entry.getValue().get(i) * (1/(prior.get(entry.getKey())*trainingData.size()-1)));
			}
		}
	}
	
	public Integer classify(ArrayList<Double> input) {
		
		for(Map.Entry<Integer, ArrayList<Double>> meanEntry : mean.entrySet()) {
			int key = meanEntry.getKey();
			if (!densityProb.containsKey(key)) {
				densityProb.put(key, new ArrayList<>());
			}
			for (int i = 0; i < meanEntry.getValue().size(); i++) { //each feature
				try {
					densityProb.get(key).get(i);
				} catch (IndexOutOfBoundsException e) {
					densityProb.get(key).add(i, 0.0);
				}
				densityProb.get(key).set(i,
					(1 / Math.sqrt(2 * Math.PI * variance.get(key).get(i))) *
					Math.exp(
						-1 * (Math.pow( input.get(i) - meanEntry.getValue().get(i), 2) / (2 * variance.get(key).get(i)))
					)
				);
			}
		}
		
		Double evidence = 0.0;
		HashMap<Integer, Double> result = new HashMap<>();
		
		for(Map.Entry<Integer, ArrayList<Double>> entry : densityProb.entrySet()) {//each class
			int key = entry.getKey();
			if (!result.containsKey(key)) {
				result.put(key, 1.0);
			}
			
			Double temp = 1.0;
			for (int i = 0; i < entry.getValue().size(); i++) {
				temp *= entry.getValue().get(i);
			}
			
			temp *= prior.get(entry.getKey());
			result.replace(key, temp);
			
			evidence += temp;
		}
		
		for(Map.Entry<Integer, Double> entry : result.entrySet()) {//each class
			entry.setValue(entry.getValue()/evidence);
		}
		
		Double max = 0.0;
		Integer maxIndex = 0;
		for(Map.Entry<Integer, Double> entry : result.entrySet()) {//each class
			if (entry.getValue()>max) {
				maxIndex = entry.getKey();
				max = entry.getValue();
			}
		}
		return maxIndex;
	}
}
