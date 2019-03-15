package lianxiboa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mainz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String x = "18620708154";
			Pattern pattern = Pattern.compile("((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$");
			Matcher matcher = pattern.matcher(x);
			while (matcher.find()) {
				System.out.print(matcher.group()+" ");
			}
	}

}
