package model;

import java.util.ArrayList;

public interface ICamembertModel {
	
	public void addItem (Item item);
	
	public void removeItem(Item item);

	public ArrayList<Item> getListItem();
	
	public int getTotalItem();
}
