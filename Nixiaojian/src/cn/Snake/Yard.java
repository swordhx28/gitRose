package cn.Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.ReentrantLock;


import javax.swing.JPanel;
import org.omg.CORBA.REBIND;

public class Yard extends JPanel {
	private Snake snake;
	private Box box;
	boolean stop = true;

	public Yard(Snake snake) {
		box = Box.takeBox();
		this.snake = snake;
		this.setSize(Variables.WIDTH, Variables.HEIGHT);
		this.setBackground(Color.white);
		ReentrantLock lock = new ReentrantLock(true);
		new Thread(new Runnable() {
			public void run() {
				try {
				while (true) {
					Thread.sleep(10);
				while (snake.isRun() == true) {
					lock.lock();
						System.out.println("xxx");
						Thread.sleep(50);
						repaint();
					lock.unlock();
				}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
				while (true) {
					Thread.sleep(10);
				while (snake.isRun() == true) {

						System.out.println("yyy");
						lock.lock();
						Thread.sleep(50);
						snake.move(snake.getDir());
						lock.unlock();
				}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void snakeMove(Dir dir) {
		switch (dir) {
		case UP:
			snake.setDir(Dir.UP);
			break;
		case DOWN:
			snake.setDir(Dir.DOWN);
			break;
		case LEFT:
			snake.setDir(Dir.LEFT);
			break;
		case RIGHT:
			snake.setDir(Dir.RIGHT);
			break;
		case STOP:
			System.out.println(snake.isRun());
			snake.setRun((snake.isRun() == true) ? false : true);
			break;
		}
		snake.move(snake.getDir());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.green);
		// ª≠∫·œﬂ
		for (int i = 0; i <= (Variables.HEIGHT / 20); i++) {
			g.drawLine(0, 20 * i, Variables.WIDTH, 20 * i);
		}
		// ª≠ ˙œﬂ
		for (int i = 0; i <= Variables.WIDTH / 20; i++) {
			g.drawLine(20 * i, 0, 20 * i, Variables.HEIGHT);
		}
		g.setColor(Color.red);
		for (int i = 0; i < snake.getSnodeBox().size(); i++) {
			g.fillOval(snake.getSnodeBox().get(i).getX(), snake.getSnodeBox().get(i).getY(), 15, 15);
		}
		
		g.setColor(Color.blue);
		g.fillOval(box.getX(), box.getY(), 15, 15);
		if (box.getX() == snake.getX() && box.getY() == snake.getY()) {
			box.setLive(false);
			snake.eat(box);
		}
	}
}
	/*public void setBox(Box box) {
		this.box = box;

	}

	public Box getBox() {
		return this.box;
	}
}*/
