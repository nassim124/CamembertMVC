package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import adapter.Adapter;
import controller.Controller;
import controller.IController;
import model.ICamembertModel;
import model.Item;

public class mView extends JFrame {

	CamembertView myView;
	JPanel CamembertPanel;
	
	JtableModel table;
	JButton b1;
	JButton b2;
	int blue_contrast=80;
	
	int x=200;
	int y=50;
	int height=200;
	int width=200;
	
	
	public mView(){
	
		ICamembertModel modelAdapter = new Adapter();
		
		modelAdapter.addItem(new Item("Loyer", "pas cher", Color.red, 190));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Impots", "cher", Color.BLUE, 100));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture electricité", "tres cher", Color.CYAN, 80));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture eau", "pas trop cher", Color.gray, 40));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture mobile", "cher", Color.orange, 30));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture carrefour", "trop cher", Color.YELLOW, 120));
		
		
		myView = new CamembertView(modelAdapter);
		JTable table = new JTable(new JtableModel(modelAdapter, myView));	
		
		//Ajouter un listener pour la JTable
		ListSelectionModel listSelectionModel = table.getSelectionModel();      
		JtableModel myJTable = new JtableModel(modelAdapter, myView);
		listSelectionModel.addListSelectionListener(myJTable);

		
		//Ajouter le bouton "ajouter"
		JButton addButton = new JButton("add");
		addButton.setBounds(100, 650, 100, 20);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				float x = rand.nextFloat();
				float y = rand.nextFloat();
				float z = rand.nextFloat();
				Color color = new Color(x,y,z);
				
				//Ajouter un item initialisé a des valeurs par defaut
				Item item1 = new Item("item","item description",color, 10);
				modelAdapter.addItem(item1);
				AbstractTableModel dm = (AbstractTableModel) table.getModel();
				dm.fireTableDataChanged();
				
				// mettre a jour la vue
				myView.update(null, null);
			}
		});
		
		
		// Ajouter le removeButton
		JButton removeButton = new JButton("delete");
		removeButton.setBounds(250, 650, 100, 20);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int lastItem = modelAdapter.getListItem().size()-1;	
				
				// supprimer le dernier item en cas de click
				if (lastItem >=0){
					modelAdapter.removeItem(modelAdapter.getListItem().get(lastItem));
				}
				
				AbstractTableModel dm = (AbstractTableModel) table.getModel();
				dm.fireTableDataChanged();
				myView.update(null, null);
				
			}
		});
		
		IController controller = new Controller (myView, modelAdapter);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 500, 800, 150);
        this.add(scrollPane);
        this.add(addButton);
        this.add(removeButton);
		this.setSize(800, 800);
       
		this.add(myView);

	}




}
