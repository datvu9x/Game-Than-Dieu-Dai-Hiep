package com.datvt.entities;

import java.awt.Graphics2D;

import com.datvt.main.GameMain;
import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class SkillMonster extends Actor implements Activities {

	private static final float SPEED = 2.5f;

	private Animation skillRight, skillLeft, skillUp, skillDown, ball, skillUpCheo, skillDownCheo;
	private int id;
	private float x, y;
	private int type;

	public SkillMonster(float x, float y, int orient, int id, int type) {
		this.positionX = x;
		this.positionY = y;
		this.orient = orient;
		this.id = id;
		this.type = type;
		init();
	}

	@Override
	public void init() {
		skillRight = new Animation(150, Assets.sFireRight);
		skillLeft = new Animation(150, Assets.sFireLeft);
		skillUp = new Animation(150, Assets.sFireUp);
		skillDown = new Animation(150, Assets.sFireDown);
		skillUpCheo = new Animation(150, Assets.FireLeft);
		skillDownCheo = new Animation(150, Assets.FireRight);
		ball = new Animation(150, Assets.ball);
		alive = true;
	}

	@Override
	protected void move() {
		skillRight.tick();
		skillLeft.tick();
		skillUp.tick();
		skillDown.tick();
		skillUpCheo.tick();
		skillDownCheo.tick();
		ball.tick();
		if (orient == 1) {
			image = skillLeft.getCurrentFrame();
			positionX -= SPEED;
			if (positionX <= 0) {
				alive = false;
			}
		} else if (orient == 0) {
			image = skillRight.getCurrentFrame();
			positionX += SPEED;
			if (positionX >= GameMain.WIDTH) {
				alive = false;
			}
		} else if (orient == 3) {
			image = skillUp.getCurrentFrame();
			positionY -= SPEED;
			if (positionY <= 0) {
				alive = false;
			}
		} else {
			if (id == 1)
				image = skillDown.getCurrentFrame();
			else
				image = ball.getCurrentFrame();
			positionY += SPEED;
			if (positionY >= y + 200) {
				alive = false;
			}
		}

		if (type == 1) {
			image = skillUpCheo.getCurrentFrame();
			positionY -= SPEED ;
			positionX -= SPEED;
			if (positionY <= 0 || positionX <= 0) {
				alive = false;
			}

		} else if (type == 2) {
			image = skillDownCheo.getCurrentFrame();
			positionY += SPEED ;
			positionX -= SPEED;
			if (positionY >= GameMain.HEIGHT || positionX >= GameMain.WIDTH) {
				alive = false;
			}

		}
	}

	public void setPositionThrow(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x, y, null);

		if (type == 1) {
			g2.drawImage(image, x, y, null);
		}

		if (type == 2) {
			g2.drawImage(image, x, y, null);
		}
	}

	@Override
	protected void attack(Actor actor) {
	}

	@Override
	protected void takeDamage(int damage) {
	}

	@Override
	protected boolean collision(Actor actor) {
		return false;
	}
}
