package model;

import java.awt.Color;

public class Item {

	private String titre;
	private String description;
	private int n;
	private Color color;
	private int x;
	private int y;
	final private Color default_color;
	
	
	public Color getDefault_color() {
		return default_color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Item (String title, String description, Color color, int n, int x, int y){
		this.titre=title;
		this.description=description;
		this.color = color;
		this.default_color = this.color;
		this.x=x;
		this.y=y;
		this.n=n;
		
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
	
}
