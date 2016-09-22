package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.ICamembertModel;
import model.Item;

public class JtableModel extends AbstractTableModel{

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
 
    public void removeAmi(int rowIndex) {
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
            Item item = model.getListItem().get(rowIndex);
     
            switch(columnIndex){
                case 0:
                	item.setTitre((String)aValue);
                    break;
                case 1:
                	item.setDescription((String)aValue);
                    break;
                case 2:
                    item.setN((String)aValue);
                    break;
            }
        }
    }
}
