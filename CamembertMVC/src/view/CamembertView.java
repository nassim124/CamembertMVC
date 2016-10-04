package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import model.ICamembertModel;
import model.Item;


public class CamembertView extends JComponent implements Observer{

	private Graphics2D g2d;	
	private ICamembertModel model;
	private ArrayList<Arc2D.Float> arcs = new ArrayList<>();
	private Arc2D.Float middleArc = new Arc2D.Float(Arc2D.PIE);
	private Arc2D.Float middleArc2 = new Arc2D.Float(Arc2D.PIE);
	private boolean selected=false;
	private String mTexte;
	private double temp=0;
	private ArrayList<Polygon> arrows;
	private Item selectedItem;


	public CamembertView(ICamembertModel im) {	
		mTexte = "";
		model = im;

		//creer un arc pour chaque item 
		for (int i=0; i<model.getListItem().size(); i++){
			Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
			arc.setFrame(125, 125, 250, 250);
			arcs.add(arc);			
		}

		// arcs pour les cercle du milieu
		Arc2D.Float middleArc = new Arc2D.Float(Arc2D.PIE);
		Arc2D.Float middleArc2 = new Arc2D.Float(Arc2D.PIE);

		arrows = new ArrayList<Polygon>();
	}


	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// dessiner les arcs des items 
		for (int i=0; i<arcs.size(); i++){
			drawCercle (g2d, arcs.get(i), model.getListItem().get(i));
		}

		// dessiner les cercles du milieu
		middleArc.setFrame(175, 175, 150, 150);
		drawMiddleCercle(g2d, middleArc, this.getBackground());
		middleArc2.setFrame(200, 200, 100, 100);
		drawMiddleCercle(g2d, middleArc2, Color.BLUE);

		
		// afficher le titre de l'item a coté du curseur de la souris
		if (this.selected){
			g2d.setColor(Color.RED);
			Point p;
			if ((p = this.getMousePosition())!=null) {
				g2d.drawString( mTexte , (int)p.getX(), (int)p.getY());
			}
		}

		// afficher le budget total au milieu
		g2d.setColor(Color.white);
		g2d.drawString( "Budget total" , 210, 240);
		g2d.drawString(this.model.getTotalItem()+" €" , 235, 260);

		Polygon arrow1 = new Polygon();
		Polygon arrow2 = new Polygon();

		// initialisation des points des fleches 
		arrow1.addPoint(450, 10);
		arrow1.addPoint(430, 40);
		arrow1.addPoint(470, 40);
		arrow2.addPoint(450, 80);
		arrow2.addPoint(430, 50);
		arrow2.addPoint(470, 50);
		
		//ajouter les fleches au tableau arrows
		arrows.add(arrow1);
		arrows.add(arrow2);


		g2d.setColor(Color.white);
		g2d.fillRect (450, 200, 200, 200);

		//affiher la description 
		g2d.setColor(Color.BLACK);
		if (this.selected){

			if (selectedItem != null){
				g2d.drawString( selectedItem.getDescription() , 500, 220);
			}
		}

		// dessiner les fleches de navigation dans g2d
		for (Polygon p : arrows) {
			g2d.setColor(Color.GRAY);
			g2d.fill(p);
		}

		super.paintComponent(g);
	}

	//methode pour dessiner une portion d'arc pour l'arc passé en parametre 
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

	// methode pour dessiner le cercle du milieu
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


	
	public Item getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}


	public ArrayList<Polygon> getArrows() {
		return arrows;
	}	

	public ArrayList<Arc2D.Float> getArcs() {
		return arcs;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setmTexte(String mTexte) {
		this.mTexte = mTexte;
	}


	@Override
	public void update(Observable o, Object arg) {

		arcs = new ArrayList<>();

		//add arc for each item 
		for (int i=0; i<model.getListItem().size(); i++){
			Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
			arc.setFrame(125, 125, 250, 250);
			arcs.add(arc);			
		}

		repaint();
		revalidate();
	}
}