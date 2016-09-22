package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		int totalItem = 0;
		ArrayList<Item> itemList = model.getListItem();
		for (int i=0; i<itemList.size(); i++){
			totalItem = totalItem + itemList.get(i).getN();
		}
		model.setTotalItem (totalItem);
		myView = new CamembertView(model);
		JTable tableau = new JTable(new JtableModel(model));		
		
		IController controller = new Controller (myView, model);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(tableau);
		scrollPane.setBounds(0, 500, 800, 200);
        this.add(scrollPane);

		this.setSize(800, 800);
       
		this.add(myView);

	}




}
