package view;

import java.awt.geom.Arc2D;
import java.util.ArrayList;

import model.Item;

public interface ICamembertView {
	
	public ArrayList<Item> getListItem();

	public ArrayList<Arc2D.Float> getArcs();

	public void setArcs(ArrayList<Arc2D.Float> arcs) ;
	
	public void setSelected (boolean b);

}
