package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.datvt.audios.AudioLibrary;
import com.datvt.images.ImageLibrary;
import com.datvt.main.About;
import com.datvt.main.GameMain;
import com.datvt.main.Help;
import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;
import com.datvt.utils.InfoFigure;
import com.datvt.utils.Map;
import com.datvt.utils.SaveGame;

public class InterfaceGame implements Activities, KeyListener {

	public static final int DURATION_ADD_DAMAGE = 1000;
	public static final int DURATION_MAP_UP = 2000;
	public static final int DURATION_LEVEL_UP = 3000;
	public static final float PERCENT = 5f;
	public static final float SPEED = 2f;
	public static final String FONT_NAME = "Cloudy";

	private Map map, map1, map2, map3, map4, map5;
	private Figure figure;
	private Monster spider, spider1, spider2, spider3, spider4, spider5;
	private MonsterKuma kuma, kuma1, kuma2, kuma3, kuma4;
	private MonsterSky bird, bird1;
	private MonsterWater dragonWater;
	private MonsterDragon dragonLand, dragonLand1;
	private MonsterIdle giant, giant1, giant2, giant3, giant4;
	private Supporter sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9;
	private ArrayList<SkillFigure> skillFigure;
	private ArrayList<SkillMonster> skillMonster;
	private ArrayList<Explosion> explosionList;
	private ArrayList<Effect> effectList;
	private ArrayList<Item> mItem;
	private InfoFigure infoFigure;
	private SaveGame saveGame;
	private Animation animiGate;

	private Random random;
	private String[] options = { "Start", "Help", "About", "Quit" };
	private String[] over = { "Replay", "Quit" };
	private Image iMap, iTree, iTree1, iTree2, iTree3, iTree4, iTree5, iHouse, iHouse1, iHouse2, iHouse3, info, iGate,
			iWater, startGame, gameOver, optionMenu, iLevelUp;
	private Image introduction;
	private int[][] maps, maps1, maps2, maps3, maps4, maps5;
	private int colMap, rowMap;
	private int currentSelection = 0, select = 0, temp, currentHP, previousHP, hp, mp, count, count1, count2, countBoss;
	private float positionX, positionY;
	private long tick, EXP;
	private boolean mPlaying, mGameOver, damage, checkMap, checkLevel, checkUp, checkEnd;
	private boolean mIntroduction;
	private boolean check, check1, check2, check3, check4, check5, check6, check7, check8, check9;
	public static boolean checkMap0, checkMap1, checkMap2, checkMap3, checkMap4, checkMap5;
	private boolean checkItem, checkItem1, checkItem2, checkItem3, checkItem4, checkItem5, checkItem6, checkItem7,
			checkItem8, checkItem9;

	private long countTime = 0;
	private long index = 0;
	private int number1, number2, number3;
	private boolean boss, finish;

	public InterfaceGame() {
		init();
	}

	public void init() {
		Assets.init();
		animiGate = new Animation(150, Assets.win);

		//
		map = new Map("res/map.txt");
		map1 = new Map("res/map1.txt");
		map2 = new Map("res/map2.txt");
		map3 = new Map("res/map3.txt");
		map4 = new Map("res/map4.txt");
		map5 = new Map("res/map5.txt");
		maps = map.getMaps();
		maps1 = map1.getMaps();
		maps2 = map2.getMaps();
		maps3 = map3.getMaps();
		maps4 = map4.getMaps();
		maps5 = map5.getMaps();
		colMap = map.getCol();
		rowMap = map.getRow();
		//
		startGame = GameMain.images.get(ImageLibrary.START_GAME);
		gameOver = GameMain.images.get(ImageLibrary.GAME_OVER);
		optionMenu = GameMain.images.get(ImageLibrary.OPTION_MENU);
		iMap = GameMain.images.get(ImageLibrary.MAP + 0);
		introduction = GameMain.images.get(ImageLibrary.INTRODUCTION + index);
		iHouse = GameMain.images.get(ImageLibrary.HOUSE + 0);
		iHouse1 = GameMain.images.get(ImageLibrary.HOUSE + 1);
		iHouse2 = GameMain.images.get(ImageLibrary.HOUSE + 4);
		iHouse3 = GameMain.images.get(ImageLibrary.HOUSE + 3);
		iTree = GameMain.images.get(ImageLibrary.TREE + 0);
		iTree1 = GameMain.images.get(ImageLibrary.TREE + 1);
		iTree2 = GameMain.images.get(ImageLibrary.TREE + 2);
		iTree3 = GameMain.images.get(ImageLibrary.TREE + 5);
		iTree4 = GameMain.images.get(ImageLibrary.TREE + 6);
		iTree5 = GameMain.images.get(ImageLibrary.TREE + 4);
		iWater = GameMain.images.get(ImageLibrary.WATER);
		info = GameMain.images.get(ImageLibrary.INFO_FIGURE);
		iLevelUp = GameMain.images.get(ImageLibrary.LEVEL_UP);
		iGate = animiGate.getCurrentFrame();

		//
		figure = new Figure(500, 250, 500, 500);
		spider = new Monster(1000, 550, 1, true, 50, 100, 200);
		spider1 = new Monster(500, 620, 0, true, 50, 100, 200);
		spider2 = new Monster(1000, 550, 1, true, 50, 100, 200);
		spider3 = new Monster(500, 450, 0, true, 50, 100, 200);
		spider4 = new Monster(500, 600, 1, true, 50, 100, 200);
		spider5 = new Monster(700, 470, 0, true, 50, 100, 200);
		kuma = new MonsterKuma(550, 65, 1, true, 200, 300, 500);
		kuma1 = new MonsterKuma(400, 115, 0, true, 200, 300, 500);
		kuma2 = new MonsterKuma(600, 480, 1, true, 200, 140, 600);
		kuma3 = new MonsterKuma(400, 360, 0, true, 200, 140, 600);
		kuma4 = new MonsterKuma(550, 400, 1, true, 200, 140, 600);
		dragonWater = new MonsterWater(200, 600, 1, true, 100, 150, 300);
		dragonLand = new MonsterDragon(930, 620, 2, true, 200, 150, 300);
		dragonLand1 = new MonsterDragon(1000, 200, 3, true, 200, 150, 300);
		giant = new MonsterIdle(120, 300, 0, true, 300, 200, 200);
		giant1 = new MonsterIdle(60, 350, 1, true, 300, 200, 200);
		giant2 = new MonsterIdle(820, 260, 0, true, 300, 200, 200);
		giant3 = new MonsterIdle(860, 300, 0, true, 300, 200, 200);
		giant4 = new MonsterIdle(900, 260, 0, true, 300, 200, 200);
		bird = new MonsterSky(200, 710, 2, true, 10, 200, 100);
		bird1 = new MonsterSky(800, -10, 3, true, 10, 200, 100);

		sp1 = new Supporter(350, 280, true, "Tạ Tốn", "Tiêu diệt 3 con quái vật cầm rìu ở rừng cây bên kia !", 0, 3,
				false);
		sp2 = new Supporter(750, 210, true, "Nhất Đăng Đại Sư",
				"Tiêu diệt 1 Người Gấu đoạt bí kíp Ám Nhân Tiêu Hồn Chưởng !", 2, 1, false);
		sp3 = new Supporter(800, 520, true, "Hồng Thất Công", "Tiêu diệt 5 con rồng đỏ để nhận bí kíp Bài Vân Chưởng !",
				1, 5, false);
		sp4 = new Supporter(335, 200, true, "Lý Mạc Sầu", "Tiêu diệt 7 con quái vật cầm rìu !", 3, 7, false);
		sp5 = new Supporter(230, 230, true, "Gia Luật Tề", "Tiêu diệt 10 con nhện tám chân !", 4, 10, false);
		sp6 = new Supporter(430, 165, true, "Hoàng Dược Sư", "Tiêu diệt 3 người Gấu !", 6, 3, false);
		sp7 = new Supporter(165, 210, true, "Tiểu Long Nữ", "Đánh bại Kim Luân Pháp Vương !", 5, 1, false);

		sp8 = new Supporter(820, 90, true, "Kim Luân Pháp Vương", "Muốn khiêu chiến à ! Nhào zô", 7, 1, false);
		sp9 = new Supporter(890, 360, true, "Kim Luân Pháp Vương", "Muốn khiêu chiến à ! Nhào zô", 7, 1, true);
		sp9.alive = true;
		sp9.attack = 500;
		sp9.defense = 500;
		sp9.healthMax = 5000;
		sp9.health = 5000;

		skillFigure = new ArrayList<SkillFigure>();
		skillMonster = new ArrayList<SkillMonster>();
		explosionList = new ArrayList<Explosion>();
		effectList = new ArrayList<Effect>();
		mItem = new ArrayList<Item>();
		saveGame = new SaveGame();

		// Load Game
		String[] info = saveGame.loadGame();
		if (info != null) {
			if (Integer.parseInt(info[0]) > 0) {
				figure.health = Integer.parseInt(info[0]);
			}
			figure.setMana(Integer.parseInt(info[1]));
			figure.healthMax = Integer.parseInt(info[2]);
			figure.setManaMax(Integer.parseInt(info[3]));
			figure.attack = Integer.parseInt(info[4]);
			figure.defense = Integer.parseInt(info[5]);
			figure.setEXP(Long.valueOf(info[6]));
			figure.setLevel(Integer.parseInt(info[7]));
		}
		//
		random = new Random();
		mPlaying = mGameOver = damage = checkMap = checkLevel = checkUp = checkEnd = false;
		mIntroduction = false;
		// Check Collision Figure
		check = check1 = check3 = check2 = check4 = check5 = check6 = check7 = check8 = check9 = false;
		checkMap0 = true;
		checkMap1 = checkMap2 = checkMap3 = checkMap4 = checkMap5 = false;
		temp = 0; // Check Map Next
		// Check Item
		checkItem = checkItem1 = checkItem2 = checkItem3 = checkItem4 = checkItem5 = checkItem6 = checkItem7 = checkItem8 = checkItem9 = true;
		EXP = 0;
		hp = mp = count = count1 = count2 = countBoss = 0;
		boss = false;
		finish = false;
	}

