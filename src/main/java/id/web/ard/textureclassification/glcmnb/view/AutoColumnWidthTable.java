/**
 * Ardiansyah | http://ard.web.id
 *
 */

package id.web.ard.textureclassification.glcmnb.view;

import com.alee.laf.table.WebTable;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class AutoColumnWidthTable extends WebTable{
	
	private int margin;
	
	public AutoColumnWidthTable(TableModel data, int margin){
		super(data);
		this.margin = margin;
		
		this.init();
	}
	
	private void init() {
		setEditable(false);
		setAutoResizeMode(WebTable.AUTO_RESIZE_OFF);
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFillsViewportHeight(true);
		
		this.refreshSize();
	}
	
	public void refreshSize() {
		for(int column = 0; column < this.getColumnCount(); column++) {
			DefaultTableColumnModel colModel = (DefaultTableColumnModel) this.getColumnModel();
			javax.swing.table.TableColumn col = colModel.getColumn(column);
			int width;
			TableCellRenderer renderer = col.getHeaderRenderer();
			if(renderer==null){
				renderer = this.getTableHeader().getDefaultRenderer();
			}
			Component comp = renderer.getTableCellRendererComponent(this, col.getHeaderValue(), false, false, 0, 0);
			width = comp.getPreferredSize().width;
			for(int r = 0; r < this.getRowCount(); r++) {
				renderer = this.getCellRenderer(r, column);
				comp = renderer.getTableCellRendererComponent(this, this.getValueAt(r, column), false, false, r, column);
				int currentWidth = comp.getPreferredSize().width;
				width = Math.max(width, currentWidth);
			}
			width += 2 * margin;
			col.setPreferredWidth(width);
			col.setWidth(width);
		}
	}
}
