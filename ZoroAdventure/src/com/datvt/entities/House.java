package com.datvt.entities;

import java.awt.Graphics2D;

import com.datvt.images.ImageLibrary;
import com.datvt.main.GameMain;
import com.datvt.utils.Activities;

public class House extends Actor implements Activities {

	private int id;
	private int width;
	private int height;
	

	public House(float x, float y, int id, int w, int h, 
			String text, String name) {
		this.positionX = x;
		this.positionY = y;
		this.id = id;
		this.width = w;
		this.height = h;
		init();
		
	}

	public void init() {
		image = GameMain.images.get(ImageLibrary.HOUSE + id);
	}
	

	protected void move() {
	}

	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		if (width != 0 && height != 0) {
			g2.drawImage(image, x, y, width, height, null);
		} else {
			g2.drawImage(image, x, y, null);
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected void attack(Actor actor) {
	}

	protected void takeDamage(int damage) {
	}

	@Override
	protected boolean collision(Actor actor) {
		return false;
	}
}
