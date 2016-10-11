/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcm.nb.view;


import id.web.ard.textureclassification.glcm.nb.filter.ImageFilter;
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
