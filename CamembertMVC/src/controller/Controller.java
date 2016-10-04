package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ListSelectionModel;

import model.ICamembertModel;
import model.Item;
import view.CamembertView;

public class Controller implements IController, MouseListener {

	private ICamembertModel model;
	private CamembertView myView;
	private int selectedItemId;

	public Controller(CamembertView view, ICamembertModel AdapterModel) {

		this.model = AdapterModel;
		this.myView=view;
		myView.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// remettre les valeurs par defaut a chaque click
		for (int i=0; i<  model.getListItem().size(); i++){
			this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
			this. model.getListItem().get(i).setColor( model.getListItem().get(i).getDefault_color());
		}
		myView.repaint();
		myView.revalidate();
		
		//agrandir l'item selectionner dans le camembert 
		for (int i=0; i<  model.getListItem().size(); i++){
			if (this.myView.getArcs().get(i).contains(e.getX(), e.getY())){
				this.myView.setSelected(true);
				this.myView.getArcs().get(i).setFrame(100, 100, 300, 300);
				this. model.getListItem().get(i).setColor(Color.BLACK);
				myView.setSelectedItem(this. model.getListItem().get(i));
				selectedItemId = i;
			}
		}

		// naviguer avec la fleche d'en haut 
		if (this.myView.getArrows().get(0).contains(e.getX(), e.getY())){

			if (selectedItemId > 0) {

				this.myView.setSelected(true);
				this.myView.getArcs().get(selectedItemId - 1).setFrame(100, 100, 300, 300);
				this. model.getListItem().get(selectedItemId -1).setColor(Color.BLACK);
				myView.setSelectedItem(this. model.getListItem().get(selectedItemId-1));
				System.out.println( "if :"+selectedItemId);
				selectedItemId --;
			} else {
				this.myView.setSelected(true);

				this.myView.getArcs().get(myView.getArcs().size()-1).setFrame(100, 100, 300, 300);
				this. model.getListItem().get(myView.getArcs().size()-1).setColor(Color.BLACK);
				myView.setSelectedItem(this. model.getListItem().get(myView.getArcs().size()-1));
				System.out.println( "else :"+selectedItemId);
				selectedItemId+= myView.getArcs().size()-1;
			}
		}
		
		// naviguer dans le camembert avec la fleche d'en bas
		if (this.myView.getArrows().get(1).contains(e.getX(), e.getY())){

			if (selectedItemId < this.myView.getArcs().size()-1) {

				this.myView.setSelected(true);
				this.myView.getArcs().get(selectedItemId + 1).setFrame(100, 100, 300, 300);
				this. model.getListItem().get(selectedItemId + 1).setColor(Color.BLACK);
				myView.setSelectedItem(this. model.getListItem().get(selectedItemId+1));
				System.out.println( "if :"+selectedItemId);
				selectedItemId ++;
			} else {
				this.myView.setSelected(true);

				this.myView.getArcs().get(0).setFrame(100, 100, 300, 300);
				this. model.getListItem().get(0).setColor(Color.BLACK);
				myView.setSelectedItem(this. model.getListItem().get(0));
				System.out.println( "else :"+selectedItemId);
				selectedItemId-= myView.getArcs().size()-1;
			}
		}
		
		if (myView.getSelectedItem()!=null){
			myView.setmTexte(myView.getSelectedItem().getTitre());
		}
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

		// deselectionner l'item quand le curseur de la souris reagit 
		for (int i=0; i<  model.getListItem().size()-1; i++){
			myView.setmTexte("");
			this.myView.getArcs().get(i).setFrame(125, 125, 250, 250);
			this. model.getListItem().get(i).setColor( model.getListItem().get(i).getDefault_color());
		}

	}


}
