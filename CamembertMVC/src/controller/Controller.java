package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D.Float;

import adapter.Adapter;
import model.CamembertModel;
import model.ICamembertModel;
import model.Item;
import view.CamembertView;

public class Controller implements IController{
	
	private ICamembertModel Model;
	private CamembertView myView;
	
	public Controller(CamembertView view, ICamembertModel AdapterModel) {
		
		this.Model = AdapterModel;
		this.myView=view;
	}

	/*public void setChangeOnClick (MouseEvent e){
		for (int i=0; i< Model.getListItem().size(); i++){
			if (myView.getArcs().get(i).contains(e.getX(), e.getY())){
				Model.getListItem().get(i).setColor(Color.BLACK);
			}
		}
	}*/
}
