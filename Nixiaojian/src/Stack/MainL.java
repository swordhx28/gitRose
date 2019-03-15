package Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class MainL {
	static long readList(List<Object> list) {
		long start = System.currentTimeMillis();
		for (int i = 0, j = list.size(); i < j; i++) {

		}
		return System.currentTimeMillis() - start;
	}
	public static long insert(List<Object> list) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 800000; i++) {
			list.add(i, new Object());
		}
		return System.currentTimeMillis() - start;
	}
	public static void main(String[] args) {
		LinkedList<Object> linkedBox = new LinkedList<Object>();
		System.out.println("LinkedList插入800000条数据花费时间"+ MainL.insert(linkedBox) + "s");
		System.out.println("LinkedList遍历800000条数据花费时间"+ MainL.readList(linkedBox) + "s");
		ArrayList<Object> listBox = new ArrayList<Object>();
		System.out.println("ArrayList插入800000条数据时间:"+ MainL.insert(listBox) + "s");
		System.out.println("ArrayList遍历800000条数据花费时间"+ MainL.readList(listBox) + "s");
	}
}