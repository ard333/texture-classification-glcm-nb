/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcmnb.filter;


import java.io.File;
import javax.swing.filechooser.*;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class ImageFilter extends FileFilter{
	@Override
	public boolean accept(File f){
		if(f.isDirectory()){
			return false;
		}
		String extension = Utils.getExtension(f);
		if(extension != null){
				if(extension.equals(Utils.png) || extension.equals(Utils.jpg)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription(){
		return "jpg/png";
	}
}
