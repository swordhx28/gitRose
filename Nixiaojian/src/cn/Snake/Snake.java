package cn.Snake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.accessibility.Accessible;

public class Snake extends Observable implements Serializable{
	private int x;
	private int y;
	private Dir dir;
	private ArrayList<Snode> snodeBox = new ArrayList<Snode>();
	private boolean run;
	public Snake() {
		/*
		 * Random random = new Random(); this.x = random.nextInt(800); this.y =
		 * random.nextInt(600);
		 */
		this.dir = Dir.RIGHT;
		this.run=true;
		snodeBox.add(new Snode());
	}

	/********** Begin :定义set/get方法 *******************/
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

	public ArrayList<Snode> getSnodeBox() {
		return snodeBox;
	}

	public void setSnodeBox(ArrayList<Snode> snodeBox) {
		this.snodeBox = snodeBox;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void move(Dir dir) {
		switch (dir) {
		case UP:
			y -= 20;
			break;

		case DOWN:
			y += 20;
			break;

		case LEFT:
			x -= 20;
			break;

		case RIGHT:
			x += 20;
			break;
		}
		for (int j = snodeBox.size() - 1; j > 0; j--) {
			snodeBox.get(j).setX(snodeBox.get(j - 1).getX()); // 2 1 2
			snodeBox.get(j).setY(snodeBox.get(j - 1).getY());
		}
		// 给头设置一个新的位置
		snodeBox.get(0).setX(this.x);
		snodeBox.get(0).setY(this.y);
	}

	public void eat(Box box) {
		System.out.println("xx");
		Snode snode = new Snode();
		snodeBox.add(snode);
		setChanged();
		addObserver(box);
		notifyObservers();
	}

}