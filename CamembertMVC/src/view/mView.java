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
		
		modelAdapter.addItem(new Item("Loyer", "pas cher", new Color(0, 0,this.blue_contrast), 190));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Impots", "cher", new Color(0, 0,this.blue_contrast), 100));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture electricité", "tres cher", new Color(0, 0,this.blue_contrast), 80));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture eau", "pas trop cher", new Color(0, 0,this.blue_contrast), 40));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture mobile", "cher", new Color(0, 0,this.blue_contrast), 30));
		blue_contrast += 35;
		modelAdapter.addItem(new Item("Facture carrefour", "trop cher", new Color(0, 0,this.blue_contrast), 120));
		
		
		myView = new CamembertView(modelAdapter);
		JTable table = new JTable(new JtableModel(modelAdapter));	
		
		//add listener to JTableModel
		ListSelectionModel listSelectionModel = table.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new JtableModel(modelAdapter));

		
		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel();
		JButton addButton = new JButton("Ajouter");
		addButton.setBounds(160, 6, 100, 20);
		//listner sur le bouton ajouter
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				float x = rand.nextFloat();
				float y = rand.nextFloat();
				float z = rand.nextFloat();
				Color color = new Color(x,y,z);
				Item item1 = new Item("....",".....",color, 20);
				
				modelAdapter.addItem(item1);
				AbstractTableModel dm = (AbstractTableModel) table.getModel();
				dm.fireTableDataChanged();
				
				myView.update(null, modelAdapter);
			}
		});
		
		IController controller = new Controller (myView, modelAdapter);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 500, 800, 200);
        this.add(scrollPane);
        this.add(addButton);
        this.add(panel1);
        this.add(panel2);
		this.setSize(800, 800);
       
		this.add(myView);

	}




}
