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
		System.out.println("LinkedList����800000�����ݻ���ʱ��"+ MainL.insert(linkedBox) + "s");
		System.out.println("LinkedList����800000�����ݻ���ʱ��"+ MainL.readList(linkedBox) + "s");
		ArrayList<Object> listBox = new ArrayList<Object>();
		System.out.println("ArrayList����800000������ʱ��:"+ MainL.insert(listBox) + "s");
		System.out.println("ArrayList����800000�����ݻ���ʱ��"+ MainL.readList(listBox) + "s");
	}
}