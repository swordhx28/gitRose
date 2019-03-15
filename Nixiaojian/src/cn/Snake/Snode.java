package cn.Snake;

import java.io.Serializable;

public class Snode implements Serializable{
	private int x;
	private int y;
	/*****Begin:定义set/get方法**********************/
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	/*****End:定义set/get方法**********************/
}