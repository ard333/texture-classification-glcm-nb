/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcm.nb.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class NameClassTM extends AbstractTableModel {
		private final String[] header;
		private ArrayList<NameClassPair> data;
		
		public NameClassTM(String[] header) {
			this.header = header;
		}
		
		@Override
		public String getColumnName(int column) {
			return header[column];
		}
		@Override
		public int getRowCount() {
			return data.size();
		}
		@Override
		public int getColumnCount() {
			return header.length;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			NameClassPair result = data.get(rowIndex);
			switch(columnIndex){
				case 0: return result.getFileName();
				case 1: return result.getFileClass();
				default: return null;
			}
		}
		
		public void setData(ArrayList<NameClassPair> data){
			this.data = data;
		}
	}
