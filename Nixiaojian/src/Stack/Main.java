package Stack;

import java.util.Iterator;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Stack<String> stack  = new Stack<String>();
			stack.push("��̴��");
			stack.push("��С��");
			/*String data = stack.elementAt(0);
			System.out.println(data);*/
			//System.out.println(stack.get(1));
			//stack.clear();
			//System.out.println(stack.empty());
			//System.out.println(stack.peek());
//			stack.add("�˹�����");
			/*Iterator<String>iterator = stack.iterator();
			while (iterator.hasNext()) {
				String data =iterator.next();
				System.out.println(data+" ");
			}*/
			/*for (int i = 0; i < stack.size(); i++) {
				String data = stack.elementAt(i);
				System.out.println(data);
			}*/
			Stack<String> box = new Stack<String>();
			stack.push("�˹�����");
			stack.addAll(box);
			for (int i = 0; i < stack.size(); i++) {
					System.out.println(stack.get(i)+" ");
			}
	}

}