	// Update State Game
	public void update() {
		if (mPlaying && !mGameOver) {
			animiGate.tick();
			iGate = animiGate.getCurrentFrame();
			count++;
			count1++;
			count2++;

			save();
			updateFigure();
			updateMonster();
			updateItem();
			updateMap();
			updateLevel();
			updateHPMP();
			updateDamage();
			updateExplosion();
			updateEffect();
			updateSupport();
			updateBoss();
		}
		if (mIntroduction) {
			updateIntroduction();
		}
	}

	// Update Figure
	private void updateFigure() {
		if (figure.alive) {
			figure.move();
			if (figure.collisionSkillMonster(skillMonster)) {
				int damage = random.nextInt(50);
				figure.takeDamage(damage);
				explosionList.add(new Explosion(figure.positionX - 25, figure.positionY - 10));
			}

			// Check Skill Figure
			if (check) {
				check = false;
				if (figure.getMana() > 0) {
					GameMain.audios.get(AudioLibrary.SKILL).play();
					skillFigure.add(new SkillFigure(figure.positionX + 80, figure.positionY - 10, 0));
				}
				figure.setMana(figure.getMana() - 10);
				if (figure.getMana() <= 0) {
					figure.setMana(0);
				}
			}
			if (check1) {
				check1 = false;
				if (figure.getMana() > 0) {
					GameMain.audios.get(AudioLibrary.SKILL).play();
					skillFigure.add(new SkillFigure(figure.positionX - 120, figure.positionY - 10, 1));
				}
				figure.setMana(figure.getMana() - 10);
				if (figure.getMana() <= 0) {
					figure.setMana(0);
				}
			}
			// Up Level, EXP
			if (EXP > figure.getEXP()) {
				figure.setEXP(EXP);
			}
			if (figure.getEXP() >= figure.getMaxEXP()) {
				checkLevel = true;
				EXP = 0;
				GameMain.audios.get(AudioLibrary.UP_LEVEL).play();
				figure.setEXP(figure.getEXP() - figure.getMaxEXP());
				figure.setMaxEXP(figure.getMaxEXP() + 50000);
				figure.setLevel(figure.getLevel() + 1);
				figure.attack += 50;
				figure.defense += 50;
				figure.healthMax += 100;
				figure.setManaMax(figure.getManaMax() + 100);
				figure.health = figure.healthMax;
				figure.setMana(figure.getManaMax());
			}
			// Skill
			for (int index = 0; index < skillFigure.size(); index++) {
				SkillFigure skill = skillFigure.get(index);
				if (skill.collisionSkillMonster(skill, skillMonster)) {
					GameMain.audios.get(AudioLibrary.EXPLOSION).play();
					skill.alive = false;
					explosionList.add(new Explosion(skill.positionX, skill.positionY));
				}
				skill.move();
				if (!skill.alive) {
					skillFigure.remove(index);
				}
			}
		} else {
			if (figure.orient == 0) {
				figure.image = GameMain.images.get(ImageLibrary.DIE + 0);
			} else {
				figure.image = GameMain.images.get(ImageLibrary.DIE + 1);
			}
			checkEnd = true;
		}
	}

	// Update Monster
	private void updateMonster() {
		updateSpider();
		updateKuma();
		updateDragon();
		updateBird();
		updateGiant();
		updateMonsterWater();
		updateSupport();
		// Update Skill Monster
		for (int index = 0; index < skillMonster.size(); index++) {
			SkillMonster skill = skillMonster.get(index);
			skill.move();
			if (!skill.alive) {
				skillMonster.remove(index);
			}
		}
	}

