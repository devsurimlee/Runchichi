package co.sr.runchichi;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Main extends PApplet {
	
	/* 
	 * 2016년 processing 3으로 개발, 프로젝트 코드 공개용으로 올립니다.
	 * Processing sound lib가 eclipse에서 실행되지 않아 주석처리하였으며,
	 * 이미지 파일 경로 수정 외에는 그대로 두었습니다
	 *  
	 */
	
	// 2020 추가; 파일 경로용
	String path = "co/sr/runchichi/data/";

	
	int i;
	int stages = 0;
	int x = 0;
	int y = 400;
	int w = 20;
	int speed = 1000;
	int offset = 0;
	int frames = 3;
	int currentFrame = 0;
	int time = 0;
	int life = 3;

	PFont font;
	PImage chicken;
	PImage banner;
	PImage icon;
	PImage sheep00;
	PImage tree00, tree01;
	PImage sun;
	PImage moon;
	PImage water;

	PImage[] bg = new PImage[frames];
	PImage[] chichi = new PImage[frames];
	PImage[] chichi_fly = new PImage[frames];
	PImage[] chichi_sleep = new PImage[2];
	PImage[] background = new PImage[100];
	PImage[] stage = new PImage[100];
	PImage[] wheel = new PImage[5];
	PImage[] gameover = new PImage[3];

	public static void main(String[] args) {
		PApplet.main("co.sr.runchichi.Main");
	}

	public void settings() {
		
		  size(1000, 600);
	
//		  bgm = new SoundFile(this, "opening.mp3");
//		  bgm.loop();
		  

		// chichi
		chichi[0] = loadImage(path + "chichi00.gif");
		chichi[1] = loadImage(path + "chichi01.gif");
		chichi[2] = loadImage(path + "chichi02.gif");

		chichi_fly[0] = loadImage(path + "chichi_fly00.gif");
		chichi_fly[1] = loadImage(path + "chichi_fly01.gif");
		chichi_fly[2] = loadImage(path + "chichi_fly02.gif");

		chichi_sleep[0] = loadImage(path + "chichi_sleep00.png");
		chichi_sleep[1] = loadImage(path + "chichi_sleep01.png");

		// background

		bg[0] = loadImage(path + "bg00.gif");
		bg[1] = loadImage(path + "bg01.gif");
		bg[2] = loadImage(path + "bg02.gif");

		background[0] = loadImage(path + "background00.jpg");
		background[1] = loadImage(path + "background01.jpg");
		background[2] = loadImage(path + "background02.jpg");
		background[3] = loadImage(path + "background03.jpg");
		background[4] = loadImage(path + "background04.jpg");
		background[5] = loadImage(path + "background05.jpg");
		background[6] = loadImage(path + "background06.jpg");

		stage[0] = loadImage(path + "stage00.jpg");
		stage[1] = loadImage(path + "stage01.jpg");
		stage[2] = loadImage(path + "stage02.jpg");
		stage[3] = loadImage(path + "stage03.jpg");
		stage[4] = loadImage(path + "stage04.jpg");
		stage[5] = loadImage(path + "stage05.jpg");
		stage[6] = loadImage(path + "stage06.jpg");

		// deco
		sun = loadImage(path + "sun.png");
		moon = loadImage(path + "moon.png");
		tree00 = loadImage(path + "tree00.png");
		tree01 = loadImage(path + "tree01.png");

		// barrier
		sheep00 = loadImage(path + "sheep00.png");

		wheel[0] = loadImage(path + "wheel00.png");
		wheel[1] = loadImage(path + "wheel01.png");
		wheel[2] = loadImage(path + "wheel02.png");
		wheel[3] = loadImage(path + "wheel03.png");
		wheel[4] = loadImage(path + "wheel04.png");
		water = loadImage(path + "water.png");

		// game over
		gameover[0] = loadImage(path + "gameover00.jpg");
	}

	public void draw() {

		if (stages == 0) {

			if (mousePressed) {
				stages = 1;
			}

			// background
			frameRate(3);
			currentFrame = (currentFrame + 1) % frames;

			imageMode(CORNER);
			image(background[0], 0, 0, 2000, 600);

			banner = loadImage(path + "banner01.gif");
			image(chichi_sleep[(currentFrame + offset) % 2], 370, 350, 200, 200);

			imageMode(CENTER);
			image(banner, width / 2, 150);

			textSize(50);
			fill(millis() % 255, 0, 100);
			textAlign(CENTER);
			text("Click your mouse", width / 2, height / 2 - 30);
		}

		if (stages == 1) {

			play1();
			frameRate(20);

			if (time / 100 >= 30)
				frameRate(30);

			if (time / 100 >= 45)
				frameRate(40);

			if (time / 100 >= 60) {
				stages = 2;
				life = 3;
			}
		}

		if (x >= -28500 && x <= -15500)
			image(moon, 800, 0, 200, 200);
		else
			image(sun, 800, 0, 200, 200);

		if (stages == 2) {
			frameRate(60);
			play2();

			image(moon, 800, 0, 200, 200);

			if (time / 100 >= 60) {
				stages = 1;
				life = 3;
				time = 0;
			}
		}

		// game over
		if (stages == 6) {
			frameRate(5);
			life = 3;
			x = 0;
			speed = 1000;

			image(gameover[0], 0, 0);
			textSize(70);
			text("GAME OVER", width / 2, height / 2 - 100);
			textSize(50);
			text(time / 100 + "m", width / 2, height / 2 - 30);
			text("one more?", 850, 570);

			if (mouseX >= 730 && mouseY >= 545) {
				fill(255);
			} else {
				fill(255, 0, millis() % 255);
			}

			if (mousePressed && mouseX >= 730 && mouseY >= 545) {
				stages = 1;
				time = 0;
			}
		}
	}//

	public void play1() {

		// background

		x -= 10;

		imageMode(CORNER);
		image(background[0], x, 0);
		image(background[0], x + 2000, 0);
		image(background[0], x + 4000, 0);
		image(background[0], x + 6000, 0);
		image(background[0], x + 8000, 0);
		image(background[1], x + 10000, 0);
		image(background[2], x + 12000, 0);
		image(background[2], x + 14000, 0);

		image(background[3], x + 16000, 0);

		image(background[4], x + 18000, 0);
		image(background[4], x + 20000, 0);
		image(background[4], x + 22000, 0);
		image(background[4], x + 24000, 0);
		image(background[4], x + 26000, 0);

		image(background[5], x + 28000, 0);

		image(background[2], x + 30000, 0);
		image(background[2], x + 32000, 0);

		image(background[6], x + 34000, 0);
		image(background[0], x + 36000, 0);

		if (x <= -36000)
			x = 0;

		// deco

		image(tree00, x + 800, 370, 100, 100);
		image(tree00, x + 1500, 370, 100, 100);
		image(tree00, x + 4000, 370, 100, 100);
		image(tree00, x + 6713, 370, 100, 100);
		image(tree00, x + 8133, 370, 100, 100);
		image(tree00, x + 12131, 370, 100, 100);
		image(tree00, x + 15464, 370, 100, 100);
		image(tree00, x + 18131, 370, 100, 100);
		image(tree00, x + 23333, 370, 100, 100);
		image(tree00, x + 25100, 370, 100, 100);
		image(tree00, x + 27131, 370, 100, 100);
		image(tree00, x + 32131, 370, 100, 100);
		image(tree00, x + 35464, 370, 100, 100);
		image(tree00, x + 35600, 370, 100, 100);

		image(tree00, x + 3031, 320, 100, 150);
		image(tree00, x + 9115, 320, 100, 150);
		image(tree00, x + 16131, 320, 100, 150);
		image(tree00, x + 18431, 320, 100, 150);
		image(tree00, x + 21130, 320, 100, 150);
		image(tree00, x + 26531, 320, 100, 150);
		image(tree00, x + 28352, 320, 100, 150);
		image(tree00, x + 35000, 320, 100, 150);

		image(tree01, x + 2300, 270, 100, 200);
		image(tree01, x + 15120, 270, 100, 200);
		image(tree01, x + 20000, 270, 100, 200);
		image(tree01, x + 22131, 270, 100, 200);
		image(tree01, x + 28431, 270, 100, 200);
		image(tree01, x + 32164, 270, 100, 200);
		image(tree01, x + 33333, 270, 100, 200);

		// barrier

		// rect(speed, 400, 100, 100);
		// image(sheep00, speed, 400, 100, 100);

		speed -= 10;
		image(wheel[(currentFrame + offset) % 5], speed, 400, 100, 100);
		image(wheel[(currentFrame + offset) % 5], speed + 450, 400, 100, 100);
		image(water, speed + 1200, 440, 200, 160);

		if (speed <= -1700)
			speed = 1000;

		// jump
		if (y < 200)
			y += 20;

		if (y >= 400)
			y = 400;

		if (mousePressed && y == 400) {
//			jump = new SoundFile(this, "jump.mp3");
//			jump.play();
			y -= 200;
		}

		currentFrame = (currentFrame + 1) % frames;

		if (y == 400)
			image(chichi[(currentFrame + offset) % frames], 200, y, 100, 100);
		else
			image(chichi_fly[(currentFrame + offset) % frames], 200, y, 100, 100);

		y = y += 5;

		// life
		if (speed >= 200 && speed < 300 && y > 300) {
			life--;
			y -= 200;
//			sound00 = new SoundFile(this, "sound00.mp3");
//			sound00.play();
		}
		if (speed >= -260 && speed < -160 && y > 300) {
			life--;
			y -= 200;
//			sound00 = new SoundFile(this, "sound00.mp3");
//			sound00.play();
		}

		if (speed >= -1000 && speed < -970 && y > 400) {
			life--;
			y -= 200;
//			sound00 = new SoundFile(this, "sound00.mp3");
//			sound00.play();
		}

		icon = loadImage(path + "icon.png");
		if (life == 3) {
			image(icon, 220, 10);
			image(icon, 120, 10);
			image(icon, 20, 10);
		}
		if (life == 2) {
			image(icon, 120, 10);
			image(icon, 20, 10);
		}
		if (life == 1) {
			image(icon, 20, 10);
		}

		time++;
		textSize(50);
		fill(255);
		text(time / 100 + "m", 60, 580);

		if (life == 0)
			stages = 6;
	}//

	public void play2() {

		// background

		x -= 10;

		imageMode(CORNER);
		image(stage[0], x, 0);
		image(stage[0], x + 2000, 0);
		image(stage[0], x + 4000, 0);
		image(stage[0], x + 6000, 0);
		image(stage[0], x + 8000, 0);
		image(stage[1], x + 10000, 0);
		image(stage[2], x + 12000, 0);
		image(stage[2], x + 14000, 0);

		image(stage[3], x + 16000, 0);

		image(stage[4], x + 18000, 0);
		image(stage[4], x + 20000, 0);
		image(stage[4], x + 22000, 0);
		image(stage[4], x + 24000, 0);
		image(stage[4], x + 26000, 0);

		image(stage[5], x + 28000, 0);

		image(stage[2], x + 30000, 0);
		image(stage[2], x + 32000, 0);

		image(stage[6], x + 34000, 0);
		image(stage[0], x + 36000, 0);

		if (x <= -36000)
			x = 0;

		// barrier

		// rect(speed, 400, 100, 100);
		// image(sheep00, speed, 400, 100, 100);

		speed -= 10;
		image(wheel[(currentFrame + offset) % 5], speed, 400, 100, 100);
		image(wheel[(currentFrame + offset) % 5], speed + 450, 400, 100, 100);
		image(water, speed + 1200, 440, 200, 160);

		if (speed <= -1700)
			speed = 1000;

		// jump
		if (y < 200)
			y += 20;

		if (y >= 400)
			y = 400;

		if (mousePressed && y == 400) {
//			jump = new SoundFile(this, "jump.mp3");
//			jump.play();
			y -= 200;
		}

		currentFrame = (currentFrame + 1) % frames;

		if (y == 400)
			image(chichi[(currentFrame + offset) % frames], 200, y, 100, 100);
		else
			image(chichi_fly[(currentFrame + offset) % frames], 200, y, 100, 100);

		y = y += 5;

		// life
		if (speed >= 200 && speed < 300 && y > 300) {
			life--;
			y -= 200;
//			sound00 = new SoundFile(this, "sound00.mp3");
//			sound00.play();
		}
		if (speed >= -260 && speed < -160 && y > 300) {
			life--;
			y -= 200;
//			sound00 = new SoundFile(this, "sound00.mp3");
//			sound00.play();
		}

		if (speed >= -1000 && speed < -970 && y > 400) {
			life--;
			y -= 200;
//			sound00 = new SoundFile(this, "sound00.mp3");
//			sound00.play();
		}

		icon = loadImage("icon.png");
		if (life == 3) {
			image(icon, 220, 10);
			image(icon, 120, 10);
			image(icon, 20, 10);
		}
		if (life == 2) {
			image(icon, 120, 10);
			image(icon, 20, 10);
		}
		if (life == 1) {
			image(icon, 20, 10);
		}

		time++;
		textSize(50);
		fill(255);
		text(time / 100 + "m", 60, 580);

		if (life == 0)
			stages = 6;
	}
}