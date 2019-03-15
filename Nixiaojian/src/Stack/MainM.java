package Stack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MainM {

	public static void main(String[] args) {
		HashMap<String, String> box = new HashMap<String,String>();
		box.put("1", "adjklajs");
		box.put("2", "adfasdf");
		box.put("3", "sdafa");
		Set<Entry<String,String>> set = box.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			System.out.println(next.getKey()+"."+next.getValue());
		}
	}
	}


