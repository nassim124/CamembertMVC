package adapter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import model.CamembertModel;
import model.ICamembertModel;
import model.Item;

public class Adapter extends Observable implements ICamembertModel{
	
	CamembertModel model;
	
	int totalItem;
	
	
	
	public Adapter() {
		super();
		this.model = new CamembertModel();
		
		this.model.addItem(new Item("item2", "description2", Color.BLUE, 20, 200, 200));
		this.model.addItem(new Item("item3", "description3", Color.CYAN, 30, 200, 200));
		this.model.addItem(new Item("item4", "description5", Color.RED, 40, 200, 200));
		this.model.addItem(new Item("item5", "description5", Color.MAGENTA, 50, 200, 200));
		
		ArrayList<Item> itemList = model.getListItem();
		for (int i=0; i<itemList.size(); i++){
			totalItem = totalItem + itemList.get(i).getN();
		}
		
	}


	public int getTotalItem() {
		return totalItem;
	}


	@Override
	public void addItem(Item item) {
		model.addItem(item);
		setChanged();
		notifyObservers();
	}

	
	@Override
	public void removeItem(Item item) {
		model.removeItem(item);
		setChanged();
		notifyObservers();
	}


	@Override
	public ArrayList<Item> getListItem() {
		return this.model.getListItem();
	}
	
	
	
	
}
