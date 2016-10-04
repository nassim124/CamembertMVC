package view;
import java.awt.Color;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.ICamembertModel;
import model.Item;

public class JtableModel extends AbstractTableModel implements ListSelectionListener{

	private ICamembertModel model;

	private CamembertView myView;

	private final String[] entetes = {"item", "description", "quantité"};


	public JtableModel(ICamembertModel model, CamembertView myView) {
		super();
		this.model = model;
		this.myView = myView;
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

	// remplir la table par les valeurs du model
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


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true; //Toutes les cellules éditables
	}

	
	//permet d'editer les valeurs des cases de chaque ligne
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
				model.setTotalItem((model.getTotalItem() - model.getListItem().get(rowIndex).getN()) + Integer.parseInt((String) aValue));
				model.getListItem().get(rowIndex).setN((String)aValue);
				break;
			}
		}
	}

	
	//Selectionner l'item dans le camembert qui correspond à l'item selectionner dans la table
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting())
			return;
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		int selectedRow = lsm.getMinSelectionIndex();
		if (lsm.isSelectionEmpty()) {
			System.out.println("No rows selected");
		}
		else{
			
			for (int i=0; i<  this.model.getListItem().size(); i++){

				this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
				this. model.getListItem().get(i).setColor( model.getListItem().get(i).getDefault_color());
			}
			
			myView.repaint();
			myView.revalidate();

			this.myView.setSelected(true);
			this.myView.getArcs().get(selectedRow).setFrame(100, 100, 300, 300);
			this. model.getListItem().get(selectedRow).setColor(Color.BLACK);
			myView.setSelectedItem(this. model.getListItem().get(selectedRow));
		}

		if (myView.getSelectedItem() != null){
			myView.setmTexte(myView.getSelectedItem().getTitre());
		}
		myView.repaint();
		myView.revalidate();


		System.out.println("The row "+selectedRow+" is now selected");

	}
}

