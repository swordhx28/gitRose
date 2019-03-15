package Stack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class MainH {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			TreeSet<AinmalH> box = new TreeSet<AinmalH>();
			box.add(new AinmalH(1, "逆小剑"));
			box.add(new AinmalH(2, "人工智能"));
			box.add(new AinmalH(3, "编程大典"));
//			box.clear();
			HashSet<AinmalH>  arr= new HashSet<AinmalH>();
			arr.add(new AinmalH(4, "大数据"));
			box.addAll(arr);
			Iterator<AinmalH>Iterator = box.iterator();
			while (Iterator.hasNext()) {
			AinmalH data=(AinmalH)Iterator.next();
				System.out.println(data);
//				System.out.println(Iterator.next());
				
			}
			System.out.println("数组个数:"+box.size());
}
}
class AinmalH implements Comparable<AinmalH>{
		private int id;
		private String name;
		public AinmalH(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public String toString() {
			return "Animal输出:[id="+id+",name="+name+"]";
			
		}
		public int compareTo(AinmalH ainmalH) {
			return 1;
			
		}
}
