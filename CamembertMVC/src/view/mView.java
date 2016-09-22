package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import adapter.Adapter;
import controller.Controller;
import controller.IController;
import model.ICamembertModel;
import model.Item;

public class mView extends JFrame {

	CamembertView myView;
	JPanel CamembertPanel;
	JButton btn;
	
	
	
	public mView(){
	
		ICamembertModel model = new Adapter();
		
		myView = new CamembertView(model);
		
		IController controller = new Controller (myView, model);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(600, 600);

		this.add(myView);

	}




}
