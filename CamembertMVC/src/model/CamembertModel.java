package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CamembertModel implements ICamembertModel{




	private ArrayList<Item> listItem = new ArrayList<>();
	
	private int totalItem;


	public ArrayList<Item> getListItem() {
		return this.listItem;
	}
	
	@Override
	public void addItem(Item item) {
		System.err.println(item.getTitre()+" added to itemList");
		this.listItem.add(item);
		this.totalItem+=item.getN();
	}

	@Override
	public void removeItem(Item item) {
		this.listItem.remove(item);
		this.totalItem-=item.getN();
	}

	@Override
	public int getTotalItem() {
		return this.totalItem;
	}

	@Override
	public void setTotalItem(int totalItem) {
		this.totalItem=totalItem;
	}

}
