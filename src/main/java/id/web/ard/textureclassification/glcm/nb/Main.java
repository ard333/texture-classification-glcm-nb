/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcm.nb;

import com.alee.laf.WebLookAndFeel;
import id.web.ard.textureclassification.glcm.nb.controller.MainController;
import id.web.ard.textureclassification.glcm.nb.view.LoadingFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class Main {
	public static void main(String[] args) {
		LoadingFrame lf = new LoadingFrame(null, "Texture Classification", false);
		
		Runnable init = () -> {
			try{
				UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
			} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
			MainController mc = new MainController();
			lf.dispose();
		};
		
		lf.setVisible(true);
		Thread initThread = new Thread(init);
		initThread.start();
		
	}
}
