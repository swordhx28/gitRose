package nixiaojian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Mshuzhu {
	public static void main(String[] args) throws Exception {
		/*File file = new File("d:"+File.separator +"ord.data");
		DataOutputStream dos = new  DataOutputStream(new FileOutputStream(file));
		String names[] = {"电脑","电竞椅","电玩皮肤图标衣服"};
		float prices[] = {5799.8f,1278.9f,199.9f};
		int nums[]= {12,15,8};
		for (int i = 0; i < nums.length; i++) {
			dos.writeUTF(names[i]);
			dos.writeUTF("\t");
			dos.writeFloat(prices[i]);
			dos.writeUTF("\t");
			dos.writeInt(nums[i]);
			dos.writeUTF("\n");
			
		}
		dos.close();*/
		File f = new File("d:" + File.separator + "ord.data") ; // 文件的保存路径
        DataInputStream in = new DataInputStream(new FileInputStream(f)) ;    // 实例化数据输入流对象
        try{
          for (int i = 0; i < 3; i++) {
			System.out.print("名称:"+in.readUTF());
			System.out.print(in.readUTF());
			System.out.print("价格:"+in.readFloat());
			System.out.print(in.readUTF());
			System.out.print("数量:"+in.readInt());
			System.out.print(in.readUTF());
		}
        }catch(Exception e){
        	e.printStackTrace();
        }
        in.close() ;
	}
}
