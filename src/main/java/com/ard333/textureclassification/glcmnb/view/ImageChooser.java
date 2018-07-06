package com.ard333.textureclassification.glcmnb.view;


import com.ard333.textureclassification.glcmnb.filter.ImageFilter;
import javax.swing.JFileChooser;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class ImageChooser extends JFileChooser{
	
	public ImageChooser() {
		addChoosableFileFilter(new ImageFilter());
		setAcceptAllFileFilterUsed(false);
	}
	
}