	// Spider
	private void updateSpider() {
		if (temp == 0) {

			if (spider.alive) {
				if (spider.positionX <= 20) {
					spider.orient = 1;
				} else if (spider.positionX >= GameMain.WIDTH - 10) {
					spider.orient = 0;
				}
				//
				if (spider.collision(dragonLand) || spider.collision(giant) || spider.collision(kuma)
						|| spider.collision(dragonLand1) || spider.collision(giant1) || spider.collision(kuma1)) {
					if (spider.orient == 0) {
						spider.orient = 1;
					} else if (spider.orient == 1) {
						spider.orient = 0;
					}
				}
				//
				if ((spider.positionX < figure.positionX - figure.width / 2)
						|| (spider.positionX > figure.positionX + figure.width / 2)) {
					if (spider.orient == 5) {
						spider.orient = 1;
					}
					if (spider.orient == 6) {
						spider.orient = 0;
					}
				}
				//
				if (spider.collisionSkillFigure(skillFigure) || check2) {
					effectList.add(new Effect(spider.positionX - 30, spider.positionY, 1));
					check2 = false;
					damage = true;
					previousHP = spider.health;
					figure.attack(spider);
					currentHP = spider.health;
					tick = System.currentTimeMillis();
					positionX = spider.positionX;
					positionY = spider.positionY;
				}
				//
				if (spider.collision(figure)) {
					if (spider.orient == 1) {
						spider.orient = 5;
					}
					if (spider.orient == 0) {
						spider.orient = 6;
					}
					spider.attack(figure);
				}
				//
				spider.move();
				//
				if (spider.health <= 0)
					spider.alive = false;
			} else {
				effectList.add(new Effect(spider.positionX, spider.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				spider = new Monster(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT - 50),
						random.nextInt(2), true, 50 + 50 * figure.getLevel(), 100 + 50 * figure.getLevel(),
						200 + 50 * figure.getLevel());
				EXP += (Math.abs(figure.attack - spider1.defense) / 4) * 10;
			}
			if (spider1.alive) {
				if (spider1.positionX <= 20) {
					spider1.orient = 1;
				} else if (spider1.positionX >= GameMain.WIDTH - 10) {
					spider1.orient = 0;
				}
				//
				if (spider1.collision(dragonLand) || spider1.collision(giant) || spider1.collision(kuma)
						|| spider1.collision(dragonLand1) || spider1.collision(giant1) || spider1.collision(kuma1)) {
					if (spider1.orient == 0) {
						spider1.orient = 1;
					} else if (spider1.orient == 1) {
						spider1.orient = 0;
					}
				}
				//
				if ((spider1.positionX < figure.positionX - figure.width / 2)
						|| (spider1.positionX > figure.positionX + figure.width / 2)) {
					if (spider1.orient == 5) {
						spider1.orient = 1;
					}
					if (spider1.orient == 6) {
						spider1.orient = 0;
					}
				}
				//
				if (spider1.collisionSkillFigure(skillFigure) || check8) {
					effectList.add(new Effect(spider1.positionX - 30, spider1.positionY, 1));
					check8 = false;
					damage = true;
					previousHP = spider1.health;
					figure.attack(spider1);
					currentHP = spider1.health;
					tick = System.currentTimeMillis();
					positionX = spider1.positionX;
					positionY = spider1.positionY;
				}
				//
				if (spider1.collision(figure)) {
					if (spider1.orient == 1) {
						spider1.orient = 5;
					}
					if (spider1.orient == 0) {
						spider1.orient = 6;
					}
					spider1.attack(figure);
				}
				spider1.move();
				//
				if (spider1.health <= 0)
					spider1.alive = false;
			} else {
				effectList.add(new Effect(spider1.positionX, spider1.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				spider1 = new Monster(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT - 50),
						random.nextInt(2), true, 50 + 50 * figure.getLevel(), 100 + 50 * figure.getLevel(),
						200 + 50 * figure.getLevel());
				EXP += (Math.abs(figure.attack - spider1.defense) / 4) * 10;
			}

		} else if (temp == 2) {

			if (spider2.alive) {
				if (spider2.positionX <= 20) {
					spider2.orient = 1;
				} else if (spider2.positionX >= GameMain.WIDTH - 10) {
					spider2.orient = 0;
				}
				//
				if ((spider2.positionX < figure.positionX - figure.width / 2)
						|| (spider2.positionX > figure.positionX + figure.width / 2)) {
					if (spider2.orient == 5) {
						spider2.orient = 1;
					}
					if (spider2.orient == 6) {
						spider2.orient = 0;
					}
				}
				//
				if (spider2.collisionSkillFigure(skillFigure) || check2) {
					effectList.add(new Effect(spider2.positionX - 30, spider2.positionY, 1));
					check2 = false;
					damage = true;
					previousHP = spider2.health;
					figure.attack(spider2);
					currentHP = spider2.health;
					tick = System.currentTimeMillis();
					positionX = spider2.positionX;
					positionY = spider2.positionY;
				}
				//
				if (spider2.collision(figure)) {
					if (spider2.orient == 1) {
						spider2.orient = 5;
					}
					if (spider2.orient == 0) {
						spider2.orient = 6;
					}
					spider2.attack(figure);
				}
				//
				spider2.move();
				//
				if (spider2.health <= 0)
					spider2.alive = false;
			} else {
				effectList.add(new Effect(spider2.positionX, spider2.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				spider2 = new Monster(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT - 50),
						random.nextInt(2), true, 50 + 50 * figure.getLevel(), 100 + 50 * figure.getLevel(),
						200 + 50 * figure.getLevel());
				EXP += (Math.abs(figure.attack - spider2.defense) / 4) * 10;
			}

			if (spider3.alive) {
				if (spider3.positionX <= 20) {
					spider3.orient = 1;
				} else if (spider3.positionX >= GameMain.WIDTH - 10) {
					spider3.orient = 0;
				}
				//
				if ((spider3.positionX < figure.positionX - figure.width / 2)
						|| (spider3.positionX > figure.positionX + figure.width / 2)) {
					if (spider3.orient == 5) {
						spider3.orient = 1;
					}
					if (spider3.orient == 6) {
						spider3.orient = 0;
					}
				}
				//
				if (spider3.collisionSkillFigure(skillFigure) || check8) {
					effectList.add(new Effect(spider3.positionX - 30, spider3.positionY, 1));
					check8 = false;
					damage = true;
					previousHP = spider3.health;
					figure.attack(spider3);
					currentHP = spider3.health;
					tick = System.currentTimeMillis();
					positionX = spider3.positionX;
					positionY = spider3.positionY;
				}
				//
				if (spider3.collision(figure)) {
					if (spider3.orient == 1) {
						spider3.orient = 5;
					}
					if (spider3.orient == 0) {
						spider3.orient = 6;
					}
					spider3.attack(figure);
				}
				spider3.move();
				//
				if (spider3.health <= 0)
					spider3.alive = false;
			} else {
				effectList.add(new Effect(spider3.positionX, spider3.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				spider3 = new Monster(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT - 50),
						random.nextInt(2), true, 50 + 50 * figure.getLevel(), 100 + 50 * figure.getLevel(),
						200 + 50 * figure.getLevel());
				EXP += (Math.abs(figure.attack - spider3.defense) / 4) * 10;
			}

			if (spider4.alive) {
				if (spider4.positionX <= 20) {
					spider4.orient = 1;
				} else if (spider4.positionX >= GameMain.WIDTH - 10) {
					spider4.orient = 0;
				}

				if ((spider4.positionX < figure.positionX - figure.width / 2)
						|| (spider4.positionX > figure.positionX + figure.width / 2)) {
					if (spider4.orient == 5) {
						spider4.orient = 1;
					}
					if (spider4.orient == 6) {
						spider4.orient = 0;
					}
				}
				//
				if (spider4.collisionSkillFigure(skillFigure) || check8) {
					effectList.add(new Effect(spider4.positionX - 30, spider4.positionY, 1));
					check8 = false;
					damage = true;
					previousHP = spider4.health;
					figure.attack(spider4);
					currentHP = spider4.health;
					tick = System.currentTimeMillis();
					positionX = spider4.positionX;
					positionY = spider4.positionY;
				}
				//
				if (spider4.collision(figure)) {
					if (spider4.orient == 1) {
						spider4.orient = 5;
					}
					if (spider4.orient == 0) {
						spider4.orient = 6;
					}
					spider4.attack(figure);
				}
				spider4.move();
				//
				if (spider4.health <= 0)
					spider4.alive = false;
			} else {
				effectList.add(new Effect(spider4.positionX, spider4.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				spider4 = new Monster(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT - 50),
						random.nextInt(2), true, 50 + 50 * figure.getLevel(), 100 + 50 * figure.getLevel(),
						200 + 50 * figure.getLevel());
				EXP += (Math.abs(figure.attack - spider4.defense) / 4) * 10;
			}

			if (spider5.alive) {
				if (spider5.positionX <= 20) {
					spider5.orient = 1;
				} else if (spider5.positionX >= GameMain.WIDTH - 10) {
					spider5.orient = 0;
				}

				if ((spider5.positionX < figure.positionX - figure.width / 2)
						|| (spider5.positionX > figure.positionX + figure.width / 2)) {
					if (spider5.orient == 5) {
						spider5.orient = 1;
					}
					if (spider5.orient == 6) {
						spider5.orient = 0;
					}
				}
				//
				if (spider5.collisionSkillFigure(skillFigure) || check8) {
					effectList.add(new Effect(spider5.positionX - 30, spider5.positionY, 1));
					check8 = false;
					damage = true;
					previousHP = spider5.health;
					figure.attack(spider5);
					currentHP = spider5.health;
					tick = System.currentTimeMillis();
					positionX = spider5.positionX;
					positionY = spider5.positionY;
				}
				//
				if (spider5.collision(figure)) {
					if (spider5.orient == 1) {
						spider5.orient = 5;
					}
					if (spider5.orient == 0) {
						spider5.orient = 6;
					}
					spider5.attack(figure);
				}
				spider5.move();
				//
				if (spider5.health <= 0)
					spider5.alive = false;
			} else {
				effectList.add(new Effect(spider5.positionX, spider5.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				spider5 = new Monster(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT - 50),
						random.nextInt(2), true, 50 + 50 * figure.getLevel(), 100 + 50 * figure.getLevel(),
						200 + 50 * figure.getLevel());
				EXP += (Math.abs(figure.attack - spider5.defense) / 4) * 10;
			}

		}

	}

	// Kuma
	private void updateKuma() {

		if (temp == 0) {

			if (kuma.alive) {
				if (kuma.positionX <= 20) {
					kuma.orient = 1;
				} else if (kuma.positionX >= GameMain.WIDTH - 10) {
					kuma.orient = 0;
				}
				//
				if (kuma.collision(dragonLand) || kuma.collision(giant) || kuma.collision(spider)
						|| kuma.collision(dragonLand1) || kuma.collision(giant1) || kuma.collision(spider1)) {
					if (kuma.orient == 0) {
						kuma.orient = 1;
					} else if (kuma.orient == 1) {
						kuma.orient = 0;
					}
				}
				//
				if ((kuma.positionX < figure.positionX - figure.width / 2 - 100)
						|| (kuma.positionX > figure.positionX + figure.width / 2 + 100)) {
					if (kuma.orient == 5) {
						kuma.orient = 1;
					}
					if (kuma.orient == 6) {
						kuma.orient = 0;
					}
				}
				//
				if (kuma.collisionSkillFigure(skillFigure) || check6) {
					effectList.add(new Effect(kuma.positionX - 30, kuma.positionY, 1));
					check6 = false;
					damage = true;
					previousHP = kuma.health;
					figure.attack(kuma);
					currentHP = kuma.health;
					tick = System.currentTimeMillis();
					positionX = kuma.positionX;
					positionY = kuma.positionY;
				}
				//
				if (kuma.collision(figure)) {
					if (kuma.orient == 1 || figure.orient == 1) {
						kuma.orient = 5;
					}
					if (kuma.orient == 0 || figure.orient == 0) {
						kuma.orient = 6;
					}
					int dama = (kuma.attack - figure.defense) / 4;
					if (dama <= 0)
						dama = 1;
					figure.checkDamage(dama);
				}
				kuma.move();
				//
				if (kuma.health <= 0) {
					kuma.alive = false;
					number2++;
					if (number2 <= 1) {
						figure.updateNV(2, number2);
					} else {
						number2 = 0;
					}
				}
			} else {
				effectList.add(new Effect(kuma.positionX, kuma.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				kuma = new MonsterKuma(random.nextInt(70) + 50, random.nextInt(100) + 400, random.nextInt(2), true,
						350 + figure.getLevel() * 50, 600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - kuma.defense) / 2) * 20;
			}

			if (kuma1.alive) {
				if (kuma1.positionX <= 20) {
					kuma1.orient = 1;
				} else if (kuma1.positionX >= GameMain.WIDTH - 10) {
					kuma1.orient = 0;
				}
				//
				if (kuma1.collision(dragonLand) || kuma1.collision(giant) || kuma1.collision(spider)
						|| kuma1.collision(dragonLand1) || kuma1.collision(giant1) || kuma1.collision(spider1)) {
					if (kuma1.orient == 0) {
						kuma1.orient = 1;
					} else if (kuma1.orient == 1) {
						kuma1.orient = 0;
					}
				}
				//
				if ((kuma1.positionX < figure.positionX - figure.width / 2 - 100)
						|| (kuma1.positionX > figure.positionX + figure.width / 2 + 100)) {
					if (kuma1.orient == 5) {
						kuma1.orient = 1;
					}
					if (kuma1.orient == 6) {
						kuma1.orient = 0;
					}
				}
				//
				if (kuma1.collisionSkillFigure(skillFigure) || check9) {
					effectList.add(new Effect(kuma1.positionX - 30, kuma1.positionY, 1));
					check9 = false;
					damage = true;
					previousHP = kuma1.health;
					figure.attack(kuma1);
					currentHP = kuma1.health;
					tick = System.currentTimeMillis();
					positionX = kuma1.positionX;
					positionY = kuma1.positionY;
				}
				//
				if (kuma1.collision(figure)) {
					if (kuma1.orient == 1 || figure.orient == 1) {
						kuma1.orient = 5;
					}
					if (kuma1.orient == 0 || figure.orient == 0) {
						kuma1.orient = 6;
					}
					int dama = (kuma1.attack - figure.defense) / 4;
					if (dama <= 0)
						dama = 1;
					figure.checkDamage(dama);
				}
				kuma1.move();
				//
				if (kuma1.health <= 0)
					kuma1.alive = false;
			} else {
				effectList.add(new Effect(kuma1.positionX, kuma1.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				kuma1 = new MonsterKuma(random.nextInt(70) + 50, random.nextInt(100) + 400, random.nextInt(2), true,
						350 + figure.getLevel() * 50, 600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - kuma1.defense) / 2) * 20;
			}

		}

		if (temp == 3) {

			/////////////////////
			if (kuma2.alive) {
				if (kuma2.positionX <= 20) {
					kuma2.orient = 1;
				} else if (kuma2.positionX >= GameMain.WIDTH - 10) {
					kuma2.orient = 0;
				}
				//
				if (kuma2.collision(kuma3) || kuma.collision(kuma4)) {
					if (kuma2.orient == 0) {
						kuma2.orient = 1;
					} else if (kuma2.orient == 1) {
						kuma2.orient = 0;
					}
				}
				//
				if ((kuma2.positionX < figure.positionX - figure.width / 2 - 100)
						|| (kuma2.positionX > figure.positionX + figure.width / 2 + 100)) {
					if (kuma2.orient == 5) {
						kuma2.orient = 1;
					}
					if (kuma2.orient == 6) {
						kuma2.orient = 0;
					}
				}
				//
				if (kuma2.collisionSkillFigure(skillFigure) || check6) {
					effectList.add(new Effect(kuma2.positionX - 30, kuma2.positionY, 1));
					check6 = false;
					damage = true;
					previousHP = kuma2.health;
					figure.attack(kuma2);
					currentHP = kuma2.health;
					tick = System.currentTimeMillis();
					positionX = kuma2.positionX;
					positionY = kuma2.positionY;
				}
				//
				if (kuma2.collision(figure)) {
					if (kuma2.orient == 1 || figure.orient == 1) {
						kuma2.orient = 5;
					}
					if (kuma2.orient == 0 || figure.orient == 0) {
						kuma2.orient = 6;
					}
					int dama = (kuma2.attack - figure.defense) / 4;
					if (dama <= 0)
						dama = 1;
					figure.checkDamage(dama);
				}
				kuma2.move();
				//
				if (kuma2.health <= 0) {
					kuma2.alive = false;
					number2++;
					if (number2 <= 1) {
						figure.updateNV(2, number2);
					} else {
						number2 = 0;
					}
				}
			} else {
				effectList.add(new Effect(kuma2.positionX, kuma2.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				kuma2 = new MonsterKuma(random.nextInt(70) + 50, random.nextInt(100) + 400, random.nextInt(2), true,
						350 + figure.getLevel() * 50, 600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - kuma2.defense) / 2) * 20;
			}

			////////////////////
			if (kuma3.alive) {
				if (kuma3.positionX <= 20) {
					kuma3.orient = 1;
				} else if (kuma3.positionX >= GameMain.WIDTH - 10) {
					kuma3.orient = 0;
				}
				//

				//
				if ((kuma3.positionX < figure.positionX - figure.width / 2 - 100)
						|| (kuma3.positionX > figure.positionX + figure.width / 2 + 100)) {
					if (kuma3.orient == 5) {
						kuma3.orient = 1;
					}
					if (kuma3.orient == 6) {
						kuma3.orient = 0;
					}
				}
				//
				if (kuma3.collisionSkillFigure(skillFigure) || check9) {
					effectList.add(new Effect(kuma3.positionX - 30, kuma3.positionY, 1));
					check9 = false;
					damage = true;
					previousHP = kuma3.health;
					figure.attack(kuma3);
					currentHP = kuma3.health;
					tick = System.currentTimeMillis();
					positionX = kuma3.positionX;
					positionY = kuma3.positionY;
				}
				//
				if (kuma3.collision(figure)) {
					if (kuma3.orient == 1 || figure.orient == 1) {
						kuma3.orient = 5;
					}
					if (kuma3.orient == 0 || figure.orient == 0) {
						kuma3.orient = 6;
					}
					int dama = (kuma3.attack - figure.defense) / 4;
					if (dama <= 0)
						dama = 1;
					figure.checkDamage(dama);
				}
				kuma3.move();
				//
				if (kuma3.health <= 0)
					kuma3.alive = false;
			} else {
				effectList.add(new Effect(kuma3.positionX, kuma3.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				kuma3 = new MonsterKuma(random.nextInt(70) + 50, random.nextInt(100) + 400, random.nextInt(2), true,
						350 + figure.getLevel() * 50, 600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - kuma3.defense) / 2) * 20;
			}

			//////////////////////
			if (kuma4.alive) {
				if (kuma4.positionX <= 20) {
					kuma4.orient = 1;
				} else if (kuma4.positionX >= GameMain.WIDTH - 10) {
					kuma4.orient = 0;
				}
				//

				//
				if ((kuma4.positionX < figure.positionX - figure.width / 2 - 100)
						|| (kuma4.positionX > figure.positionX + figure.width / 2 + 100)) {
					if (kuma4.orient == 5) {
						kuma4.orient = 1;
					}
					if (kuma4.orient == 6) {
						kuma4.orient = 0;
					}
				}
				//
				if (kuma4.collisionSkillFigure(skillFigure) || check9) {
					effectList.add(new Effect(kuma4.positionX - 30, kuma4.positionY, 1));
					check9 = false;
					damage = true;
					previousHP = kuma4.health;
					figure.attack(kuma4);
					currentHP = kuma4.health;
					tick = System.currentTimeMillis();
					positionX = kuma4.positionX;
					positionY = kuma4.positionY;
				}
				//
				if (kuma4.collision(figure)) {
					if (kuma4.orient == 1 || figure.orient == 1) {
						kuma4.orient = 5;
					}
					if (kuma4.orient == 0 || figure.orient == 0) {
						kuma4.orient = 6;
					}
					int dama = (kuma4.attack - figure.defense) / 4;
					if (dama <= 0)
						dama = 1;
					figure.checkDamage(dama);
				}
				kuma4.move();
				//
				if (kuma4.health <= 0)
					kuma4.alive = false;
			} else {
				effectList.add(new Effect(kuma4.positionX, kuma4.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				kuma4 = new MonsterKuma(random.nextInt(70) + 50, random.nextInt(100) + 400, random.nextInt(2), true,
						350 + figure.getLevel() * 50, 600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - kuma4.defense) / 2) * 20;
			}

		}
	}

	// Dragon
	private void updateDragon() {
		if (temp == 0) {

			// Monster Dragon Land
			if (dragonLand.alive) {
				// Check Collision Edge
				if (dragonLand.positionX <= 20) {
					dragonLand.orient = 1;
				} else if (dragonLand.positionX >= GameMain.WIDTH) {
					dragonLand.orient = 0;
				} else if (dragonLand.positionY <= 20) {
					dragonLand.orient = 3;
				} else if (dragonLand.positionY >= GameMain.HEIGHT) {
					dragonLand.orient = 2;
				}
				//
				if (dragonLand.collision(kuma) || dragonLand.collision(giant) || dragonLand.collision(spider)
						|| dragonLand.collision(kuma1) || dragonLand.collision(giant1)
						|| dragonLand.collision(spider1)) {
					if (dragonLand.orient == 0) {
						dragonLand.orient = 1;
					} else if (dragonLand.orient == 1) {
						dragonLand.orient = 0;
					}
				}
				//
				if (count >= 400) {
					count = 0;
					dragonLand.orient = random.nextInt(4);
				}
				//
				if ((dragonLand.positionX < figure.positionX - figure.width / 2)
						|| (dragonLand.positionX > figure.positionX + figure.width / 2)) {
					if (dragonLand.orient == 5) {
						dragonLand.orient = 0;
					}
					if (dragonLand.orient == 6) {
						dragonLand.orient = 1;
					}
				}
				// Check Shoot Skill
				if (dragonLand.isThrow()) {
					dragonLand.setThrow(false);
					int percent = random.nextInt(10);
					if (percent <= PERCENT) {
						if (dragonLand.orient == 0) {
							skillMonster.add(
									new SkillMonster(dragonLand.positionX - 90, dragonLand.positionY - 30, 1, 1, 0));
						} else if (dragonLand.orient == 1) {
							skillMonster.add(
									new SkillMonster(dragonLand.positionX + 20, dragonLand.positionY - 35, 0, 1, 0));
						} else if (dragonLand.orient == 2) {
							skillMonster.add(
									new SkillMonster(dragonLand.positionX - 30, dragonLand.positionY - 95, 3, 1, 0));
						} else {
							skillMonster
									.add(new SkillMonster(dragonLand.positionX - 30, dragonLand.positionY, 4, 1, 0));
						}
					}
				}
				// Check Collision Skill Figure
				if (dragonLand.collisionSkillFigure(skillFigure) || check3) {
					effectList.add(new Effect(dragonLand.positionX - 30, dragonLand.positionY, 1));
					check3 = false;
					damage = true;
					previousHP = dragonLand.health;
					figure.attack(dragonLand);
					currentHP = dragonLand.health;
					tick = System.currentTimeMillis();
					positionX = dragonLand.positionX;
					positionY = dragonLand.positionY;
				}

				if (dragonLand.collision(figure)) {
					if (dragonLand.orient == 1) {
						dragonLand.orient = 5;
					}
					if (dragonLand.orient == 0) {
						dragonLand.orient = 6;
					}
				}
				dragonLand.move();
				//
				if (dragonLand.health <= 0) {
					dragonLand.alive = false;
					number3++;
					if (number3 <= 5) {
						figure.updateNV(5, number3);
					} else {
						number3 = 0;
					}
				}
			} else {
				effectList.add(new Effect(dragonLand.positionX, dragonLand.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				dragonLand = new MonsterDragon(random.nextInt(100) + 900, random.nextInt(600) + 50, random.nextInt(2),
						true, 200 + figure.getLevel() * 50, 150 + figure.getLevel() * 50, 300 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - dragonLand.defense) / 2) * 10;
			}
			// Monster Dragon Land1
			if (dragonLand1.alive) {
				// Check Collision Edge
				if (dragonLand1.positionX <= 20) {
					dragonLand1.orient = 1;
				} else if (dragonLand1.positionX >= GameMain.WIDTH) {
					dragonLand1.orient = 0;
				} else if (dragonLand1.positionY <= 20) {
					dragonLand1.orient = 3;
				} else if (dragonLand1.positionY >= GameMain.HEIGHT) {
					dragonLand1.orient = 2;
				}
				//
				if (dragonLand1.collision(kuma) || dragonLand1.collision(giant) || dragonLand1.collision(spider)
						|| dragonLand1.collision(kuma1) || dragonLand1.collision(giant1)
						|| dragonLand1.collision(spider1)) {
					if (dragonLand1.orient == 0) {
						dragonLand1.orient = 1;
					} else if (dragonLand1.orient == 1) {
						dragonLand1.orient = 0;
					}
				}
				//
				if (count >= 400) {
					count = 0;
					dragonLand1.orient = random.nextInt(4);
				}
				//
				if ((dragonLand1.positionX < figure.positionX - figure.width / 2)
						|| (dragonLand1.positionX > figure.positionX + figure.width / 2)) {
					if (dragonLand1.orient == 5) {
						dragonLand1.orient = 0;
					}
					if (dragonLand1.orient == 6) {
						dragonLand1.orient = 1;
					}
				}
				// Check Shoot Skill
				if (dragonLand1.isThrow()) {
					dragonLand1.setThrow(false);
					int percent = random.nextInt(10);
					if (percent <= PERCENT) {
						if (dragonLand1.orient == 0) {
							skillMonster.add(
									new SkillMonster(dragonLand1.positionX - 90, dragonLand1.positionY - 30, 1, 1, 0));
						} else if (dragonLand1.orient == 1) {
							skillMonster.add(
									new SkillMonster(dragonLand1.positionX + 20, dragonLand1.positionY - 35, 0, 1, 0));
						} else if (dragonLand1.orient == 2) {
							skillMonster.add(
									new SkillMonster(dragonLand1.positionX - 30, dragonLand1.positionY - 95, 3, 1, 0));
						} else {
							skillMonster
									.add(new SkillMonster(dragonLand1.positionX - 30, dragonLand1.positionY, 4, 1, 0));
						}
					}
				}
				// Check Collision Skill Figure
				if (dragonLand1.collisionSkillFigure(skillFigure) || check7) {
					effectList.add(new Effect(dragonLand1.positionX - 30, dragonLand1.positionY, 1));
					check7 = false;
					damage = true;
					previousHP = dragonLand1.health;
					figure.attack(dragonLand1);
					currentHP = dragonLand1.health;
					tick = System.currentTimeMillis();
					positionX = dragonLand1.positionX;
					positionY = dragonLand1.positionY;
				}

				if (dragonLand1.collision(figure)) {
					if (dragonLand1.orient == 1) {
						dragonLand1.orient = 5;
					}
					if (dragonLand1.orient == 0) {
						dragonLand1.orient = 6;
					}
				}
				dragonLand1.move();
				//
				if (dragonLand1.health <= 0)
					dragonLand1.alive = false;
			} else {
				effectList.add(new Effect(dragonLand1.positionX, dragonLand1.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				dragonLand1 = new MonsterDragon(random.nextInt(100) + 900, random.nextInt(600) + 50, random.nextInt(2),
						true, 200 + figure.getLevel() * 50, 150 + figure.getLevel() * 50, 300 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - dragonLand1.defense) / 2) * 10;
			}

		}

	}

	// Bird
	private void updateBird() {
		if (temp == 2) {

			// Monster Sky
			if (bird.alive) {
				if (bird.positionX <= 20) {
					bird.orient = 1;
				} else if (bird.positionX >= GameMain.WIDTH) {
					bird.orient = 0;
				} else if (bird.positionY <= 20) {
					bird.orient = 3;
				} else if (bird.positionY >= GameMain.HEIGHT) {
					bird.orient = 2;
				}
				if (count1 >= 500) {
					count1 = 0;
					bird.orient = random.nextInt(4);
				}
				//
				if (bird.isThrow()) {
					bird.setThrow(false);
					int percent = random.nextInt(15);
					if (percent <= PERCENT) {
						SkillMonster skill = new SkillMonster(bird.positionX - 10, bird.positionY + 10, 2, 0, 0);
						skill.setPositionThrow(bird.positionX, bird.positionY);
						skillMonster.add(skill);
					}
				}
				if (bird.collisionSkillFigure(skillFigure)) {
					effectList.add(new Effect(bird.positionX - 30, bird.positionY, 1));
					damage = true;
					previousHP = bird.health;
					figure.attack(bird);
					currentHP = bird.health;
					tick = System.currentTimeMillis();
					positionX = bird.positionX;
					positionY = bird.positionY;
				}
				bird.move();
				//
				if (bird.health <= 0)
					bird.alive = false;
			} else {
				effectList.add(new Effect(bird.positionX, bird.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				bird = new MonsterSky(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT),
						random.nextInt(4), true, 10 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
						100 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - bird.defense) / 2) * 5;
			}

		}

		if (temp == 4) {
			//
			if (bird1.alive) {
				if (bird1.positionX <= 20) {
					bird1.orient = 1;
				} else if (bird1.positionX >= GameMain.WIDTH) {
					bird1.orient = 0;
				} else if (bird1.positionY <= 20) {
					bird1.orient = 3;
				} else if (bird1.positionY >= GameMain.HEIGHT) {
					bird1.orient = 2;
				}
				//
				if (count2 >= 600) {
					count2 = 0;
					bird1.orient = random.nextInt(4);
				}
				//
				if (bird1.isThrow()) {
					bird1.setThrow(false);
					int percent = random.nextInt(15);
					if (percent <= PERCENT) {
						SkillMonster skill = new SkillMonster(bird1.positionX - 10, bird1.positionY + 10, 2, 0, 0);
						skill.setPositionThrow(bird1.positionX, bird1.positionY);
						skillMonster.add(skill);
					}
				}
				if (bird1.collisionSkillFigure(skillFigure)) {
					effectList.add(new Effect(bird1.positionX - 30, bird1.positionY, 1));
					damage = true;
					previousHP = bird1.health;
					figure.attack(bird1);
					currentHP = bird1.health;
					tick = System.currentTimeMillis();
					positionX = bird1.positionX;
					positionY = bird1.positionY;
				}
				bird1.move();
				//
				if (bird1.health <= 0)
					bird1.alive = false;
			} else {
				effectList.add(new Effect(bird1.positionX, bird1.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				bird1 = new MonsterSky(random.nextInt(GameMain.WIDTH), random.nextInt(GameMain.HEIGHT),
						random.nextInt(4), true, 10 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
						100 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - bird1.defense) / 2) * 5;
			}
		}

	}

	// Giant
	private void updateGiant() {
		if (temp == 0) {

			// Monster Giant
			if (giant.alive) {
				if ((giant.positionX < figure.positionX - figure.width / 2)
						|| (giant.positionX > figure.positionX + figure.width / 2)) {
					if (giant.orient == 5) {
						giant.orient = 0;
					}
				}
				//
				if (giant.collisionSkillFigure(skillFigure) || check4) {
					effectList.add(new Effect(giant.positionX - 30, giant.positionY, 1));
					check4 = false;
					damage = true;
					previousHP = giant.health;
					figure.attack(giant);
					currentHP = giant.health;
					tick = System.currentTimeMillis();
					positionX = giant.positionX;
					positionY = giant.positionY;
				}
				//
				if (giant.collision(figure)) {
					giant.orient = 5;
					giant.attack(figure);
				}
				giant.move();
				//
				if (giant.health <= 0) {
					giant.alive = false;
					number1++;
					if (number1 <= 3) {
						figure.updateNV(1, number1);
					} else {
						number1 = 0;
					}
				}
			} else {
				effectList.add(new Effect(giant.positionX, giant.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();

				giant = new MonsterIdle(random.nextInt(80) + 50, random.nextInt(100) + 300, 0, true,
						300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50, 500 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - giant.defense) / 2) * 5;
			}
			if (giant1.alive) {
				if ((giant1.positionX < figure.positionX - figure.width / 2)
						|| (giant1.positionX > figure.positionX + figure.width / 2)) {
					if (giant1.orient == 6) {
						giant1.orient = 1;
					}
				}
				//
				if (giant1.collisionSkillFigure(skillFigure) || check5) {
					effectList.add(new Effect(giant1.positionX - 30, giant1.positionY, 1));
					check5 = false;
					damage = true;
					previousHP = giant1.health;
					figure.attack(giant1);
					currentHP = giant1.health;
					tick = System.currentTimeMillis();
					positionX = giant1.positionX;
					positionY = giant1.positionY;
				}
				//
				if (giant1.collision(figure)) {
					giant1.orient = 6;
					giant1.attack(figure);
				}
				giant1.move();
				//
				if (giant1.health <= 0)
					giant1.alive = false;
			} else {
				effectList.add(new Effect(giant1.positionX, giant1.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				giant1 = new MonsterIdle(random.nextInt(80) + 50, random.nextInt(100) + 300, 1, true,
						300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50, 500 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - giant1.defense) / 2) * 5;
			}

		}

		if (temp == 1) {

			if (giant2.alive) {
				if ((giant2.positionX < figure.positionX - figure.width / 2)
						|| (giant2.positionX > figure.positionX + figure.width / 2)) {
					if (giant2.orient == 5) {
						giant2.orient = 0;
					}
				}
				//
				if (giant2.collisionSkillFigure(skillFigure) || check4) {
					effectList.add(new Effect(giant2.positionX - 30, giant2.positionY, 1));
					check4 = false;
					damage = true;
					previousHP = giant2.health;
					figure.attack(giant2);
					currentHP = giant2.health;
					tick = System.currentTimeMillis();
					positionX = giant2.positionX;
					positionY = giant2.positionY;
				}
				//
				if (giant2.collision(figure)) {
					giant2.orient = 5;
					giant2.attack(figure);
				}
				giant2.move();
				//
				if (giant2.health <= 0) {
					giant2.alive = false;
					number1++;
					if (number1 <= 3) {
						figure.updateNV(1, number1);
					} else {
						number1 = 0;
					}
				}
			} else {
				effectList.add(new Effect(giant2.positionX, giant.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();

				giant2 = new MonsterIdle(random.nextInt(80) + 50, random.nextInt(100) + 300, 0, true,
						300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50, 500 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - giant2.defense) / 2) * 5;
			}
			if (giant3.alive) {
				if ((giant3.positionX < figure.positionX - figure.width / 2)
						|| (giant3.positionX > figure.positionX + figure.width / 2)) {
					if (giant3.orient == 6) {
						giant3.orient = 1;
					}
				}
				//
				if (giant3.collisionSkillFigure(skillFigure) || check5) {
					effectList.add(new Effect(giant3.positionX - 30, giant3.positionY, 1));
					check5 = false;
					damage = true;
					previousHP = giant1.health;
					figure.attack(giant3);
					currentHP = giant3.health;
					tick = System.currentTimeMillis();
					positionX = giant3.positionX;
					positionY = giant3.positionY;
				}
				//
				if (giant3.collision(figure)) {
					giant3.orient = 6;
					giant3.attack(figure);
				}
				giant3.move();
				//
				if (giant3.health <= 0)
					giant3.alive = false;
			} else {
				effectList.add(new Effect(giant3.positionX, giant3.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				giant3 = new MonsterIdle(random.nextInt(80) + 50, random.nextInt(100) + 300, 1, true,
						300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50, 500 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - giant3.defense) / 2) * 5;
			}
			if (giant4.alive) {
				if ((giant4.positionX < figure.positionX - figure.width / 2)
						|| (giant4.positionX > figure.positionX + figure.width / 2)) {
					if (giant4.orient == 6) {
						giant4.orient = 1;
					}
				}
				//
				if (giant4.collisionSkillFigure(skillFigure) || check5) {
					effectList.add(new Effect(giant4.positionX - 30, giant4.positionY, 1));
					check5 = false;
					damage = true;
					previousHP = giant1.health;
					figure.attack(giant4);
					currentHP = giant4.health;
					tick = System.currentTimeMillis();
					positionX = giant4.positionX;
					positionY = giant4.positionY;
				}
				//
				if (giant4.collision(figure)) {
					giant4.orient = 6;
					giant4.attack(figure);
				}
				giant4.move();
				//
				if (giant4.health <= 0)
					giant4.alive = false;
			} else {
				effectList.add(new Effect(giant4.positionX, giant4.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				giant4 = new MonsterIdle(random.nextInt(80) + 50, random.nextInt(100) + 300, 1, true,
						300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50, 500 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - giant4.defense) / 2) * 5;
			}

		}

	}

	// Monster Water
	private void updateMonsterWater() {
		if (temp == 1) {

			// Monster Water
			if (dragonWater.alive) {
				if (dragonWater.positionX <= 160) {
					dragonWater.orient = 1;
				}
				if (dragonWater.positionX > 650) {
					dragonWater.orient = 0;
				}
				//
				if (dragonWater.collisionSkillFigure(skillFigure)) {
					effectList.add(new Effect(dragonWater.positionX - 30, dragonWater.positionY, 1));
					damage = true;
					previousHP = dragonWater.health;
					figure.attack(dragonWater);
					currentHP = dragonWater.health;
					tick = System.currentTimeMillis();
					positionX = dragonWater.positionX;
					positionY = dragonWater.positionY;
				}
				dragonWater.move();
				//
				if (dragonWater.health <= 0)
					dragonWater.alive = false;
			} else {
				effectList.add(new Effect(dragonWater.positionX, dragonWater.positionY, 0));
				GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				dragonWater = new MonsterWater(200, 550, random.nextInt(2), true, 10 + figure.getLevel() * 50,
						200 + figure.getLevel() * 50, 100 + figure.getLevel() * 50);
				EXP += (Math.abs(figure.attack - dragonWater.defense) / 2) * 5;
			}

		}

	}

	// Update Item
	private void updateItem() {
		if (!spider.alive && checkItem) {
			mItem.add(new Item(spider.positionX, spider.positionY, 0, 50, 0));
			checkItem = false;
		}
		if (!spider1.alive && checkItem8) {
			mItem.add(new Item(spider1.positionX, spider1.positionY, 0, 50, 0));
			checkItem8 = false;
		}
		if (!bird.alive && checkItem1) {
			mItem.add(new Item(bird.positionX, bird.positionY, 3, 100, 100));
			checkItem1 = false;
		}
		if (!bird1.alive && checkItem2) {
			mItem.add(new Item(bird1.positionX, bird1.positionY, 1, 0, 50));
			checkItem2 = false;
		}
		if (!dragonLand.alive && checkItem3) {
			mItem.add(new Item(dragonLand.positionX, dragonLand.positionY, 2, 70, 50));
			checkItem3 = false;
		}
		if (!dragonLand1.alive && checkItem7) {
			mItem.add(new Item(dragonLand1.positionX, dragonLand1.positionY, 2, 70, 50));
			checkItem7 = false;
		}
		if (!giant.alive && checkItem4) {
			mItem.add(new Item(giant.positionX, giant.positionY, 1, 0, 150));
			checkItem4 = false;
		}
		if (!giant1.alive && checkItem5) {
			mItem.add(new Item(giant1.positionX, giant1.positionY, 2, 100, 150));
			checkItem5 = false;
		}
		if (!kuma.alive && checkItem6) {
			mItem.add(new Item(kuma.positionX, kuma.positionY, 2, 150, 150));
			checkItem6 = false;
		}
		if (!kuma1.alive && checkItem9) {
			mItem.add(new Item(kuma1.positionX, kuma1.positionY, 2, 150, 150));
			checkItem9 = false;
		}
		if (figure.checkItem(mItem)) {
			checkUp = true;
			tick = System.currentTimeMillis();
		}
		for (int i = 0; i < mItem.size(); i++) {
			Item item = mItem.get(i);
			if (!item.alive) {
				hp = item.getHp();
				mp = item.getMp();
				GameMain.audios.get(AudioLibrary.ITEM).play();
				mItem.remove(i);
			}
		}

	}

	// Update Explosion
	private void updateExplosion() {
		for (int index = 0; index < explosionList.size(); index++) {
			Explosion explosion = explosionList.get(index);
			explosion.move();
			if (!explosion.alive) {
				explosionList.remove(index);
			}
		}
	}

	// Update Effect
	private void updateEffect() {
		for (int index = 0; index < effectList.size(); index++) {
			Effect effect = effectList.get(index);
			effect.move();
			if (!effect.alive) {
				effectList.remove(index);
			}
		}
	}

	// Update Damage Of Monster
	private void updateDamage() {
		if (positionY >= 0) {
			positionY -= SPEED;
		}
		if (damage && System.currentTimeMillis() - DURATION_ADD_DAMAGE >= tick) {
			damage = false;
		}
	}

	// Update Map Next
	private void updateMap() {

		if (checkMap && System.currentTimeMillis() - DURATION_MAP_UP >= tick) {
			checkMap = false;
		}
	}

	// Update Level
	private void updateLevel() {
		if (checkLevel && System.currentTimeMillis() - DURATION_LEVEL_UP >= tick) {
			checkLevel = false;
		}
	}

	// Update HP, MP
	private void updateHPMP() {
		if (checkUp && System.currentTimeMillis() - DURATION_MAP_UP >= tick) {
			checkUp = false;
		}
	}

	private void updateSupport() {
		if (temp == 0) {

			if (sp1.alive) {
				if (sp1.collision(figure)) {
					sp1.setShow(true);
					figure.addNV(1, 0);
				} else {
					sp1.setShow(false);
				}
			}
			if (sp2.alive) {
				if (sp2.collision(figure)) {
					sp2.setShow(true);
					figure.addNV(2, 0);
				} else {
					sp2.setShow(false);
				}
			}
			if (sp3.alive) {
				if (sp3.collision(figure)) {
					sp3.setShow(true);
					figure.addNV(3, 0);
				} else {
					sp3.setShow(false);
				}
			}
		}

		if (temp == 1) {

			if (sp4.alive) {
				if (sp4.collision(figure)) {
					sp4.setShow(true);
					figure.addNV(4, 0);
				} else {
					sp4.setShow(false);
				}
			}

		}

		if (temp == 2) {

			if (sp5.alive) {
				if (sp5.collision(figure)) {
					sp5.setShow(true);
					figure.addNV(5, 0);
				} else {
					sp5.setShow(false);
				}
			}

		}

		if (temp == 3) {

			if (sp6.alive) {
				if (sp6.collision(figure)) {
					sp6.setShow(true);
					figure.addNV(6, 0);
				} else {
					sp6.setShow(false);
				}
			}

		}

		if (temp == 4) {

			if (sp7.collision(figure)) {
				sp7.setShow(true);
				figure.addNV(7, 0);
			} else {
				sp7.setShow(false);
			}

			if (sp8.collision(figure)) {
				sp8.setShow(true);
				figure.addNV(8, 0);
				boss = true;
			} else {
				sp8.setShow(false);
			}

		}

		if (figure.getListNV().size() > 0)
			for (int i = 0; i < figure.getListNV().size(); i++) {
				if (figure.getListNV().get(i).containsKey(1)) {
					sp1.setPer(figure.getListNV().get(i).get(1));
				}
				if (figure.getListNV().get(i).containsKey(2)) {
					sp2.setPer(figure.getListNV().get(i).get(2));
				}
				if (figure.getListNV().get(i).containsKey(3)) {
					sp3.setPer(figure.getListNV().get(i).get(3));
				}
				if (figure.getListNV().get(i).containsKey(4)) {
					sp4.setPer(figure.getListNV().get(i).get(4));
				}
				if (figure.getListNV().get(i).containsKey(5)) {
					sp5.setPer(figure.getListNV().get(i).get(5));
				}
				if (figure.getListNV().get(i).containsKey(6)) {
					sp6.setPer(figure.getListNV().get(i).get(6));
				}
				if (figure.getListNV().get(i).containsKey(7)) {
					sp7.setPer(figure.getListNV().get(i).get(7));
				}
				if (figure.getListNV().get(i).containsKey(8)) {
					sp8.setPer(figure.getListNV().get(i).get(8));
				}
			}
	}

	private void updateIntroduction() {

		if (index < 3) {
			// if (mIntroduction && System.currentTimeMillis() - countTime >=
			// 8000) {
			// countTime = System.currentTimeMillis();
			// index++;
			introduction = GameMain.images.get(ImageLibrary.INTRODUCTION + index);
			// }
		} else {
			GameMain.audios.get(AudioLibrary.INTRODUCTION + 1).stop();
			GameMain.audios.get(AudioLibrary.MAP + 3).loop();
			mIntroduction = false;
			mPlaying = !mPlaying;
		}

	}

	private void updateBoss() {

		if (temp == 5) {
			if (sp9.alive) {
				figure.setManaMax(1000);
				figure.setMana(1000);
				// Check Shoot Skill
				if (sp9.isThrow()) {
					sp9.setThrow(false);
					int percent = random.nextInt(10);
					if (percent <= PERCENT) {
						skillMonster.add(new SkillMonster(sp9.positionX - 90, sp9.positionY - 40, 1, 1, 1));
						skillMonster.add(new SkillMonster(sp9.positionX - 90, sp9.positionY - 30, 1, 1, 0));
						skillMonster.add(new SkillMonster(sp9.positionX - 90, sp9.positionY - 20, 1, 1, 2));
					}
				}
				// Check Collision Skill Figure
				if (sp9.collisionSkillFigure(skillFigure) || check3) {
					effectList.add(new Effect(sp9.positionX - 30, sp9.positionY, 1));
					check3 = false;
					damage = true;
					previousHP = sp9.health;
					figure.attack(sp9);
					currentHP = sp9.health;
					tick = System.currentTimeMillis();
					positionX = sp9.positionX;
					positionY = sp9.positionY;
				}

				sp9.move();
				//
				if (sp9.health <= 0) {
					sp9.alive = false;
					finish = true;
					EXP += (Math.abs(figure.attack - sp9.defense) / 2) * 10000;
					effectList.add(new Effect(sp9.positionX, sp9.positionY, 0));
					GameMain.audios.get(AudioLibrary.EXPLOSION).play();
				}
			}
		}

	}

	// Draw

	public void draw(Graphics2D g2) {

		if (!mPlaying && !mGameOver) {

			if (mIntroduction) {
				g2.drawImage(introduction, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);
				g2.setColor(Color.RED);
				g2.setFont(new Font(FONT_NAME, Font.PLAIN, 30));
				g2.drawString("Next", GameMain.WIDTH - 150, GameMain.HEIGHT - 60);
			} else {
				g2.drawImage(startGame, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);
				g2.drawImage(optionMenu, 250, 0, null);
				for (int i = 0; i < options.length; i++) {
					if (i == currentSelection) {
						g2.setColor(Color.WHITE);
					} else {
						g2.setColor(Color.RED);
					}
					g2.setFont(new Font(FONT_NAME, Font.PLAIN, 60));
					g2.drawString(options[i], GameMain.WIDTH / 2 - 70, 165 + i * 110);
				}
			}

		}
		//
		if (mPlaying && !mGameOver && !mIntroduction) {
			// Check Map
			// MAP 2
			if ((figure.positionX >= 990 && figure.positionY <= 50 && temp == 0)) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 2);

				figure.positionX = 990;
				figure.positionY = 70;
				temp = 1;
				checkMap2 = true;
				checkMap0 = false;
				checkMap1 = false;
				checkMap3 = false;
				checkMap4 = false;

				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 1).loop();
			}
			// MAP 1
			if ((temp == 1 && figure.positionX >= 990 && figure.positionY <= 50)) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 0);

				figure.positionX = 970;
				figure.positionY = 70;
				temp = 0;
				checkMap2 = false;
				checkMap0 = true;
				checkMap1 = false;
				checkMap3 = false;
				checkMap4 = false;

				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 3).loop();
			}
			// MAP 3
			if (figure.positionX >= 990 && figure.positionY >= 650 && temp == 1) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 1);

				figure.positionX = 950;
				figure.positionY = 670;
				temp = 2;
				checkMap2 = false;
				checkMap0 = false;
				checkMap1 = true;
				checkMap3 = false;
				checkMap4 = false;

				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).loop();
			}
			if (temp == 2 && figure.positionX >= 990 && figure.positionY >= 650) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 2);

				figure.positionX = 960;
				figure.positionY = 650;
				temp = 1;
				checkMap2 = true;
				checkMap0 = false;
				checkMap1 = false;
				checkMap3 = false;
				checkMap4 = false;
				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 1).loop();
			}

			// MAP 4
			if (temp == 2 && figure.positionX >= 320 && figure.positionY <= 50 && figure.positionX <= 390) {

				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 3);

				figure.positionX = 960;
				figure.positionY = 420;
				temp = 3;
				checkMap2 = false;
				checkMap0 = false;
				checkMap1 = false;
				checkMap3 = true;
				checkMap4 = false;

				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 3).loop();
			}

			// MAP 5
			if (temp == 3 && figure.positionX <= 60 && figure.positionY <= 540 && figure.positionY >= 470) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 4);

				figure.positionX = 960;
				figure.positionY = 500;
				temp = 4;
				checkMap2 = false;
				checkMap0 = false;
				checkMap1 = false;
				checkMap3 = false;
				checkMap4 = true;

				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 3).loop();

			}

			if (temp == 4 && figure.positionY <= 60 && figure.positionX <= 800 && figure.positionX >= 700 && boss) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 5);

				figure.positionX = 60;
				figure.positionY = 500;
				temp = 5;
				checkMap2 = false;
				checkMap0 = false;
				checkMap1 = false;
				checkMap3 = false;
				checkMap4 = false;
				checkMap5 = true;

				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).loop();
				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 3).stop();

			}

