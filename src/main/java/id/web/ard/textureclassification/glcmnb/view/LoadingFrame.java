/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcmnb.view;

import com.alee.laf.progressbar.WebProgressBar;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class LoadingFrame extends JDialog{
	
	private WebProgressBar progressBar;
	
	public LoadingFrame(JFrame owner, String title, Boolean modal){
		super(owner, title, modal);
		this.init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setResizable(false);
		setSize(350, 75);
		setLocationRelativeTo(null);
		
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		progressBar = new WebProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		progressBar.setString("Loading, please wait...");
		p.add(progressBar, BorderLayout.CENTER);
		
		add(p, BorderLayout.CENTER);
	}
	
	public void setString(String label) {
		progressBar.setString(label);
	}
}
