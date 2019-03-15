package cn.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) throws Exception {
		JFrame win = new JFrame("Ã∞≥‘…ﬂ");
		win.setSize(Variables.WIDTH, Variables.HEIGHT);
		File file=new File("d:/snake.data");
		file.createNewFile();
		Object data=null;
		if(file.length()!=0){
			ObjectInputStream oin=new ObjectInputStream(new FileInputStream(file));
			data=oin.readObject();
			oin.close();
		}
		Snake snake=(data==null)?new Snake():(Snake)data;
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				try {
				ObjectOutputStream ow=new ObjectOutputStream(new FileOutputStream(file));
				ow.writeObject(snake);
				ow.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
		Yard yard = new Yard(snake);
		win.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					yard.snakeMove(Dir.UP);
					break;
				case KeyEvent.VK_DOWN:
					yard.snakeMove(Dir.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					yard.snakeMove(Dir.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					yard.snakeMove(Dir.RIGHT);
					break;
				case KeyEvent.VK_SPACE:
					yard.snakeMove(Dir.STOP);
					break;
				}
			}
		});
		win.setLocationRelativeTo(null);
		win.add(yard);
		win.setVisible(true);
	}
}
