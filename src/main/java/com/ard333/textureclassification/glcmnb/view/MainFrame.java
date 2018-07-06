package com.ard333.textureclassification.glcmnb.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class MainFrame extends JFrame{
	
	private SectionPanel tnp;
	private SectionPanel ttp;
	
	public MainFrame(String title) {
		super(title);
		this.init();
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLayout(new GridLayout(1, 2));
		setLocationRelativeTo(null);
		
		String[] trainingHeader = {"File", "Class Target"};
		String[] testingHeader = {"File", "Class Result"};
		
		tnp = new SectionPanel("Training Section", "Choose and Train Data", trainingHeader);
		ttp = new SectionPanel("Testing Section", "Choose and Test Data", testingHeader);
		
		add(tnp, BorderLayout.CENTER);
		add(ttp, BorderLayout.SOUTH);
	}
	
	public SectionPanel getTrainingPanel() {
		return tnp;
	}
	public SectionPanel getTestignPanel() {
		return ttp;
	}
	
}
