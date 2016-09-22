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
	
	int totalItem;
	int blue=80;
	
	
	public Adapter() {
		super();
		this.model = new CamembertModel();
		
		this.model.addItem(new Item("Loyer", "pas cher", new Color(0, 0,this.blue), 190, 250, 250));
		this.blue += 35;
		this.model.addItem(new Item("Impots", "cher", new Color(0, 0,this.blue), 100, 250, 250));
		this.blue += 35;
		this.model.addItem(new Item("Facture electricité", "tres cher", new Color(0, 0,this.blue), 80, 250, 250));
		this.blue += 35;
		this.model.addItem(new Item("Facture eau", "pas trop cher", new Color(0, 0,this.blue), 40, 250, 250));
		this.blue += 35;
		this.model.addItem(new Item("Facture mobile", "cher", new Color(0, 0,this.blue), 30, 250, 250));
		this.blue += 35;
		this.model.addItem(new Item("Facture carrefour", "trop cher", new Color(0, 0,this.blue), 120, 250, 250));
		
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
