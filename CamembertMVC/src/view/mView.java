package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
	
		ICamembertModel model = new Adapter();
		
		model.addItem(new Item("Loyer", "pas cher", new Color(0, 0,this.blue_contrast), 190));
		blue_contrast += 35;
		model.addItem(new Item("Impots", "cher", new Color(0, 0,this.blue_contrast), 100));
		blue_contrast += 35;
		model.addItem(new Item("Facture electricité", "tres cher", new Color(0, 0,this.blue_contrast), 80));
		blue_contrast += 35;
		model.addItem(new Item("Facture eau", "pas trop cher", new Color(0, 0,this.blue_contrast), 40));
		blue_contrast += 35;
		model.addItem(new Item("Facture mobile", "cher", new Color(0, 0,this.blue_contrast), 30));
		blue_contrast += 35;
		model.addItem(new Item("Facture carrefour", "trop cher", new Color(0, 0,this.blue_contrast), 120));
		
		
		myView = new CamembertView(model);
		JTable table = new JTable(new JtableModel(model));	
		
		//add listener to JTableModel
		ListSelectionModel listSelectionModel = table.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new JtableModel(model));

		
		IController controller = new Controller (myView, model);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 500, 800, 200);
        this.add(scrollPane);

		this.setSize(800, 800);
       
		this.add(myView);

	}




}
