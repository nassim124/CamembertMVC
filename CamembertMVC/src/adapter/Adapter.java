package adapter;

import java.awt.Color;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.Observable;

import model.CamembertModel;
import model.ICamembertModel;
import model.Item;

public class Adapter extends Observable implements ICamembertModel{
	
	CamembertModel model;
	
	public Adapter() {
		super();
		this.model = new CamembertModel();		
	}

	public void notifyObservers() {
		super.notifyObservers();
	}

	public int getTotalItem() {
		return model.getTotalItem();
	}


	@Override
	public void addItem(Item item) {
		model.addItem(item);
		setChanged();
		this.notifyObservers();
	}

	
	@Override
	public void removeItem(Item item) {
		this.model.removeItem(item);
		setChanged();
		this.notifyObservers();
	}


	@Override
	public ArrayList<Item> getListItem() {
		return this.model.getListItem();
	}
	
	
	public void setTotalItem(int totalItem) {
		model.setTotalItem(totalItem);
		setChanged();
		this.notifyObservers();
	}
	
	
	
	
}
