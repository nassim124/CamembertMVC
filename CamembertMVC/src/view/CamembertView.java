package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import model.ICamembertModel;
import model.Item;


public class CamembertView extends JComponent implements Observer{

	private Graphics2D g2d;	
	private ICamembertModel model;
	private ArrayList<Item> listItem;
	private ArrayList<Arc2D.Float> arcs = new ArrayList<>();
	private Arc2D.Float middleArc = new Arc2D.Float(Arc2D.PIE);
	private Arc2D.Float middleArc2 = new Arc2D.Float(Arc2D.PIE);
	private boolean selected=false;
	private String mTexte;
	private double temp=0;
	
	
	public CamembertView(ICamembertModel im) {	
		mTexte = "";
		model = im;
		this.listItem = model.getListItem();

		//add arc for each item 
		for (int i=0; i<listItem.size(); i++){
			Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
			arc.setFrame(125, 125, 250, 250);
			arcs.add(arc);			
		}

		Arc2D.Float middleArc = new Arc2D.Float(Arc2D.PIE);
		Arc2D.Float middleArc2 = new Arc2D.Float(Arc2D.PIE);
	}
	
	
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// TODO: utilisation des données du IModel pour l'affichage
		// g2d.drawString( mTexte , 20, 34);
		
		for (int i=0; i<arcs.size(); i++){
				drawCercle (g2d, arcs.get(i), listItem.get(i));
		}

		middleArc.setFrame(175, 175, 150, 150);
		drawMiddleCercle(g2d, middleArc, this.getBackground());
		
		middleArc2.setFrame(200, 200, 100, 100);
		drawMiddleCercle(g2d, middleArc2, Color.BLUE);
		
		if (this.selected){
			g2d.setColor(Color.RED);
			Point p;
			if ((p = this.getMousePosition())!=null) {
				g2d.drawString( mTexte , (int)p.getX(), (int)p.getY());
			}
		}
		
		g2d.setColor(Color.white);
		g2d.drawString( "Budget total" , 210, 240);
		g2d.drawString(this.model.getTotalItem()+" €" , 235, 260);
		
		super.paintComponent(g);
	}
	
	//Drawing the items cercle
	public void drawCercle ( Graphics2D g2, Arc2D.Float arc, Item item){
		arc.setAngleStart(temp*360/this.model.getTotalItem());
		arc.setAngleExtent(item.getN()*360/this.model.getTotalItem());
		temp = temp + item.getN();
		g2.setColor(Color.BLACK);
		g2.draw(arc);
		g2.setColor(item.getColor());
		g2.fill(arc);
		repaint();
		revalidate();
	}
	
	// drawing the middle cercle
	public void drawMiddleCercle ( Graphics2D g2, Arc2D.Float arc, Color c){
		arc.setAngleStart(0);
		arc.setAngleExtent(360);
		g2.setColor(Color.lightGray);
		g2.draw(arc);
		g2.setColor(c);
		g2.fill(arc);
		repaint();
		revalidate();
	}	

	

	public ArrayList<Arc2D.Float> getArcs() {
		return arcs;
	}

	public void setArcs(ArrayList<Arc2D.Float> arcs) {
		this.arcs = arcs;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getmTexte() {
		return mTexte;
	}

	public void setmTexte(String mTexte) {
		this.mTexte = mTexte;
	}


	@Override
	public void update(Observable o, Object arg) {
		this.listItem = model.getListItem();
		arcs = new ArrayList<>();

		//add arc for each item 
		for (int i=0; i<listItem.size(); i++){
			Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
			arc.setFrame(125, 125, 250, 250);
			arcs.add(arc);			
		}
		
		repaint();
		revalidate();
	}
}