/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcmnb.controller;

import id.web.ard.textureclassification.glcmnb.model.TextureClass;
import id.web.ard.textureclassification.glcmnb.model.NameClassPair;
import id.web.ard.textureclassification.glcmnb.model.TrainingData;
import id.web.ard.textureclassification.glcmnb.service.GLCMFeatureExtraction;
import id.web.ard.textureclassification.glcmnb.service.NaiveBayesClassifier;
import id.web.ard.textureclassification.glcmnb.view.ImageChooser;
import id.web.ard.textureclassification.glcmnb.view.LoadingFrame;
import id.web.ard.textureclassification.glcmnb.view.MainFrame;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class MainController {
	
	private MainFrame mf;
	
	ArrayList<TrainingData> td = new ArrayList<>();
	private NaiveBayesClassifier nbc;
	private GLCMFeatureExtraction glcmfe;
	
	private boolean nbReady = false;
	
	public MainController() {
		init();
	}
	
	private void init() {
		mf = new MainFrame("Texture Classification");
		
		mf.getTrainingPanel().getAddDataButton().addActionListener((ActionEvent e)->{
			this.trainData();
		});
		
		mf.getTestignPanel().getAddDataButton().addActionListener((ActionEvent e)->{
			if (nbReady) {
				this.testData();
			} else {
				JOptionPane.showMessageDialog(null, "Choose and Train Data first...");
			}
		});
		
		mf.setVisible(true);
	}
	
	private ArrayList<String> chooseData() {
		ArrayList<String> trainingDataPath = new ArrayList<>();
		td = new ArrayList<>();
		
		ImageChooser wavChooser = new ImageChooser();
		wavChooser.setMultiSelectionEnabled(true);
		
		int returnVal = wavChooser.showDialog(mf, "Choose png or jpg image");
		if(returnVal == ImageChooser.APPROVE_OPTION){
			File[] wavFiles =  wavChooser.getSelectedFiles();
			for (File file : wavFiles) {
				try {
					InputStream is = new BufferedInputStream(new FileInputStream(file));
					String mimeType = URLConnection.guessContentTypeFromStream(is);
					
					if (mimeType != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg"))) {
						trainingDataPath.add(file.getAbsolutePath());
					}
				} catch (IOException ex) {
					Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return trainingDataPath;
	}
	
	private ArrayList<Double> extractFeature(String dataPath) {
		ArrayList<Double> feature = new ArrayList<>();
		try {
			glcmfe = new GLCMFeatureExtraction(new File(dataPath), 255);
			glcmfe.extract();
			feature.add(glcmfe.getContrast());
			feature.add(glcmfe.getDissimilarity());
			feature.add(glcmfe.getEnergy());
			feature.add(glcmfe.getEntropy());
			feature.add(glcmfe.getHomogenity());
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return feature;
	}
	
	private void trainData() {
		ArrayList<String> trainingDataPath = this.chooseData();
		
		if (trainingDataPath.size() > 0) {
			LoadingFrame lf = new LoadingFrame(mf, "Loading, Please wait...", true);
			lf.setString("Feature Extraction and Naive Bayes Training");
			
			ArrayList<NameClassPair> nameClassPair = new ArrayList<>();
			
			Pattern pattern = Pattern.compile("([1-"+TextureClass.values().length+"]){1}(\\.){1}jpg|png$");
			Runnable extractAndTrain = () -> {
				for (String filePath : trainingDataPath) {
					Matcher matcher = pattern.matcher(filePath);
					if (matcher.find()) {
						int indexClass = Integer.parseInt(matcher.group(1));
						nameClassPair.add(new NameClassPair(
								filePath,
								String.valueOf(TextureClass.values()[indexClass-1])
							)
						);
						td.add(new TrainingData(this.extractFeature(filePath), indexClass));
					}
				}
				if (td.size() > 0) {
					mf.getTrainingPanel().setTableData(nameClassPair);
					mf.getTrainingPanel().refresh();
					nbc = new NaiveBayesClassifier(td);
					
					nbc.train();
					nbReady = true;
					lf.dispose();
				} else {
					lf.dispose();
					JOptionPane.showMessageDialog(null, "There are no valid training data...");
				}
			};
			Thread extractingFeature = new Thread(extractAndTrain);
			extractingFeature.start();
			lf.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "There are no training data...");
		}
	}
	
	private void testData() {
		ArrayList<String> testingDataPath = this.chooseData();
		
		if (testingDataPath.size() > 0) {
			LoadingFrame lf = new LoadingFrame(mf, "Loading, please wait", true);
			lf.setString("Feature Extraction");
			
			Runnable testExec = () -> {
				lf.setString("Testing Data");
				ArrayList<NameClassPair> nameClassPair = new ArrayList<>();
				
				for (String path : testingDataPath) {
					nameClassPair.add(new NameClassPair(
							path,
							String.valueOf(TextureClass.values()[nbc.classify(this.extractFeature(path))-1]
							)
						)
					);
				}
				mf.getTestignPanel().setTableData(nameClassPair);
				mf.getTestignPanel().refresh();
				lf.dispose();
			};
			Thread extractingFeature = new Thread(testExec);
			extractingFeature.start();
			lf.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "There are no valid training data...");
		}
	}
	
}
