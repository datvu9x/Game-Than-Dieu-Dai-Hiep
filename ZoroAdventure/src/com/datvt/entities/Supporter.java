package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.datvt.images.ImageLibrary;
import com.datvt.main.GameMain;
import com.datvt.utils.Activities;

public class Supporter extends Actor implements Activities {

	private String name;
	private String content;
	private boolean isChek = false;
	private int id;
	private String complete;
	private int maxPer;
	private int per;
	private boolean isBoss;
	private long tick;
	private boolean mThrow;

	public Supporter(float x, float y, boolean alive, String name, String content, int id, int number, boolean isBoss) {
		positionX = x;
		positionY = y;
		this.name = name;
		this.content = content;
		this.alive = alive;
		this.id = id;
		this.maxPer = number;
		this.isBoss = isBoss;
		init();
	}

	@Override
	public void init() {
		health = healthMax;
		width = 63;
		height = 94;
		image = GameMain.images.get(ImageLibrary.SUPPORT + id);
		setPer(0);
		mThrow = false;
		tick = System.currentTimeMillis();
	}

	@Override
	protected void move() {
		
		if (isBoss) {
			if (System.currentTimeMillis() - tick >= 500) {
				tick = System.currentTimeMillis();
				mThrow = true;
			}
		}
	
	}
	
	public Rectangle getBounds() {
		return new Rectangle();
	}


	public void setShow(boolean check) {
		isChek = check;
	}

	public void setPer(int check) {
		per = check;
		if (per >= maxPer) {
			complete = "Đã hoàn thành";
		}
		if (per <= 0) {
			complete = "Chưa làm";
		} else {
			complete = "Đã làm " + per + "/" + maxPer;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		if (isBoss) {
			g2.drawImage(image, x, y, 40, 100, null);
			g2.setColor(Color.YELLOW);
			if (health <= 50)
				g2.setColor(Color.RED);
			g2.fillRect(x + 5, y - 10, (int) (37 * (health * 1.0 / healthMax)), 3);
			g2.setColor(Color.BLACK);
			g2.drawRect(x + 5, y - 10, 37, 3);
			g2.setColor(Color.RED);
			g2.setFont(new Font("Arial", Font.BOLD, 10));
			g2.drawString(health + "", x + 45, y - 5);
		} else {
			g2.drawImage(image, x, y, null);
		}
		
		g2.setFont(new Font("Arial", Font.BOLD, 12));
		if (isChek) {
			g2.setColor(Color.WHITE);
			g2.drawRect(260, GameMain.HEIGHT - 100, 600, 90);
			g2.drawRect(260, GameMain.HEIGHT - 130, 150, 30);
			g2.fillRect(260, GameMain.HEIGHT - 130, 150, 30);
			g2.setColor(new Color(255, 255, 255, 150));
			g2.fillRect(260, GameMain.HEIGHT - 100, 600, 90);
			g2.drawImage(image, 280, GameMain.HEIGHT - 90, 50, 60, null);
			g2.setColor(Color.RED);
			g2.drawString(content, 350, GameMain.HEIGHT - 60);
			g2.setColor(Color.BLUE);
			g2.drawString(name, 280, GameMain.HEIGHT - 110);
			g2.setColor(Color.BLACK);
			g2.drawString(complete, 350, GameMain.HEIGHT - 40);
		}
		g2.setColor(Color.WHITE);
		g2.drawString(name, positionX - 50, y - 17);
	}

	protected void attack(Actor actor) {
		int result = attack - actor.defense;
		if (result <= 0) {
			result = 1;
		}
		actor.takeDamage(result);
	}

	protected void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			health = 0;
		}
	}

	protected boolean collision(Actor actor) {
		if (actor.alive) {
			float x = actor.positionX;
			float y = actor.positionY;
			if (x >= positionX - width / 2 - 30 && x <= positionX + width / 2 && y >= positionY - height / 2 - 30
					&& y <= positionY + height / 2) {
				return true;
			}
		}
		return false;
	}

	public boolean collisionSkillFigure(ArrayList<SkillFigure> skills) {
		for (SkillFigure skill : skills) {
			if (skill.alive) {
				float x = skill.positionX;
				float y = skill.positionY;
				if (x >= positionX - width / 2 - 30 && x <= positionX + width / 2 && y >= positionY - height / 2
						&& y <= positionY + height / 2) {
					skill.alive = false;
					return true;
				}
			}
		}
		return false;
	}

	public boolean isThrow() {
		return mThrow;
	}

	public void setThrow(boolean mThrow) {
		this.mThrow = mThrow;
	}

}
