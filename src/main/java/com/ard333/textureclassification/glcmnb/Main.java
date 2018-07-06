package id.web.ard.textureclassification.glcmnb;

import com.alee.laf.WebLookAndFeel;
import com.ard333.textureclassification.glcmnb.controller.MainController;
import com.ard333.textureclassification.glcmnb.view.LoadingFrame;
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