			// END GAME
			if (temp == 5 && figure.positionX >= 650 && figure.positionY >= 950 && finish) {
				mGameOver = true;
				mPlaying = false;
			}

			// Draw Map
			g2.drawImage(iMap, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);

			if (temp == 0) {
				// Draw Kuma
				if (kuma.alive) {
					kuma.draw(g2);
				}
				if (kuma1.alive) {
					kuma1.draw(g2);
				}

			}

			int w1 = Math.round(GameMain.WIDTH / colMap);
			int h1 = Math.round(GameMain.HEIGHT / rowMap);
			// Draw Map

			for (int i = 0; i < rowMap; i++) {
				for (int j = 0; j < colMap; j++) {
					// Map 0
					if (temp == 0) {
						if (maps[i][j] == 1) {
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}

						if (maps[i][j] == 6) {
							g2.drawImage(iWater, j * w1, i * h1, null);
						}

						if (maps[i][j] == 2) {
							g2.drawImage(iHouse, j * w1 - 20, i * h1 - 130, w1 + 150, h1 + 80, null);
						}
						if (maps[i][j] == 3) {
							g2.drawImage(iHouse1, j * w1 - 140, i * h1 - 150, w1 + 180, h1 + 90, null);

						}
						if (maps[i][j] == 4) {
							g2.drawImage(iHouse2, j * w1 - 130, i * h1 - 70, w1 + 150, h1 + 90, null);
						}
						if (maps[i][j] == 6) {
							g2.drawImage(iHouse3, j * w1, i * h1, null);
						}

						if (maps[i][j] == 5) {
							g2.drawImage(iTree, j * w1, i * h1, null);
						}

					} else if (temp == 1) {
						// Map 1

						if (maps1[i][j] == 2) {
							g2.drawImage(iTree1, j * w1, i * h1, null);
						}

						if (maps1[i][j] == 4) {
							g2.drawImage(iTree3, j * w1, i * h1, null);
						}

						if (maps1[i][j] == 5) {
							g2.drawImage(GameMain.images.get(ImageLibrary.HOUSE + 3), j * w1, i * h1, null);
						}

						if (maps1[i][j] == 1) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}

					} else if (temp == 2) {
						// MAP 2
						if (maps2[i][j] == 2) {
							g2.drawImage(iTree3, j * w1, i * h1, null);
						}

						if (maps2[i][j] == 3) {
							g2.drawImage(GameMain.images.get(ImageLibrary.HOUSE + 3), j * w1, i * h1, null);
						}
						if (maps2[i][j] == 4) {
							g2.drawImage(iTree5, j * w1, i * h1, null);
						}

						if (maps2[i][j] == 1) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}
					}

