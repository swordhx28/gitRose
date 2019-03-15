package cn.Snake;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Stack;

public class Box implements Observer {
	/********** Begin:定义属性 ******************/
	private int x;
	private int y;
	private boolean live;

	/********* End:定义属性 ******************/
	private Box() {

		this.live = true;
		Random random = new Random();
		x = random.nextInt(40) * 20;
		y = random.nextInt(30) * 20;
	}

	/******* Begin:set/get方法 *****************************************/
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

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private static Box box;

	public static Box takeBox() {
		if (box == null) {
			synchronized (Object.class) {
				if (box == null) {
					box = new Box();
				}
			}
		}
		return box;
	}

	public String toString() {
		return String.valueOf(Integer.toHexString(hashCode())).toUpperCase();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (live == false) {
			Random random = new Random();
			this.x = random.nextInt(40) * 20;
			this.y = random.nextInt(30) * 20;
			this.setLive(true);
		}
	}
}
