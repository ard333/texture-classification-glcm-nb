package com.ard333.textureclassification.glcmnb.view;

import com.ard333.textureclassification.glcmnb.model.NameClassPair;
import com.ard333.textureclassification.glcmnb.model.NameClassTM;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class SectionPanel extends JPanel{
	
	private NameClassTM tdtm;
	private ArrayList<NameClassPair> td;
	private JButton addButton;
	private AutoColumnWidthTable table;
	
	public SectionPanel(String title, String buttonLabel, String[] headerTable) {
		super(new BorderLayout());
		
		td = new ArrayList<>();
		addButton = new JButton(buttonLabel);
		tdtm = new NameClassTM(headerTable);
		tdtm.setData(td);
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		titlePanel.add(new JLabel("<html><b>"+title+"</b></html>"));
		buttonPanel.add(addButton);
		
		topPanel.add(titlePanel);
		topPanel.add(buttonPanel);
		
		table = new AutoColumnWidthTable(tdtm, 3);
		
		JScrollPane trainingPane = new JScrollPane(
			table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		
		add(topPanel, BorderLayout.NORTH);
		add(trainingPane, BorderLayout.CENTER);
	}
	
	public void refresh() {
		tdtm.fireTableDataChanged();
		table.refreshSize();
	}

	public JButton getAddDataButton() {
		return addButton;
	}
	
	public void setTableData(ArrayList<NameClassPair> td) {
		tdtm.setData(td);
	}
}