					// Map 3
					if (temp == 3) {
						if (maps3[i][j] == 2) {
							g2.drawImage(GameMain.images.get(ImageLibrary.Quarry + 0), j * w1, i * h1, null);
						}

						if (maps3[i][j] == 3) {
							g2.drawImage(GameMain.images.get(ImageLibrary.Quarry + 1), j * w1, i * h1, null);
						}

						if (maps3[i][j] == 4) {
							g2.drawImage(GameMain.images.get(ImageLibrary.HOUSE + 3), j * w1, i * h1, null);
						}

						if (maps3[i][j] == 1) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}
					}
					// MAP 4
					if (temp == 4) {

						if (maps4[i][j] == 10) {
							g2.drawImage(GameMain.images.get(ImageLibrary.Quarry + 0), j * w1, i * h1, null);
						}

						if (maps4[i][j] == 2) {
							g2.drawImage(iHouse3, j * w1, i * h1, null);
						}

						if (maps4[i][j] == 7) {
							g2.drawImage(GameMain.images.get(ImageLibrary.FLOWER + 2), j * w1, i * h1, 25, 25, null);
						}

						if (maps4[i][j] == 8) {
							g2.drawImage(GameMain.images.get(ImageLibrary.FLOWER + 3), j * w1, i * h1, 25, 25, null);
						}

						if (maps4[i][j] == 3) {
							g2.drawImage(GameMain.images.get(ImageLibrary.TREE + 11), j * w1, i * h1, 260, 260, null);
						}

						if (maps4[i][j] == 4) {
							g2.drawImage(GameMain.images.get(ImageLibrary.TREE + 7), j * w1, i * h1, 250, 250, null);
						}

						if (maps4[i][j] == 6) {
							g2.drawImage(GameMain.images.get(ImageLibrary.TREE + 12), j * w1, i * h1, 75, 75, null);
						}

						if (maps4[i][j] == 1) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}

						if (maps4[i][j] == 5) {
							g2.setColor(Color.RED);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}
					}

					if (temp == 5) {

						if (maps5[i][j] == 5 && finish) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Kết thúc", j * w1 + 10, i * h1 + 30);
						}
					}
				}
			}
			// Draw Item
			for (Item item : mItem) {
				item.draw(g2);
			}

			if (temp == 0) {

				// Draw Dragon
				if (dragonLand.alive) {
					dragonLand.draw(g2);
				}
				if (dragonLand1.alive) {
					dragonLand1.draw(g2);
				}
				// Draw Spider
				if (spider.alive) {
					spider.draw(g2);
				}
				if (spider1.alive) {
					spider1.draw(g2);
				}

				// Draw Giant
				if (giant.alive) {
					giant.draw(g2);
				}
				if (giant1.alive) {
					giant1.draw(g2);
				}
				//

				for (SkillMonster skill : skillMonster) {
					skill.draw(g2);
				}
				// Draw Support
				sp1.draw(g2);
				sp2.draw(g2);
				sp3.draw(g2);

			}

			if (temp == 1) {
				dragonWater.draw(g2);
				sp4.draw(g2);

				if (giant2.alive) {
					giant2.draw(g2);
				}
				if (giant3.alive) {
					giant3.draw(g2);
				}
				if (giant4.alive) {
					giant4.draw(g2);
				}

			}

			if (temp == 2) {
				sp5.draw(g2);

				if (spider2.alive) {
					spider2.draw(g2);
				}
				if (spider3.alive) {
					spider3.draw(g2);
				}
				if (spider4.alive) {
					spider4.draw(g2);
				}
				if (spider5.alive) {
					spider5.draw(g2);
				}

				if (bird.alive) {
					bird.draw(g2);
				}

				for (SkillMonster skill : skillMonster) {
					skill.draw(g2);
				}

			}

			if (temp == 3) {
				sp6.draw(g2);

				if (kuma2.alive) {
					kuma2.draw(g2);
				}
				if (kuma3.alive) {
					kuma3.draw(g2);
				}
				if (kuma4.alive) {
					kuma4.draw(g2);
				}

			}

			if (temp == 4) {
				sp7.draw(g2);
				sp8.draw(g2);

				// Draw Bird
				if (bird1.alive) {
					bird1.draw(g2);
				}

				for (SkillMonster skill : skillMonster) {
					skill.draw(g2);
				}
			}

			if (temp == 5) {
				if (sp9.alive) {
					sp9.draw(g2);
				}

				for (SkillMonster skill : skillMonster) {
					skill.draw(g2);
				}

			}

			// Draw Skill
			for (SkillFigure skill : skillFigure) {
				skill.draw(g2);
			}

			// Draw Explosion
			for (Explosion explosion : explosionList) {
				explosion.draw(g2);
			}
			// Draw Effect
			for (Effect effect : effectList) {
				effect.draw(g2);
			}

			// Draw Figure
			figure.draw(g2);

			// Draw Damage
			if (damage) {
				g2.setColor(Color.RED);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 20));
				g2.drawString((currentHP - previousHP) + "", (int) positionX, (int) positionY);
			}
			// Draw Next Map
			if (checkMap) {
				g2.setColor(Color.BLUE);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 40));
				g2.drawString("NEXT MAP", GameMain.WIDTH / 3 + 50, GameMain.HEIGHT / 2);
			}
			// Draw Level
			if (checkLevel) {
				GameMain.audios.get(AudioLibrary.UP_LEVEL).play();
				g2.drawImage(iLevelUp, GameMain.WIDTH / 3 - 100, GameMain.HEIGHT / 3 - 100, null);
			}
			// Draw Up HP, MP
			if (checkUp) {
				g2.setColor(Color.RED);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 20));
				if (hp > 0) {
					g2.drawString("+" + hp + " HP", GameMain.WIDTH / 3 + 80, GameMain.HEIGHT / 2 + 20);
				}
				if (mp > 0) {
					g2.setColor(Color.BLUE);
					g2.drawString("+" + mp + " MP", GameMain.WIDTH / 3 + 180, GameMain.HEIGHT / 2 + 20);
				}
			}
			// Draw Info
			g2.drawImage(info, 0, 0, null);
			g2.setColor(Color.RED);
			g2.fillRect(65, 5, (int) (181 * (figure.health * 1.0 / figure.healthMax)), 11);
			g2.setColor(Color.YELLOW);
			g2.fillRect(65, 17, (int) (129 * (figure.getMana() * 1.0 / figure.getManaMax())), 8);
			g2.setColor(Color.BLUE);
			g2.fillRect(65, 26, (int) (119 * (figure.getEXP() * 1.0 / figure.getMaxEXP())), 8);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Consolas", Font.BOLD, 11));
			g2.drawString(figure.health + "/" + figure.healthMax, 142, 13);
			g2.setFont(new Font("Consolas", Font.BOLD, 10));
			g2.setColor(Color.GREEN);
			g2.drawString(figure.getMana() + "/" + figure.getManaMax(), 114, 24);
			g2.setColor(Color.YELLOW);
			g2.setFont(new Font("Consolas", Font.BOLD, 9));
			g2.drawString(figure.getEXP() + "/" + figure.getMaxEXP() + "EXP", 90, 33);
			g2.setColor(Color.RED);
			g2.setFont(new Font(FONT_NAME, Font.BOLD, 19));
			g2.drawString(figure.getLevel() + "", 15, 30);
			//
			if (checkEnd) {
				checkEnd = false;
				GameMain.audios.stopAll();
				g2.setColor(Color.BLUE);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 60));
				g2.drawString("YOU DIE ! ^^", 300, 250);
				for (int i = 0; i < over.length; i++) {
					if (i == select) {
						g2.setColor(Color.WHITE);
					} else {
						g2.setColor(Color.BLACK);
					}
					g2.setFont(new Font(FONT_NAME, Font.BOLD, 40));
					g2.drawString(over[i], 430 + i * 20, 360 + i * 90);
				}
			}
		}

		if (mGameOver) {
			GameMain.audios.stopAll();
			g2.drawImage(gameOver, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);
			g2.setColor(Color.ORANGE);
			g2.setFont(new Font(FONT_NAME, Font.BOLD, 60));
			g2.drawString("WINER ! ^^", 300, 250);
			for (int i = 0; i < over.length; i++) {
				if (i == select) {
					g2.setColor(Color.WHITE);
				} else {
					g2.setColor(Color.BLACK);
				}
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 40));
				g2.drawString(over[i], 430 + i * 20, 360 + i * 90);
			}
		}
	}

	public void save() {
		String[] inf = new String[8];
		inf[0] = String.valueOf(figure.health);
		inf[1] = String.valueOf(figure.getMana());
		inf[2] = String.valueOf(figure.healthMax);
		inf[3] = String.valueOf(figure.getManaMax());
		inf[4] = String.valueOf(figure.attack);
		inf[5] = String.valueOf(figure.defense);
		inf[6] = String.valueOf(figure.getEXP());
		inf[7] = String.valueOf(figure.getLevel());
		saveGame.saveGame(inf);
	}

	public void keyPressed(KeyEvent e) {
		figure.keyPressed(e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (!mPlaying && !mGameOver && mIntroduction) {
				index--;
				if (index <= 0) {
					index = 0;
				}
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (!mPlaying && !mGameOver && mIntroduction) {
				index++;
				if (index >= 3) {
					index = 3;
				}
			}
			break;
		case KeyEvent.VK_DOWN:
			if (!mPlaying) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				currentSelection++;
				if (currentSelection >= options.length) {
					currentSelection = 0;
				}
			}
			if ((mGameOver && !mPlaying) || checkEnd) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				select++;
				if (select >= over.length) {
					select = 0;
				}
			}
			break;
		case KeyEvent.VK_UP:
			if (!mPlaying && !mGameOver) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				currentSelection--;
				if (currentSelection < 0) {
					currentSelection = options.length - 1;
				}
			}
			if ((mGameOver && !mPlaying) || checkEnd) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				select--;
				if (select < 0) {
					select = over.length - 1;
				}
			}
			break;
		case KeyEvent.VK_ENTER:
			if (!mPlaying && !mGameOver) {
				if (currentSelection == 0) {
					GameMain.audios.get(AudioLibrary.INTRODUCTION + 1).play();
					mIntroduction = true;
					countTime = System.currentTimeMillis();
				} else if (currentSelection == 1) {
					new Help();
				} else if (currentSelection == 2) {
					new About();
				} else if (currentSelection == 3) {
					JOptionPane.showMessageDialog(null, "GoodBye ! ^^");
					System.exit(0);
				}
			}
			if ((mGameOver && !mPlaying) || (checkEnd && !mGameOver)) {
				if (select == 0) {
					init();
					GameMain.audios.get(AudioLibrary.TITLE).stop();
					GameMain.audios.get(AudioLibrary.MAP + 2).loop();
					mPlaying = true;
				} else if (select == 1) {
					JOptionPane.showMessageDialog(null, "GoodBye ! ^^");
					System.exit(0);
				}
			}
			break;
		case KeyEvent.VK_Z:
			if (mPlaying) {
				check1 = true;
			}
			break;
		case KeyEvent.VK_X:
			if (mPlaying) {
				check = true;
			}
			break;
		case KeyEvent.VK_A:
			if (mPlaying) {
				GameMain.audios.get(AudioLibrary.ATTACK).play();
				if (figure.collision(spider)) {
					check2 = true;
				}
				if (figure.collision(dragonLand)) {
					check3 = true;
				}
				if (figure.collision(giant)) {
					check4 = true;
				}
				if (figure.collision(giant1)) {
					check5 = true;
				}
				if (figure.collision(kuma)) {
					check6 = true;
				}
				if (figure.collision(dragonLand1)) {
					check7 = true;
				}
				if (figure.collision(spider1)) {
					check8 = true;
				}
				if (figure.collision(kuma1)) {
					check9 = true;
				}
			}
			break;
		case KeyEvent.VK_S:
			if (mPlaying) {
				GameMain.audios.get(AudioLibrary.ATTACK).play();
				if (figure.collision(spider)) {
					check2 = true;
				}
				if (figure.collision(dragonLand)) {
					check3 = true;
				}
				if (figure.collision(giant)) {
					check4 = true;
				}
				if (figure.collision(giant1)) {
					check5 = true;
				}
				if (figure.collision(kuma)) {
					check6 = true;
				}
				if (figure.collision(dragonLand1)) {
					check7 = true;
				}
				if (figure.collision(spider1)) {
					check8 = true;
				}
				if (figure.collision(kuma1)) {
					check9 = true;
				}
			}
			break;
		case KeyEvent.VK_I:
			infoFigure = new InfoFigure(figure.health, figure.getMana(), figure.attack, figure.defense, figure.getEXP(),
					figure.getLevel());
			infoFigure.setVisible(true);
			break;
		case KeyEvent.VK_P:
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure ?", "QUIT", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				save();
				JOptionPane.showMessageDialog(null, "Save success ! Bye Bye ^^");
				System.exit(0);
			} else {
				init();
				mPlaying = true;
				GameMain.audios.get(AudioLibrary.MAP + 2).loop();
			}
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		figure.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		figure.keyTyped(e);
	}
}
