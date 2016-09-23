package view;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.ICamembertModel;
import model.Item;

public class JtableModel extends AbstractTableModel implements ListSelectionListener{

	private ICamembertModel model;

	private final String[] entetes = {"item", "description", "quantité"};


	public JtableModel(ICamembertModel model) {
		super();
		this.model = model;
	}

	@Override
	public int getRowCount() {
		return model.getListItem().size();
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return model.getListItem().get(rowIndex).getTitre();
		case 1:
			return model.getListItem().get(rowIndex).getDescription();
		case 2:
			return model.getListItem().get(rowIndex).getN();
		}
		return null;
	}

	public void addItem(Item item) {
		model.getListItem().add(item);

		fireTableRowsInserted(model.getListItem().size() -1, model.getListItem().size() -1);
	}

	public void removeItem(int rowIndex) {
		model.getListItem().remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true; //Toutes les cellules éditables
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(aValue != null){

			switch(columnIndex){
			case 0:
				model.getListItem().get(rowIndex).setTitre((String)aValue);
				break;
			case 1:
				model.getListItem().get(rowIndex).setDescription((String)aValue);
				break;
			case 2:
				
				System.out.println(model.getTotalItem());
				System.out.println("*********************");
				model.getListItem().get(rowIndex).setN((String)aValue);
				model.setTotalItem(model.getTotalItem()-model.getListItem().get(rowIndex).getN()+Integer.parseInt((String) aValue));
				System.out.println(model.getTotalItem());
				System.out.println(model.getListItem().get(rowIndex).getN());
				System.out.println(aValue);
				break;
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting())
			return;
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		if (lsm.isSelectionEmpty()) {
			System.out.println("No rows selected");
		}
		else{
			int selectedRow = lsm.getMinSelectionIndex();
			System.out.println("The row "+selectedRow+" is now selected");

		}
	}


}
