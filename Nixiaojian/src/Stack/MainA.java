package Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class MainA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ArrayList<Animal> box = new ArrayList<Animal>();
			box.add(new Animal(1, "逆小剑"));
			box.add(new Animal(2, "人工智能时代"));
			box.add(new Animal(3, "大数据"));
//			box.set(2, "代码时代");
//			box.add(1,"大数据");
//			box.remove(1);
//			System.out.println(box.get(2));
//			Iterator<String> iterator = box.iterator();
			/*while (iterator.hasNext()) {
				String data = iterator.next();
				System.out.println(data+" ");
//				iterator.remove();
			}*/
			Collections.sort(box, new Comparator<Animal>() {
				public int compare(Animal o1, Animal o2) {
					if(o1.id<o2.id){
						return 1;
					}else if (o1.id==o2.id) {
						return 0;
					}else {
						return -1;
					}
				}
			});
			for (Animal animal : box) {
				System.out.println(animal.id + "."+animal.name);
			}
			
		}
	}
class Animal {
	public  int id;
	public String name;
	public Animal(int id, String name) {
		this.id = id;
		this.name = name;
	}
}