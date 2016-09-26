package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ICamembertModel;
import model.Item;
import view.CamembertView;

public class Controller implements IController, MouseListener {

	private ICamembertModel model;
	private CamembertView myView;

	public Controller(CamembertView view, ICamembertModel AdapterModel) {

		this.model = AdapterModel;
		this.myView=view;
		myView.addMouseListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		String SelectedItem= "";
		// TODO Auto-generated method stub
		for (int i=0; i<  model.getListItem().size(); i++){

			this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
			this. model.getListItem().get(i).setColor( model.getListItem().get(i).getDefault_color());
		}
		myView.repaint();
		myView.revalidate();
		for (int i=0; i<  model.getListItem().size(); i++){
			if (this.myView.getArcs().get(i).contains(e.getX(), e.getY())){
				this.myView.setSelected(true);
				this.myView.getArcs().get(i).setFrame(100, 100, 300, 300);
				this. model.getListItem().get(i).setColor(Color.BLACK);
				SelectedItem = this. model.getListItem().get(i).getTitre();
			}
		}

		myView.setmTexte(SelectedItem);
		myView.repaint();
		myView.revalidate();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		for (int i=0; i<  model.getListItem().size(); i++){
			myView.setmTexte("");
			this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
			this. model.getListItem().get(i).setColor( model.getListItem().get(i).getDefault_color());
		}

	}
	
	
}
