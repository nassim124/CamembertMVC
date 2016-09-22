package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ICamembertModel;

import view.CamembertView;

public class Controller implements IController, MouseListener{

	private ICamembertModel Model;
	private CamembertView myView;

	public Controller(CamembertView view, ICamembertModel AdapterModel) {

		this.Model = AdapterModel;
		this.myView=view;
		myView.addMouseListener(this);
	}

	public void upDateToDefaultValue (){
		for (int i=0; i< myView.getListItem().size(); i++){

			this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
			this.myView.getListItem().get(i).setColor(this.myView.getListItem().get(i).getDefault_color());
		}


	}

	public void setChangeOnClick (MouseEvent e){


		for (int i=0; i< myView.getListItem().size(); i++){
			if (this.myView.getArcs().get(i).contains(e.getX(), e.getY())){
				this.myView.setSelected(true);
				this.myView.getArcs().get(i).setFrame(100, 100, 300, 300);
				this.myView.getListItem().get(i).setColor(Color.BLACK);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i=0; i<  myView.getListItem().size(); i++){

			this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
			this. myView.getListItem().get(i).setColor( myView.getListItem().get(i).getDefault_color());
		}
		myView.repaint();
		myView.revalidate();
		for (int i=0; i<  myView.getListItem().size(); i++){
			if (this.myView.getArcs().get(i).contains(e.getX(), e.getY())){
				this.myView.setSelected(true);
				this.myView.getArcs().get(i).setFrame(100, 100, 300, 300);
				this. myView.getListItem().get(i).setColor(Color.BLACK);
			}
		}

		myView.setmTexte("Mouse at "+e.getX()+"x"+e.getY());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
