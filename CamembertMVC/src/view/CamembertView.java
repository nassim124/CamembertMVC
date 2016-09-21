package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.IController;
import model.ICamembertModel;
import model.Item;


public class CamembertView extends JComponent implements MouseListener
{

	Graphics2D g2d;	
	ICamembertModel model;
	IController controller;
	ArrayList<Item> listItem;
	ArrayList<Arc2D.Float> arcs = new ArrayList<>();
	
	boolean selected=false;
		
	String mTexte;
	private double temp=0;
	
	public CamembertView(ICamembertModel im, IController ic) {
		
		mTexte = new String("Hello");
		model = im;
		controller = ic;
		
		
		this.listItem = model.getListItem();
				
		//add arc for each item 
		for (int i=0; i<listItem.size(); i++){
			Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
			arc.setFrame(150, 150, listItem.get(i).getX(), listItem.get(i).getY());
			arcs.add(arc);
		}

		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		
		Dimension d = getSize();

		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// TODO: utilisation des données du IModel pour l'affichage
		g2d.drawString( mTexte , 20, 34);
		
		
		for (int i=0; i<arcs.size(); i++){
			drawCercle (g2d, arcs.get(i), listItem.get(i));
		}
		
		
		super.paintComponent(g);
	}
	
	//Drawing the ring
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
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		// TODO: vérifier si un quartier de camembert a été selectionné 
		// et renvoyer vers le controlleur 

	}
	

	public ArrayList<Arc2D.Float> getArcs() {
		return arcs;
	}

	public void setArcs(ArrayList<Arc2D.Float> arcs) {
		this.arcs = arcs;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
				
		for (int i=0; i< listItem.size(); i++){
				this.listItem.get(i).setColor(listItem.get(i).getDefault_color());
		}
		repaint();
		revalidate();
		for (int i=0; i< listItem.size(); i++){
			if (this.arcs.get(i).contains(e.getX(), e.getY())){
				this.listItem.get(i).setColor(Color.BLACK);
			}
		}
		mTexte = "Mouse at "+e.getX()+"x"+e.getY();
		repaint();
		revalidate();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}